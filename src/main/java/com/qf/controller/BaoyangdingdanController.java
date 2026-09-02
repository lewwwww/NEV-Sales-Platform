package com.qf.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.qf.annotation.IgnoreAuth;
import com.qf.entity.BaoyangdingdanEntity;
import com.qf.service.BaoyangdingdanService;
import com.qf.utils.MPUtil;
import com.qf.utils.PageUtils;
import com.qf.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/baoyangdingdan")
public class BaoyangdingdanController {
    @Autowired
    private BaoyangdingdanService baoyangdingdanService;

    /**
     * 前端保存
     * @param baoyangdingdan
     * @return
     */
    @RequestMapping("/add")
    public R add(@RequestBody BaoyangdingdanEntity baoyangdingdan, HttpServletRequest request){
        baoyangdingdan.setId(new Date().getTime());
        baoyangdingdanService.insert(baoyangdingdan);
        return R.ok();
    }

    /**
     * 前端列表
     *
     * @param params
     * @param baoyangdingdan
     * @return
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, BaoyangdingdanEntity baoyangdingdan) {
        EntityWrapper<BaoyangdingdanEntity> ew = new EntityWrapper<>();
        PageUtils pageUtils = baoyangdingdanService.queryPage(params, MPUtil.likeOrEq(ew, baoyangdingdan));
        return R.ok().put("data", pageUtils);
    }

    /**
     * 查询
     *
     * @param baoyangdingdan
     * @return
     */
    @RequestMapping("/query")
    public R query(BaoyangdingdanEntity baoyangdingdan) {
        EntityWrapper<BaoyangdingdanEntity> ew = new EntityWrapper<>();
        ew.allEq(MPUtil.allEQMapPre(baoyangdingdan, "baoyangdingdan"));
        BaoyangdingdanEntity baoyangdingdan1 = baoyangdingdanService.selectOne(ew);
        return R.ok().put("data", baoyangdingdan1);
    }

    /**
     * 前端信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request) {
        BaoyangdingdanEntity baoyangdingdan1 = baoyangdingdanService.selectById(id);
        return R.ok().put("data", baoyangdingdan1);
    }

    /**
     * 后端列表
     * @param params
     * @param baoyangdingdan
     * @param request
     * @return
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, BaoyangdingdanEntity baoyangdingdan, HttpServletRequest request){
        String tableName = request.getSession().getAttribute("tableName") == null ? "" : request.getSession().getAttribute("tableName").toString();
        if (tableName.equals("yonghu")){
            baoyangdingdan.setZhanghao((String) request.getSession().getAttribute("username"));
        }
        EntityWrapper<BaoyangdingdanEntity> ew = new EntityWrapper<>();
        PageUtils pageUtils = baoyangdingdanService.queryPage(params, MPUtil.likeOrEq(ew, baoyangdingdan));
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
        BaoyangdingdanEntity baoyangdingdan = baoyangdingdanService.selectById(id);
        return R.ok().put("data", baoyangdingdan);
    }

    /**
     * 保存
     *
     * @param baoyangdingdan
     * @return
     */
    @RequestMapping("/save")
    public R save(@RequestBody  BaoyangdingdanEntity baoyangdingdan) {
        baoyangdingdanService.insert(baoyangdingdan);
        return R.ok();
    }

    /**
     * 修改
     *
     * @param baoyangdingdan
     * @return
     */
    @RequestMapping("/update")
    public R update(@RequestBody BaoyangdingdanEntity baoyangdingdan) {
        //正常更新
        baoyangdingdanService.updateById(baoyangdingdan);
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
        baoyangdingdanService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    /**
     * 统计数量
     *
     * @param params
     * @param baoyangdingdan
     * @param request
     * @return
     */
    @RequestMapping("/count")
    public R count(@RequestParam Map<String, Object> params, BaoyangdingdanEntity baoyangdingdan, HttpServletRequest request) {
        EntityWrapper<BaoyangdingdanEntity> ew = new EntityWrapper<>();
        int count = baoyangdingdanService.selectCount(MPUtil.likeOrEq(ew, baoyangdingdan));
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
        EntityWrapper<BaoyangdingdanEntity> ew = new EntityWrapper<>();
        //获取表名
        String tableName = request.getSession().getAttribute("tableName") == null ? "" : request.getSession().getAttribute("tableName").toString();
        if (tableName.equals("yonghu")){
            ew.eq("zhanghao",(String)request.getSession().getAttribute("username"));
        }
        //调用service完成业务操作
        List<Map<String, Object>> result = baoyangdingdanService.selectTimeStatValue(params, ew);
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
