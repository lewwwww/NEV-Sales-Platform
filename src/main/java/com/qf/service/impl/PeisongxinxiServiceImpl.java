package com.qf.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qf.entity.PeisongxinxiEntity;
import com.qf.mapper.PeisongxinxiDao;
import com.qf.service.PeisongxinxiService;
import com.qf.utils.PageUtils;
import com.qf.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("peisongxinxiService")
public class PeisongxinxiServiceImpl extends ServiceImpl<PeisongxinxiDao, PeisongxinxiEntity> implements PeisongxinxiService {

    @Override
    public List<PeisongxinxiEntity> selectListView(Wrapper<PeisongxinxiEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<PeisongxinxiEntity> wrapper) {
        Page<PeisongxinxiEntity> page = new Query<PeisongxinxiEntity>(params).getPage();
        page.setRecords(baseMapper.selectListView(page, wrapper));
        return new PageUtils(page);
    }

    @Override
    public List<Map<String, Object>> selectValue(Map<String, Object> params, Wrapper<PeisongxinxiEntity> wrapper) {
        return baseMapper.selectValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params, Wrapper<PeisongxinxiEntity> wrapper) {
        return baseMapper.selectTimeStatValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectGroup(Map<String, Object> params, Wrapper<PeisongxinxiEntity> wrapper) {
        return baseMapper.selectGroup(params, wrapper);
    }
}