package com.yupi.springbootinit.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.Arrays;

@SpringBootTest
class ChartMapperTest {

    @Resource
    private ChartMapper chartMapper;

    @Test
    void createTable() {
//        chartMapper.createTable("chart_1234", Arrays.asList("日期", "人数"));
        chartMapper.insertValues("chart_1234",
                Arrays.asList(Arrays.asList("1号", "10"), Arrays.asList("2号", "20")));
    }
}