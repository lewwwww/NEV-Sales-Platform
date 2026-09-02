package com.qf.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qf.entity.StoreupEntity;
import com.qf.mapper.StoreupDao;
import com.qf.service.StoreupService;
import com.qf.utils.PageUtils;
import com.qf.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class StoreupServiceImpl extends ServiceImpl<StoreupDao, StoreupEntity>implements StoreupService {
    @Override
    public List<StoreupEntity> selectListView(Wrapper<StoreupEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public PageUtils selectListView(Map<String, Object> params, Wrapper<StoreupEntity> wrapper) {
        Page<StoreupEntity> page = new Query<StoreupEntity>(params).getPage();
        page.setRecords(baseMapper.selectListView(wrapper));

        return new PageUtils(page);
    }
}
