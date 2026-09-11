package com.qf.controller;

import com.baidubce.qianfan.util.StringUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.qf.entity.StoreupEntity;
import com.qf.entity.UsersEntity;
import com.qf.entity.XinnengyuanqicheEntity;
import com.qf.service.XinnnegyuanqicheService;
import com.qf.service.StoreupService;
import com.qf.utils.MPUtil;
import com.qf.utils.PageUtils;
import com.qf.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/xinnengyuanqiche")
public class XinnnegyuanqicheController {
    @Autowired
    private XinnnegyuanqicheService xinnnegyuanqicheService;

    @Autowired
    private StoreupService storeupService;

    /**
     * 前端列表
     * @param params
     * @param xinnengyuanqiche
     * @return
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, XinnengyuanqicheEntity xinnengyuanqiche) {
        EntityWrapper<XinnengyuanqicheEntity> ew = new EntityWrapper<>();
        PageUtils pageUtils = xinnnegyuanqicheService.queryPage(params, MPUtil.likeOrEq(ew, xinnengyuanqiche));
        return R.ok().put("data", pageUtils);
    }

    /**
     * 查询
     * @param xinnengyuanqiche
     * @param xinnengyuanqiche
     * @return
     */
    @RequestMapping("/query")
    public R query(XinnengyuanqicheEntity xinnengyuanqiche) {
        EntityWrapper<XinnengyuanqicheEntity> ew = new EntityWrapper<>();
        ew.allEq(MPUtil.allEQMapPre(xinnengyuanqiche,"cheming"));
        XinnengyuanqicheEntity xinnengyuanqiche1 = xinnnegyuanqicheService.selectOne(ew);
        return R.ok().put("data", xinnengyuanqiche1);
    }
    /**
     * 前端信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request) {
        XinnengyuanqicheEntity xinnengyuanqiche1 = xinnnegyuanqicheService.selectById(id);
        // 点击时间闭环：用户端访问详情页即刷新该车 clicktime（热门推荐 autoSort 按 clicktime 倒序）。
        // 防刷：touchClicktime 是 SQL 条件更新（date(clicktime) < curdate() 才写库），
        // 同一辆车同一天最多更新 1 次，把高频"点击"压成低频"写库"；且本接口经拦截器鉴权，
        // 未登录请求在进入方法前已被 401 拦截，匿名脚本无法刷量。
        // 更新失败（当天已刷过/车辆不存在）不影响详情返回。
        if (xinnengyuanqiche1 != null) {
            xinnnegyuanqicheService.touchClicktime(id);
        }
        return R.ok().put("data", xinnengyuanqiche1);
    }

    /**
     * 后端列表
     * @param params
     * @param xinnengyuanqiche
     * @return
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, XinnengyuanqicheEntity xinnengyuanqiche){
        EntityWrapper<XinnengyuanqicheEntity> ew = new EntityWrapper<>();
        PageUtils pageUtils = xinnnegyuanqicheService.queryPage(params, MPUtil.likeOrEq(ew, xinnengyuanqiche));
        return R.ok().put("data", pageUtils);
    }
    /**
     * 信息
     * @param id
     * @return
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        XinnengyuanqicheEntity xinnengyuanqiche = xinnnegyuanqicheService.selectById(id);
        return R.ok().put("data", xinnengyuanqiche);
    }

    /**
     * 保存
     * @param xinnengyuanqiche
     * @return
     */
    @RequestMapping({"/save","/add"})
    public R save(@RequestBody XinnengyuanqicheEntity xinnengyuanqiche){

        if (xinnnegyuanqicheService.selectOne(new EntityWrapper<XinnengyuanqicheEntity>().eq("cheming", xinnengyuanqiche.getCheming())) != null) {
            return R.error("此车名已存在");
        }
        xinnnegyuanqicheService.insert(xinnengyuanqiche);
        return R.ok();
    }

    /**
     * 修改
     * @param xinnengyuanqiche
     * @return
     */
    @RequestMapping("/update")
    public R update(@RequestBody XinnengyuanqicheEntity xinnengyuanqiche){
        //正常更新
        xinnnegyuanqicheService.updateById(xinnengyuanqiche);
        return R.ok();
    }

    /**
     * 库存条件扣减（防超卖）
     * 原子扣减：库存充足才扣减，库存不足返回错误；
     * 并发场景下由数据库行锁保证原子性，避免超卖和丢更新。
     * @param params 需包含车辆 id 与扣减数量 num
     */
    @RequestMapping("/subStock")
    public R subStock(@RequestBody Map<String, Object> params){
        Object idObj = params.get("id");
        Object numObj = params.get("num");
        if (idObj == null || numObj == null) {
            return R.error("参数不完整");
        }
        Long id = Long.valueOf(idObj.toString());
        Integer num = Integer.valueOf(numObj.toString());
        if (num <= 0) {
            return R.error("扣减数量不合法");
        }
        int rows = xinnnegyuanqicheService.subStock(id, num);
        if (rows == 0) {
            return R.error("库存不足");
        }
        return R.ok();
    }

    /**
     * 删除
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        xinnnegyuanqicheService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * 前端智能排序
     *
     * @param params
     * @param xinnengyuanqiche
     * @param request
     * @return
     */
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params, XinnengyuanqicheEntity xinnengyuanqiche, HttpServletRequest request, String pre) {
        EntityWrapper<XinnengyuanqicheEntity> ew = new EntityWrapper<>();
        // 默认推荐（热门 + 新上架混合排序，物品冷启动兜底）：
        // coalesce(clicktime, addtime) —— 有点击的按最近点击时间倒序（热门在前）；
        // clicktime 为 NULL 的新车用上架时间 addtime 顶替参与排序，保证新车也有曝光机会、不会永远沉底。
        // （修复说明：原实现 param.put("sort","clicktime") 传排序字段，但 Query 只认 sidx 键，sort 未生效导致无排序；
        //   且前端 page/limit 也没传入 queryPage。现改为：直接用前端 params + 在 Wrapper 上拼 ORDER BY，
        //   一箭双雕：分页/limit 生效 + 排序修复 + 新车曝光。）
        ew.orderBy("coalesce(clicktime, addtime) desc");
        PageUtils pageUtils = xinnnegyuanqicheService.queryPage(params, MPUtil.likeOrEq(ew, xinnengyuanqiche));
        return R.ok().put("data", pageUtils);

    }

    /**
     * 协同过滤（按收藏推荐）
     * @param params
     * @param xinnengyuanqiche
     * @param request
     * @return
     */

    @RequestMapping("/autoSort2")
    public R autoSort2(@RequestParam Map<String, Object> params,XinnengyuanqicheEntity xinnengyuanqiche, HttpServletRequest request){
        //获取用户缓存id
        String userId = request.getSession().getAttribute("userId").toString();
        //定义“推荐维度字段”：按“品牌类别（pinpaileibie）”推荐
        String inteltypeColumn = "pinpaileibie";
        //根据类型，找到用户下面，新能源汽车模块数据 排序
        //查询当前用户的收藏记录：类型=1、用户ID匹配、收藏表名=新能源汽车表，按收藏时间倒序
        List<StoreupEntity> storeups = storeupService.selectList(new EntityWrapper<StoreupEntity>().eq("type", 1).eq("userid", userId).eq("tablename", "xinnnegyuanqiche").orderBy("addtime", false));
        //初始化存储推荐结果列表
        ArrayList<String> inteltypes = new ArrayList<>();
        //获取前端传递的条件，返回记录 默认10条
        Integer limit = params.get("limit") == null ? 10 : Integer.parseInt(params.get("limit").toString());
        //若用户有收藏记录 查询对应新能源汽车数据
        List<XinnengyuanqicheEntity> xinnengyuanqicheList = new ArrayList<>();
        if (storeups!=null&&storeups.size()>0){
            for (StoreupEntity s : storeups) {
                //封装到新能源汽车数据集合
                xinnengyuanqicheList.addAll(xinnnegyuanqicheService.selectList(new EntityWrapper<XinnengyuanqicheEntity>().eq(inteltypeColumn, s.getInteltype())));
            }
        }
        //设置查询条件
        EntityWrapper<XinnengyuanqicheEntity> ew = new EntityWrapper<>();
        // 兜底排序与 autoSort 保持一致：热门点击倒序 + 新车按上架时间倒序（coalesce 顶替），
        // 无收藏/收藏不足时补足的是"最近热门 + 新上架"的稳定序列（用户冷启动兜底）
        ew.orderBy("coalesce(clicktime, addtime) desc");
        //执行查询（直接用前端 params，page/limit 才真正生效）
        PageUtils page = xinnnegyuanqicheService.queryPage(params, MPUtil.likeOrEq(ew, xinnengyuanqiche));
        //获取数据列表
        List<XinnengyuanqicheEntity> pageList = (List<XinnengyuanqicheEntity>) page.getList();
        //若收藏推荐数量<目标数量 补充默认排序的车型（去重）
        if (xinnengyuanqicheList.size()<limit){
            int toAdd = (limit - xinnengyuanqicheList.size())<pageList.size()?(limit - xinnengyuanqicheList.size()):pageList.size();
            for (XinnengyuanqicheEntity o1 : pageList) {
                boolean addflag = true;
                for (XinnengyuanqicheEntity o2 :xinnengyuanqicheList){
                    if (o1.getId().intValue()==o2.getId().intValue()){
                        addflag = false;
                        break;

                    }
                }
                if (addflag){
                    xinnengyuanqicheList.add(o1);
                    if (--toAdd==0) break;
                }

            }

        } else if (xinnengyuanqicheList.size()>limit) {
            xinnengyuanqicheList = xinnengyuanqicheList.subList(0,limit);
        }
        //设置数据列表
        page.setList(xinnengyuanqicheList);
        return R.ok().put("data", page);
    }

    /**
     * 按照数值统计
     * @param xColumn
     * @param yColumn
     * @return
     */
    @RequestMapping("/value/{xColumn}/{yColumn}")
    public R value(@PathVariable("xColumn") String xColumn, @PathVariable("yColumn") String yColumn){
        HashMap<String, Object> map = new HashMap<>();
        map.put("xColumn", xColumn);
        map.put("yColumn", yColumn);
        //设置查询条件
        EntityWrapper<XinnengyuanqicheEntity> ew = new EntityWrapper<>();
        List<Map<String, Object>> result = xinnnegyuanqicheService.selectValue(map, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (Map<String, Object> m : result) {
            for (String k : m.keySet()) {
                if (m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date) m.get(k)));

                }
            }
        }
        return R.ok().put("data", result);


    }


    /**
     * 分组统计
     * @param columnName
     * @param request
     * @return
     */
    @RequestMapping("/group/{columnName}")
    public R group(@PathVariable("columnName") String columnName, HttpServletRequest request){
        HashMap<String, Object> map = new HashMap<>();
        map.put("column", columnName);
        EntityWrapper<XinnengyuanqicheEntity> ew = new EntityWrapper<>();
        List<Map<String, Object>> result = xinnnegyuanqicheService.selectGroup(map, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (Map<String, Object> m : result) {
            for (String k : m.keySet()) {
                if (m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date) m.get(k)));

                }
            }
        }
        return R.ok().put("data", result);
    }

    /**
     * 统计数量
     * @param params
     * @param xinnengyuanqiche
     * @param request
     * @return
     */
    @RequestMapping("/count")
    public R count(@RequestParam Map<String, Object> params, XinnengyuanqicheEntity xinnengyuanqiche, HttpServletRequest request) {
        EntityWrapper<XinnengyuanqicheEntity> ew = new EntityWrapper<>();
        int count = xinnnegyuanqicheService.selectCount(MPUtil.likeOrEq(ew, xinnengyuanqiche));
        return R.ok().put("data", count);
    }



}
