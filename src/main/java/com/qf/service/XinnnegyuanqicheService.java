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

    /**
     * 库存条件扣减（防超卖）
     */
    int subStock(Long id, Integer num);

    /**
     * 最近点击时间刷新（点击时间闭环）
     * 用户端进入详情页时刷新该车 clicktime；防刷见 Dao 实现（同一天每车最多写库 1 次）
     */
    int touchClicktime(Long id);
}
