package com.qf.controller;

import com.qf.entity.ApiResponse;
import com.qf.utils.BaiduUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin
@RestController
@RequestMapping("/api/ai")
public class AiController {

    /*
     * 百度千帆
     */
    @RequestMapping("/chat")
    public ApiResponse<String> baiduAskai(@RequestBody String message) {
        System.out.println("进入ai");
        return ApiResponse.success(BaiduUtil.qfChat(message));

    }

}
