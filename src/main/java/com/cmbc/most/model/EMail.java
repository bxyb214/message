package com.cmbc.most.model;


import lombok.Data;

import javax.persistence.Table;

/**
 * Created by Yan on 16/5/25.
 */

@Table(name = "email")
@Data
public class EMail extends MessageEntity {
    /**
     * 标题
     */
    private String mailSubject;

    /**
     * 发件人
     */
    private String mailFrom;

    /**
     * 接受人  to&cc&bcc
     */
    private String mailTo;

    /**
     * 邮件体
     */
    private String mailBody;
}
