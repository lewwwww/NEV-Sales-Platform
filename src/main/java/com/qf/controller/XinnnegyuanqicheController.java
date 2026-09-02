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
    @RequestMapping("/save")
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
        HashMap<String, Object> newMap = new HashMap<>();
        HashMap<String, Object> param = new HashMap<>();
        Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = it.next();
            String key = entry.getKey();
            String newkey = entry.getKey();
            if (pre.endsWith(".")) {
                newMap.put(pre + newkey, entry.getValue());

            } else if (StringUtils.isEmpty(pre)) {
                newMap.put(newkey, entry.getValue());
            } else {
                newMap.put(pre + "." + newkey, entry.getValue());
            }
        }
        param.put("sort", "clicktime");
        param.put("order", "desc");
        PageUtils pageUtils = xinnnegyuanqicheService.queryPage(param, MPUtil.likeOrEq(ew, xinnengyuanqiche));
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
        //设置排序字段参数条件 按照id降序
        params.put("sort", "id");
        params.put("order", "desc");
        //执行查询
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
