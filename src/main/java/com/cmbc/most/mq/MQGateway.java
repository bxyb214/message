package com.cmbc.most.mq;

import com.cmbc.most.config.JMSConfig;
import com.cmbc.most.message.Message;
import com.cmbc.most.model.MessageEntity;
import com.cmbc.most.model.AppSetting;
import com.cmbc.most.model.UserSetting;
import com.cmbc.most.service.MessageService;
import com.cmbc.most.service.AppSettingService;
import com.cmbc.most.service.UserSettingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.io.IOException;
import java.util.List;

/**
 * Created by Yan on 2016/5/21.
 */

@Named
@Transactional
@lombok.extern.slf4j.Slf4j
public class MQGateway {

    @Inject
    JmsTemplate jmsTemplate;

    @Inject
    ObjectMapper objectMapper;

    @Inject
    MessageService messageService;

    @Inject
    AppSettingService appSettingService;

    @Inject
    UserSettingService userSettingService;

    @Inject
    private JMSConfig.MQProperties properties;

    @JmsListener(destination = "${cmbc.most.mq.incomingQueue}", containerFactory = "DefaultJmsListenerContainerFactory")
    public void onMessage(TextMessage mqMessage) throws JMSException {
        log.info("onMessage - MessageEntity: {}", mqMessage.getText());
        //1. 转成对象
        Message message = Message.parseMessage(mqMessage.getText());

        //2. TODO 服务端检查消息, 如uuid, appid

        //3. 应用规则
        Boolean needSend = true;         //发送标志

        // 重要消息 必须要发
        if(message.getImportant()) {

            //app 规则
            Integer appId = message.getAppId();
            AppSetting appSetting = new AppSetting();
            appSetting.setAppId(appId);
            List<AppSetting> appSettings = appSettingService.getAll(appSetting);

            //TODO 遍历APPCONFIG列表, 如果在静默时间段, 设置重发状态未发送
            for(AppSetting as : appSettings){

//                    message.setStatus(2); //未发送
//                    needSend = false;
            }


            //User规则
            UserSetting userSetting = new UserSetting();
            userSetting.setAppId(appId);
            List<UserSetting> userSettings = userSettingService.getAll(userSetting);

            //TODO 遍历USERCONFIG列表, 如果拒绝接收发送, 设置状态为无效
            for (UserSetting us : userSettings){

//                    message.setStatus(0); //未发送
//                    needSend = false;
            }
        }

        //4 发送消息
        // TODO 发送消息,
        if (needSend){

        }



        //5.持久化消息
        // TODO 修改发送状态
        messageService.save(message);


    }

    public void send(String message) {
        log.info("send");
        log.debug("send - MessageEntity: {}", message);
        jmsTemplate.convertAndSend(properties.getOutgoingQueue(), message);
    }
}
