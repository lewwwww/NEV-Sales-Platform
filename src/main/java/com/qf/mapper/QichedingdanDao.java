package com.qf.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.qf.entity.QichedingdanEntity;
import com.qf.entity.XinnengyuanqicheEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface QichedingdanDao extends BaseMapper<QichedingdanEntity> {
    List<QichedingdanEntity> selectListView(@Param("ew") Wrapper<QichedingdanEntity> wrapper);

    List<QichedingdanEntity> selectListView(Pagination page, @Param("ew") Wrapper<QichedingdanEntity> wrapper);

    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params, @Param("ew") Wrapper<QichedingdanEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params, @Param("ew") Wrapper<QichedingdanEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params, @Param("ew") Wrapper<QichedingdanEntity> wrapper);
}
