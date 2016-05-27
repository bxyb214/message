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

import com.cmbc.most.mapper.SMSMapper;
import com.cmbc.most.model.SMS;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Yan
 * @since 2015-12-19 11:09
 */
@Service
public class SMSService {

    @Inject
    private SMSMapper smsMapper;

    public List<SMS> getAll(SMS sms) {
        if (sms.getPage() != null && sms.getRows() != null) {
            PageHelper.startPage(sms.getPage(), sms.getRows(), "id");
        }
        return smsMapper.selectAll();
    }

    public SMS getById(Integer id) {
        return smsMapper.selectByPrimaryKey(id);
    }

    public void deleteById(Integer id) {
        smsMapper.deleteByPrimaryKey(id);
    }

    public void save(SMS sms) {
        if (sms.getId() != null) {
            smsMapper.updateByPrimaryKey(sms);
        } else {
            smsMapper.insert(sms);
        }
    }
}
