package com.fivet.buddy.controller;

import com.fivet.buddy.dao.MemberDAO;
import com.fivet.buddy.dto.MemberDTO;
import com.fivet.buddy.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin/")
public class AdminController {
    @Autowired
    private MemberService memberService;

    @Autowired
    private HttpSession session;

    // 관리자 페이지로 이동
    @RequestMapping("toAdminPage")
    public String toAdminPage(Model model) throws Exception {
        if (session.getAttribute("memberLogtype").equals("admin")) {
            List<MemberDTO> list = memberService.selectMembers();
            model.addAttribute("memberList", list);
            return "admin/adminMain";
        }
        return "error";
    }

    // 회원 검색(관리자)
    @RequestMapping("memberSearch")
    public String memberSearch(String searchPick, String memberSearchText, Model model) throws Exception {
        List<MemberDTO> list = memberService.memberSearch(searchPick, memberSearchText);
        model.addAttribute("memberList", list);
        return "admin/adminMain";
    }

    // 회원 강퇴(관리자)
    @RequestMapping("memberKickOut")
    public String memberKickOut(int memberSeq, Model model) throws Exception {
        memberService.memberKickOut(memberSeq);
        List<MemberDTO> list = memberService.selectMembers();
        model.addAttribute("memberList", list);
        return "admin/adminMain";
    }

    //공지관리페이지로 이동
    @RequestMapping("toAdminNotice")
    public String toAdminNotice() {
        if (session.getAttribute("memberLogtype").equals("admin")) {
            return "admin/adminNotice";
        } else {
            return "error";
        }
    }

    @RequestMapping("toAdminQna")
    public String toAdminQna() {
        if (session.getAttribute("memberLogtype").equals("admin")) {
            return "admin/adminQna";
        } else {
            return "error";
        }
    }

    @RequestMapping("toAdminNoticeWrite")
    public String toAdminNoticeWrite() {
        if (session.getAttribute("memberLogtype").equals("admin")) {
            return "admin/adminNoticeWrite";
        } else {
            return "error";
        }
    }
}
