package com.qf.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.qf.entity.PinpaileibieEntity;
import com.qf.entity.YonghuEntity;
import com.qf.utils.PageUtils;

import java.util.List;
import java.util.Map;

public interface PinpaileibieService extends IService<PinpaileibieEntity> {
    List<PinpaileibieEntity> selectListView(Wrapper<PinpaileibieEntity> wrapper);
    PageUtils selectListView(Map<String,Object> params, Wrapper<PinpaileibieEntity> wrapper);
}
