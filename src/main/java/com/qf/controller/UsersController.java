package com.qf.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.qf.annotation.IgnoreAuth;
import com.qf.entity.UsersEntity;
import com.qf.service.TokenService;
import com.qf.service.UsersService;
import com.qf.utils.MPUtil;
import com.qf.utils.PageUtils;
import com.qf.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;
    @Autowired
    private TokenService tokenService;

    /**
     * 登录
     *
     * @param username
     * @param password
     * @param captcha
     * @param request
     * @return
     */
    @IgnoreAuth
    @RequestMapping("/login")
    public R login(String username, String password, String captcha, HttpServletRequest request) {
        //根据账号查询用户对象
        UsersEntity users = usersService.selectOne(new EntityWrapper<UsersEntity>().eq("username", username));
        if (users == null || !users.getPassword().equals(password)) {
            return R.error("账号或者密码不正确");
        }
        //正确就获取和刷新用户token 返回
        String token = tokenService.generateToken(users.getId(), username, "users", users.getRole());
        return R.ok().put("token", token);

    }

    /**
     * 注册
     * @param users
     * @return
     */
    @IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody UsersEntity users){
        //查重
        if (usersService.selectOne(new EntityWrapper<UsersEntity>().eq("username", users.getUsername())) != null) {
            return R.error("此用户已存在");
        }
        //正常插入数据
        usersService.insert(users);
        return R.ok();
    }

    /**
     * 退出
     * @param request
     * @return
     */
    @RequestMapping("/logout")
    public R logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return R.ok("退出成功");
    }

    /**
     * 重置密码
     * @param username
     * @param request
     * @return
     */
    @IgnoreAuth
    @RequestMapping("/resetPass")
    public R resetPassword(String username,HttpServletRequest request) {
        UsersEntity users = usersService.selectOne(new EntityWrapper<UsersEntity>().eq("username", username));
        if (users == null) {
            return R.error("账号不存在");
        }
        users.setPassword("123456");
        usersService.update(users,null);
        return R.ok("密码已重置：123456");
    }

    /**
     * 分页
     * @param params
     * @param users
     * @return
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,UsersEntity users){
        //查询条件
        EntityWrapper<UsersEntity> ew = new EntityWrapper<>();
        //执行查询
        PageUtils pageUtils = usersService.queryPage(params, MPUtil.likeOrEq(ew, users));
        return R.ok().put("data", pageUtils);
    }

    /**
     * 信息
     * @param id
     * @return
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id,HttpServletRequest request){
        UsersEntity users = usersService.selectById(id);
        return R.ok().put("data", users);
    }

    /**
     * 保存
     * @param users
     * @return
     */
    @RequestMapping("/save")
    public R save(@RequestBody UsersEntity users){

        if (usersService.selectOne(new EntityWrapper<UsersEntity>().eq("username", users.getUsername())) != null) {
            return R.error("此用户已存在");
        }
        usersService.insert(users);
        return R.ok();
    }

    /**
     * 修改
     * @param users
     * @return
     */
    @RequestMapping("/update")
    public R update(@RequestBody UsersEntity users){
        //查重
        UsersEntity u = usersService.selectOne(new EntityWrapper<UsersEntity>().eq("username", users.getUsername()));
        if (u!=null && u.getId()!=users.getId() && u.getUsername().equals(users.getUsername())){
            return R.error("用户名已存在");
        }
        //正常更新
        usersService.updateById(users);
        return R.ok();
    }

    /**
     * 删除
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        usersService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    /**
     * session
     * @param request
     * @return
     */
    @RequestMapping("/session")
    public R session(HttpServletRequest request){
        Long userId = (Long) request.getSession().getAttribute("userId");
        UsersEntity users = usersService.selectById(userId);
        return R.ok().put("data", users);
    }


}
