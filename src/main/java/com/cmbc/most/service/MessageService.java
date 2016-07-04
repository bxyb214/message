/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2016 abel533@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.cmbc.most.service;

import com.cmbc.most.mapper.MessageMapper;
import com.cmbc.most.message.Message;
import com.cmbc.most.model.MessageEntity;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Yan
 * @since 2015-12-19 11:09
 */
@Service
public class MessageService {

    @Inject
    private MessageMapper MessageMapper;

    public List<MessageEntity> getAll(MessageEntity message) {
        if (message.getPage() != null && message.getRows() != null) {
            PageHelper.startPage(message.getPage(), message.getRows(), "id");
        }
        return MessageMapper.selectAll();
    }

    public MessageEntity getById(Integer id) {
        return MessageMapper.selectByPrimaryKey(id);
    }

    public void deleteById(Integer id) {
        MessageMapper.deleteByPrimaryKey(id);
    }

    public void save(MessageEntity Message) {
        if (Message.getId() != null) {
            MessageMapper.updateByPrimaryKey(Message);
        } else {
            MessageMapper.insert(Message);
        }
    }

    public void save(Message message) {

        MessageEntity messageEntity = new MessageEntity(message);

        if (messageEntity.getId() != null) {
            MessageMapper.updateByPrimaryKey(messageEntity);
        } else {
            MessageMapper.insert(messageEntity);
        }
    }
}
