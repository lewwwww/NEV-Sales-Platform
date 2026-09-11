package com.qf.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.qf.entity.UsersEntity;
import com.qf.entity.XinnengyuanqicheEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface XinnnegyuanqicheDao extends BaseMapper<XinnengyuanqicheEntity> {

    List<XinnengyuanqicheEntity> selectListView(@Param("ew") Wrapper<XinnengyuanqicheEntity> wrapper);
    List<XinnengyuanqicheEntity> selectListView(Pagination page, @Param("ew") Wrapper<XinnengyuanqicheEntity> wrapper);

    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params, @Param("ew") Wrapper<XinnengyuanqicheEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params, @Param("ew") Wrapper<XinnengyuanqicheEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params, @Param("ew") Wrapper<XinnengyuanqicheEntity> wrapper);

    /**
     * 库存条件扣减（防超卖）
     * 库存充足才扣减（shuliang >= num），返回受影响行数；
     * 并发下由数据库行锁保证原子性，避免超卖和丢更新。
     */
    int subStock(@Param("id") Long id, @Param("num") Integer num);

    /**
     * 最近点击时间刷新（点击时间闭环）
     * 用户端访问车辆详情页时调用：刷新该车 clicktime（热门推荐 autoSort 按它倒序）。
     * 防刷设计：SQL 条件更新，仅当 clicktime 为 NULL 或不是今天时才写库（date(clicktime) < curdate()），
     * 保证同一辆车同一天最多写库 1 次；条件更新天然原子，并发下无写放大、无竞态。
     * @param id 车辆 id
     * @return 受影响行数：1=本次已刷新；0=当天已刷新过或车辆不存在
     */
    int touchClicktime(@Param("id") Long id);

}
