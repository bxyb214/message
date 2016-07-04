package com.cmbc.most.config;

import com.cmbc.most.message.jmx.client.CMBCConnectionFactory;
import com.cmbc.most.message.jmx.client.CMBCMQProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

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
        CMBCConnectionFactory cmbcConnectionFactory = null;

        try {
            cmbcConnectionFactory = new CMBCConnectionFactory();
            CMBCMQProperties cmbcmqProperties = new CMBCMQProperties();
            cmbcmqProperties.setHost(properties.getHost());
            cmbcmqProperties.setPort(properties.getPort());
            cmbcmqProperties.setQueueManager(properties.getQueueManager());
            cmbcmqProperties.setChannel(properties.getChannel());
            cmbcmqProperties.setCCSID(properties.getCCSID());
            cmbcConnectionFactory.setCmbcmqProperties(cmbcmqProperties);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
        return cmbcConnectionFactory;
    }

    @Bean(name = "JmsTemplate")
    public JmsTemplate provideJmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory());
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
