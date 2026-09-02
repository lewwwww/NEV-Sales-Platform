package com.qf.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.qf.entity.PeisongdingdanEntity;
import com.qf.utils.PageUtils;

import java.util.List;
import java.util.Map;

public interface PeisongdingdanService extends IService<PeisongdingdanEntity> {
    // 基础列表查询
    List<PeisongdingdanEntity> selectListView(Wrapper<PeisongdingdanEntity> wrapper);
    // 分页查询
    PageUtils queryPage(Map<String, Object> params, Wrapper<PeisongdingdanEntity> wrapper);

    // 统计相关方法
    List<Map<String, Object>> selectValue(Map<String, Object> params, Wrapper<PeisongdingdanEntity> wrapper);
    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params, Wrapper<PeisongdingdanEntity> wrapper);
    List<Map<String, Object>> selectGroup(Map<String, Object> params, Wrapper<PeisongdingdanEntity> wrapper);
}