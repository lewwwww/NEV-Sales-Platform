package com.qf.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qf.entity.SystemintroEntity;
import com.qf.mapper.SystemintroDao;
import com.qf.service.SystemintroService;
import com.qf.utils.PageUtils;
import com.qf.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("systemintroService")
public class SystemintroServiceImpl extends ServiceImpl<SystemintroDao, SystemintroEntity> implements SystemintroService {

    @Override
    public List<SystemintroEntity> selectListView(Wrapper<SystemintroEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<SystemintroEntity> wrapper) {
        Page<SystemintroEntity> page = new Query<SystemintroEntity>(params).getPage();
        page.setRecords(baseMapper.selectListView(page, wrapper));
        return new PageUtils(page);
    }

    @Override
    public List<Map<String, Object>> selectGroup(Map<String, Object> params, Wrapper<SystemintroEntity> wrapper) {
        return baseMapper.selectGroup(params, wrapper);
    }
}