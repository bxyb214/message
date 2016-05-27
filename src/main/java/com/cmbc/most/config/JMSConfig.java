package com.cmbc.most.config;

import com.ibm.mq.jms.MQConnectionFactory;
import com.ibm.mq.jms.MQQueueConnectionFactory;
import com.ibm.mq.jms.MQXAConnectionFactory;
import com.ibm.msg.client.wmq.WMQConstants;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Session;
/**
 * Created by Yan on 2016/5/21.
 */
@Configuration
@EnableConfigurationProperties(JMSConfig.MQProperties.class)
@EnableJms
public class JMSConfig {

    @Inject
    JMSConfig.MQProperties properties;

    @Bean
    public ConnectionFactory connectionFactory() {
        MQQueueConnectionFactory factory = null;
        try {
            factory = new MQQueueConnectionFactory();
            factory.setHostName(properties.getHost());
            factory.setPort(properties.getPort());
            factory.setQueueManager(properties.getQueueManager());
            factory.setChannel(properties.getChannel());
            factory.setCCSID(properties.getCCSID());
            factory.setTransportType(WMQConstants.WMQ_CM_CLIENT);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }

        return factory;
    }

    @Bean(name = "JmsTemplate")
    public JmsTemplate provideJmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory());
        jmsTemplate.setPubSubNoLocal(false);
        return jmsTemplate;
    }

    //监听队列
    @Bean(name = "DefaultJmsListenerContainerFactory")
    public DefaultJmsListenerContainerFactory defaultJmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setConcurrency("5-10");
        factory.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
        return factory;
    }

    @ConfigurationProperties(prefix = "cmbc.most.mq")
    @Data
    public static class MQProperties {
        String queueManager;
        String host;
        int port;
        String channel;
        int CCSID;
        String incomingQueue;
        String outgoingQueue;
    }



}
