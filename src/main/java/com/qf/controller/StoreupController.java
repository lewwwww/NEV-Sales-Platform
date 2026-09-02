package com.qf.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.qf.entity.StoreupEntity;
import com.qf.service.StoreupService;
import com.qf.utils.MPUtil;
import com.qf.utils.PageUtils;
import com.qf.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/storeup")
public class StoreupController {
    @Autowired
    private StoreupService storeupService;


    /**
     * 后端列表
     * @param params
     * @param storeup
     * @param request
     * @return
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, StoreupEntity storeup, HttpServletRequest request){
        //校验登录角色
        if (!request.getSession().getAttribute("role").equals("管理员")){
            //获取seesion用户id 设置到storeup
            storeup.setUserid((Long) request.getSession().getAttribute("userid"));

        }
        //设置查询条件
        EntityWrapper<StoreupEntity> ew = new EntityWrapper<>();
        //调用service完成业务查询
        PageUtils pageUtils = storeupService.selectListView(params, MPUtil.likeOrEq(ew, storeup));
        return R.ok().put("data", pageUtils);
    }

    /**
     * 前端列表
     * @param params
     * @param storeup
     * @param request
     * @return
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, StoreupEntity storeup, HttpServletRequest request){
        //设置查询条件
        EntityWrapper<StoreupEntity> ew = new EntityWrapper<>();
        //调用service完成业务查询
        PageUtils pageUtils = storeupService.selectListView(params, MPUtil.likeOrEq(ew, storeup));
        return R.ok().put("data", pageUtils);
    }
    /**
     * 前端列表
     * @param params
     * @param storeup
     * @param request
     * @return
     */
    @RequestMapping("/lists")
    public R lists(@RequestParam Map<String, Object> params, StoreupEntity storeup, HttpServletRequest request){
        //设置查询条件
        EntityWrapper<StoreupEntity> ew = new EntityWrapper<>();
        //调用service完成业务查询
        ew.allEq(MPUtil.allEQMapPre(storeup,"storeup"));
        //查询列表数据
        List<StoreupEntity> storeupEntities = storeupService.selectListView(ew);
        return R.ok("查询收藏表成功").put("data", storeupEntities);
    }

    /**
     * 查询
     * @param storeup
     * @param request
     * @return
     */
    @RequestMapping("/query")
    public R query(StoreupEntity storeup, HttpServletRequest request){
        //设置查询条件
        EntityWrapper<StoreupEntity> ew = new EntityWrapper<>();
        //调用service完成业务查询
        ew.allEq(MPUtil.allEQMapPre(storeup,"storeup"));
        StoreupEntity storeupEntity = storeupService.selectOne(ew);
        return R.ok().put("data", storeupEntity);

    }

    /**
     * 后端详情
     * @param id
     * @return
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        StoreupEntity storeupEntity = storeupService.selectById(id);
        return R.ok().put("data", storeupEntity);
    }


    /**
     * 前端详情
     * @param id
     * @return
     */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        StoreupEntity storeupEntity = storeupService.selectById(id);
        return R.ok().put("data", storeupEntity);
    }

    /**
     * 后端保存
     * @param storeup
     * @return
     */
    @RequestMapping("/save")
    public R save(@RequestBody StoreupEntity storeup, HttpServletRequest request){
        storeup.setId(new Date().getTime());
        storeup.setUserid((Long) request.getSession().getAttribute("userId"));
        storeupService.insert(storeup);
        return R.ok();
    }

    /**
     * 前端保存
     * @param storeup
     * @return
     */
    @RequestMapping("/add")
    public R add(@RequestBody StoreupEntity storeup, HttpServletRequest request){
        storeup.setId(new Date().getTime());
        storeup.setUserid((Long) request.getSession().getAttribute("userId"));
        storeupService.insert(storeup);
        return R.ok();
    }

    /**
     * 后端修改
     * @param storeup
     * @return
     */
    @RequestMapping("/update")
    public R update(@RequestBody StoreupEntity storeup, HttpServletRequest request){
        storeupService.updateById(storeup);
        return R.ok();
    }

    /**
     * 删除
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        storeupService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

}
