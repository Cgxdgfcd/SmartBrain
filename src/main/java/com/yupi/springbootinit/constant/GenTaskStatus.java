package com.yupi.springbootinit.constant;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * AI生成任务状态常量
 */
@Getter
public enum GenTaskStatus {
    WAIT("wait", 1), RUNNING("running", 2), SUCCEED("succeed", 3), FAILED("failed", 4);

    @JsonValue
    private final String desc;
    @EnumValue
    private final Integer value;

    GenTaskStatus(String desc, Integer value){
        this.desc = desc;
        this.value = value;
    }

}
