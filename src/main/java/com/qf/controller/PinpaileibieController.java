package com.qf.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.qf.annotation.IgnoreAuth;
import com.qf.entity.PinpaileibieEntity;
import com.qf.entity.UsersEntity;
import com.qf.service.PinpaileibieService;
import com.qf.utils.MPUtil;
import com.qf.utils.PageUtils;
import com.qf.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/pinpaileibie")
public class PinpaileibieController {

    @Autowired
    private PinpaileibieService pinpaileibieService;


    /**
     * 前端列表
     * @param params
     * @param pinpaileibie
     * @return
     */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, PinpaileibieEntity pinpaileibie) {
        EntityWrapper<PinpaileibieEntity> ew = new EntityWrapper<>();
        PageUtils pageUtils = pinpaileibieService.selectListView(params, MPUtil.likeOrEq(ew, pinpaileibie));
        return R.ok().put("data", pageUtils);
    }

    /**
     * 查询
     * @param pinpaileibie
     * @param pinpaileibie
     * @return
     */
    @RequestMapping("/query")
    public R query(PinpaileibieEntity pinpaileibie) {
        EntityWrapper<PinpaileibieEntity> ew = new EntityWrapper<>();
        ew.allEq(MPUtil.allEQMapPre(pinpaileibie,"pinpaileibie"));
        PinpaileibieEntity pinpaileibie1 = pinpaileibieService.selectOne(ew);
        return R.ok().put("data", pinpaileibie1);
    }
    /**
     * 前端信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request) {
        PinpaileibieEntity pinpaileibie = pinpaileibieService.selectById(id);
        return R.ok().put("data", pinpaileibie);
    }

    /**
     * 品牌列别列表
     * @param params
     * @param pinpaileibie
     * @return
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, PinpaileibieEntity pinpaileibie){
        EntityWrapper<PinpaileibieEntity> ew = new EntityWrapper<>();
        PageUtils pageUtils = pinpaileibieService.selectListView(params, MPUtil.likeOrEq(ew, pinpaileibie));
        return R.ok().put("data", pageUtils);
    }
    /**
     * 信息
     * @param id
     * @return
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        PinpaileibieEntity pinpaileibie = pinpaileibieService.selectById(id);
        return R.ok().put("data", pinpaileibie);
    }

    /**
     * 保存
     * @param pinpaileibie
     * @return
     */
    @RequestMapping("/save")
    public R save(@RequestBody  PinpaileibieEntity pinpaileibie){

        if (pinpaileibieService.selectOne(new EntityWrapper<PinpaileibieEntity>().eq("pinpaileibie", pinpaileibie.getPinpaileibie())) != null) {
            return R.error("此类别已存在");
        }
        pinpaileibieService.insert(pinpaileibie);
        return R.ok();
    }

    /**
     * 修改
     * @param pinpaileibie
     * @return
     */
    @RequestMapping("/update")
    public R update(@RequestBody PinpaileibieEntity pinpaileibie){


        //正常更新
        pinpaileibieService.updateById(pinpaileibie);
        return R.ok();
    }

    /**
     * 删除
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        pinpaileibieService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

}
