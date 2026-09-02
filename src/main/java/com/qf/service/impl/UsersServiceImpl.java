package com.qf.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qf.entity.UsersEntity;
import com.qf.mapper.UsersDao;
import com.qf.service.UsersService;
import com.qf.utils.PageUtils;
import com.qf.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("usersServiceImpl")
public class UsersServiceImpl extends ServiceImpl<UsersDao, UsersEntity>implements UsersService {
    @Override
    public List<UsersEntity> selectListView(Wrapper<UsersEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<UsersEntity> wrapper) {
        Page<UsersEntity> page = new Query<UsersEntity>(params).getPage();
        page.setRecords(baseMapper.selectListView(wrapper));
        return new PageUtils(page);
    }
}
