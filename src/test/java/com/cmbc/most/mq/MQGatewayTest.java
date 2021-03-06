package com.cmbc.most.mq;

import com.cmbc.most.Application;
import com.cmbc.most.message.Message;
import com.ibm.mq.jms.MQConnectionFactory;
import com.ibm.msg.client.wmq.common.CommonConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.jms.JMSException;

import static org.junit.Assert.assertThat;
import static sun.nio.cs.Surrogate.is;

/**
 * Created by Yan on 2016/5/21.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@Transactional
@SpringApplicationConfiguration(Application.class)
public class MQGatewayTest {

    private static final Logger log = LoggerFactory.getLogger(MQGatewayTest.class);

    @Inject
    private MQGateway mqGateway;

    @Test
    public void testMessageSender(){

        Message message = new Message();
        message.setAppId(11111);
        message.setUuid("111111111111");
        mqGateway.send(message.toJson(message));
    }
}
