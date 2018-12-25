package com.imooc.web.admin;

import com.imooc.base.ApiResponse;
import com.imooc.web.form.HouseForm;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@Controller
public class AdminController {
    @GetMapping("/admin/center")
    public String adminCenter(){
        return "/admin/center";
    }
    @GetMapping("/admin/welcome")
    public String adminWelcome(){
        return "/admin/welcome";
    }
    @GetMapping("/admin/login")
    public String adminLogin(){
        return "/admin/login";
    }
    /**
     * 新增房源功能页
     * @return
     */
    @GetMapping("/admin/add/house")
    public String addHousePage() {
        return "admin/house-add";
    }
    /**
     * 房源管理
     * @return
     */
    @GetMapping("/admin/house/list")
    public String listHousePage() {
        return "admin/house-list";
    }
    /**
     * 预约管理
     * @return
     */
    @GetMapping("/admin/house/subscribe")
    public String subscribeHousePage() {
        return "admin/subscribe";
    }
    /*
    *
    *
    * 图片上传
    *
    * */
    @PostMapping(value = "admin/upload/photo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public ApiResponse uploadPhoto(@RequestParam("file") MultipartFile file) {

        if (file.isEmpty()){
            return ApiResponse.ofStatus(ApiResponse.Status.NOT_VALID_PARAM);
        }
        String filename = file.getOriginalFilename();
        File target = new File("E:/picture/"+filename);
        try {
            file.transferTo(target);
        } catch (IOException e) {
            return ApiResponse.ofStatus(ApiResponse.Status.INTERNAL_SERVER_ERROR);
        }
        return ApiResponse.ofSuccess(null);
    }
    /*
    *
    * 新增房源
    *
    * */
    @RequestMapping("admin/add/house")
    @ResponseBody
    public ApiResponse addHouse(@Valid @ModelAttribute("form-house-add")HouseForm houseForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ApiResponse(HttpStatus.BAD_REQUEST.value(),bindingResult.getAllErrors().get(0).getDefaultMessage(),null);
        }
        if (houseForm.getPhotos()==null || houseForm.getCover()==null){
            return ApiResponse.ofMessage(HttpStatus.BAD_REQUEST.value(),"必须上传图片");
        }


        return null;
    }


}










































