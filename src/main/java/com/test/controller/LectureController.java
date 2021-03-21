package com.test.controller;

import com.test.dto.LectureDto;
import com.test.dto.TestDto;
import com.test.service.test.LectureService;
import com.test.service.test.TestService;
import com.test.util.firebase.FirebaseMessagingSnippets;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;

@Controller
public class LectureController {

    @Autowired
    ServletContext servletContext;

    @Autowired
    TestService testService;
    @Autowired
    LectureService lectureService;
    @Autowired
    FirebaseMessagingSnippets firebaseMessagingSnippets;

    @GetMapping("/") // 홈
    public String main(Model model){
        try{

        }catch (Exception e){
            e.printStackTrace();
        }
        return "index";
    }





    ///////////////////////////////////////////////////////어드민 페이지////////////////////////////////////////////
    @GetMapping("/admin/home") // 어드민 홈
    public String adminHome(Model model){
        try{

        }catch (Exception e){
            e.printStackTrace();
        }
        return "admin/home";
    }

    @GetMapping("/admin/lecture-data-table.do") // 어드민 강의 데이터 보여주기
    public String dataTable(Model model){
        try{
            ArrayList<LectureDto> lectureList = lectureService.getItemList();
            model.addAttribute("lectureList", lectureList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "admin/lecture-data-table";
    }

    @GetMapping("/admin/lecture-form.do") // 어드민 강의 데이터 입력 폼
    public String form(Model model){
        try{

        }catch (Exception e){
            e.printStackTrace();
        }
        return "admin/lecture-form";
    }

    @GetMapping("/admin/login.do") // 어드민 로그인 페이지 (미구현)
    public String adminLogin(Model model){
        try{

        }catch (Exception e){
            e.printStackTrace();
        }
        return "admin/login";
    }

    @PostMapping(value = "/admin/addLecture.do") //어드민 강의 추가
    public String addLecture(@Param("lecCategory") String lecCategory,
                             @Param("lecName") String lecName,
                             @Param("lecPrice") int lecPrice,
                             @Param("lecImg") MultipartFile lecImg){
        try{
            // 한칸이라도 비어있다면 다 입력하라는 알림창이 뜨게 만들기
            // 동일한 사진이름이 있다면 ? 덮어씌워질텐데
            System.out.println("hi from post");

            String filename = lecImg.getOriginalFilename();
            String lecImgPath = "/files/lecture/" + filename; // db에 저장될 파일 주소
            String dirPath = servletContext.getRealPath("/") + "files\\lecture\\"; // 서버에 저장될 파일 주소

            if (!lecImg.isEmpty()){
                lecImg.transferTo(new File(dirPath + filename));
                System.out.println("file upload success");
            } else {
                lecImgPath = null;
            }
            lectureService.addItem(lecCategory, lecName, lecPrice, lecImgPath);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/admin/lecture-data-table.do";
    }

    @GetMapping(value = "/admin/lecture-delete.do") // 어드민 강의 삭제
    public String delete(@RequestParam(value = "lecNo") int lecNo){
        try{
            LectureDto dblecture = lectureService.selectItem(lecNo); // 강의키로 강의 정보 가져오기
            String dirPath = servletContext.getRealPath("/"); // 경로 저장
            File targetFile = new File(dirPath + dblecture.getLecImg()); // 삭제할 파일선언
            String delName = targetFile.getName(); // 삭제할 파일 이름 변수에 저장
            if (targetFile.delete()) { // 파일 삭제
                System.out.println("Deleted the file: " + delName);
            } else {
                System.out.println("Failed to delete the file.");
            }
            lectureService.deleteItem(lecNo); //성공시 DB에서도 삭제
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/admin/lecture-data-table.do";
    }

    @GetMapping("/admin/lecture-edit.do") // 어드민 강의 수정 폼
    public String editForm(@RequestParam(value = "lecNo") int lecNo, Model model){
        try{
            System.out.println("lecNo: " + lecNo);
            LectureDto lecture = lectureService.selectItem(lecNo);
            model.addAttribute("lecture", lecture);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "admin/lecture-edit";
    }

    @RequestMapping(value = "/admin/editLecture.do", method = {RequestMethod.POST, RequestMethod.GET}) // 어드민 강의 수정
    public String editLecture(@Param("lecNo") int lecNo,
                              @Param("lecCategory") String lecCategory,
                             @Param("lecName") String lecName,
                             @Param("lecPrice") int lecPrice,
                              @Param("lecImg") MultipartFile lecImg){
        try{
            System.out.println("hi from edit");
            LectureDto dblecture = lectureService.selectItem(lecNo);
            File targetFile = new File(servletContext.getRealPath("/") + dblecture.getLecImg());
            String delName = targetFile.getName();

            try {// 이미지가 업로드되었다면 삭제, 새로 저장후 링크 db에 저장, 같은 이름의 이미지가 서버에 저장되어있는지 확인 필요
                System.out.println("editItemWithImg");
                if (targetFile.delete()) {
                    System.out.println("Deleted the file: " + delName);
                } else {
                    System.out.println("Failed to delete the file.");
                }

                String filename = lecImg.getOriginalFilename();
                String lecImgPath = "/files/lecture/" + filename;
                System.out.println("db data path : "+lecImgPath);

                if (!lecImg.isEmpty()) {
                    String dirPath = servletContext.getRealPath("/") + "files\\lecture\\";
                    lecImg.transferTo(new File(dirPath + filename));
                } else {
                    lecImgPath = null;
                }
                lectureService.editItem(lecNo, lecCategory, lecName, lecPrice, lecImgPath);
            } catch (NullPointerException e) {// 이미지를 새로 업로드하지 않았으면 데이터베이스에있는 이미지를 그대로 저장
                System.out.println("editItemWithoutImg");
                lectureService.editItem(lecNo, lecCategory, lecName, lecPrice, dblecture.getLecImg());
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/admin/lecture-data-table.do";
    }

///////////////////////////////////////////////////////어드민 페이지////////////////////////////////////////////















    @GetMapping("/test.do")
    public String test(Model model){
        try{
            System.out.println("test.do Controller");
            ArrayList<TestDto> itemList = testService.getItemList();
            model.addAttribute("itemList", itemList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "test";
    }

    @RequestMapping(value = "/get.do", method = RequestMethod.GET)
    public String get(@RequestParam(value = "data") String data){
        try{
            System.out.println("controller: " + data);
            testService.addItem(data);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "redirect:/test.do";
    }

    @RequestMapping(value = "/post.do", method = RequestMethod.POST)
    public String post(@RequestParam(value = "data") String data){
        try{
            System.out.println("post방식 data: " + data);
            testService.addItem(data);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/test.do";
    }

    @RequestMapping(value = "/delete.do", method = RequestMethod.GET)
    public String delete(@RequestParam(value = "number") String number){
        try{
            System.out.println("number: " + number);
            testService.deleteItem(number);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/test.do";
    }

    @GetMapping("/fcm.do")
    public String fcm(@RequestParam(value = "fcm") String fcm, @RequestParam(value = "title") String title, @RequestParam(value = "content") String content, HttpServletRequest req){
        try{
            System.out.println("fcm: " + fcm);
            System.out.println("title: " + title);
            System.out.println("content: " + content);
            firebaseMessagingSnippets.test_send_FCM(fcm, title, content, req);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/";
    }
}
