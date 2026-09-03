package com.qf.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.qf.entity.QichedingdanEntity;
import com.qf.entity.XinnengyuanqicheEntity;
import com.qf.service.QichedingdanService;
import com.qf.utils.MPUtil;
import com.qf.utils.PageUtils;
import com.qf.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/qichedingdan")
public class QichedingdanController {

    @Autowired
    private QichedingdanService qichedingdanService;

    /**
     * 前端保存
     * @param qichedingdan
     * @return
     */
    @RequestMapping("/add")
    public R add(@RequestBody QichedingdanEntity qichedingdan, HttpServletRequest request){
        // 防重复下单：同一账号 + 同一车 + 存在未完成（未发货）订单时拒绝
        R dup = duplicateCheck(qichedingdan);
        if (dup != null) {
            return dup;
        }
        qichedingdan.setId(new Date().getTime());
        qichedingdanService.insert(qichedingdan);
        return R.ok();
    }

    /**
     * 防重复下单校验：同一账号下同一车辆存在未完成（未发货）订单时，拒绝再次下单。
     * 信息不完整（无账号/无车名）时不拦截，交给表单校验处理。
     */
    private R duplicateCheck(QichedingdanEntity qichedingdan) {
        String zhanghao = qichedingdan.getZhanghao();
        String cheming = qichedingdan.getCheming();
        if (zhanghao == null || zhanghao.isEmpty() || cheming == null || cheming.isEmpty()) {
            return null;
        }
        EntityWrapper<QichedingdanEntity> ck = new EntityWrapper<>();
        ck.eq("zhanghao", zhanghao)
          .eq("cheming", cheming)
          .eq("dingdanzhuangtai", "未发货");
        int cnt = qichedingdanService.selectCount(ck);
        if (cnt > 0) {
            return R.error("您已有该车辆的未完成订单，请勿重复下单");
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
    @RequestMapping("/save")
    public R save(@RequestBody  QichedingdanEntity qichedingdan) {
        // 防重复下单：同一账号 + 同一车 + 存在未完成（未发货）订单时拒绝
        R dup = duplicateCheck(qichedingdan);
        if (dup != null) {
            return dup;
        }
        qichedingdanService.insert(qichedingdan);
        return R.ok();
    }

    /**
     * 修改
     *
     * @param qichedingdan
     * @return
     */
    @RequestMapping("/update")
    public R update(@RequestBody QichedingdanEntity qichedingdan) {
        //正常更新
        qichedingdanService.updateById(qichedingdan);
        return R.ok();
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
}
