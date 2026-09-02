package com.qf.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.qf.entity.UsersEntity;
import com.qf.entity.XinnengyuanqicheEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface XinnnegyuanqicheDao extends BaseMapper<XinnengyuanqicheEntity> {

    List<XinnengyuanqicheEntity> selectListView(@Param("ew") Wrapper<XinnengyuanqicheEntity> wrapper);
    List<XinnengyuanqicheEntity> selectListView(Pagination page, @Param("ew") Wrapper<XinnengyuanqicheEntity> wrapper);

    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params, @Param("ew") Wrapper<XinnengyuanqicheEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params, @Param("ew") Wrapper<XinnengyuanqicheEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params, @Param("ew") Wrapper<XinnengyuanqicheEntity> wrapper);




}
