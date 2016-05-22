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

import com.cmbc.most.web.rest.util.HeaderUtil;
import com.cmbc.most.web.rest.util.PaginationUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Timed;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.cmbc.most.model.UserInfo;
import com.cmbc.most.service.UserInfoService;

import java.net.URISyntaxException;
import java.util.List;

/**
 * @author Yan
 * @since 2015-12-19 11:10
 */
@RestController
@RequestMapping("/api")
public class UserInfoResource {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * GET  /users -> get all of users.
     */
    @RequestMapping(value = "/users",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserInfo>> getAll(UserInfo userInfo) throws URISyntaxException {
        List<UserInfo> userInfoList = userInfoService.getAll(userInfo);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(new PageInfo<>(userInfoList), "/api/users");
        return new ResponseEntity<>(userInfoList, headers, HttpStatus.OK);
    }

    /**
     * GET  /user -> Get a new user.
     */
    @RequestMapping(value = "/user/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public UserInfo view(@PathVariable Integer id) {
        UserInfo userInfo = userInfoService.getById(id);
        return userInfo;
    }

    /**
     * DELETE  /user -> Delete a  user.
     */
    @RequestMapping(value = "/user/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        userInfoService.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("User", id.toString())).build();
    }


    /**
     * POST  /user -> Create a new user.
     */
    @RequestMapping(value = "/user",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> add(UserInfo userInfo) {
        userInfoService.save(userInfo);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * PUT  /user -> update a user.
     */
    @RequestMapping(value = "/user",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(UserInfo userInfo) {
        if(userInfo.getId() == null){
           return add(userInfo);
        }

        userInfoService.save(userInfo);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert("User", userInfo.getId().toString())).build();
    }
}
