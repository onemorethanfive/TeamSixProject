package com.czbank.easylife.service;

import com.czbank.easylife.mapper.DemoMapper;
import com.czbank.easylife.model.Demo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DemoService {
    @Autowired
    private DemoMapper demoMapper;

    public Demo findBunkById(String demoId) {
        Demo demo = demoMapper.findDemoById(demoId);
        return demo;
    }

    public List<Demo> findAll() {
        List<Demo> demos = demoMapper.findAll();
        return demos;
    }
}
