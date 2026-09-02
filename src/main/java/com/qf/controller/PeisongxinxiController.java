package com.qf.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.qf.annotation.IgnoreAuth;
import com.qf.entity.PeisongxinxiEntity;
import com.qf.service.PeisongxinxiService;
import com.qf.utils.MPUtil;
import com.qf.utils.PageUtils;
import com.qf.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/peisongxinxi")
public class PeisongxinxiController {
    @Autowired
    private PeisongxinxiService peisongxinxiService;

    /**
     * 后端分页列表（管理员/配送员）
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, PeisongxinxiEntity peisongxinxi) {
        EntityWrapper<PeisongxinxiEntity> ew = new EntityWrapper<>();
        PageUtils pageUtils = peisongxinxiService.queryPage(params, MPUtil.likeOrEq(ew, peisongxinxi));
        return R.ok().put("data", pageUtils);
    }

    /**
     * 前端列表（用户端，查看自己的配送信息）
     */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, PeisongxinxiEntity peisongxinxi) {
        EntityWrapper<PeisongxinxiEntity> ew = new EntityWrapper<>();
        // 普通用户仅查看自己的配送信息
        String tableName = params.get("tableName") == null ? "" : params.get("tableName").toString();
        if (tableName.equals("yonghu")) {
            ew.eq("zhanghao", params.get("username"));
        }
        PageUtils pageUtils = peisongxinxiService.queryPage(params, MPUtil.likeOrEq(ew, peisongxinxi));
        return R.ok().put("data", pageUtils);
    }

    /**
     * 配送信息详情（管理员）
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request) {
        PeisongxinxiEntity peisongxinxi = peisongxinxiService.selectById(id);
        return R.ok().put("data", peisongxinxi);
    }

    /**
     * 前端配送信息详情（用户）
     */
    @IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id) {
        PeisongxinxiEntity peisongxinxi = peisongxinxiService.selectById(id);
        return R.ok().put("data", peisongxinxi);
    }

    /**
     * 新增配送信息（管理员/下单时自动创建）
     */
    @RequestMapping("/save")
    public R save(@RequestBody PeisongxinxiEntity peisongxinxi) {
        // 订单编号查重
        if (peisongxinxiService.selectOne(new EntityWrapper<PeisongxinxiEntity>().eq("dingdanbianhao", peisongxinxi.getDingdanbianhao())) != null) {
            return R.error("该订单编号的配送信息已存在");
        }
        // 自动计算应付金额（单价×数量）
        if (peisongxinxi.getJiage() != null && peisongxinxi.getShuliang() != null) {
            peisongxinxi.setYingfujine(peisongxinxi.getJiage() * peisongxinxi.getShuliang());
        }
        peisongxinxiService.insert(peisongxinxi);
        return R.ok("配送信息新增成功");
    }

    /**
     * 更新配送信息（管理员/配送员）
     */
    @RequestMapping("/update")
    public R update(@RequestBody PeisongxinxiEntity peisongxinxi) {
        // 更新时自动填充更新时间
        if (peisongxinxi.getGengxinshijian() == null) {
            peisongxinxi.setGengxinshijian(new java.util.Date());
        }
        peisongxinxiService.updateById(peisongxinxi);
        return R.ok("配送信息更新成功");
    }

    /**
     * 删除配送信息（管理员）
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        peisongxinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok("配送信息删除成功");
    }

    /**
     * 统计配送信息数量
     */
    @RequestMapping("/count")
    public R count(@RequestParam Map<String, Object> params, PeisongxinxiEntity peisongxinxi) {
        EntityWrapper<PeisongxinxiEntity> ew = new EntityWrapper<>();
        int count = peisongxinxiService.selectCount(MPUtil.likeOrEq(ew, peisongxinxi));
        return R.ok().put("data", count);
    }

    /**
     * 时间维度统计（如每月配送金额/单量）
     */
    @RequestMapping("/value/{xColumnName}/{yColumnName}/{timestatType}")
    public R valueTime(@PathVariable("xColumnName") String xColumnName,
                       @PathVariable("yColumnName") String yColumnName,
                       @PathVariable("timestatType") String timestatType,
                       HttpServletRequest request) {
        Map<String, Object> params = new HashMap<>();
        params.put("xColumn", xColumnName);
        params.put("yColumn", yColumnName);
        params.put("timestatType", timestatType);

        EntityWrapper<PeisongxinxiEntity> ew = new EntityWrapper<>();
        // 普通用户仅统计自己的配送信息
        String tableName = request.getSession().getAttribute("tableName") == null ? "" : request.getSession().getAttribute("tableName").toString();
        if (tableName.equals("yonghu")) {
            ew.eq("zhanghao", request.getSession().getAttribute("username"));
        }

        List<Map<String, Object>> result = peisongxinxiService.selectTimeStatValue(params, ew);
        // 日期格式化
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (Map<String, Object> map : result) {
            for (String k : map.keySet()) {
                if (map.get(k) instanceof java.util.Date) {
                    map.put(k, sdf.format(map.get(k)));
                }
            }
        }
        return R.ok().put("data", result);
    }

    /**
     * 分组统计（如按品牌/收货地址分组）
     */
    @RequestMapping("/group/{columnName}")
    public R group(@PathVariable("columnName") String columnName, HttpServletRequest request) {
        Map<String, Object> params = new HashMap<>();
        params.put("column", columnName);

        EntityWrapper<PeisongxinxiEntity> ew = new EntityWrapper<>();
        String tableName = request.getSession().getAttribute("tableName") == null ? "" : request.getSession().getAttribute("tableName").toString();
        if (tableName.equals("yonghu")) {
            ew.eq("zhanghao", request.getSession().getAttribute("username"));
        }

        List<Map<String, Object>> result = peisongxinxiService.selectGroup(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (Map<String, Object> map : result) {
            for (String k : map.keySet()) {
                if (map.get(k) instanceof java.util.Date) {
                    map.put(k, sdf.format(map.get(k)));
                }
            }
        }
        return R.ok().put("data", result);
    }

    /**
     * 根据订单编号查询配送信息（前端专用）
     */
    @IgnoreAuth
    @RequestMapping("/queryByOrderNo/{dingdanbianhao}")
    public R queryByOrderNo(@PathVariable("dingdanbianhao") String dingdanbianhao) {
        EntityWrapper<PeisongxinxiEntity> ew = new EntityWrapper<>();
        ew.eq("dingdanbianhao", dingdanbianhao);
        PeisongxinxiEntity peisongxinxi = peisongxinxiService.selectOne(ew);
        if (peisongxinxi == null) {
            return R.error("未查询到该订单的配送信息");
        }
        return R.ok().put("data", peisongxinxi);
    }
}