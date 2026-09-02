package com.qf.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qf.entity.WeixiudingdanEntity;
import com.qf.mapper.WeixiudingdanDao;
import com.qf.service.WeixiudingdanService;
import com.qf.utils.PageUtils;
import com.qf.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("weixiudingdanService")
public class WeixiudingdanServiceImpl extends ServiceImpl<WeixiudingdanDao, WeixiudingdanEntity> implements WeixiudingdanService {

    @Override
    public List<WeixiudingdanEntity> selectListView(Wrapper<WeixiudingdanEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<WeixiudingdanEntity> wrapper) {
        Page<WeixiudingdanEntity> page = new Query<WeixiudingdanEntity>(params).getPage();
        page.setRecords(baseMapper.selectListView(page, wrapper));
        return new PageUtils(page);
    }

    @Override
    public List<Map<String, Object>> selectValue(Map<String, Object> params, Wrapper<WeixiudingdanEntity> wrapper) {
        return baseMapper.selectValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params, Wrapper<WeixiudingdanEntity> wrapper) {
        return baseMapper.selectTimeStatValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectGroup(Map<String, Object> params, Wrapper<WeixiudingdanEntity> wrapper) {
        return baseMapper.selectGroup(params, wrapper);
    }
}
