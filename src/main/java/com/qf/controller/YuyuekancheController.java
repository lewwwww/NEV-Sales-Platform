package com.qf.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.qf.annotation.IgnoreAuth;
import com.qf.entity.YuyuekancheEntity;
import com.qf.service.YuyuekancheService;
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
@RequestMapping("/yuyuekanche")
public class YuyuekancheController {
    @Autowired
    private YuyuekancheService yuyuekancheService;

    /**
     * 后端分页列表（管理员）
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, YuyuekancheEntity yuyuekanche) {
        EntityWrapper<YuyuekancheEntity> ew = new EntityWrapper<>();
        PageUtils pageUtils = yuyuekancheService.queryPage(params, MPUtil.likeOrEq(ew, yuyuekanche));
        return R.ok().put("data", pageUtils);
    }

    /**
     * 前端列表（用户端）
     */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, YuyuekancheEntity yuyuekanche) {
        EntityWrapper<YuyuekancheEntity> ew = new EntityWrapper<>();
        PageUtils pageUtils = yuyuekancheService.queryPage(params, MPUtil.likeOrEq(ew, yuyuekanche));
        return R.ok().put("data", pageUtils);
    }

    /**
     * 详情/信息查询
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request) {
        YuyuekancheEntity yuyuekanche = yuyuekancheService.selectById(id);
        return R.ok().put("data", yuyuekanche);
    }

    /**
     * 前端详情查询
     */
    @IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id) {
        YuyuekancheEntity yuyuekanche = yuyuekancheService.selectById(id);
        return R.ok().put("data", yuyuekanche);
    }

    /**
     * 预约提交（保存）
     */
    @IgnoreAuth
    @RequestMapping("/save")
    public R save(@RequestBody YuyuekancheEntity yuyuekanche) {
        // 预约编号查重（可选）
        if (yuyuekancheService.selectOne(new EntityWrapper<YuyuekancheEntity>().eq("yuyuebianhao", yuyuekanche.getYuyuebianhao())) != null) {
            return R.error("预约编号已存在");
        }
        // 默认未审核
        yuyuekanche.setSfsh("否");
        yuyuekancheService.insert(yuyuekanche);
        return R.ok("预约提交成功，请等待审核");
    }

    /**
     * 审核操作（管理员）
     */
    @RequestMapping("/update")
    public R update(@RequestBody YuyuekancheEntity yuyuekanche) {
        yuyuekancheService.updateById(yuyuekanche);
        return R.ok("审核状态更新成功");
    }

    /**
     * 删除预约
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        yuyuekancheService.deleteBatchIds(Arrays.asList(ids));
        return R.ok("删除成功");
    }

    /**
     * 统计数量
     */
    @RequestMapping("/count")
    public R count(@RequestParam Map<String, Object> params, YuyuekancheEntity yuyuekanche) {
        EntityWrapper<YuyuekancheEntity> ew = new EntityWrapper<>();
        int count = yuyuekancheService.selectCount(MPUtil.likeOrEq(ew, yuyuekanche));
        return R.ok().put("data", count);
    }

    /**
     * 时间维度统计（如每月预约数）
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

        EntityWrapper<YuyuekancheEntity> ew = new EntityWrapper<>();
        // 普通用户仅查看自己的预约
        String tableName = request.getSession().getAttribute("tableName") == null ? "" : request.getSession().getAttribute("tableName").toString();
        if (tableName.equals("yonghu")) {
            ew.eq("zhanghao", request.getSession().getAttribute("username"));
        }

        List<Map<String, Object>> result = yuyuekancheService.selectTimeStatValue(params, ew);
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
     * 分组统计（如按审核状态分组）
     */
    @RequestMapping("/group/{columnName}")
    public R group(@PathVariable("columnName") String columnName, HttpServletRequest request) {
        Map<String, Object> params = new HashMap<>();
        params.put("column", columnName);

        EntityWrapper<YuyuekancheEntity> ew = new EntityWrapper<>();
        String tableName = request.getSession().getAttribute("tableName") == null ? "" : request.getSession().getAttribute("tableName").toString();
        if (tableName.equals("yonghu")) {
            ew.eq("zhanghao", request.getSession().getAttribute("username"));
        }

        List<Map<String, Object>> result = yuyuekancheService.selectGroup(params, ew);
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