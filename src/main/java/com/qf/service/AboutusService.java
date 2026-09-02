package com.qf.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.qf.entity.AboutusEntity;
import com.qf.utils.PageUtils;

import java.util.Map;

public interface AboutusService extends IService<AboutusEntity> {
    PageUtils queryPage(Map<String, Object> params, Wrapper<AboutusEntity> wrapper);
}
