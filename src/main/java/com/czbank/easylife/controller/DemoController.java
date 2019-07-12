package com.czbank.easylife.controller;

import com.czbank.easylife.model.Demo;
import com.czbank.easylife.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/demo/")
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

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public @ResponseBody
    List<Demo> findAll(Model model,
                      HttpServletRequest request) throws Exception {
        System.out.println("1111111111");
        List<Demo> demos = demoService.findAll();
        return demos;
    }

}
