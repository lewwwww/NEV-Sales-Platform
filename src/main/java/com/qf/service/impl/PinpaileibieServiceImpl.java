package com.qf.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qf.entity.PinpaileibieEntity;
import com.qf.mapper.PinpaileibieDao;
import com.qf.service.PinpaileibieService;
import com.qf.utils.PageUtils;
import com.qf.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service("pinpaileibieService")
public class PinpaileibieServiceImpl extends ServiceImpl<PinpaileibieDao, PinpaileibieEntity>implements PinpaileibieService {
    @Override
    public List<PinpaileibieEntity> selectListView(Wrapper<PinpaileibieEntity> wrapper) {

        return baseMapper.selectListView(wrapper);
    }

    @Override
    public PageUtils selectListView(Map<String, Object> params, Wrapper<PinpaileibieEntity> wrapper) {
        Page<PinpaileibieEntity> page = new Query<PinpaileibieEntity>(params).getPage();
        page.setRecords(baseMapper.selectListView(wrapper));
        return new PageUtils(page);
    }
}
