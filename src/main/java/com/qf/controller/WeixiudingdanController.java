package com.qf.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.qf.annotation.IgnoreAuth;
import com.qf.entity.WeixiudingdanEntity;
import com.qf.service.WeixiudingdanService;
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
@RequestMapping("/weixiudingdan")
public class WeixiudingdanController {
    @Autowired
    private WeixiudingdanService weixiudingdanService;

    /**
     * 前端保存
     * @param weixiudingdan
     * @return
     */
    @RequestMapping("/add")
    public R add(@RequestBody WeixiudingdanEntity weixiudingdan, HttpServletRequest request){
        weixiudingdan.setId(new Date().getTime());
        weixiudingdanService.insert(weixiudingdan);
        return R.ok();
    }

    /**
     * 前端列表
     *
     * @param params
     * @param weixiudingdan
     * @return
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, WeixiudingdanEntity weixiudingdan) {
        EntityWrapper<WeixiudingdanEntity> ew = new EntityWrapper<>();
        PageUtils pageUtils = weixiudingdanService.queryPage(params, MPUtil.likeOrEq(ew, weixiudingdan));
        return R.ok().put("data", pageUtils);
    }

    /**
     * 查询
     *
     * @param weixiudingdan
     * @return
     */
    @RequestMapping("/query")
    public R query(WeixiudingdanEntity weixiudingdan) {
        EntityWrapper<WeixiudingdanEntity> ew = new EntityWrapper<>();
        ew.allEq(MPUtil.allEQMapPre(weixiudingdan, "weixiudingdan"));
        WeixiudingdanEntity weixiudingdan1 = weixiudingdanService.selectOne(ew);
        return R.ok().put("data", weixiudingdan1);
    }

    /**
     * 前端信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request) {
        WeixiudingdanEntity weixiudingdan1 = weixiudingdanService.selectById(id);
        return R.ok().put("data", weixiudingdan1);
    }

    /**
     * 后端列表
     * @param params
     * @param weixiudingdan
     * @param request
     * @return
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, WeixiudingdanEntity weixiudingdan, HttpServletRequest request){
        String tableName = request.getSession().getAttribute("tableName") == null ? "" : request.getSession().getAttribute("tableName").toString();
        if (tableName.equals("yonghu")){
            weixiudingdan.setZhanghao((String) request.getSession().getAttribute("username"));
        }
        EntityWrapper<WeixiudingdanEntity> ew = new EntityWrapper<>();
        PageUtils pageUtils = weixiudingdanService.queryPage(params, MPUtil.likeOrEq(ew, weixiudingdan));
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
        WeixiudingdanEntity weixiudingdan = weixiudingdanService.selectById(id);
        return R.ok().put("data", weixiudingdan);
    }

    /**
     * 保存
     *
     * @param weixiudingdan
     * @return
     */
    @RequestMapping("/save")
    public R save(@RequestBody  WeixiudingdanEntity weixiudingdan) {
        weixiudingdanService.insert(weixiudingdan);
        return R.ok();
    }

    /**
     * 修改
     *
     * @param weixiudingdan
     * @return
     */
    @RequestMapping("/update")
    public R update(@RequestBody WeixiudingdanEntity weixiudingdan) {
        //正常更新
        weixiudingdanService.updateById(weixiudingdan);
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
        weixiudingdanService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    /**
     * 统计数量
     *
     * @param params
     * @param weixiudingdan
     * @param request
     * @return
     */
    @RequestMapping("/count")
    public R count(@RequestParam Map<String, Object> params, WeixiudingdanEntity weixiudingdan, HttpServletRequest request) {
        EntityWrapper<WeixiudingdanEntity> ew = new EntityWrapper<>();
        int count = weixiudingdanService.selectCount(MPUtil.likeOrEq(ew, weixiudingdan));
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
        EntityWrapper<WeixiudingdanEntity> ew = new EntityWrapper<>();
        //获取表名
        String tableName = request.getSession().getAttribute("tableName") == null ? "" : request.getSession().getAttribute("tableName").toString();
        if (tableName.equals("yonghu")){
            ew.eq("zhanghao",(String)request.getSession().getAttribute("username"));
        }
        //调用service完成业务操作
        List<Map<String, Object>> result = weixiudingdanService.selectTimeStatValue(params, ew);
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
