package com.qf.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.qf.entity.ConfigEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ConfigDao extends BaseMapper<ConfigEntity> {
}
