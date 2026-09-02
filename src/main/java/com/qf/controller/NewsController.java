package com.qf.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.qf.annotation.IgnoreAuth;
import com.qf.entity.NewsEntity;
import com.qf.service.NewsService;
import com.qf.utils.MPUtil;
import com.qf.utils.PageUtils;
import com.qf.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsService;

    /**
     * 后端分页列表（管理员）- 公告信息管理
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, NewsEntity news) {
        EntityWrapper<NewsEntity> ew = new EntityWrapper<>();
        PageUtils pageUtils = newsService.queryPage(params, MPUtil.likeOrEq(ew, news));
        return R.ok().put("data", pageUtils);
    }

    /**
     * 前端列表（获取所有公告，用于前端展示）
     */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, NewsEntity news) {
        EntityWrapper<NewsEntity> ew = new EntityWrapper<>();
        PageUtils pageUtils = newsService.queryPage(params, MPUtil.likeOrEq(ew, news));
        return R.ok().put("data", pageUtils);
    }

    /**
     * 详情/信息查询（管理员）
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request) {
        NewsEntity news = newsService.selectById(id);
        return R.ok().put("data", news);
    }

    /**
     * 前端详情查询（游客/用户，展示公告详情）
     */
    @IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id) {
        NewsEntity news = newsService.selectById(id);
        return R.ok().put("data", news);
    }

    /**
     * 保存/新增公告（管理员）
     */
    @RequestMapping("/save")
    public R save(@RequestBody NewsEntity news) {
        newsService.insert(news);
        return R.ok("公告新增成功");
    }

    /**
     * 修改公告（管理员）
     */
    @RequestMapping("/update")
    public R update(@RequestBody NewsEntity news) {
        newsService.updateById(news);
        return R.ok("公告更新成功");
    }

    /**
     * 删除公告（管理员）
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        newsService.deleteBatchIds(Arrays.asList(ids));
        return R.ok("公告删除成功");
    }

    /**
     * 统计数量（管理员）
     */
    @RequestMapping("/count")
    public R count(@RequestParam Map<String, Object> params, NewsEntity news) {
        EntityWrapper<NewsEntity> ew = new EntityWrapper<>();
        int count = newsService.selectCount(MPUtil.likeOrEq(ew, news));
        return R.ok().put("data", count);
    }
}
