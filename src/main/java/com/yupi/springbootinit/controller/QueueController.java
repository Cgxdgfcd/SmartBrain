package com.yupi.springbootinit.controller;

import cn.hutool.json.JSONUtil;
import com.github.rholder.retry.RetryException;
import com.github.rholder.retry.Retryer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 队列测试
 */
@RestController
@RequestMapping("/queue")
@Slf4j
@Profile({"dev", "local"})
public class QueueController {

    @Resource
    private ThreadPoolExecutor threadPoolExecutor;
    @Resource
    private Retryer retryer;

    @GetMapping("/add")
    public void add(String name){
        CompletableFuture.runAsync(() -> {
            try {
                retryer.call(() -> {
                    log.info("任务执行中" + name + "  执行人：" + Thread.currentThread().getName());
                    perform(name);
                    return true;
                });
            } catch (ExecutionException | RetryException e) {
                log.error("任务执行失败:" + name, e);
            }
        }, threadPoolExecutor);
    }

    void perform(String name) {
        System.out.println("正在执行:" + name);
        if (Math.random() < 0.5) {
            throw new RuntimeException("操作失败");
        }
        try {
            Thread.sleep(600000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/get")
    public String get(){
        HashMap<String, Object> map = new HashMap<>();
        int size = threadPoolExecutor.getQueue().size();
        map.put("队列长度", size);
        long taskCount = threadPoolExecutor.getTaskCount();
        map.put("任务数量", taskCount);
        long completedTaskCount = threadPoolExecutor.getCompletedTaskCount();
        map.put("已完成任务数", completedTaskCount);
        int activeCount = threadPoolExecutor.getActiveCount();
        map.put("正在工作的线程数", activeCount);

        return JSONUtil.toJsonStr(map);
    }
}
