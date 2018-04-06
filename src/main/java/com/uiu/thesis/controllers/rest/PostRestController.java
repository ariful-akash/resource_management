package com.uiu.thesis.controllers.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ashif
 */
@RestController
public class PostRestController {

    @RequestMapping(value = "/test/post")
    public String testPostService() {

        return "";
    }
}
