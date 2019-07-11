package com.czbank.easylife.controller;

import com.czbank.easylife.model.Demo;
import com.czbank.easylife.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DemoController {
    @Autowired
    private DemoService demoService;

    @RequestMapping(value = "findDemoById/{demoId}", method = RequestMethod.GET)
    public @ResponseBody
    Demo findDemoById(Model model,
                            HttpServletRequest request,
                            @PathVariable String demoId) throws Exception {
        Demo demo = demoService.findBunkById(demoId);
        return demo;
    }

    @RequestMapping(value = "all/{demoId}", method = RequestMethod.GET)
    public @ResponseBody
    List<Demo> findAll(Model model,
                      HttpServletRequest request) throws Exception {
        List<Demo> demos = demoService.findAll();
        return demos;
    }

}
