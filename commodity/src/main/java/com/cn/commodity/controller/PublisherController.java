package com.cn.commodity.controller;

import com.cn.commodity.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("publisher")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @RequestMapping("{name}")
    public String sendMessage(@PathVariable("name") String name) {
        List<String> strLists = new ArrayList<>();
        for(int i =0 ;i<10;i++){
            String result =  publisherService.sendMessage(name+i);
            strLists.add(result);
        }
         return strLists.toString();
    }
}
