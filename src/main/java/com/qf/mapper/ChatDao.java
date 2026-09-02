package com.qf.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.qf.entity.ChatEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChatDao extends BaseMapper<ChatEntity> {
}
