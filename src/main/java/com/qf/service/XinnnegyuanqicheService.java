package com.qf.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.qf.entity.UsersEntity;
import com.qf.entity.XinnengyuanqicheEntity;
import com.qf.utils.PageUtils;

import java.util.List;
import java.util.Map;

public interface XinnnegyuanqicheService extends IService<XinnengyuanqicheEntity> {
    List<XinnengyuanqicheEntity> selectListView(Wrapper<XinnengyuanqicheEntity> wrapper);
    PageUtils queryPage(Map<String, Object> params, Wrapper<XinnengyuanqicheEntity> wrapper);
    List<Map<String, Object>> selectValue( Map<String, Object> params,  Wrapper<XinnengyuanqicheEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue( Map<String, Object> params,  Wrapper<XinnengyuanqicheEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params, Wrapper<XinnengyuanqicheEntity> wrapper);
}
