package com.fivet.buddy.controller;

import com.fivet.buddy.dto.CalDTO;
import com.fivet.buddy.dto.ChatRoomDTO;
import com.fivet.buddy.dto.TeamMemberDTO;
import com.fivet.buddy.dto.TeamMemberListDTO;
import com.fivet.buddy.services.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/calendar/")
public class CalController {

    @Autowired
    private CalService calService;

    @Autowired
    private HttpSession session;



    private Logger logger = LoggerFactory.getLogger(CalController.class);

    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e) {
        e.printStackTrace();
        return "error";
    }

    @RequestMapping("insertEvent")
    public String insertEvent(CalDTO calDto) throws Exception{
        calDto.setTeamSeq((Integer)session.getAttribute("teamSeq"));
        calDto.setEventWriter(session.getAttribute("teamMemberNickname").toString());
        calService.insertEvent(calDto);
        return "redirect:/calendar/moveCalendar";
    }

    @RequestMapping(value = "moveCalendar")
    public String moveCalendar()throws Exception {
        if ((Integer) session.getAttribute("teamSeq") != null ){
        return "calendar/calendar";
        }
        return "error";
    }

    @RequestMapping(value = "goPrivate")
    public String goPrivate()throws Exception {
        if ((Integer) session.getAttribute("teamSeq") != null ){
            return "calendar/calendarPrivate";
        }
        return "index";
    }

    @RequestMapping(value = "goTeam")
    public String goTeam()throws Exception {
        if ((Integer) session.getAttribute("teamSeq") != null ){
            return "calendar/calendarTeam";
        }
        return "index";
    }

    @ResponseBody
    @RequestMapping (value = "selectAll" , produces = "text/html;charset=utf8")
    public String selectAll(Model model) throws Exception{
            List<CalDTO> calList = calService.selectAll((Integer) session.getAttribute("teamSeq"));
            model.addAttribute("list",calList);
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            String list = gson.toJson(calList);
            return list;
    }

    @ResponseBody
    @RequestMapping (value = "selectTeam" , produces = "text/html;charset=utf8")
    public String selectTeam(Model model) throws Exception{
        List<CalDTO> calList = calService.selectTeam((Integer) session.getAttribute("teamSeq"));
        model.addAttribute("list",calList);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        String list = gson.toJson(calList);
        System.out.println(list);
        return list;
    }

    @ResponseBody
    @RequestMapping (value = "selectPrivate" , produces = "text/html;charset=utf8")
    public String selectPrivate(Model model) throws Exception{
        List<CalDTO> calList = calService.selectPrivate((Integer) session.getAttribute("teamSeq"));
        model.addAttribute("list",calList);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        String list = gson.toJson(calList);
        System.out.println(list);
        return list;
    }

    @GetMapping("selectGrade")
    public String selectGrade(int eventSeq) throws Exception{
        String grade = calService.selectGrade(session.getAttribute("teamMemberNickname").toString());
        String manager = "manager";
        System.out.println(grade);
        System.out.println(manager);
        System.out.println(eventSeq);
        boolean result =  Objects.equals(grade, manager);
        if (result){
            calService.deleteEvent(eventSeq);
            return "redirect:/calendar/moveCalendar";
        }else {
            return "/calendar/sessionError";
        }
    }
    @GetMapping("deleteEvent")
    public String deleteEvent(int eventSeq , String eventWriter ) throws Exception{
        String Session = session.getAttribute("teamMemberNickname").toString();
        boolean result = Objects.equals(eventWriter, Session);
        if (result){
        calService.deleteEvent(eventSeq);
        return "redirect:/calendar/moveCalendar";

        }else {
                return "/calendar/sessionError";
        }
    }


    @GetMapping (value="updateEvent")
    public String updateEvent(CalDTO calDto, String eventWriter) throws Exception{
        String Session = session.getAttribute("teamMemberNickname").toString();
        boolean result = Objects.equals(eventWriter, Session);
        if(result) {
            calDto.setEventWriter(session.getAttribute("teamMemberNickname").toString());
            calService.updateEvent(calDto);
            return "redirect:/calendar/moveCalendar";
        }else {
            return "/calendar/sessionError";
        }

    }

}