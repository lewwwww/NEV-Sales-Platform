package com.qf.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.qf.entity.ConfigEntity;
import com.qf.utils.PageUtils;

import java.util.Map;

public interface ConfigService extends IService<ConfigEntity>{
    PageUtils queryPage(Map<String, Object> params, Wrapper<ConfigEntity> wrapper);
}
