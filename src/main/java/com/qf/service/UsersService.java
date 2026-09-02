package com.qf.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.qf.entity.UsersEntity;
import com.qf.utils.PageUtils;

import java.util.List;
import java.util.Map;

public interface UsersService extends IService<UsersEntity> {
    List<UsersEntity> selectListView(Wrapper<UsersEntity> wrapper);
    PageUtils queryPage(Map<String, Object> params, Wrapper<UsersEntity> wrapper);
}
