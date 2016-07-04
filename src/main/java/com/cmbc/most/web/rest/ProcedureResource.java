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

import com.cmbc.most.model.AppSetting;
import com.cmbc.most.service.AppSettingService;
import com.cmbc.most.web.rest.util.HeaderUtil;
import com.cmbc.most.web.rest.util.PaginationUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.net.URISyntaxException;
import java.util.List;

/**
 * @author Yan
 * @since 2015-12-19 11:10
 */
@RestController
@RequestMapping("/api")
public class ProcedureResource {

    @Inject
    private AppSettingService procedureService;

    /**
     * GET  /procedures -> 获取所有规则.
     */
    @RequestMapping(value = "/procedures",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AppSetting>> getAll(AppSetting procedure) throws URISyntaxException {
        List<AppSetting> procedureList = procedureService.getAll(procedure);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(new PageInfo<>(procedureList), "/api/procedures");
        return new ResponseEntity<>(procedureList, headers, HttpStatus.OK);
    }

    /**
     * GET  /procedure/{id} -> 通过ID获取单个规则.
     */
    @RequestMapping(value = "/procedure/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public AppSetting view(@PathVariable Integer id) {
        AppSetting procedure = procedureService.getById(id);
        return procedure;
    }

    /**
     * DELETE  /procedure -> 删除某个规则
     */
    @RequestMapping(value = "/procedure/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        procedureService.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("规则", id.toString())).build();
    }


    /**
     * POST  /procedure -> 创建某个规则.
     */
    @RequestMapping(value = "/procedure",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> add(AppSetting procedure) {
        procedureService.save(procedure);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * PUT  /procedure -> 更新某个规则.
     */
    @RequestMapping(value = "/procedure",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(AppSetting procedure) {
        if(procedure.getId() == null){
           return add(procedure);
        }

        procedureService.save(procedure);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert("规则", procedure.getId().toString())).build();
    }
}
