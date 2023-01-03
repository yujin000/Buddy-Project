package com.fivet.buddy.controller;

import com.fivet.buddy.dto.CalDTO;
import com.fivet.buddy.services.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e) {
        e.printStackTrace();
        return "/calendar/calendarError";
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
        return "/calendar/calendarError";
    }

    @RequestMapping(value = "goPrivate")
    public String goPrivate()throws Exception {
        if ((Integer) session.getAttribute("teamSeq") != null ){
            return "calendar/calendarPrivate";
        }
        return "/calendar/calendarError";
    }

    @RequestMapping(value = "goTeam")
    public String goTeam()throws Exception {
        if ((Integer) session.getAttribute("teamSeq") != null ){
            return "calendar/calendarTeam";
        }
        return "/calendar/calendarError";
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
        return list;
    }

    @ResponseBody
    @RequestMapping (value = "selectPrivate" , produces = "text/html;charset=utf8")
    public String selectPrivate(Model model) throws Exception{
        List<CalDTO> calList = calService.selectPrivate((Integer) session.getAttribute("teamSeq"));
        model.addAttribute("list",calList);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        String list = gson.toJson(calList);
        return list;
    }

    @GetMapping("teamEventDel")
    public String teamEventDel(int eventSeq) throws Exception{
        String grade = calService.selectGrade(session.getAttribute("teamMemberNickname").toString());
        String manager = "manager";
        boolean result =  Objects.equals(grade, manager);
        if (result){
            calService.deleteEvent(eventSeq);
            return "redirect:/calendar/moveCalendar";
        }else {
            return "/calendar/gradeError";
        }
    }
    @GetMapping("teamEventUpd")
    public String privateEventDel(CalDTO calDto) throws Exception{
        String grade = calService.selectGrade(session.getAttribute("teamMemberNickname").toString());
        String manager = "manager";
        boolean result =  Objects.equals(grade, manager);
        if (result){
            calService.updateEvent(calDto);
            return "redirect:/calendar/moveCalendar";
        }else {
            return "/calendar/gradeError";

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
            return "/calendar/gradeError";

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
            return "/calendar/gradeError";

        }

    }

}