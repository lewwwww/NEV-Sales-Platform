package com.qf.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.qf.annotation.IgnoreAuth;
import com.qf.entity.ChatEntity;
import com.qf.service.ChatService;
import com.qf.utils.MPUtil;
import com.qf.utils.PageUtils;
import com.qf.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private ChatService chatService;

    /**
     * 后端分页列表（管理员）- 在线咨询管理
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, ChatEntity chat) {
        EntityWrapper<ChatEntity> ew = new EntityWrapper<>();
        // 如果传入了isreply参数，进行筛选
        if (params.get("isreply") != null) {
            ew.eq("isreply", params.get("isreply"));
        }
        // 如果传入了userid参数，进行筛选（用于查看某个用户的所有聊天记录）
        if (params.get("userid") != null) {
            ew.eq("userid", params.get("userid"));
        }
        PageUtils pageUtils = chatService.queryPage(params, MPUtil.likeOrEq(ew, chat));
        return R.ok().put("data", pageUtils);
    }

    /**
     * 前端列表（用户查看自己的咨询记录）
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, ChatEntity chat, HttpServletRequest request) {
        EntityWrapper<ChatEntity> ew = new EntityWrapper<>();
        // 普通用户只能查看自己的咨询记录
        String tableName = request.getSession().getAttribute("tableName") == null ? "" : request.getSession().getAttribute("tableName").toString();
        if (tableName.equals("yonghu")) {
            Long userId = (Long) request.getSession().getAttribute("userId");
            ew.eq("userid", userId);
        }
        // 如果传入了isreply参数，进行筛选
        if (params.get("isreply") != null) {
            ew.eq("isreply", params.get("isreply"));
        }
        ew.orderBy("addtime", false);
        PageUtils pageUtils = chatService.queryPage(params, MPUtil.likeOrEq(ew, chat));
        return R.ok().put("data", pageUtils);
    }

    /**
     * 详情/信息查询（管理员）
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request) {
        ChatEntity chat = chatService.selectById(id);
        return R.ok().put("data", chat);
    }

    /**
     * 前端详情查询（用户查看自己的咨询详情）
     */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request) {
        ChatEntity chat = chatService.selectById(id);
        // 普通用户只能查看自己的咨询记录
        String tableName = request.getSession().getAttribute("tableName") == null ? "" : request.getSession().getAttribute("tableName").toString();
        if (tableName.equals("yonghu")) {
            Long userId = (Long) request.getSession().getAttribute("userId");
            if (!chat.getUserid().equals(userId)) {
                return R.error("无权查看此咨询记录");
            }
        }
        return R.ok().put("data", chat);
    }

    /**
     * 用户提问（保存咨询）
     * 前端有的页面请求路径为 /chat/add，这里兼容 /save 和 /add 两种写法
     */
    @RequestMapping({"/save","/add"})
    public R save(@RequestBody ChatEntity chat, HttpServletRequest request) {
        // 设置用户ID
        String tableName = request.getSession().getAttribute("tableName") == null ? "" : request.getSession().getAttribute("tableName").toString();
        if (tableName.equals("yonghu")) {
            Long userId = (Long) request.getSession().getAttribute("userId");
            chat.setUserid(userId);
        }
        // 默认未回复：1 表示未回复，0 表示已回复
        if (chat.getIsreply() == null) {
            chat.setIsreply(1);
        }
        chatService.insert(chat);
        return R.ok("咨询提交成功，等待回复");
    }

    /**
     * 管理员回复（更新咨询）
     */
    @RequestMapping("/update")
    public R update(@RequestBody ChatEntity chat) {
        // 如果管理员回复了，更新回复状态为已回复（0表示已回复，1表示未回复）
        if (chat.getReply() != null && !chat.getReply().isEmpty()) {
            chat.setIsreply(0);  // 0表示已回复
        }
        chatService.updateById(chat);
        return R.ok("回复成功");
    }

    /**
     * 删除咨询（管理员）
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        chatService.deleteBatchIds(Arrays.asList(ids));
        return R.ok("咨询删除成功");
    }

    /**
     * 统计数量（管理员）
     */
    @RequestMapping("/count")
    public R count(@RequestParam Map<String, Object> params, ChatEntity chat) {
        EntityWrapper<ChatEntity> ew = new EntityWrapper<>();
        if (params.get("isreply") != null) {
            ew.eq("isreply", params.get("isreply"));
        }
        int count = chatService.selectCount(MPUtil.likeOrEq(ew, chat));
        return R.ok().put("data", count);
    }
}
