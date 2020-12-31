package com.glsw.gelin.web.admin;

import com.glsw.gelin.po.Home;
import com.glsw.gelin.service.HomeService;
import com.glsw.gelin.util.UploadUtils;
import com.linkinstars.springBootTemplate.util.FileHandleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;

/**
 * @program: gelin
 * @description:
 * @author: 作者
 * @create: 2020-12-31 11:27
 */
@Controller
@RequestMapping("/admin")
public class HomeController {

    @Autowired
    private HomeService homeService;

    @GetMapping("/listhomes")
    public String listcards(@PageableDefault(size = 1,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable,
                            Model model){
        model.addAttribute("page",homeService.listHome(pageable));
        return "admin/homes";
    }



    @PostMapping("/homes/inputs")
    public String post(Home home, @RequestParam("file") MultipartFile file, RedirectAttributes attributes, HttpSession session) throws IOException {
        System.out.println(file);
        //获取文件的内容
        InputStream is = file.getInputStream();
        //获取原始文件名
        String originalFilename = file.getOriginalFilename();
        //生成一个uuid名称出来
        String uuidFilename = UploadUtils.getUUIDName(originalFilename);

        String savePath = FileHandleUtil.upload(is,"video/",uuidFilename);

        //设置宣传视频路径路径
        home.setPublicityVideo(savePath);

        Home h;
        if(home.getId() == null){
            h = homeService.saveHome(home);
        }else{
            h = homeService.updateHome(home.getId(),home);
        }
        if(h == null){
            //保存不成功
            attributes.addFlashAttribute("message","操作失败");
        }else{
            //保存成功
            attributes.addFlashAttribute("message","操作成功");
        }


        return "redirect:/admin/listhomes";
    }


    @GetMapping("/homes/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        homeService.deleteHome(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/listhomes";
    };

    @GetMapping("/homes/input")
    public String input(Model model){
        model.addAttribute("home",new Home());
        return "admin/homes-input";
    }


    @GetMapping("/homes/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("home",homeService.getHome(id));
        return "admin/homes-input";
    }



}
