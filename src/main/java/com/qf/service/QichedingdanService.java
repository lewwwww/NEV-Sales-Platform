package com.qf.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.qf.entity.QichedingdanEntity;
import com.qf.entity.XinnengyuanqicheEntity;
import com.qf.utils.PageUtils;

import java.util.List;
import java.util.Map;

public interface QichedingdanService extends IService<QichedingdanEntity> {
    List<QichedingdanEntity> selectListView(Wrapper<QichedingdanEntity> wrapper);
    PageUtils queryPage(Map<String, Object> params, Wrapper<QichedingdanEntity> wrapper);

    List<Map<String, Object>> selectValue( Map<String, Object> params,  Wrapper<QichedingdanEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue( Map<String, Object> params,  Wrapper<QichedingdanEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params, Wrapper<QichedingdanEntity> wrapper);
}
