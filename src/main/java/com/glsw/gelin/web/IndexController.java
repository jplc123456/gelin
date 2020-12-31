package com.glsw.gelin.web;

import com.glsw.gelin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: gelin
 * @description:
 * @author: 罗成
 * @create: 2020-12-04 12:14
 */
@Controller
public class IndexController {


    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @Autowired
    private HomeService homeService;

    @Autowired
    private CardService cardService;

    @GetMapping("/")
    public String home(@PageableDefault(size = 1,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable,Model model){
        model.addAttribute("home",homeService.listHome(pageable));
        model.addAttribute("page",cardService.listCard(pageable));
        model.addAttribute("blog",blogService.listBlogTop(1));
        return "home";
    }


    @GetMapping("/index")
    public String index(@PageableDefault(size = 10,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                        Model model){
        model.addAttribute("page",blogService.listBlog(pageable));
        model.addAttribute("types",typeService.listTypeTop(6));
        model.addAttribute("tags",tagService.listTagTop(10));
        model.addAttribute("recommendBlogs",blogService.listBlogTop(8));

        return "index";
    }


    @PostMapping("/search")
    public String search(@PageableDefault(size = 10,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                         @RequestParam String query, Model model){
        model.addAttribute("page",blogService.listBlog("%"+query+"%", pageable));
        model.addAttribute("query",query);
        return "search";
    }



    @GetMapping("/blogs/{id}")
    public String blog(@PathVariable Long id, Model model){
        model.addAttribute("blog",blogService.getAndConvert(id));
        return "blog";
    }


    @GetMapping("/footer/newblog")
    public String newblogs(Model model){
        model.addAttribute("newblogs",blogService.listBlogTop(3));
        return "_fragments :: newblogList";
    }

}
