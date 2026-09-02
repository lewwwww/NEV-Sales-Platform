package com.qf.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qf.entity.QichedingdanEntity;
import com.qf.mapper.QichedingdanDao;
import com.qf.service.QichedingdanService;
import com.qf.utils.PageUtils;
import com.qf.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class QichedingdanServiceImpl extends ServiceImpl<QichedingdanDao, QichedingdanEntity>implements QichedingdanService {
    @Override
    public List<QichedingdanEntity> selectListView(Wrapper<QichedingdanEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<QichedingdanEntity> wrapper) {
        Page<QichedingdanEntity> page = new Query<QichedingdanEntity>(params).getPage();
        page.setRecords(baseMapper.selectListView(wrapper));
        return new PageUtils(page);
    }

    @Override
    public List<Map<String, Object>> selectValue(Map<String, Object> params, Wrapper<QichedingdanEntity> wrapper) {
        return baseMapper.selectValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params, Wrapper<QichedingdanEntity> wrapper) {
        return baseMapper.selectTimeStatValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectGroup(Map<String, Object> params, Wrapper<QichedingdanEntity> wrapper) {
        return baseMapper.selectGroup(params, wrapper);
    }
}
