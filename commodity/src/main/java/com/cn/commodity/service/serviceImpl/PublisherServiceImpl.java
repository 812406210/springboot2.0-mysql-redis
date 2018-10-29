package com.cn.commodity.service.serviceImpl;

import com.cn.commodity.service.PublisherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service("publisherService")
public class PublisherServiceImpl implements PublisherService {
    private static final Logger log = LoggerFactory.getLogger(PublisherServiceImpl.class);
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public String sendMessage(String name) {
        try {
            redisTemplate.convertAndSend("TOPIC_USERNAME", name);
            return "消息发送成功了";

        } catch (Exception e) {
            e.printStackTrace();
            return "消息发送失败了";
        }
    }

}
