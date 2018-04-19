package com.uiu.thesis.controllers.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uiu.thesis.dao.interfaces.TagTypeDAO;
import com.uiu.thesis.dao.interfaces.TokenDAO;
import com.uiu.thesis.models.forum.TagType;
import java.util.List;
import javax.servlet.http.HttpSession;
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

    @Autowired
    private TokenDAO tokenDAO;

    /**
     * Return all the tags
     *
     * @param session
     * @return
     */
    @RequestMapping(
            value = "/api/service/forum/tag/tags",
            method = RequestMethod.GET,
            produces = {"application/json;charset:UTF-8"})
    public String getAllTagService(HttpSession session) {

        String token = (String) session.getAttribute("token");

        if (token != null && tokenDAO.isTokenExist(token)) {

            ObjectMapper objectMapper = new ObjectMapper();

            List<TagType> tagTypesList = tagTypeDAO.getAllTagTypes();

            if (tagTypesList != null && tagTypesList.size() > 0) {

                try {

                    return objectMapper.writeValueAsString(tagTypesList);
                } catch (JsonProcessingException ex) {

                    System.err.println(ex.toString());
                }
            }
        }

        return "[]";
    }
}
