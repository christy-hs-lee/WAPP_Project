package com.test.controller;

import com.test.dto.PopupDto;
import com.test.service.test.PopupService;
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
public class PopupController {
    @Autowired
    ServletContext servletContext;
    @Autowired
    PopupService popupService;

    @GetMapping("/admin/popup/form.do") //.do없애도 되나?
    public String popupHome(Model model){
        System.out.println("HI from popupCtrl");
        return "admin/popup/form";
    }

    @PostMapping("/admin/popup/form.do")
    public String addPopup(PopupDto popupDto, MultipartFile popImage, HttpServletRequest request){

        popupDto.setPopImg("/files/popup/" + popImage.getOriginalFilename()); // 파일이름을 dto파일이름으로 set ( 경로가 바뀌면 데이터베이스에있는 경로도 다 바꿔야하는데?)
        System.out.println( popupDto.toString() );

        popupService.addItem(popupDto);//DB에 저장함
        saveFile(popImage, request);// 파일을 서버에 업로드함

        return "redirect:/admin/popup/data-table.do";
    }

    @GetMapping("/admin/popup/data-table.do")
    public String popupDataTable(Model model){
        System.out.println("HI from dt");
        try{
            ArrayList<PopupDto> popupList = popupService.getItemList();
            model.addAttribute("popupList", popupList);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "admin/popup/data-table";
    }

    @GetMapping(value = "/admin/popup/delete.do") // 어드민 강의 삭제
    public String delete(@RequestParam(value = "popNo") int popNo){
        try{
            PopupDto dbPopup = popupService.selectItem(popNo); // 강의키로 강의 정보 가져오기

            String dirPath = servletContext.getRealPath("/"); // 경로 가져오기
            File targetFile = new File(dirPath + dbPopup.getPopImg()); // 삭제할 파일선언
            String delName = targetFile.getName(); // 삭제할 파일 이름 변수에 저장
            if (targetFile.delete()) { // 파일 삭제
                System.out.println("Deleted the file: " + delName);
            } else {
                System.out.println("Failed to delete the file.");
            }
            popupService.deleteItem(popNo); //성공시 DB에서도 삭제
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/admin/popup/data-table.do";
    }

    @GetMapping("/admin/popup/edit.do") // 어드민 강의 수정 폼
    public String editForm(@RequestParam(value = "popNo") int popNo, Model model){
        try{
            System.out.println("popNo: " + popNo);
            PopupDto popup = popupService.selectItem(popNo);
            model.addAttribute("popup", popup);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "admin/popup/edit";
    }

    @RequestMapping(value = "/admin/editPopup.do", method = {RequestMethod.POST, RequestMethod.GET}) // 어드민 강의 수정
    public String editPopup(@Param("popNo") int popNo, PopupDto popupDto, MultipartFile popImage, HttpServletRequest request){
        try{
            System.out.println("hi from edit");
            PopupDto dbPopup = popupService.selectItem(popNo); // popNo로 db에있는 배너 가져오기

            String filename = popImage.getOriginalFilename();
            if (filename.isEmpty()) { // 이미지이름이 빈칸 == 이미지새로 업로드 안함
                System.out.println("editItemWithoutImg");

                popupDto.setPopImg(dbPopup.getPopImg()); // db에 있던 배너이미지를 그대로 넣어주기
            } else { // 이미지 이름이 있으면 기존이미지 삭제 후 새이미지를 저장
                System.out.println("editItemWithImg");

                File targetFile = new File(servletContext.getRealPath("/") + dbPopup.getPopImg()); // 서버에있는 삭제할 배너파일 지정
                String delName = targetFile.getName(); // 삭제될 배너파일이름
                if (targetFile.delete()) {
                    System.out.println("Deleted the file: " + delName);
                } else {
                    System.out.println("Failed to delete the file.");
                }
                saveFile(popImage, request); // 서버에 사진 저장

                popupDto.setPopImg("/files/popup/" + popImage.getOriginalFilename()); // 새로운 이미지이름으로 dto객체의 이미지이름 저장
            }
            popupService.editItem(popupDto); // db에 저장
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/admin/popup/data-table.do";
    }

    private String saveFile(MultipartFile file, HttpServletRequest request) {
        String root_path = request.getSession().getServletContext().getRealPath("/");
        String attach_path = "files/popup/";
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
