package com.qf.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.qf.entity.WeixiudingdanEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface WeixiudingdanDao extends BaseMapper<WeixiudingdanEntity> {
    // 基础列表查询
    List<WeixiudingdanEntity> selectListView(@Param("ew") Wrapper<WeixiudingdanEntity> wrapper);
    // 分页列表查询
    List<WeixiudingdanEntity> selectListView(Pagination page, @Param("ew") Wrapper<WeixiudingdanEntity> wrapper);

    // 统计相关方法
    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params, @Param("ew") Wrapper<WeixiudingdanEntity> wrapper);
    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params, @Param("ew") Wrapper<WeixiudingdanEntity> wrapper);
    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params, @Param("ew") Wrapper<WeixiudingdanEntity> wrapper);
}
