package com.qf.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.qf.entity.SystemintroEntity;
import com.qf.utils.PageUtils;

import java.util.List;
import java.util.Map;

public interface SystemintroService extends IService<SystemintroEntity> {
    // 基础列表查询
    List<SystemintroEntity> selectListView(Wrapper<SystemintroEntity> wrapper);
    // 分页查询
    PageUtils queryPage(Map<String, Object> params, Wrapper<SystemintroEntity> wrapper);

    // 分组统计（适配少量统计场景）
    List<Map<String, Object>> selectGroup(Map<String, Object> params, Wrapper<SystemintroEntity> wrapper);
}