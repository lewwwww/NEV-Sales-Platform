package com.qf.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.qf.entity.StoreupEntity;
import com.qf.entity.TokenEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StoreupDao extends BaseMapper<StoreupEntity> {

    List<StoreupEntity> selectListView(@Param("ew") Wrapper<StoreupEntity> wrapper);
    List<StoreupEntity> selectListView(Pagination page, @Param("ew") Wrapper<StoreupEntity> wrapper);
}
