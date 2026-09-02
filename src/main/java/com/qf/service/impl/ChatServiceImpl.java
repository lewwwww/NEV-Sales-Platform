package com.qf.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qf.entity.ChatEntity;
import com.qf.mapper.ChatDao;
import com.qf.service.ChatService;
import com.qf.utils.PageUtils;
import com.qf.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("chatService")
public class ChatServiceImpl extends ServiceImpl<ChatDao, ChatEntity> implements ChatService {
    
    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<ChatEntity> wrapper) {
        Page<ChatEntity> page = new Query<ChatEntity>(params).getPage();
        page.setRecords(baseMapper.selectPage(page, wrapper));
        return new PageUtils(page);
    }
}
