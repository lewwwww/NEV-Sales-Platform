package com.qf.controller;

import com.qf.service.CommonService;
import com.qf.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class CommonController {
    @Autowired
    private CommonService commonService;

    /**
     * 公共下拉查询
     * @param table
     * @param column
     * @return
     */
    @RequestMapping("/option/{tableName}/{columnName}")
    public R getOptions(@PathVariable("tableName") String table,@PathVariable("columnName") String column){
        HashMap<String, Object> map = new HashMap<>();
        map.put("table", table);
        map.put("column", column);
        //调用commonService完成公共下拉数据查询
        List<String> options = commonService.getOptions(map);
        return R.ok().put("data", options);

    }
}
