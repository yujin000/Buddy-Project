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
        return "calendar/calendarError";
    }

    @RequestMapping("insertEvent")
    public String insertEvent(CalDTO calDto) throws Exception{
        calDto.setMemberSeq((Integer)session.getAttribute("memberSeq"));
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
        return "calendar/calendarError";
    }

    @RequestMapping(value = "goPrivate")
    public String goPrivate()throws Exception {
        if ((Integer) session.getAttribute("teamSeq") != null ){
            return "calendar/calendarPrivate";
        }
        return "calendar/calendarError";
    }

    @RequestMapping(value = "goTeam")
    public String goTeam()throws Exception {
        if ((Integer) session.getAttribute("teamSeq") != null ){
            return "calendar/calendarTeam";
        }
        return "calendar/calendarError";
    }

    @ResponseBody
    @RequestMapping (value = "selectAll" , produces = "text/html;charset=utf8")
    public String selectAll() throws Exception{
            List<CalDTO> calList = calService.selectAll((Integer) session.getAttribute("teamSeq"));
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            String list = gson.toJson(calList);
            return list;
    }

    @ResponseBody
    @RequestMapping (value = "selectTeam" , produces = "text/html;charset=utf8")
    public String selectTeam() throws Exception{
        List<CalDTO> calList = calService.selectTeam((Integer) session.getAttribute("teamSeq"));
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        String list = gson.toJson(calList);
        return list;
    }

    @ResponseBody
    @RequestMapping (value = "selectPrivate" , produces = "text/html;charset=utf8")
    public String selectPrivate() throws Exception{
        int memberSeq = ((Integer)session.getAttribute("memberSeq"));
        int teamSeq = ((Integer)session.getAttribute("teamSeq"));
        List<CalDTO> calList = calService.selectPrivate(memberSeq,teamSeq);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        String list = gson.toJson(calList);
        System.out.println(list);
        return list;
    }

    @GetMapping("teamEventDel")
    public String teamEventDel(int eventSeq) throws Exception{
        String grade = calService.selectGrade(session.getAttribute("teamMemberNickname").toString());
        String manager = "manager";
        boolean result =  Objects.equals(grade, manager);
        if (result){
            int memberSeq = (Integer)session.getAttribute("memberSeq");
            calService.deleteEvent(eventSeq, memberSeq);
            return "redirect:/calendar/moveCalendar";
        }else {
            return "calendar/gradeError";
        }
    }
    @GetMapping("teamEventUpd")
    public String privateEventDel(CalDTO calDto) throws Exception{
        String grade = calService.selectGrade(session.getAttribute("teamMemberNickname").toString());
        String manager = "manager";
        boolean result =  Objects.equals(grade, manager);
        if (result){
            calDto.setEventWriter(session.getAttribute("teamMemberNickname").toString());
            calService.updateEvent(calDto);
            return "redirect:/calendar/moveCalendar";
        }else {
            return "calendar/gradeError";

        }
    }
    @GetMapping("deleteEvent")
    public String deleteEvent(int eventSeq , String eventWriter ) throws Exception{
        String Session = session.getAttribute("teamMemberNickname").toString();
        boolean result = Objects.equals(eventWriter, Session);
        if (result){
            int memberSeq = (Integer)session.getAttribute("memberSeq");
        calService.deleteEvent(eventSeq, memberSeq);
        return "redirect:/calendar/moveCalendar";

        }else {
            return "calendar/gradeError";

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
            return "calendar/gradeError";

        }

    }

}