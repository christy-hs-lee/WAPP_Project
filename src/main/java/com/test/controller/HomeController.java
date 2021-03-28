package com.test.controller;

import com.test.dto.UserDto;
import com.test.service.test.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;


//@SessionAttributes({"userName"})
@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @GetMapping("/") // 홈
    public String main(Model model){
        try{

        }catch (Exception e){
            e.printStackTrace();
        }
        return "index";
    }

    @GetMapping("/qna")
    public String qna(Model model){
        try{

        }catch (Exception e){
            e.printStackTrace();
        }
        return "qna";
    }

    @GetMapping("/signIn") // 회원가입페이지
    public String signIn(Model model){
        try{

        }catch (Exception e){
            e.printStackTrace();
        }
        return "signIn"; // 비밀번호, 확인 같은지, 이메일 유효한지, 이름 유효한지 확인하기
    }

    @PostMapping("/signIn.do") // 회원가입 처리
    public String signInPost(HttpSession session, UserDto userDto, Model model){
        try{
            String targetEmail = userDto.getUserEmail();
            String targetPassword = userDto.getUserPassword(); // 패스워드가 유효한지 확인(미구현, 프론트)
            ArrayList<UserDto> userList = userService.getItemList();
            boolean valid = true;
            for (UserDto i : userList) { // 중복확인
                if (i.getUserEmail().equals(targetEmail)) { //중복
                    valid  = false;
                    break;
                }
            }

            if (valid) {//중복아니면 DB에 회원정보 추가 후 회원가입완료 메세지 띄워주고 로그인화면으로 이동시켜주기
                userService.addItem(userDto);
                System.out.println("회원가입성공, 로그인화면으로 이동");
                model.addAttribute("userName", userDto.getUserName()); // jsp에유저이름 보내주기
//                session.setAttribute("userName", userDto.getUserName()); // 세션에 유저이름 등록하기

                return "redirect:/welcome";
            } else { //중복이면 다시입력하게하기
                System.out.println("회원가입실패, 중복된아이디 존재");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/signIn";
    }

    @GetMapping("/login") // 로그인페이지
    public String login(Model model){
        try{

        }catch (Exception e){
            e.printStackTrace();
        }
        return "userlogin";
    }

    @PostMapping("/login.do") // 로그인처리
    public String loginPost(HttpSession session, UserDto userDto, Model model){
        try{
            String targetEmail = userDto.getUserEmail();
            String targetPassword = userDto.getUserPassword();
            if (targetEmail.equals("admin@admin.admin") && targetPassword.equals("1111")) { // 관리자로그인
                return "redirect:/admin/home";
            }

            ArrayList<UserDto> userList = userService.getItemList();



            boolean validId = false;
            for (UserDto i : userList) { // 데이터리스트중에서 일치하는 아이디가 있는지 확인
                if (i.getUserEmail().equals(targetEmail)) { // 아이디 비교, 일치
                    validId = true;
                    if (i.getUserPassword().equals(targetPassword)) { // 비밀번호 같은지 확인
                        System.out.println("로그인성공, 홈페이지로 유도");

                        session.setAttribute("userName", i.getUserName()); // 세션에 유저이름 등록하기 (로그인판별위해서)
                        session.setAttribute("userNo", i.getUserNo()); // 세션에 유저이름 등록하기 (유저정보위해서)
//                        model.addAttribute("userName", i.getUserName()); // 모델에 세션(userName) 넘겨주기 : redirect하는 jsp에서도 사용가능

                        return "redirect:/";
                    }
                    else { // 비밀번호 일치하지 않을시
                        System.out.println("비밀번호가 일치하지 않습니다.");
                    }
                }
            }
            if (!validId) { // 일치하는 아이디가 없을시
                System.out.println("존재하지 않는 아이디입니다");
            }


        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/login";
    }

    @GetMapping("/welcome") // 로그인완료시
    public String welcome(HttpSession session, Model model){
        try{

        }catch (Exception e){
            e.printStackTrace();
        }
        return "welcome";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, Model model){
        try{
            session.removeAttribute("userName"); // 세션에서 유저이름 지우기
            System.out.println("logout 성공");
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/";
    }

}
