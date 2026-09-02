package com.qf.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.qf.entity.PinpaileibieEntity;
import com.qf.entity.TokenEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PinpaileibieDao extends BaseMapper<PinpaileibieEntity> {
    List<PinpaileibieEntity> selectListView(@Param("ew") Wrapper<PinpaileibieEntity> wrapper);
    List<PinpaileibieEntity> selectListView(Pagination page, @Param("ew") Wrapper<PinpaileibieEntity> wrapper);
}
