package com.glsw.gelin.web;

import com.glsw.gelin.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @program: gelin
 * @description:
 * @author: 作者
 * @create: 2020-12-17 14:06
 */
@Controller
public class ArchivesShowController {


    @Autowired
    private BlogService blogService;



    @GetMapping("/archives")
    public String archives(Model model){
        model.addAttribute("archiveMap",blogService.archiveBlog());
        model.addAttribute("archiveCount",blogService.countBlog());
        return "archives";
    }
}
