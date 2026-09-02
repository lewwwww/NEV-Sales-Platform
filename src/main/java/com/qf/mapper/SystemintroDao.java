package com.qf.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.qf.entity.SystemintroEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SystemintroDao extends BaseMapper<SystemintroEntity> {
    // 基础列表查询
    List<SystemintroEntity> selectListView(@Param("ew") Wrapper<SystemintroEntity> wrapper);
    // 分页列表查询
    List<SystemintroEntity> selectListView(Pagination page, @Param("ew") Wrapper<SystemintroEntity> wrapper);

    // 分组统计（如按标题分组，适配少量统计场景）
    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params, @Param("ew") Wrapper<SystemintroEntity> wrapper);
}