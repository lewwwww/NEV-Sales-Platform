package com.qf.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.qf.annotation.IgnoreAuth;
import com.qf.entity.UsersEntity;
import com.qf.entity.YonghuEntity;
import com.qf.service.TokenService;
import com.qf.service.YonghuService;
import com.qf.utils.MD5Util;
import com.qf.utils.MPUtil;
import com.qf.utils.PageUtils;
import com.qf.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/yonghu")
public class YonghuController {
    @Autowired
    private YonghuService yonghuService;
    @Autowired
    private TokenService tokenService ;
    /**
     * 用户登录iu
     * @param username
     * @param password
     * @param request
     * @return
     */
    @IgnoreAuth
    @RequestMapping("/login")
    public R login(String username, String password, HttpServletRequest request){
        YonghuEntity yonghu = yonghuService.selectOne(new EntityWrapper<YonghuEntity>().eq("zhanghao", username));
        if(yonghu == null || !yonghu.getMima().equals(MD5Util.md5(password))){
            return R.error("账号或者密码不正确");
        }
        String token = tokenService.generateToken(yonghu.getId(), username, "yonghu", "用户");
        return R.ok().put("token", token);

    }

    /**
     * 注册
     * @param yonghu
     * @return
     */
    @IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody YonghuEntity yonghu){
        YonghuEntity yonghu1 = yonghuService.selectOne(new EntityWrapper<YonghuEntity>().eq("zhanghao", yonghu.getZhanghao()));
        if(yonghu1 != null){
            return R.error("此用户已存在");
        }
        long Uid = new Date().getTime();
        yonghu.setId(Uid);
        //密码加密
        yonghu.setMima(MD5Util.md5(yonghu.getMima()));
        //操作数据库
        yonghuService.insert(yonghu);
        return R.ok();

    }

    /**
     * 退出
     * @param request
     * @return
     */
    @RequestMapping("/logout")
    public R logout(HttpServletRequest request){
        request.getSession().invalidate();
        return R.ok();
    }

    /**
     * 密码重置
     * @param username
     * @param request
     * @return
     */
    @RequestMapping("/resetPass")
    public R resetPass(String username,HttpServletRequest request){
        YonghuEntity yonghu1 = yonghuService.selectOne(new EntityWrapper<YonghuEntity>().eq("zhanghao", username));
        if(yonghu1 == null){
            return R.error("账号不存在");
        }
        //设置初始密码
        yonghu1.setMima(MD5Util.md5(MD5Util.md5("123456")));
        yonghuService.updateById(yonghu1);
        return R.ok();

    }

    /**
     * 个人信息
     * @param request
     * @return
     */
    @RequestMapping("/session")
    public R session(HttpServletRequest request){
        Long yid = (Long)request.getSession().getAttribute("userId");
        YonghuEntity yonghu = yonghuService.selectById(yid);
        if(yonghu == null){
            return R.error("此用户不存在");
        }
        return R.ok().put("data", yonghu);
    }
    /**
     * 前端列表
     * @param params
     * @param yonghu
     * @return
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, YonghuEntity yonghu) {
        EntityWrapper<YonghuEntity> ew = new EntityWrapper<>();
        PageUtils pageUtils = yonghuService.selectListView(params, MPUtil.likeOrEq(ew, yonghu));
        return R.ok().put("data", pageUtils);
    }

    /**
     * 查询
     * @param yonghu
     * @return
     */
    @RequestMapping("/query")
    public R query(YonghuEntity yonghu){
        EntityWrapper<YonghuEntity> ew = new EntityWrapper<>();
        ew.allEq(MPUtil.allEQMapPre(yonghu,"yonghu"));
        YonghuEntity yh = yonghuService.selectOne(ew);
        return R.ok("查询用户成功").put("data", yh);
    }

    /**
     * 前端详情信息
     * @param id
     * @return
     */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        YonghuEntity users = yonghuService.selectById(id);
        return R.ok().put("data", users);
    }



    /**
     * 后端列表
     * @param params
     * @param yonghu
     * @return
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, YonghuEntity yonghu) {
        EntityWrapper<YonghuEntity> ew = new EntityWrapper<>();
        PageUtils pageUtils = yonghuService.selectListView(params, MPUtil.likeOrEq(ew, yonghu));
        return R.ok().put("data", pageUtils);
    }

    /**
     * 信息
     * @param id
     * @return
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        YonghuEntity users = yonghuService.selectById(id);
        return R.ok().put("data", users);
    }

    /**
     * 保存
     * @param users
     * @return
     */
    @RequestMapping("/save")
    public R save(@RequestBody YonghuEntity users){

        if (yonghuService.selectOne(new EntityWrapper<YonghuEntity>().eq("zhanghao", users.getZhanghao())) != null) {
            return R.error("此用户已存在");
        }
        yonghuService.insert(users);
        return R.ok();
    }

    /**
     * 修改
     * @param users
     * @return
     */
    @RequestMapping("/update")
    public R update(@RequestBody YonghuEntity users){
        //查重
        YonghuEntity u = yonghuService.selectOne(new EntityWrapper<YonghuEntity>().eq("zhanghao", users.getZhanghao()));

        //正常更新
        yonghuService.updateById(users);
        return R.ok();
    }

    /**
     * 删除
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        yonghuService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


}
