package com.qf.controller;

import com.qf.mapper.QichedingdanDao;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.qf.entity.EIException;
import com.qf.entity.QichedingdanEntity;
import com.qf.entity.XinnengyuanqicheEntity;
import com.qf.service.QichedingdanService;
import com.qf.mapper.XinnnegyuanqicheDao;
import com.qf.utils.MPUtil;
import com.qf.utils.OrderStateMachine;
import com.qf.utils.PageUtils;
import com.qf.utils.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@RestController
@RequestMapping("/qichedingdan")
public class QichedingdanController {

    private static final Logger log = LoggerFactory.getLogger(QichedingdanController.class);

    @Autowired
    private QichedingdanService qichedingdanService;
    @Autowired
    private XinnnegyuanqicheDao xinnnegyuanqicheDao;
    @Autowired
    private QichedingdanDao qichedingdanDao;

    /**
     * 前端保存
     * @param qichedingdan
     * @return
     */
    @Transactional
    @RequestMapping("/add")
    public R add(@RequestBody QichedingdanEntity qichedingdan, HttpServletRequest request){
        // 防重复下单：同一账号 + 同一车 + 存在未支付订单时拒绝
        R dup = duplicateCheck(qichedingdan);
        if (dup != null) {
            return dup;
        }
        // 幂等处理：同一 requestId 只创建一单（前端连点/重试防护）
        R idem = idempotentCheck(qichedingdan);
        if (idem != null) {
            return idem;
        }
        // 事务内扣库存（防超卖）：与建订单同一事务，任一步失败整体回滚
        String stockMsg = subStockInternal(qichedingdan);
        if (stockMsg != null) {
            return R.error(stockMsg);
        }
        qichedingdan.setId(new Date().getTime());
        try {
            qichedingdanService.insert(qichedingdan);
        } catch (DuplicateKeyException e) {
            // 撞唯一索引说明已处理过 → 抛异常回滚已扣库存，全局异常返回友好提示
            throw new EIException("订单已提交，请勿重复操作");
        }
        return R.ok();
    }

    /**
     * 防重复下单校验：同一账号下同一车辆存在未支付订单时，拒绝再次下单。
     * 信息不完整（无账号/无车名）时不拦截，交给表单校验处理。
     */
    private R duplicateCheck(QichedingdanEntity qichedingdan) {
        String zhanghao = qichedingdan.getZhanghao();
        String cheming = qichedingdan.getCheming();
        if (zhanghao == null || zhanghao.isEmpty() || cheming == null || cheming.isEmpty()) {
            return null;
        }
        // 未支付 = ispay 为空 或 ispay 不等于"已支付"（兼容历史空值），已支付的不拦截
        EntityWrapper<QichedingdanEntity> ck = new EntityWrapper<>();
        ck.eq("zhanghao", zhanghao)
          .eq("cheming", cheming)
          .isNull("ispay");
        int cnt = qichedingdanService.selectCount(ck);
        if (cnt > 0) {
            return R.error("您已有该车辆的未支付订单，请勿重复下单");
        }
        EntityWrapper<QichedingdanEntity> ck2 = new EntityWrapper<>();
        ck2.eq("zhanghao", zhanghao)
           .eq("cheming", cheming)
           .ne("ispay", "已支付");
        int cnt2 = qichedingdanService.selectCount(ck2);
        if (cnt2 > 0) {
            return R.error("您已有该车辆的未支付订单，请勿重复下单");
        }
        return null;
    }

    /**
     * 幂等校验：同一 requestId 只允许创建一单。
     * requestId 为空时跳过（兼容旧调用方）；并发兜底由 request_id 唯一索引承担。
     */
    private R idempotentCheck(QichedingdanEntity qichedingdan) {
        String requestId = qichedingdan.getRequestId();
        if (requestId == null || requestId.isEmpty()) {
            return null;
        }
        EntityWrapper<QichedingdanEntity> ck = new EntityWrapper<>();
        ck.eq("request_id", requestId);
        int cnt = qichedingdanService.selectCount(ck);
        if (cnt > 0) {
            return R.error("订单已提交，请勿重复操作");
        }
        return null;
    }

    /**
     * 前端列表
     *
     * @param params
     * @param qichedingdan
     * @return
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, QichedingdanEntity qichedingdan) {
        EntityWrapper<QichedingdanEntity> ew = new EntityWrapper<>();
        PageUtils pageUtils = qichedingdanService.queryPage(params, MPUtil.likeOrEq(ew, qichedingdan));
        return R.ok().put("data", pageUtils);
    }

    /**
     * 查询
     *
     * @param qichedingdan
     * @param qichedingdan
     * @return
     */
    @RequestMapping("/query")
    public R query(QichedingdanEntity qichedingdan) {
        EntityWrapper<QichedingdanEntity> ew = new EntityWrapper<>();
        ew.allEq(MPUtil.allEQMapPre(qichedingdan, "qichedingdan"));
        QichedingdanEntity qichedingdan1 = qichedingdanService.selectOne(ew);
        return R.ok().put("data", qichedingdan1);
    }

    /**
     * 前端信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request) {
        QichedingdanEntity qichedingdan1 = qichedingdanService.selectById(id);
        return R.ok().put("data", qichedingdan1);
    }

    /**
     * 后端列表
     * @param params
     * @param qichedingdan
     * @param request
     * @return
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, QichedingdanEntity qichedingdan, HttpServletRequest request){
        String tableName = request.getSession().getAttribute("tableName").toString();
        if (tableName.equals("yonghu")){
            qichedingdan.setZhanghao((String) request.getSession().getAttribute("username"));
        }
        EntityWrapper<QichedingdanEntity> ew = new EntityWrapper<>();
        PageUtils pageUtils = qichedingdanService.queryPage(params, MPUtil.likeOrEq(ew, qichedingdan));
        return R.ok().put("data", pageUtils);
    }
    /**
     * 信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request) {
        QichedingdanEntity qichedingdan = qichedingdanService.selectById(id);
        return R.ok().put("data", qichedingdan);
    }

    /**
     * 保存
     *
     * @param qichedingdan
     * @return
     */
    @Transactional
    @RequestMapping("/save")
    public R save(@RequestBody  QichedingdanEntity qichedingdan) {
        // 防重复下单：同一账号 + 同一车 + 存在未支付订单时拒绝
        R dup = duplicateCheck(qichedingdan);
        if (dup != null) {
            return dup;
        }
        // 幂等处理：同一 requestId 只创建一单
        R idem = idempotentCheck(qichedingdan);
        if (idem != null) {
            return idem;
        }
        // 事务内扣库存（防超卖）：与建订单同一事务，任一步失败整体回滚
        String stockMsg = subStockInternal(qichedingdan);
        if (stockMsg != null) {
            return R.error(stockMsg);
        }
        try {
            qichedingdanService.insert(qichedingdan);
        } catch (DuplicateKeyException e) {
            // 撞唯一索引说明已处理过 → 抛异常回滚已扣库存，全局异常返回友好提示
            throw new EIException("订单已提交，请勿重复操作");
        }
        return R.ok();
    }

    /**
     * 事务内扣库存（防超卖）：根据车名定位车辆，条件扣减 shuliang >= num 才减。
     * 返回 null 表示扣减成功或无需扣减；返回非 null 为错误提示（如库存不足）。
     */
    private String subStockInternal(QichedingdanEntity qichedingdan) {
        String cheming = qichedingdan.getCheming();
        Integer shuliang = qichedingdan.getShuliang();
        if (cheming == null || cheming.isEmpty() || shuliang == null || shuliang <= 0) {
            return null;
        }
        List<XinnengyuanqicheEntity> cars = xinnnegyuanqicheDao.selectList(
                new EntityWrapper<XinnengyuanqicheEntity>().eq("cheming", cheming));
        if (cars == null || cars.isEmpty()) {
            return null; // 车名不存在（历史数据/手工单），不阻断下单
        }
        int rows = xinnnegyuanqicheDao.subStock(cars.get(0).getId(), shuliang);
        if (rows == 0) {
            return "库存不足";
        }
        return null;
    }

    /**
     * 修改
     *
     * @param qichedingdan
     * @return
     */
    @RequestMapping("/update")
    public R update(@RequestBody QichedingdanEntity qichedingdan) {
        // 状态机校验：ispay / dingdanzhuangtai 发生变化时，必须走合法流转（防回退、防跳步）
        Long orderId = qichedingdan.getId();
        if (orderId != null) {
            QichedingdanEntity old = qichedingdanService.selectById(orderId);
            if (old != null) {
                String oldIspay = old.getIspay();
                String oldStatus = old.getDingdanzhuangtai();
                String newIspay = qichedingdan.getIspay();
                String newStatus = qichedingdan.getDingdanzhuangtai();
                // 部分更新：未传的状态字段视为保持原值，而不是清空
                if (newIspay == null) { newIspay = oldIspay; }
                if (newStatus == null) { newStatus = oldStatus; }
                boolean stateChanged = !eqSafe(oldIspay, newIspay) || !eqSafe(oldStatus, newStatus);
                if (stateChanged) {
                    String err = OrderStateMachine.checkTransition(oldIspay, oldStatus, newIspay, newStatus);
                    if (err != null) {
                        return R.error(err);
                    }
                    // 操作日志：合法流转记录一条（生产可落操作日志表，当前先打日志）
                    log.info("[订单状态流转] id={} {} -> {}", orderId,
                            OrderStateMachine.display(oldIspay, oldStatus),
                            OrderStateMachine.display(newIspay, newStatus));
                }
            }
        }
        //正常更新
        qichedingdanService.updateById(qichedingdan);
        return R.ok();
    }

    private boolean eqSafe(String a, String b) {
        return a == null ? b == null : a.equals(b);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        qichedingdanService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    /**
     * 统计数量
     *
     * @param params
     * @param qichedingdan
     * @param request
     * @return
     */
    @RequestMapping("/count")
    public R count(@RequestParam Map<String, Object> params, QichedingdanEntity qichedingdan, HttpServletRequest request) {
        EntityWrapper<QichedingdanEntity> ew = new EntityWrapper<>();
        int count = qichedingdanService.selectCount(MPUtil.likeOrEq(ew, qichedingdan));
        return R.ok().put("data", count);
    }

    /**
     * 按照时间统计
     * @return
     */
    @RequestMapping("/value/{xColumnName}/{yColumnName}/{timestatType}")
    public R valueDay(@PathVariable("xColumnName") String xColumnName,
                      @PathVariable("yColumnName") String yColumnName,
                      @PathVariable("timestatType") String timestatType,
                      HttpServletRequest request){
        //设置查询参数
        HashMap<String, Object> params = new HashMap<>();
        params.put("xColumn", xColumnName);
        params.put("yColumn", yColumnName);
        params.put("timeStatType", timestatType);
        //设置查询条件
        EntityWrapper<QichedingdanEntity> ew = new EntityWrapper<>();
        //获取表名
        String tableName = request.getSession().getAttribute("tableName").toString();
        if (tableName.equals("yonghu")){
            ew.eq("zhanghao",(String)request.getSession().getAttribute("username"));
        }
        //调用service完成业务操作
        List<Map<String, Object>> result = qichedingdanService.selectTimeStatValue(params, ew);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (Map<String, Object> map : result) {
            for (String k : map.keySet()) {
                if (map.get(k) instanceof Date) {
                    map.put(k,sdf.format((Date) map.get(k)));
                }
            }
        }
        return R.ok().put("data", result);

    }

    /**
     * 支付（后端校验版）：归属校验 + 金额校验 + 状态机校验，条件更新防并发重复支付
     * @param params {id, amount}
     */
    @RequestMapping("/pay")
    public R pay(@RequestBody Map<String, Object> params, HttpServletRequest request){
        if (params == null || params.get("id") == null) {
            return R.error("参数缺失");
        }
        Long id = Long.valueOf(params.get("id").toString());
        QichedingdanEntity order = qichedingdanService.selectById(id);
        if (order == null) {
            return R.error("订单不存在");
        }
        // 归属校验：管理员(users)可代付，普通用户只能支付自己的订单
        String username = (String) request.getSession().getAttribute("username");
        String tableName = (String) request.getSession().getAttribute("tableName");
        if (!"users".equals(tableName)) {
            if (order.getZhanghao() == null || !order.getZhanghao().equals(username)) {
                return R.error("无权支付该订单");
            }
        }
        // 状态校验：已支付订单不能重复支付
        if ("已支付".equals(order.getIspay())) {
            return R.error("订单已支付，请勿重复支付");
        }
        // 金额校验：前端传参与订单应付金额一致
        if (params.get("amount") != null) {
            double payAmt = Double.parseDouble(params.get("amount").toString());
            double orderAmt = order.getYingfujine() == null ? 0 : order.getYingfujine().doubleValue();
            if (Math.abs(payAmt - orderAmt) > 0.01) {
                return R.error("支付金额与订单不符");
            }
        }
        // 条件更新：仅未支付可更新（并发重复支付时影响 0 行）
        int rows = qichedingdanDao.markPaid(id);
        if (rows == 0) {
            return R.error("订单状态已变化，请刷新重试");
        }
        return R.ok();
    }
}
