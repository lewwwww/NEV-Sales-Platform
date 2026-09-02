package com.qf.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qf.entity.YuyuekancheEntity;
import com.qf.mapper.YuyuekancheDao;
import com.qf.service.YuyuekancheService;
import com.qf.utils.PageUtils;
import com.qf.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("yuyuekancheService")
public class YuyuekancheServiceImpl extends ServiceImpl<YuyuekancheDao, YuyuekancheEntity> implements YuyuekancheService {

    @Override
    public List<YuyuekancheEntity> selectListView(Wrapper<YuyuekancheEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<YuyuekancheEntity> wrapper) {
        Page<YuyuekancheEntity> page = new Query<YuyuekancheEntity>(params).getPage();
        page.setRecords(baseMapper.selectListView(page, wrapper));
        return new PageUtils(page);
    }

    @Override
    public List<Map<String, Object>> selectValue(Map<String, Object> params, Wrapper<YuyuekancheEntity> wrapper) {
        return baseMapper.selectValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params, Wrapper<YuyuekancheEntity> wrapper) {
        return baseMapper.selectTimeStatValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectGroup(Map<String, Object> params, Wrapper<YuyuekancheEntity> wrapper) {
        return baseMapper.selectGroup(params, wrapper);
    }
}