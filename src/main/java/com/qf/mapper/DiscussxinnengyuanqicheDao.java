package com.qf.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.qf.entity.DiscussxinnengyuanqicheEntity;
import com.qf.entity.PinpaileibieEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussxinnengyuanqicheDao extends BaseMapper<DiscussxinnengyuanqicheEntity> {
    List<DiscussxinnengyuanqicheEntity> selectListView(@Param("ew") Wrapper<DiscussxinnengyuanqicheEntity> wrapper);
    List<DiscussxinnengyuanqicheEntity> selectListView(Pagination page, @Param("ew") Wrapper<DiscussxinnengyuanqicheEntity> wrapper);
}
