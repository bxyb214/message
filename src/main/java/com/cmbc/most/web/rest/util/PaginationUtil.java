package com.cmbc.most.web.rest.util;

import com.github.pagehelper.PageInfo;
import org.springframework.http.HttpHeaders;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Utility class for handling pagination.
 *
 * <p>
 * Pagination uses the same principles as the <a href="https://developer.github.com/v3/#pagination">Github API</api>,
 * and follow <a href="http://tools.ietf.org/html/rfc5988">RFC 5988 (Link header)</a>.
 * </p>
 */
public class PaginationUtil {

    public static HttpHeaders generatePaginationHttpHeaders(PageInfo page, String baseUrl)
        throws URISyntaxException {

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", "" + page.getTotal());
        String link = "";
        if ((page.getPageNum() + 1) < page.getPageSize()) {
            link = "<" + (new URI(baseUrl +"?page=" + (page.getPageNum() + 1) + "&size=" + page.getSize())).toString() + ">; rel=\"next\",";
        }
        // prev link
        if ((page.getPageNum()) > 0) {
            link += "<" + (new URI(baseUrl +"?page=" + (page.getPageNum() - 1) + "&size=" + page.getSize())).toString() + ">; rel=\"prev\",";
        }
        // last and first link
        int lastPage = 0;
        if (page.getPageSize() > 0) {
            lastPage = page.getPageSize() - 1;
        }
        link += "<" + (new URI(baseUrl +"?page=" + lastPage + "&size=" + page.getSize())).toString() + ">; rel=\"last\",";
        link += "<" + (new URI(baseUrl +"?page=" + 0 + "&size=" + page.getSize())).toString() + ">; rel=\"first\"";
        headers.add(HttpHeaders.LINK, link);
        return headers;
    }
}
