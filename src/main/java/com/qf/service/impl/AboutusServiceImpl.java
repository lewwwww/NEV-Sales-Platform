package com.qf.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qf.entity.AboutusEntity;
import com.qf.mapper.AboutusDao;
import com.qf.service.AboutusService;
import com.qf.utils.PageUtils;
import com.qf.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("aboutusService")
public class AboutusServiceImpl extends ServiceImpl<AboutusDao, AboutusEntity> implements AboutusService {
    
    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<AboutusEntity> wrapper) {
        Page<AboutusEntity> page = new Query<AboutusEntity>(params).getPage();
        page.setRecords(baseMapper.selectPage(page, wrapper));
        return new PageUtils(page);
    }
}
