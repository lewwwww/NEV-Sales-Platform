package com.qf.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qf.entity.NewsEntity;
import com.qf.mapper.NewsDao;
import com.qf.service.NewsService;
import com.qf.utils.PageUtils;
import com.qf.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("newsService")
public class NewsServiceImpl extends ServiceImpl<NewsDao, NewsEntity> implements NewsService {
    
    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<NewsEntity> wrapper) {
        Page<NewsEntity> page = new Query<NewsEntity>(params).getPage();
        page.setRecords(baseMapper.selectPage(page, wrapper));
        return new PageUtils(page);
    }
}
