package com.qf.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.qf.entity.StoreupEntity;
import com.qf.entity.YonghuEntity;
import com.qf.utils.PageUtils;

import java.util.List;
import java.util.Map;

public interface StoreupService extends IService<StoreupEntity> {

    List<StoreupEntity> selectListView(Wrapper<StoreupEntity> wrapper);
    PageUtils selectListView(Map<String,Object> params, Wrapper<StoreupEntity> wrapper);
}
