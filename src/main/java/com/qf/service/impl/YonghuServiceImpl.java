package com.qf.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qf.entity.YonghuEntity;
import com.qf.mapper.YonghuDao;
import com.qf.service.YonghuService;
import com.qf.utils.PageUtils;
import com.qf.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("yonghuserviceImpl")
public class YonghuServiceImpl extends ServiceImpl<YonghuDao, YonghuEntity>implements YonghuService {
    @Override
    public List<YonghuEntity> selectListView(Wrapper<YonghuEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public PageUtils selectListView(Map<String, Object> params, Wrapper<YonghuEntity> wrapper) {
        Page<YonghuEntity> page = new Query<YonghuEntity>(params).getPage();
        page.setRecords(baseMapper.selectListView(wrapper));
        return new PageUtils(page);
    }
}
