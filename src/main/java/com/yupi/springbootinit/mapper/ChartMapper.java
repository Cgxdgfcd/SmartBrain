package com.yupi.springbootinit.mapper;

import com.yupi.springbootinit.model.entity.Chart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface ChartMapper extends BaseMapper<Chart> {

    List<Map<String, Object>> queryChartData(String querySql);

    void createTable(@Param("newTableName") String newTableName,
                     @Param("fieldNameList") List<String>fieldNameList);

    boolean insertValues(@Param("tableName") String tableName,
                      @Param("values")List<Collection<String>> values);
}




