package com.qf.service.impl;

import com.qf.mapper.CommonDao;
import com.qf.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class CommonServiceImpl implements CommonService {
    @Autowired
    private CommonDao commonDao;

    @Override
    public List<String> getOptions(Map<String, Object> map) {
        return commonDao.getOptions(map);
    }
}
