package com.qf.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.qf.entity.WeixiudingdanEntity;
import com.qf.utils.PageUtils;

import java.util.List;
import java.util.Map;

public interface WeixiudingdanService extends IService<WeixiudingdanEntity> {
    List<WeixiudingdanEntity> selectListView(Wrapper<WeixiudingdanEntity> wrapper);
    PageUtils queryPage(Map<String, Object> params, Wrapper<WeixiudingdanEntity> wrapper);

    List<Map<String, Object>> selectValue( Map<String, Object> params,  Wrapper<WeixiudingdanEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue( Map<String, Object> params,  Wrapper<WeixiudingdanEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params, Wrapper<WeixiudingdanEntity> wrapper);
}
