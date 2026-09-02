package com.qf.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.qf.entity.ChatEntity;
import com.qf.utils.PageUtils;

import java.util.Map;

public interface ChatService extends IService<ChatEntity> {
    PageUtils queryPage(Map<String, Object> params, Wrapper<ChatEntity> wrapper);
}
