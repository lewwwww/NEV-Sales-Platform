package com.qf.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.qf.entity.NewsEntity;
import com.qf.utils.PageUtils;

import java.util.Map;

public interface NewsService extends IService<NewsEntity> {
    PageUtils queryPage(Map<String, Object> params, Wrapper<NewsEntity> wrapper);
}
