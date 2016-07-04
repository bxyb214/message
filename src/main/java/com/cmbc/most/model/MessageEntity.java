package com.cmbc.most.model;

import com.cmbc.most.message.Message;
import lombok.Data;

import java.util.Date;

/**
 * Created by Yan on 16/5/27.
 */
@Data
public class MessageEntity extends BaseEntity {

    public MessageEntity(){}

    public MessageEntity(Message message) {
        this.appId = message.getAppId();
        this.uuid = message.getUuid();
        this.createdDate = message.getCreatedDate();
        this.saveDate = message.getSaveDate();
        this.sendDate = message.getSendDate();
        this.sendNumber = message.getSendNumber();
        this.send = message.getSend();
        this.important = message.getImportant();
        this.channel = message.getChannel();
        this.status = message.getStatus();
        this.subject = message.getSubject();
        this.from = message.getFrom();
        this.recevier = message.getRecevier();
        this.body = message.getBody();
        this.errorCode = message.getErrorCode();
        this.errorDescription = message.getErrorDescription();
    }



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
    private Date saveDate;

    /**
     * 最后一次发送时间
     */
    private Date sendDate;

    /**
     * 发送次数
     */
    private Integer sendNumber;

    /**
     * 成功与否
     */
    private Boolean send;

    /**
     * 重要消息, 强制发送
     */
    private Boolean important;

    /**
     * 发送渠道
     * 二进制 1 1 1
     */
    private Integer channel;

    /**
     * 状态
     * 2 表示 未发送
     * 1 表示 已发送
     * 0 表示 无效
     */
    private Integer status;

    /**
     * 邮件标题
     */
    private String subject;

    /**
     * 邮件发件人
     */
    private String from;

    /**
     * 邮件接受人  to&cc&bcc
     */
    private String recevier;

    /**
     * 邮件邮件体
     */
    private String body;

    /**
     * 错误code
     */
    private String errorCode;

    /**
     * 错误描述
     */
    private String errorDescription;


}
