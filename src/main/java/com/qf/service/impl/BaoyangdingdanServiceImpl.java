package com.qf.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qf.entity.BaoyangdingdanEntity;
import com.qf.mapper.BaoyangdingdanDao;
import com.qf.service.BaoyangdingdanService;
import com.qf.utils.PageUtils;
import com.qf.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("baoyangdingdanService")
public class BaoyangdingdanServiceImpl extends ServiceImpl<BaoyangdingdanDao, BaoyangdingdanEntity> implements BaoyangdingdanService {

    @Override
    public List<BaoyangdingdanEntity> selectListView(Wrapper<BaoyangdingdanEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<BaoyangdingdanEntity> wrapper) {
        Page<BaoyangdingdanEntity> page = new Query<BaoyangdingdanEntity>(params).getPage();
        page.setRecords(baseMapper.selectListView(page, wrapper));
        return new PageUtils(page);
    }

    @Override
    public List<Map<String, Object>> selectValue(Map<String, Object> params, Wrapper<BaoyangdingdanEntity> wrapper) {
        return baseMapper.selectValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params, Wrapper<BaoyangdingdanEntity> wrapper) {
        return baseMapper.selectTimeStatValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectGroup(Map<String, Object> params, Wrapper<BaoyangdingdanEntity> wrapper) {
        return baseMapper.selectGroup(params, wrapper);
    }
}
