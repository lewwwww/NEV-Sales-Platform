package com.qf.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qf.entity.DiscussxinnengyuanqicheEntity;
import com.qf.mapper.DiscussxinnengyuanqicheDao;
import com.qf.service.DiscussxinnengyuanqicheService;
import com.qf.utils.PageUtils;
import com.qf.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class DiscussxinnengyuanqicheServiceImpl extends ServiceImpl<DiscussxinnengyuanqicheDao, DiscussxinnengyuanqicheEntity>implements DiscussxinnengyuanqicheService {
    @Override
    public List<DiscussxinnengyuanqicheEntity> selectListView(Wrapper<DiscussxinnengyuanqicheEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public PageUtils selectListView(Map<String, Object> params, Wrapper<DiscussxinnengyuanqicheEntity> wrapper) {
        Page<DiscussxinnengyuanqicheEntity> page= new Query<DiscussxinnengyuanqicheEntity>(params).getPage();
        page.setRecords(baseMapper.selectListView(wrapper));
        return new PageUtils(page);
    }
}
