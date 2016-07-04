package com.cmbc.most.model;

import lombok.Data;

import java.util.Date;

/**
 * Created by Yan on 16/5/27.
 * 规则表
 */
@Data
public class AppSetting extends BaseEntity {

    /**
     *  应用来源
     */
    private Integer appId;

    /**
     * 发送次数
     */
    private Integer sendLimit;

    /**
     * 发送时间
     */
    private Date sendTime;

    /**
     * 静默开启时间
     */
    private String silenceStartTime;

    /**
     * 静默结束时间
     */
    private String silenceEndTime;

}
