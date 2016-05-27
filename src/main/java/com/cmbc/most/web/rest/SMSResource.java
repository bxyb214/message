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

package com.cmbc.most.web.rest;

import com.cmbc.most.model.SMS;
import com.cmbc.most.service.SMSService;
import com.cmbc.most.web.rest.util.PaginationUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import java.net.URISyntaxException;
import java.util.List;

/**
 * @author Yan
 * @since 2015-12-19 11:10
 */
@Controller
@RequestMapping("/api")
public class SMSResource {

    @Inject
    private SMSService smsService;

    /**
     * GET  /smss -> 获取一页短信.
     */
    @RequestMapping(value = "/smss",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SMS>> getAll(SMS sms) throws URISyntaxException {
        List<SMS> smsList = smsService.getAll(sms);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(new PageInfo<>(smsList), "/api/countries");
        return new ResponseEntity<>(smsList, headers, HttpStatus.OK);
    }
}
