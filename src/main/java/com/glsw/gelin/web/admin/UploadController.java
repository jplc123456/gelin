package com.glsw.gelin.web.admin;

import com.glsw.gelin.po.User;
import com.glsw.gelin.service.UserService;
import com.glsw.gelin.util.UploadUtils;
import com.linkinstars.springBootTemplate.util.FileHandleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;

/**
 * @program: gelin
 * @description:
 * @author: 作者
 * @create: 2020-12-18 13:13
 */

@Controller
@RequestMapping("/user")
public class UploadController {
    @Autowired
    private UserService userService;

    private static final String LIST = "user/users";



    @GetMapping("/register")
    public String register(){
        return "admin/register";
    }


    @PostMapping("/upload")
    public String upload(User user,@RequestParam("file") MultipartFile file, HttpSession session) throws IOException {
        //获取文件的内容
        InputStream is = file.getInputStream();
        //获取原始文件名
        String originalFilename = file.getOriginalFilename();
        //生成一个uuid名称出来
        String uuidFilename = UploadUtils.getUUIDName(originalFilename);

        String savePath = FileHandleUtil.upload(is,"image/",uuidFilename);

        //设置头像图片路径
        user.setAvatar(savePath);

        //调用业务更新user
        userService.update(user);
        String username = user.getUsername();
        String password = user.getPassword();
        User user1 = userService.checkUser(username, password);
        if (user != null){
            user.setPassword(null);
            session.setAttribute("user",user);
            return "redirect:/";
        }else{

        }
        //生成响应 : 跳转去用户详情页面
        return "redirect:/index";
    }
}