package com.qf.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.qf.entity.YuyuekancheEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface YuyuekancheDao extends BaseMapper<YuyuekancheEntity> {
    // 基础列表查询
    List<YuyuekancheEntity> selectListView(@Param("ew") Wrapper<YuyuekancheEntity> wrapper);
    // 分页列表查询
    List<YuyuekancheEntity> selectListView(Pagination page, @Param("ew") Wrapper<YuyuekancheEntity> wrapper);

    // 统计相关方法（适配可视化/报表需求）
    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params, @Param("ew") Wrapper<YuyuekancheEntity> wrapper);
    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params, @Param("ew") Wrapper<YuyuekancheEntity> wrapper);
    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params, @Param("ew") Wrapper<YuyuekancheEntity> wrapper);
}