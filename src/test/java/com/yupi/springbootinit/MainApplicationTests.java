package com.yupi.springbootinit;

import com.github.rholder.retry.Retryer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

/**
 * 主类测试
 *

 */
//@SpringBootTest
class MainApplicationTests {

    @Test
    void contextLoads() {
        // 创建一个示例List<Map<Integer, String>>
        List<Map<Integer, String>> listOfMaps = new ArrayList<>();

        Map<Integer, String> map1 = new HashMap<>();
        map1.put(1, "value1");
        map1.put(2, "value2");

        Map<Integer, String> map2 = new HashMap<>();
        map2.put(3, "value3");
        map2.put(4, "value4");

        listOfMaps.add(map1);
        listOfMaps.add(map2);

        // 使用Stream API转换
//        List<List<String>> list = (List<List<String>>)listOfMaps.stream()
//                .map(map -> map.values())
//                .collect(Collectors.toList());
        List<Collection<String>> list = listOfMaps.stream()
                .map(map -> map.values())
                .collect(Collectors.toList());

        // 输出结果
        System.out.println(list);
    }

}
