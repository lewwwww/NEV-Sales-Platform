package com.qf.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.qf.entity.YonghuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface YonghuDao extends BaseMapper<YonghuEntity> {
    List<YonghuEntity> selectListView(@Param("ew") Wrapper<YonghuEntity> wrapper);
    List<YonghuEntity> selectListView(Pagination page,@Param("ew") Wrapper<YonghuEntity> wrapper);

}
