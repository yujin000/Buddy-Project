package com.fivet.buddy.controller;

import com.fivet.buddy.dto.MemberDTO;
import com.fivet.buddy.dto.MemberImgDTO;
import com.fivet.buddy.dto.TeamDTO;
import com.fivet.buddy.services.MemberService;
import com.fivet.buddy.services.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;
import java.util.UUID;



@Controller
@RequestMapping("/member/")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private HttpSession session;

    private Logger logger = LoggerFactory.getLogger(MemberController.class);

    // 회원 indexPage 경로

    private String memberIndex = "redirect:/member/loginIndex";

    // ExceptionHandler
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e) {
        e.printStackTrace();
        return "error";
    }

    // 회원가입 페이지 이동
    @RequestMapping("toSignUp")
    public String toSignUp(MemberDTO memberDto,Model model) throws Exception{
        // 세션확인
        if(session.getAttribute("memberSeq")!=null){
            model.addAttribute("memberSeq",session.getAttribute("memberSeq"));
            return "member/duplLogin";
        }

        // 일반회원인지 카카오 / 네이버 회원인지 검토
        if(memberDto.getMemberLogtype() == null){
            memberDto.setMemberLogtype("normal");
            model.addAttribute("userInfo",memberDto);
        }else{
            model.addAttribute("userInfo",memberDto);
        }
        return "member/signup";
    }

    // 회원가입 (signUp)
    @RequestMapping("signUp")
    public String signUp(MemberDTO memberDto) throws Exception{
        memberService.signUp(memberDto);
        return "redirect:/";
    }

    // 회원가입 아이디 체크
    @ResponseBody
    @RequestMapping(value="idCheck",produces = "text/html;charest=utf8")
    public String idCheck(String id ) throws Exception{
        boolean result = memberService.idCheck(id);
        return String.valueOf(result);
    }

    // 로그인
    @PostMapping("login")
    public String login(MemberDTO memberDto, Model model) throws Exception{
        // 세션확인
        if(session.getAttribute("memberSeq")!=null){
            model.addAttribute("memberSeq",session.getAttribute("memberSeq"));
            return "member/duplLogin";
        }

        boolean result = memberService.isAccountExist(memberDto);
        if(result){
            // 로그인 정보가 있으면 마이페이지
            MemberDTO dto = memberService.selectAccountInfo(memberDto);
            // 세션에 멤버 seq,logtype 뿌림
            session.setAttribute("memberSeq",dto.getMemberSeq());
            session.setAttribute("memberLogtype",dto.getMemberLogtype());
            session.setAttribute("memberName",dto.getMemberName());
            //model.addAttribute("userInfo",dto); model에 넣을 필요 없어보여서 주석처리.
            return memberIndex;
        }else{
            // 로그인 정보가 없으면 새로고침
            return "redirect:/";
        }
    }

    // 로그아웃
    @RequestMapping("logout")
    public String logout(String memberLogtype) throws Exception{
        session.invalidate();
        return "redirect:/";
    }

    // 카카오 로그인
    @RequestMapping("kakaoLogin")
    public String kakaoLogin(MemberDTO memberDto,Model model) throws Exception{
        // 세션확인
        if(session.getAttribute("memberSeq")!=null){
            model.addAttribute("memberSeq",session.getAttribute("memberSeq"));
            return "member/duplLogin";
        }

        boolean result = memberService.isKakaoExist(memberDto);
        if(!result){
            // 회원이 아닐 때 회원가입 페이지
            model.addAttribute("userInfo",memberDto);
            return "member/signup";
        }else{
            // 회원일 때 마이페이지
            MemberDTO dto = memberService.selectAccountInfoForNK(memberDto);
            // 세션에 멤버 seq,logtype 뿌림
            session.setAttribute("memberSeq",dto.getMemberSeq());
            session.setAttribute("memberLogtype",dto.getMemberLogtype());
            session.setAttribute("memberName",dto.getMemberName());
            model.addAttribute("userInfo",dto);
            return memberIndex;
        }
    }

    // 네이버 로그인
    @RequestMapping("naverLogin")
    public String naverLogin(MemberDTO memberDto,Model model) throws Exception{
        // 세션확인
        if(session.getAttribute("memberSeq")!=null){
            model.addAttribute("memberSeq",session.getAttribute("memberSeq"));
            return "member/duplLogin";
        }

        boolean result = memberService.isNaverExist(memberDto);
        if(!result){
            // 회원이 아닐 때 회원가입 페이지
            String naverPhone = memberDto.getMemberPhone();
            String replacePhone = naverPhone.replaceAll("-","");
            memberDto.setMemberPhone(replacePhone);

            model.addAttribute("userInfo",memberDto);
            return "member/signup";
        }else{
            // 회원일 때 마이페이지
            MemberDTO dto = memberService.selectAccountInfoForNK(memberDto);
            // 세션에 멤버 seq,logtype 뿌림
            session.setAttribute("memberSeq",dto.getMemberSeq());
            session.setAttribute("memberLogtype",dto.getMemberLogtype());
            session.setAttribute("memberName",dto.getMemberName());
            model.addAttribute("userInfo",dto);
            return memberIndex;
        }
    }

    // 에러페이지 >> 마이페이지 이동
    @RequestMapping("goMypage")
    public String selectMyInfo(String memberSeq,Model model) throws Exception{
        MemberDTO dto = memberService.selectMyInfo(memberSeq);
        model.addAttribute("userInfo",dto);
        List <TeamDTO> teamDtoList = teamService.selectMemberTeam(dto.getMemberSeq());
        model.addAttribute("teamDtoList", teamDtoList);
        return memberIndex;
    }

    //계정설정으로 이동
    @RequestMapping("goMyProfile")
    public String selectMyProfile(Model model) throws Exception {
        MemberDTO memberDto = memberService.selectMyProfile(String.valueOf(session.getAttribute("memberSeq")));
        model.addAttribute("myProfile",memberDto);
        return "member/myProfile";
    }

    //휴대전화 수정
    @RequestMapping ("updatePhone")
    public String updatePhone(MemberDTO memberDto,Model model) throws Exception{
        memberDto.setMemberSeq((Integer) session.getAttribute("memberSeq"));
        memberService.updatePhone(memberDto);
        return "redirect:/member/goMyProfile";
    }

    //현재 비밀번호 일치여부
    @ResponseBody
    @RequestMapping(value="myProfileCheckPw", produces = "text/html;charset=utf8")
    public String selectMyProfilePw(MemberDTO memberDto) throws Exception{
        memberDto.setMemberSeq((Integer) session.getAttribute("memberSeq"));
        boolean result = memberService.selectMyProfilePw(memberDto);
        return String.valueOf(result);
    }

    //비밀번호 수정
    @RequestMapping ("updatePw")
    public String updatePw(MemberDTO memberDto) throws Exception{
        memberDto.setMemberSeq((Integer) session.getAttribute("memberSeq"));
        memberService.updatePw(memberDto);
        return "redirect:/member/goMyProfile";
    }

    //프로필 이미지 출력
    @ResponseBody
    @RequestMapping("selectProfileImg")
    public String selectProfileImg() throws Exception{
        String member_img_sysname = memberService.selectProfileImg(String.valueOf(session.getAttribute("memberSeq")));
        return member_img_sysname;
    }

    //프로필 이미지 수정
    @RequestMapping("updateImg")
    public String updateImg(MemberImgDTO memberImgDto, MultipartFile file) throws Exception{
        String realPath=session.getServletContext().getRealPath("/");
        File filePath = new File(realPath);
        if(!filePath.exists()) {
            filePath.mkdir();
        }// 파일 업로드 폴더가 없다면 생성하는 코드

        String memberImgOriName = file.getOriginalFilename();
        String memberImgSysName = UUID.randomUUID() + "_" + memberImgOriName;
        //UUID.randomUUID() : 현재 시간과 자체 매커니즘을 통해 겹치지 않는 기다란 문자를 자동으로 생성해줌
        file.transferTo(new File(filePath+"/"+memberImgSysName));
        memberImgDto.setMemberImgMemberSeq((Integer) session.getAttribute("memberSeq"));
        memberImgDto.setMemberImgOriName(memberImgOriName);
        memberImgDto.setMemberImgSysName(memberImgSysName);
        memberService.updateProfileImg(memberImgDto);
        return "redirect:/member/goMyProfile";
    }

    //회원 탈퇴
    @RequestMapping("deleteMember")
    public String deleteMember() throws Exception{
        memberService.deleteMember(String.valueOf(session.getAttribute("memberSeq")));
        session.invalidate();
        return "redirect:/";
    }
}