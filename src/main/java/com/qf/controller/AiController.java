package com.qf.controller;

import com.qf.entity.ApiResponse;
import com.qf.utils.BaiduUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/ai")
public class AiController {

    /** 单条问题最大长度 */
    private static final int MAX_MSG_LEN = 500;

    /*
     * 百度千帆：多轮对话（前端传 message + history，后端按 role/content 拼 messages）
     */
    @RequestMapping("/chat")
    public ApiResponse<String> baiduAskai(@RequestBody Map<String, Object> body) {
        // 1. 取当前问题并做长度限制（前端 maxlength 只做体验，后端是权威）
        Object msgObj = body.get("message");
        String message = msgObj == null ? "" : msgObj.toString().trim();
        if (message.length() > MAX_MSG_LEN) {
            return ApiResponse.fail("问题太长了，请精简到" + MAX_MSG_LEN + "字以内");
        }

        // 2. 组装多轮历史（role/content），前端已把当前问题 push 进 history
        List<Map<String, String>> messages = new ArrayList<>();
        Object history = body.get("history");
        if (history instanceof List) {
            for (Object o : (List<?>) history) {
                if (o instanceof Map) {
                    Map<?, ?> m = (Map<?, ?>) o;
                    Object role = m.get("role");
                    Object content = m.get("content");
                    Map<String, String> item = new HashMap<>();
                    item.put("role", role == null ? "user" : role.toString());
                    item.put("content", content == null ? "" : content.toString());
                    messages.add(item);
                }
            }
        }

        // 3. 兜底：history 缺失时至少带当前问题，保证能正常对话
        if (messages.isEmpty()) {
            Map<String, String> cur = new HashMap<>();
            cur.put("role", "user");
            cur.put("content", message);
            messages.add(cur);
        }

        return ApiResponse.success(BaiduUtil.qfChat(messages));
    }
}
