package com.test.controller;

import com.test.dto.LectureDto;
import com.test.service.test.LectureService;
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
import java.io.IOException;
import java.util.ArrayList;

@Controller
public class LectureController {

    @Autowired
    ServletContext servletContext;

    @Autowired
    LectureService lectureService;
    @Autowired
    FirebaseMessagingSnippets firebaseMessagingSnippets;


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
    public String addLecture(LectureDto lectureDto, MultipartFile lecImage,  HttpServletRequest request){
        try{
            // 동일한 사진이름이 있다면 ? 덮어씌워질텐데
            lectureDto.setLecImg("/files/lecture/" + lecImage.getOriginalFilename()); //db에 저장될 이름세팅
            saveFile(lecImage, request); // 서버에 저장
            lectureService.addItem(lectureDto); // DB에 저장
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/admin/lecture-data-table.do";
    }

    @GetMapping(value = "/admin/lecture-delete.do") // 어드민 강의 삭제
    public String delete(@RequestParam(value = "lecNo") int lecNo){
        try{
            LectureDto dbLecture = lectureService.selectItem(lecNo); // 강의키로 강의 정보 가져오기
            String dirPath = servletContext.getRealPath("/"); // 경로 저장
            File targetFile = new File(dirPath + dbLecture.getLecImg()); // 삭제할 파일선언
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
    public String editLecture(@Param("lecNo") int lecNo, LectureDto lectureDto, MultipartFile lecImage, HttpServletRequest request){
        try{
            System.out.println("hi from edit");
            LectureDto dbLecture = lectureService.selectItem(lecNo); // DB에있던 강의 가져오기

            String filename = lecImage.getOriginalFilename();
            if (filename.isEmpty()) { // 이미지이름이 빈칸 == 이미지새로 업로드 안함
                System.out.println("editItemWithoutImg");
                lectureDto.setLecImg(dbLecture.getLecImg()); // db에 있던 강의이미지를 그대로 넣어주기
            } else { // 이미지 이름이 있으면 기존이미지 삭제 후 새이미지를 저장
                System.out.println("editItemWithImg");

                File targetFile = new File(servletContext.getRealPath("/") + dbLecture.getLecImg()); // 서버에있는 삭제할 강의파일 지정
                String delName = targetFile.getName(); // 삭제될 배너파일이름
                if (targetFile.delete()) {
                    System.out.println("Deleted the file: " + delName);
                } else {
                    System.out.println("Failed to delete the file.");
                }
                saveFile(lecImage, request); // 서버에 사진 저장

                lectureDto.setLecImg("/files/lecture/" + lecImage.getOriginalFilename()); // 새로운 이미지이름으로 dto객체의 이미지이름 저장
            }
            lectureService.editItem(lectureDto); // db에 저장
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/admin/lecture-data-table.do";
    }







    private String saveFile(MultipartFile file, HttpServletRequest request) {
        String root_path = request.getSession().getServletContext().getRealPath("/");
        String attach_path = "files/lecture/";
        String filename = file.getOriginalFilename();

        System.out.println("saveName: "+ root_path + attach_path + filename);
        File saveFile = new File(root_path + attach_path + filename);
        try {
            file.transferTo(saveFile);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return filename;
    }
}