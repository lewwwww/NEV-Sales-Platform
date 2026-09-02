package com.qf.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.qf.annotation.IgnoreAuth;
import com.qf.entity.PeisongdingdanEntity;
import com.qf.service.PeisongdingdanService;
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
@RequestMapping("/peisongdingdan")
public class PeisongdingdanController {
    @Autowired
    private PeisongdingdanService peisongdingdanService;

    /**
     * 后端分页列表（管理员）
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, PeisongdingdanEntity peisongdingdan) {
        EntityWrapper<PeisongdingdanEntity> ew = new EntityWrapper<>();
        PageUtils pageUtils = peisongdingdanService.queryPage(params, MPUtil.likeOrEq(ew, peisongdingdan));
        return R.ok().put("data", pageUtils);
    }

    /**
     * 前端列表（用户端/配送端）
     */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, PeisongdingdanEntity peisongdingdan) {
        EntityWrapper<PeisongdingdanEntity> ew = new EntityWrapper<>();
        PageUtils pageUtils = peisongdingdanService.queryPage(params, MPUtil.likeOrEq(ew, peisongdingdan));
        return R.ok().put("data", pageUtils);
    }

    /**
     * 订单详情/信息查询（管理员）
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request) {
        PeisongdingdanEntity peisongdingdan = peisongdingdanService.selectById(id);
        return R.ok().put("data", peisongdingdan);
    }

    /**
     * 前端订单详情查询（用户/配送员）
     */
    @IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id) {
        PeisongdingdanEntity peisongdingdan = peisongdingdanService.selectById(id);
        return R.ok().put("data", peisongdingdan);
    }

    /**
     * 下单（保存配送订单）
     */
    @IgnoreAuth
    @RequestMapping("/save")
    public R save(@RequestBody PeisongdingdanEntity peisongdingdan) {
        // 订单编号查重
        if (peisongdingdanService.selectOne(new EntityWrapper<PeisongdingdanEntity>().eq("dingdanbianhao", peisongdingdan.getDingdanbianhao())) != null) {
            return R.error("订单编号已存在");
        }
        // 自动计算应付金额（单价*数量）
        if (peisongdingdan.getJiage() != null && peisongdingdan.getShuliang() != null) {
            peisongdingdan.setYingfujine(peisongdingdan.getJiage() * peisongdingdan.getShuliang());
        }
        peisongdingdanService.insert(peisongdingdan);
        return R.ok("下单成功，等待接单");
    }

    /**
     * 接单操作（配送员/管理员）
     */
    @RequestMapping("/update")
    public R update(@RequestBody PeisongdingdanEntity peisongdingdan) {
        // 接单时自动填充接单时间
        if (peisongdingdan.getJiedanshijian() == null) {
            peisongdingdan.setJiedanshijian(new java.util.Date());
        }
        peisongdingdanService.updateById(peisongdingdan);
        return R.ok("接单成功，已更新订单状态");
    }

    /**
     * 删除订单
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        peisongdingdanService.deleteBatchIds(Arrays.asList(ids));
        return R.ok("订单删除成功");
    }

    /**
     * 统计订单数量
     */
    @RequestMapping("/count")
    public R count(@RequestParam Map<String, Object> params, PeisongdingdanEntity peisongdingdan) {
        EntityWrapper<PeisongdingdanEntity> ew = new EntityWrapper<>();
        int count = peisongdingdanService.selectCount(MPUtil.likeOrEq(ew, peisongdingdan));
        return R.ok().put("data", count);
    }

    /**
     * 时间维度统计（如每月销售额）
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

        EntityWrapper<PeisongdingdanEntity> ew = new EntityWrapper<>();
        // 普通用户仅查看自己的订单
        String tableName = request.getSession().getAttribute("tableName") == null ? "" : request.getSession().getAttribute("tableName").toString();
        if (tableName.equals("yonghu")) {
            ew.eq("zhanghao", request.getSession().getAttribute("username"));
        }

        List<Map<String, Object>> result = peisongdingdanService.selectTimeStatValue(params, ew);
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
     * 分组统计（如按品牌/能源类型分组）
     */
    @RequestMapping("/group/{columnName}")
    public R group(@PathVariable("columnName") String columnName, HttpServletRequest request) {
        Map<String, Object> params = new HashMap<>();
        params.put("column", columnName);

        EntityWrapper<PeisongdingdanEntity> ew = new EntityWrapper<>();
        String tableName = request.getSession().getAttribute("tableName") == null ? "" : request.getSession().getAttribute("tableName").toString();
        if (tableName.equals("yonghu")) {
            ew.eq("zhanghao", request.getSession().getAttribute("username"));
        }

        List<Map<String, Object>> result = peisongdingdanService.selectGroup(params, ew);
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
}