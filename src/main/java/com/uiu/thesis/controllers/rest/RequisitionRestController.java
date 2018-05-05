package com.uiu.thesis.controllers.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uiu.thesis.dao.interfaces.HumanResourceDAO;
import com.uiu.thesis.dao.interfaces.RequisitionDAO;
import com.uiu.thesis.dao.interfaces.RequisitionTypeDAO;
import com.uiu.thesis.dao.interfaces.TokenDAO;
import com.uiu.thesis.models.forum.json.AdminComplainRequisitionJson;
import com.uiu.thesis.models.forum.json.RequisitionJson;
import com.uiu.thesis.models.requisition.Requisition;
import com.uiu.thesis.models.requisition.RequisitionType;
import com.uiu.thesis.models.user.AccessType;
import com.uiu.thesis.models.user.HumanResource;
import com.uiu.thesis.services.interfaces.RequisitionService;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ashif
 */
@RestController(value = "/api/service/office/requisition")
public class RequisitionRestController {

    @Autowired
    private RequisitionService requisitionService;

    @Autowired
    private RequisitionDAO requisitionDAO;

    @Autowired
    private RequisitionTypeDAO requisitionTypeDAO;

    @Autowired
    private TokenDAO tokenDAO;

    @Autowired
    private HumanResourceDAO humanResourceDAO;

    private SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");

    /**
     *
     * @param session
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/requisition",
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.GET)
    public String getAllRequisitins(HttpSession session) {

        String token = (String) session.getAttribute("token");

        if (token != null && tokenDAO.isTokenExist(token)) {

            long userId = tokenDAO.getUserId(token);
            if (humanResourceDAO.hasAccess(userId, (long) 19)) {

                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.setDateFormat(df);

                List<Requisition> requisitions = requisitionService.getAllRequisitions();

                if (requisitions != null && requisitions.size() > 0) {

                    try {

                        return objectMapper.writeValueAsString(requisitions);
                    } catch (JsonProcessingException ex) {

                        System.err.println(ex.toString());
                    }
                }
            }
        }

        return "[]";
    }

    /**
     *
     * @param year
     * @param month
     * @param session
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/requisition/admin/{year}/{month}",
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.GET)
    public String getAdminRequisitions(
            @PathVariable("year") String year,
            @PathVariable("month") String month,
            HttpSession session) {

        String token = (String) session.getAttribute("token");
        if (token != null && tokenDAO.isTokenExist(token)) {

            long userId = tokenDAO.getUserId(token);
            if (humanResourceDAO.hasAccess(userId, (long) 19)) {

                ObjectMapper objectMapper = new ObjectMapper();

                List<RequisitionType> requisitionTypes = requisitionTypeDAO.getAllRequisitionTypes();
                List<AdminComplainRequisitionJson> adminCRJs = new ArrayList<>();

                if (year.equals("All")) {

                    for (RequisitionType requisitionType : requisitionTypes) {

                        AdminComplainRequisitionJson adminCRJ = new AdminComplainRequisitionJson();

                        List<Requisition> requisitionsTrue = requisitionDAO.getRequisitionsByType(requisitionType.getId(), true);
                        List<Requisition> requisitionsFalse = requisitionDAO.getRequisitionsByType(requisitionType.getId(), false);

                        adminCRJ.setType(requisitionType.getType());

                        if (requisitionsTrue == null) {

                            adminCRJ.setSolved(0);
                        } else {

                            adminCRJ.setSolved(requisitionsTrue.size());
                        }

                        if (requisitionsFalse == null) {

                            adminCRJ.setUnsolved(0);
                        } else {

                            adminCRJ.setUnsolved(requisitionsFalse.size());
                        }

                        adminCRJs.add(adminCRJ);
                    }
                } else if (month.equals("All")) {

                    int yearInt;
                    try {

                        yearInt = Integer.parseInt(year);
                    } catch (NumberFormatException e) {

                        System.err.println(e.toString());
                        return "[]";
                    }

                    Calendar cal = Calendar.getInstance();

                    cal.set(Calendar.YEAR, yearInt);
                    cal.set(Calendar.MONTH, Calendar.JANUARY);
                    cal.set(Calendar.DATE, 1);
                    cal.set(Calendar.HOUR, 0);
                    cal.set(Calendar.MINUTE, 0);
                    cal.set(Calendar.SECOND, 0);

                    Date date = cal.getTime();

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    String from = sdf.format(date);

                    cal.clear();

                    cal.set(Calendar.YEAR, yearInt);
                    cal.set(Calendar.MONTH, Calendar.DECEMBER);
                    cal.set(Calendar.DATE, 31);
                    cal.set(Calendar.HOUR, 23);
                    cal.set(Calendar.MINUTE, 59);
                    cal.set(Calendar.SECOND, 59);

                    date = cal.getTime();

                    String to = sdf.format(date);

                    for (RequisitionType requisitionType : requisitionTypes) {

                        AdminComplainRequisitionJson adminCRJ = new AdminComplainRequisitionJson();

                        List<Requisition> requisitionsTrue = requisitionDAO.getRequisitionsByTypeDate(requisitionType.getId(), from, to, true);
                        List<Requisition> requisitionsFalse = requisitionDAO.getRequisitionsByTypeDate(requisitionType.getId(), from, to, false);

                        adminCRJ.setType(requisitionType.getType());

                        if (requisitionsTrue == null) {

                            adminCRJ.setSolved(0);
                        } else {

                            adminCRJ.setSolved(requisitionsTrue.size());
                        }

                        if (requisitionsFalse == null) {

                            adminCRJ.setUnsolved(0);
                        } else {

                            adminCRJ.setUnsolved(requisitionsFalse.size());
                        }

                        adminCRJs.add(adminCRJ);
                    }
                } else {

                    int yearInt;
                    try {

                        yearInt = Integer.parseInt(year);
                    } catch (NumberFormatException e) {

                        System.err.println(e.toString());
                        return "[]";
                    }

                    int monthInt;
                    int day = 0;

                    switch (month) {

                        case "January":
                            monthInt = 0;
                            day = 31;
                            break;

                        case "February":
                            monthInt = 1;
                            day = 28;
                            break;

                        case "March":
                            monthInt = 2;
                            day = 31;
                            break;

                        case "April":
                            monthInt = 3;
                            day = 30;
                            break;

                        case "May":
                            monthInt = 4;
                            day = 31;
                            break;

                        case "June":
                            monthInt = 5;
                            day = 30;
                            break;

                        case "July":
                            monthInt = 6;
                            day = 31;
                            break;

                        case "August":
                            monthInt = 7;
                            day = 31;
                            break;

                        case "September":
                            monthInt = 8;
                            day = 30;
                            break;

                        case "October":
                            monthInt = 9;
                            day = 31;
                            break;

                        case "November":
                            monthInt = 10;
                            day = 30;
                            break;

                        case "December":
                            monthInt = 11;
                            day = 31;
                            break;

                        default:
                            monthInt = 0;
                    }

                    Calendar cal = Calendar.getInstance();

                    cal.set(Calendar.YEAR, yearInt);
                    cal.set(Calendar.MONTH, monthInt);
                    cal.set(Calendar.DATE, 1);
                    cal.set(Calendar.HOUR, 0);
                    cal.set(Calendar.MINUTE, 0);
                    cal.set(Calendar.SECOND, 0);

                    Date date = cal.getTime();

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    String from = sdf.format(date);

                    cal.clear();
                    cal.set(Calendar.YEAR, yearInt);
                    cal.set(Calendar.MONTH, monthInt);
                    cal.set(Calendar.DATE, day);
                    cal.set(Calendar.HOUR, 23);
                    cal.set(Calendar.MINUTE, 59);
                    cal.set(Calendar.SECOND, 59);

                    date = cal.getTime();

                    String to = sdf.format(date);

                    System.out.println(from);
                    System.out.println(to);

                    for (RequisitionType requisitionType : requisitionTypes) {

                        AdminComplainRequisitionJson adminCRJ = new AdminComplainRequisitionJson();

                        List<Requisition> requisitionsTrue = requisitionDAO.getRequisitionsByTypeDate(requisitionType.getId(), from, to, true);
                        List<Requisition> requisitionsFalse = requisitionDAO.getRequisitionsByTypeDate(requisitionType.getId(), from, to, false);

                        adminCRJ.setType(requisitionType.getType());

                        if (requisitionsTrue == null) {

                            adminCRJ.setSolved(0);
                        } else {

                            adminCRJ.setSolved(requisitionsTrue.size());
                        }

                        if (requisitionsFalse == null) {

                            adminCRJ.setUnsolved(0);
                        } else {

                            adminCRJ.setUnsolved(requisitionsFalse.size());
                        }

                        adminCRJs.add(adminCRJ);
                    }
                }

                try {

                    return objectMapper.writeValueAsString(adminCRJs);
                } catch (JsonProcessingException e) {

                    System.err.println(e.toString());
                }
            }
        }

        return "[]";
    }

    /**
     *
     * @param session
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/requisition/admin/years",
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.GET)
    public String getRequisitionYears(HttpSession session) {

        String token = (String) session.getAttribute("token");
        if (token != null && tokenDAO.isTokenExist(token)) {

            long userId = tokenDAO.getUserId(token);
            if (humanResourceDAO.hasAccess(userId, (long) 19)) {

                ObjectMapper objectMapper = new ObjectMapper();

                List<String> years = requisitionDAO.getYears();

                if (years != null && years.size() > 0) {

                    try {

                        return objectMapper.writeValueAsString(years);
                    } catch (JsonProcessingException e) {

                        System.err.println(e.toString());
                    }
                }
            }
        }

        return "[]";
    }

    /**
     *
     * @param requisitionJson
     * @param typeId
     * @param needDate
     * @param session
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/requisition",
            params = {"requisition", "type_id", "need_date"},
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.POST)
    public String addRequisition(
            @RequestParam("requisition") String requisitionJson,
            @RequestParam("type_id") long typeId,
            @RequestParam("need_date") long needDate,
            HttpSession session) {

        String token = (String) session.getAttribute("token");

        if (token != null && tokenDAO.isTokenExist(token)) {

            ObjectMapper objectMapper = new ObjectMapper();

            if (requisitionJson != null && !requisitionJson.isEmpty()) {

                try {

                    Requisition requisition
                            = objectMapper.readValue(requisitionJson, Requisition.class);

                    if (needDate != 0) {

                        requisition.setRequisitionNeedDate(new Date(needDate));
                    }
                    requisition.setRequisitionPlacingDate(new Date());
                    requisition.setCreatorId(tokenDAO.getUserId(token));
                    requisition.setType(requisitionTypeDAO.getRequisitionTypeById(typeId));

                    int value = requisitionService.addRequisition(requisition);
                    if (value != 0) {

                        return "{\"add\":\"true\"}";
                    }

                } catch (IOException ex) {

                    System.err.println(ex.toString());
                }
            }
        }

        return "{\"add\":\"false\"}";
    }

    /**
     *
     * @param id
     * @param token
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/requisition/{id}",
            produces = {"application/json;charset:UTF-8"},
            params = {"token"},
            method = RequestMethod.GET)
    public String getRequisition(
            @PathVariable("id") long id,
            @RequestParam("token") String token
    ) {

        long userId = tokenDAO.getUserId(token);

        if (userId > 0) {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setDateFormat(df);

            if (id > 0) {

                Requisition requisition = requisitionService.getRequisitionById(id);

                if (requisition != null) {

                    try {

                        return objectMapper.writeValueAsString(requisition);
                    } catch (JsonProcessingException ex) {

                        System.err.println(ex.toString());
                    }
                }
            }
        }

        return "[]";
    }

    /**
     *
     * @param key
     * @param id
     * @param session
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/requisition/{key}/{id}",
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.GET)
    public String getRequisitionByKey(
            @PathVariable("key") String key,
            @PathVariable("id") long id,
            HttpSession session) {

        String token = (String) session.getAttribute("token");

        if (token != null && tokenDAO.isTokenExist(token)) {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setDateFormat(df);

            if (key != null && !key.isEmpty() && id > 0) {

                List<Requisition> requisitions;
                List<RequisitionJson> requisitionJsons = new ArrayList<>();
                switch (key) {
                    case "type":

                        requisitions = requisitionService.getRequisitionsByType(id);

                        if (requisitions != null && requisitions.size() > 0) {

                            for (Requisition requisition : requisitions) {

                                RequisitionJson requisitionJson = getRequisitionJson(requisition);

                                requisitionJsons.add(requisitionJson);
                            }

                            try {

                                return objectMapper.writeValueAsString(requisitionJsons);
                            } catch (JsonProcessingException ex) {

                                System.err.println(ex.toString());
                            }
                        }
                        break;

                    case "solver":

                        requisitions = requisitionService.getRequisitionsBySolver(id);
                        if (requisitions != null && requisitions.size() > 0) {

                            for (Requisition requisition : requisitions) {

                                RequisitionJson requisitionJson = getRequisitionJson(requisition);

                                requisitionJsons.add(requisitionJson);
                            }

                            try {

                                return objectMapper.writeValueAsString(requisitionJsons);
                            } catch (JsonProcessingException ex) {

                                System.err.println(ex.toString());
                            }
                        }
                        break;

                    case "creator":

                        requisitions = requisitionService.getRequisitionsByCreator(id);
                        if (requisitions != null && requisitions.size() > 0) {

                            for (Requisition requisition : requisitions) {

                                RequisitionJson requisitionJson = getRequisitionJson(requisition);

                                requisitionJsons.add(requisitionJson);
                            }

                            try {

                                return objectMapper.writeValueAsString(requisitionJsons);
                            } catch (JsonProcessingException ex) {

                                System.err.println(ex.toString());
                            }
                        }
                        break;

                    default:
                        break;
                }
            }
        }

        return "[]";
    }

    /**
     *
     * @param value
     * @param token
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/requisition/solved/{value}",
            produces = {"application/json;charset:UTF-8"},
            params = {"token"},
            method = RequestMethod.GET)
    public String getRequisitionsBySolved(
            @PathVariable("value") boolean value,
            @RequestParam("token") String token
    ) {

        long userId = tokenDAO.getUserId(token);
        if (userId > 0) {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setDateFormat(df);

            List<Requisition> requisitions = requisitionService.getRequisitionsBySU(value);
            if (requisitions != null && requisitions.size() > 0) {

                try {

                    return objectMapper.writeValueAsString(requisitions);
                } catch (JsonProcessingException e) {

                    System.err.println(e.toString());
                }
            }
        }

        return "[]";
    }

    /**
     *
     * @param id
     * @param session
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/requisition/update",
            params = {"id"},
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.POST)
    public String updateRequisition(
            @RequestParam("id") long id,
            HttpSession session) {

        String token = (String) session.getAttribute("token");

        if (token != null && tokenDAO.isTokenExist(token)) {

            if (id > 0) {

                Requisition requisition = requisitionService.getRequisitionById(id);

                if (requisition != null
                        && requisition.getRequisitionSolvedDate() == null
                        && requisition.isSolved() == false) {

                    requisition.setSolved(true);
                    requisition.setSolverId(tokenDAO.getUserId(token));
                    requisition.setRequisitionSolvedDate(new Date());

                    int value = requisitionService.updateRequisition(requisition);
                    if (value != 0) {

                        return "{\"update\":\"true\"}";
                    }
                }
            }
        }

        return "{\"update\":\"false\"}";
    }

    /**
     * Return current user's requisitions (solved/unsolved)
     *
     * @param solved
     * @param session
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/requisition/own/{solved}",
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.GET)
    public String getOwnRequisition(
            @PathVariable("solved") boolean solved,
            HttpSession session) {

        String token = (String) session.getAttribute("token");
        if (token != null && tokenDAO.isTokenExist(token)) {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setDateFormat(df);

            long userId = tokenDAO.getUserId(token);

            List<Requisition> requisitions = requisitionDAO.getRequisitionsByCreator(userId, solved);
            List<RequisitionJson> requisitionJsons = new ArrayList<>();

            for (Requisition requisition : requisitions) {

                RequisitionJson requisitionJson = getRequisitionJson(requisition);

                requisitionJsons.add(requisitionJson);
            }

            try {

                return objectMapper.writeValueAsString(requisitionJsons);
            } catch (JsonProcessingException e) {

                System.err.println(e.toString());
            }
        }

        return "[]";
    }

    /**
     * Returns the requisition that should be handled by the current user
     *
     * @param session
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/requisition/incoming",
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.GET)
    public String getOwnHandleRequisition(HttpSession session) {

        String token = (String) session.getAttribute("token");
        if (token != null && tokenDAO.isTokenExist(token)) {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setDateFormat(df);

            long userId = tokenDAO.getUserId(token);
            HumanResource user = humanResourceDAO.getHumanResource(userId);

            Set<AccessType> userAllAccess = user.getAccess();
            List<Requisition> requisitions = new ArrayList<>();

            long comPart[] = {3, 13, 19, 20, 21, 22};
            long officeRes[] = {4, 6, 7, 8, 9, 10,
                11, 12, 14, 15, 16, 17, 18, 23, 24, 25,
                26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37};

            for (AccessType userAcces : userAllAccess) {

                int accessId = Integer.valueOf(userAcces.getId().toString());

                switch (accessId) {

                    case 14:

                        requisitions.addAll(requisitionDAO.getRequisitionsByType((long) 1, false));
                        break;
                    case 15:

                        requisitions.addAll(requisitionDAO.getRequisitionsByType((long) 2, false));
                        break;
                    case 16:

                        for (long value : comPart) {

                            requisitions.addAll(requisitionDAO.getRequisitionsByType(value, false));
                        }
                        break;
                    case 17:

                        for (long value : officeRes) {

                            requisitions.addAll(requisitionDAO.getRequisitionsByType(value, false));
                        }
                        break;
                    default:
                }
            }

            List<RequisitionJson> requisitionJsons = new ArrayList<>();

            for (Requisition requisition : requisitions) {

                RequisitionJson requisitionJson = getRequisitionJson(requisition);

                requisitionJsons.add(requisitionJson);
            }

            try {

                return objectMapper.writeValueAsString(requisitionJsons);
            } catch (JsonProcessingException e) {

                System.err.println(e.toString());
            }
        }

        return "[]";
    }

    /**
     * Util method to create Json for Requisition
     *
     * @param requisition
     * @return
     */
    private RequisitionJson getRequisitionJson(Requisition requisition) {

        RequisitionJson requisitionJson = new RequisitionJson();

        if (requisition != null) {

            requisitionJson.setCreator(humanResourceDAO.getHumanResource(requisition.getCreatorId()));
            requisitionJson.setCreatorId(requisition.getCreatorId());
            requisitionJson.setId(requisition.getId());
            requisitionJson.setPurpose(requisition.getPurpose());
            requisitionJson.setQuantity(requisition.getQuantity());
            requisitionJson.setRemarks(requisition.getRemarks());
            requisitionJson.setRequisitionNeedDate(requisition.getRequisitionNeedDate());
            requisitionJson.setRequisitionPlacingDate(requisition.getRequisitionPlacingDate());
            requisitionJson.setRequisitionSolvedDate(requisition.getRequisitionSolvedDate());
            requisitionJson.setSolved(requisition.isSolved());
            requisitionJson.setSolver(humanResourceDAO.getHumanResource(requisition.getSolverId()));
            requisitionJson.setType(requisition.getType());

            return requisitionJson;
        }

        return null;
    }
}
