package com.cmbc.most.model;

import lombok.Data;

import java.util.Date;

/**
 * Created by Yan on 16/5/27.
 */
@Data
public class MessageEntity extends BaseEntity {

    /**
     * UUID
     * 16位唯一标识, 通过客户端工具包生成,由APP,时间等信息生成
     */
    private String uuid;

    /**
     *  应用来源
     */
    private Integer appId;

    /**
     * 生成时间
     */
    private Date createdDate;

    /**
     * 写入时间
     */
    private Date sendDate;

    /**
     * 规则ID
     */
    private Integer procedureId;

    /**
     * 成功个数
     */
    private Integer success;

    /**
     * 失败个数
     */
    private Integer fail;

    /**
     * 状态
     */
    private Integer status;
}
