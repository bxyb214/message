package com.cmbc.most.mq;

import com.cmbc.most.Application;
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
        mqGateway.send("testest");
    }
}
