package com.cmbc.most.mq;

import com.cmbc.most.config.JMSConfig;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * Created by Yan on 2016/5/21.
 */
@Named
@Transactional
@lombok.extern.slf4j.Slf4j
public class MQGateway {
    @Inject
    @Named("JmsTemplate")
    JmsTemplate jmsTemplate;

    @Inject
    private JMSConfig.MQProperties properties;

    @JmsListener(destination = "${cmbc.most.mq.incomingQueue}", containerFactory = "DefaultJmsListenerContainerFactory")
    public void onMessage(TextMessage message) throws JMSException {
        log.info("onMessage");
        log.debug("onMessage - Message: {}", message);
    }

    public void send(String message) {
        log.info("send");
        log.debug("send - Message: {}", message);
        jmsTemplate.convertAndSend(properties.getOutgoingQueue(), message);
    }
}
