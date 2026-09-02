package com.qf.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.qf.entity.PeisongxinxiEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface PeisongxinxiDao extends BaseMapper<PeisongxinxiEntity> {
    // 基础列表查询
    List<PeisongxinxiEntity> selectListView(@Param("ew") Wrapper<PeisongxinxiEntity> wrapper);
    // 分页列表查询
    List<PeisongxinxiEntity> selectListView(Pagination page, @Param("ew") Wrapper<PeisongxinxiEntity> wrapper);

    // 统计相关方法（适配配送信息的数值/时间/分组统计）
    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params, @Param("ew") Wrapper<PeisongxinxiEntity> wrapper);
    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params, @Param("ew") Wrapper<PeisongxinxiEntity> wrapper);
    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params, @Param("ew") Wrapper<PeisongxinxiEntity> wrapper);
}