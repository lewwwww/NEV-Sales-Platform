package com.qf.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.qf.entity.PeisongdingdanEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface PeisongdingdanDao extends BaseMapper<PeisongdingdanEntity> {
    // 基础列表查询
    List<PeisongdingdanEntity> selectListView(@Param("ew") Wrapper<PeisongdingdanEntity> wrapper);
    // 分页列表查询
    List<PeisongdingdanEntity> selectListView(Pagination page, @Param("ew") Wrapper<PeisongdingdanEntity> wrapper);

    // 统计相关方法（适配销售额/订单量统计）
    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params, @Param("ew") Wrapper<PeisongdingdanEntity> wrapper);
    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params, @Param("ew") Wrapper<PeisongdingdanEntity> wrapper);
    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params, @Param("ew") Wrapper<PeisongdingdanEntity> wrapper);
}