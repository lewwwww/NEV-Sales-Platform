package com.qf.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.qf.annotation.IgnoreAuth;
import com.qf.entity.AboutusEntity;
import com.qf.service.AboutusService;
import com.qf.utils.MPUtil;
import com.qf.utils.PageUtils;
import com.qf.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/aboutus")
public class AboutusController {
    @Autowired
    private AboutusService aboutusService;

    /**
     * 后端分页列表（管理员）- 关于我们管理
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, AboutusEntity aboutus) {
        EntityWrapper<AboutusEntity> ew = new EntityWrapper<>();
        PageUtils pageUtils = aboutusService.queryPage(params, MPUtil.likeOrEq(ew, aboutus));
        return R.ok().put("data", pageUtils);
    }

    /**
     * 前端列表（获取所有关于我们内容，用于前端展示）
     */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, AboutusEntity aboutus) {
        EntityWrapper<AboutusEntity> ew = new EntityWrapper<>();
        PageUtils pageUtils = aboutusService.queryPage(params, MPUtil.likeOrEq(ew, aboutus));
        return R.ok().put("data", pageUtils);
    }

    /**
     * 详情/信息查询（管理员）
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request) {
        AboutusEntity aboutus = aboutusService.selectById(id);
        return R.ok().put("data", aboutus);
    }

    /**
     * 前端详情查询（游客/用户，展示关于我们详情）
     */
    @IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id) {
        AboutusEntity aboutus = aboutusService.selectById(id);
        return R.ok().put("data", aboutus);
    }

    /**
     * 获取最新的关于我们内容（前端首页展示专用）
     */
    @IgnoreAuth
    @RequestMapping("/getLatest")
    public R getLatest() {
        EntityWrapper<AboutusEntity> ew = new EntityWrapper<>();
        ew.orderBy("addtime", false);
        java.util.List<AboutusEntity> list = aboutusService.selectList(ew.last("limit 1"));
        if (list.isEmpty()) {
            return R.ok().put("data", null);
        }
        return R.ok().put("data", list.get(0));
    }

    /**
     * 保存/新增关于我们内容（管理员）
     */
    @RequestMapping("/save")
    public R save(@RequestBody AboutusEntity aboutus) {
        aboutusService.insert(aboutus);
        return R.ok("关于我们内容新增成功");
    }

    /**
     * 修改关于我们内容（管理员）
     */
    @RequestMapping("/update")
    public R update(@RequestBody AboutusEntity aboutus) {
        aboutusService.updateById(aboutus);
        return R.ok("关于我们内容修改成功");
    }

    /**
     * 删除关于我们内容（管理员）
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        aboutusService.deleteBatchIds(Arrays.asList(ids));
        return R.ok("关于我们内容删除成功");
    }

    /**
     * 统计数量（管理员）
     */
    @RequestMapping("/count")
    public R count(@RequestParam Map<String, Object> params, AboutusEntity aboutus) {
        EntityWrapper<AboutusEntity> ew = new EntityWrapper<>();
        int count = aboutusService.selectCount(MPUtil.likeOrEq(ew, aboutus));
        return R.ok().put("data", count);
    }
}
