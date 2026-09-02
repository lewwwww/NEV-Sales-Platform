package com.qf.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.qf.entity.YuyuekancheEntity;
import com.qf.utils.PageUtils;

import java.util.List;
import java.util.Map;

public interface YuyuekancheService extends IService<YuyuekancheEntity> {
    // 基础列表查询
    List<YuyuekancheEntity> selectListView(Wrapper<YuyuekancheEntity> wrapper);
    // 分页查询
    PageUtils queryPage(Map<String, Object> params, Wrapper<YuyuekancheEntity> wrapper);

    // 统计相关方法
    List<Map<String, Object>> selectValue(Map<String, Object> params, Wrapper<YuyuekancheEntity> wrapper);
    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params, Wrapper<YuyuekancheEntity> wrapper);
    List<Map<String, Object>> selectGroup(Map<String, Object> params, Wrapper<YuyuekancheEntity> wrapper);
}