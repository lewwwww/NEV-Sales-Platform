package com.qf.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qf.entity.PeisongdingdanEntity;
import com.qf.mapper.PeisongdingdanDao;
import com.qf.service.PeisongdingdanService;
import com.qf.utils.PageUtils;
import com.qf.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("peisongdingdanService")
public class PeisongdingdanServiceImpl extends ServiceImpl<PeisongdingdanDao, PeisongdingdanEntity> implements PeisongdingdanService {

    @Override
    public List<PeisongdingdanEntity> selectListView(Wrapper<PeisongdingdanEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<PeisongdingdanEntity> wrapper) {
        Page<PeisongdingdanEntity> page = new Query<PeisongdingdanEntity>(params).getPage();
        page.setRecords(baseMapper.selectListView(page, wrapper));
        return new PageUtils(page);
    }

    @Override
    public List<Map<String, Object>> selectValue(Map<String, Object> params, Wrapper<PeisongdingdanEntity> wrapper) {
        return baseMapper.selectValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params, Wrapper<PeisongdingdanEntity> wrapper) {
        return baseMapper.selectTimeStatValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectGroup(Map<String, Object> params, Wrapper<PeisongdingdanEntity> wrapper) {
        return baseMapper.selectGroup(params, wrapper);
    }
}