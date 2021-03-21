package com.test.controller;

import com.test.dto.BannerDto;
import com.test.service.test.BannerService;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.Null;
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
public class BannerController {
    @Autowired
    ServletContext servletContext;
    @Autowired
    BannerService bannerService;

    @GetMapping("/admin/banner-form.do") //.do없애도 되나?
    public String bannerHome(Model model){
        System.out.println("HI from bannerCtrl");
        return "admin/banner-form";
    }

    @PostMapping("/admin/banner-form.do")
    public String bannerUpload(BannerDto bannerDto, MultipartFile banImage, HttpServletRequest request){

        System.out.println( banImage.getOriginalFilename() );

        bannerDto.setBanImg("/files/banner/" + banImage.getOriginalFilename()); // 파일이름을 dto파일이름으로 set ( 경로가 바뀌면 데이터베이스에있는 경로도 다 바꿔야하는데?)
        System.out.println( bannerDto.toString() );

        bannerService.insertBanner(bannerDto);//DB에 저장함
        saveFile(banImage, request);// 파일을 서버에 업로드함

        return "redirect:/admin/banner-data-table.do";
    }

    @GetMapping("/admin/banner-data-table.do")
    public String bannerDataTable(Model model){
        System.out.println("HI from dt");
        try{
            ArrayList<BannerDto> bannerList = bannerService.getItemList();
            model.addAttribute("bannerList", bannerList);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "admin/banner-data-table";
    }

    @GetMapping(value = "/admin/banner-delete.do") // 어드민 강의 삭제
    public String delete(@RequestParam(value = "banNo") int banNo){
        try{
            BannerDto dbBanner = bannerService.selectItem(banNo); // 강의키로 강의 정보 가져오기

            String dirPath = servletContext.getRealPath("/"); // 경로 가져오기
            File targetFile = new File(dirPath + dbBanner.getBanImg()); // 삭제할 파일선언
            String delName = targetFile.getName(); // 삭제할 파일 이름 변수에 저장
            if (targetFile.delete()) { // 파일 삭제
                System.out.println("Deleted the file: " + delName);
            } else {
                System.out.println("Failed to delete the file.");
            }
            bannerService.deleteItem(banNo); //성공시 DB에서도 삭제
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/admin/banner-data-table.do";
    }

    @GetMapping("/admin/banner-edit.do") // 어드민 강의 수정 폼
    public String editForm(@RequestParam(value = "banNo") int banNo, Model model){
        try{
            System.out.println("banNo: " + banNo);
            BannerDto banner = bannerService.selectItem(banNo);
            model.addAttribute("banner", banner);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "admin/banner-edit";
    }

    @RequestMapping(value = "/admin/editBanner.do", method = {RequestMethod.POST, RequestMethod.GET}) // 어드민 강의 수정
    public String editBanner(@Param("banNo") int banNo, BannerDto bannerDto, MultipartFile banImage, HttpServletRequest request){
        try{
            System.out.println("hi from edit");
            BannerDto dbBanner = bannerService.selectItem(banNo); // banNo로 db에있는 배너 가져오기

            String filename = banImage.getOriginalFilename();
            if (filename.isEmpty()) { // 이미지이름이 빈칸 == 이미지새로 업로드 안함
                System.out.println("editItemWithoutImg");

                bannerDto.setBanImg(dbBanner.getBanImg()); // db에 있던 배너이미지를 그대로 넣어주기
            } else { // 이미지 이름이 있으면 기존이미지 삭제 후 새이미지를 저장
                System.out.println("editItemWithImg");

                File targetFile = new File(servletContext.getRealPath("/") + dbBanner.getBanImg()); // 서버에있는 삭제할 배너파일 지정
                String delName = targetFile.getName(); // 삭제될 배너파일이름
                if (targetFile.delete()) {
                    System.out.println("Deleted the file: " + delName);
                } else {
                    System.out.println("Failed to delete the file.");
                }
                saveFile(banImage, request); // 서버에 사진 저장

                bannerDto.setBanImg("/files/banner/" + banImage.getOriginalFilename()); // 새로운 이미지이름으로 dto객체의 이미지이름 저장
            }
            bannerService.editItem(bannerDto); // db에 저장
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/admin/banner-data-table.do";
    }

    private String saveFile(MultipartFile file, HttpServletRequest request) {
        String root_path = request.getSession().getServletContext().getRealPath("/");
        String attach_path = "files/banner/";
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
