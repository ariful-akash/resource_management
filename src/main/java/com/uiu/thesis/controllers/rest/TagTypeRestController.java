/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uiu.thesis.controllers.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uiu.thesis.dao.interfaces.TagTypeDAO;
import com.uiu.thesis.models.forum.TagType;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ashif
 */
@RestController(value = "/api/service/forum/tag")
public class TagTypeRestController {

    @Autowired
    private TagTypeDAO tagTypeDAO;

    @RequestMapping(
            value = "/api/service/forum/tag/tags",
            method = RequestMethod.GET,
            produces = {"application/json;charset:UTF-8"})
    public String getAllTagService() {

        ObjectMapper objectMapper = new ObjectMapper();

        List<TagType> tagTypesList = tagTypeDAO.getAllTagTypes();

        if (tagTypesList != null && tagTypesList.size() > 0) {

            try {

                return objectMapper.writeValueAsString(tagTypesList);
            } catch (JsonProcessingException ex) {

                System.err.println(ex.toString());
            }
        }

        return "[]";
    }
}
