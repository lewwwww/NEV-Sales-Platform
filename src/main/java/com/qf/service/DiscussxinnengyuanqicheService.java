package com.qf.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.qf.entity.DiscussxinnengyuanqicheEntity;
import com.qf.entity.PinpaileibieEntity;
import com.qf.utils.PageUtils;

import java.util.List;
import java.util.Map;

public interface DiscussxinnengyuanqicheService extends IService<DiscussxinnengyuanqicheEntity> {
    List<DiscussxinnengyuanqicheEntity> selectListView(Wrapper<DiscussxinnengyuanqicheEntity> wrapper);
    PageUtils selectListView(Map<String,Object> params, Wrapper<DiscussxinnengyuanqicheEntity> wrapper);

}
