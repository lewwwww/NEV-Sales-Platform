package com.qf.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.qf.annotation.IgnoreAuth;
import com.qf.entity.SystemintroEntity;
import com.qf.service.SystemintroService;
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
@RequestMapping("/systemintro")
public class SystemintroController {
    @Autowired
    private SystemintroService systemintroService;

    /**
     * 后端分页列表（管理员）
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, SystemintroEntity systemintro) {
        EntityWrapper<SystemintroEntity> ew = new EntityWrapper<>();
        PageUtils pageUtils = systemintroService.queryPage(params, MPUtil.likeOrEq(ew, systemintro));
        return R.ok().put("data", pageUtils);
    }

    /**
     * 前端列表（游客/用户端，展示关于我们内容）
     */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, SystemintroEntity systemintro) {
        EntityWrapper<SystemintroEntity> ew = new EntityWrapper<>();
        PageUtils pageUtils = systemintroService.queryPage(params, MPUtil.likeOrEq(ew, systemintro));
        return R.ok().put("data", pageUtils);
    }

    /**
     * 详情/信息查询（管理员）
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request) {
        SystemintroEntity systemintro = systemintroService.selectById(id);
        return R.ok().put("data", systemintro);
    }

    /**
     * 前端详情查询（游客/用户，展示关于我们详情）
     */
    @IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id) {
        SystemintroEntity systemintro = systemintroService.selectById(id);
        return R.ok().put("data", systemintro);
    }

    /**
     * 获取最新的“关于我们”内容（前端首页展示专用）
     */
    @IgnoreAuth
    @RequestMapping("/getLatest")
    public R getLatest() {
        // 查询最新添加的一条关于我们内容
        EntityWrapper<SystemintroEntity> ew = new EntityWrapper<>();
        ew.orderBy("addtime", false); // 按添加时间降序
        List<SystemintroEntity> list = systemintroService.selectListView(ew.last("limit 1"));
        if (list.isEmpty()) {
            return R.ok().put("data", null);
        }
        return R.ok().put("data", list.get(0));
    }

    /**
     * 保存/新增关于我们内容（管理员）
     */
    @RequestMapping("/save")
    public R save(@RequestBody SystemintroEntity systemintro) {
        // 标题查重（可选，避免重复内容）
        if (systemintroService.selectOne(new EntityWrapper<SystemintroEntity>().eq("title", systemintro.getTitle())) != null) {
            return R.error("该标题的关于我们内容已存在");
        }
        systemintroService.insert(systemintro);
        return R.ok("关于我们内容新增成功");
    }

    /**
     * 修改关于我们内容（管理员）
     */
    @RequestMapping("/update")
    public R update(@RequestBody SystemintroEntity systemintro) {
        systemintroService.updateById(systemintro);
        return R.ok("关于我们内容修改成功");
    }

    /**
     * 删除关于我们内容（管理员）
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        systemintroService.deleteBatchIds(Arrays.asList(ids));
        return R.ok("关于我们内容删除成功");
    }

    /**
     * 统计数量（管理员）
     */
    @RequestMapping("/count")
    public R count(@RequestParam Map<String, Object> params, SystemintroEntity systemintro) {
        EntityWrapper<SystemintroEntity> ew = new EntityWrapper<>();
        int count = systemintroService.selectCount(MPUtil.likeOrEq(ew, systemintro));
        return R.ok().put("data", count);
    }

    /**
     * 分组统计（如按标题分组，管理员查看）
     */
    @RequestMapping("/group/{columnName}")
    public R group(@PathVariable("columnName") String columnName, HttpServletRequest request) {
        Map<String, Object> params = new HashMap<>();
        params.put("column", columnName);

        EntityWrapper<SystemintroEntity> ew = new EntityWrapper<>();
        List<Map<String, Object>> result = systemintroService.selectGroup(params, ew);

        // 日期格式化（兼容添加时间字段的统计）
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