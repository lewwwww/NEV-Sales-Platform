package com.qf.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.qf.entity.ConfigEntity;
import com.qf.service.ConfigService;
import com.qf.utils.MPUtil;
import com.qf.utils.PageUtils;
import com.qf.utils.Query;
import com.qf.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/config")
public class ConfigController {

    @Autowired
    private ConfigService configService;

    /**
     * 后端分页列表（管理员）
     * 支持 name / value 等字段的模糊或等值查询
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, ConfigEntity config) {
        EntityWrapper<ConfigEntity> ew = new EntityWrapper<>();
        PageUtils pageUtils = configService.queryPage(params, MPUtil.likeOrEq(ew, config));
        return R.ok().put("data", pageUtils);
    }

    /**
     * 列表
     * @param params
     * @param config
     * @param request
     * @return
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,
                  ConfigEntity config,
                  HttpServletRequest request){
        EntityWrapper<ConfigEntity> ew = new EntityWrapper<>();
        List<ConfigEntity> list = configService.selectList(ew);
        Page<ConfigEntity> page = new Query<ConfigEntity>(params).getPage();
        page.setRecords(list);
        PageUtils pageUtils = new PageUtils(page);
        return R.ok().put("data", pageUtils);


    }
}
