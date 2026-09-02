package com.qf.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qf.entity.ConfigEntity;
import com.qf.mapper.ConfigDao;
import com.qf.service.ConfigService;
import com.qf.utils.PageUtils;
import com.qf.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("configService")
public class ConfigServiceImpl extends ServiceImpl<ConfigDao, ConfigEntity> implements ConfigService {
    
    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<ConfigEntity> wrapper) {
        Page<ConfigEntity> page = new Query<ConfigEntity>(params).getPage();
        page.setRecords(baseMapper.selectPage(page, wrapper));
        return new PageUtils(page);
    }
}
