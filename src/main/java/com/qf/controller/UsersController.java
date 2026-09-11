package com.qf.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.qf.annotation.IgnoreAuth;
import com.qf.entity.UsersEntity;
import com.qf.service.TokenService;
import com.qf.service.UsersService;
import com.qf.utils.CookieUtil;
import com.qf.utils.MPUtil;
import com.qf.utils.PageUtils;
import com.qf.utils.PasswordUtil;
import com.qf.utils.R;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public R login(String username, String password, String captcha, HttpServletRequest request, HttpServletResponse response) {
        //根据账号查询用户对象
        UsersEntity users = usersService.selectOne(new EntityWrapper<UsersEntity>().eq("username", username));
        if (users == null || !PasswordUtil.matches(password, users.getPassword())) {
            return R.error("账号或者密码不正确");
        }
        // 旧格式(明文/MD5)渐进迁移：老账号首次登录成功立即升级为 BCrypt 加盐哈希存储
        if (PasswordUtil.needsUpgrade(users.getPassword())) {
            users.setPassword(PasswordUtil.encode(password));
            usersService.updateById(users);
        }
        //正确就获取和刷新用户token 返回
        String token = tokenService.generateToken(users.getId(), username, "users", users.getRole());
        // Token 写入 HttpOnly Cookie（前端不再 localStorage 存 token；响应体中 token 保留用于兼容）
        CookieUtil.addTokenCookie(response, token);
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
        //密码加盐哈希
        users.setPassword(PasswordUtil.encode(users.getPassword()));
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
    public R logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        // 清除 HttpOnly Token Cookie
        CookieUtil.clearTokenCookie(response);
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
        users.setPassword(PasswordUtil.encode("123456"));
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
    @RequestMapping({"/save","/add"})
    public R save(@RequestBody UsersEntity users){

        if (usersService.selectOne(new EntityWrapper<UsersEntity>().eq("username", users.getUsername())) != null) {
            return R.error("此用户已存在");
        }
        // 新增用户密码统一加盐哈希（已是哈希则保持原样）
        if(StringUtils.isNotBlank(users.getPassword()) && !PasswordUtil.isAlreadyHashed(users.getPassword())){
            users.setPassword(PasswordUtil.encode(users.getPassword()));
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
        // 修改密码场景（前端 update-password 提交 oldPassword）：后端校验原密码
        if(StringUtils.isNotBlank(users.getOldPassword())){
            UsersEntity stored = usersService.selectById(users.getId());
            if(stored == null || !PasswordUtil.matches(users.getOldPassword(), stored.getPassword())){
                return R.error("原密码错误");
            }
        }
        // 密码字段：明文视为新密码加密存储；已是哈希(BCrypt/MD5)保持原样；空则不更新
        if(StringUtils.isNotBlank(users.getPassword()) && !PasswordUtil.isAlreadyHashed(users.getPassword())){
            users.setPassword(PasswordUtil.encode(users.getPassword()));
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
