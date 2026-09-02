package com.qf.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qf.entity.XinnengyuanqicheEntity;
import com.qf.mapper.XinnnegyuanqicheDao;
import com.qf.service.XinnnegyuanqicheService;
import com.qf.utils.PageUtils;
import com.qf.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service("xinnnegyuanqicheService")
public class XinnnegyuanqicheServiceImpl extends ServiceImpl<XinnnegyuanqicheDao, XinnengyuanqicheEntity>implements XinnnegyuanqicheService {
    @Override
    public List<XinnengyuanqicheEntity> selectListView(Wrapper<XinnengyuanqicheEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<XinnengyuanqicheEntity> wrapper) {
        Page<XinnengyuanqicheEntity> page = new Query<XinnengyuanqicheEntity>(params).getPage();
        page.setRecords(baseMapper.selectListView(wrapper));
        return new PageUtils(page);
    }
    @Override
    public List<Map<String, Object>> selectValue(Map<String, Object> params, Wrapper<XinnengyuanqicheEntity> wrapper) {
        return baseMapper.selectValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params, Wrapper<XinnengyuanqicheEntity> wrapper) {
        return baseMapper.selectTimeStatValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectGroup(Map<String, Object> params, Wrapper<XinnengyuanqicheEntity> wrapper) {
        return baseMapper.selectGroup(params, wrapper);
    }
}
