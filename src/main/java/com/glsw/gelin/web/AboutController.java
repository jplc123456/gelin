package com.glsw.gelin.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @program: gelin
 * @description:
 * @author: 作者
 * @create: 2020-12-17 14:50
 */
@Controller
public class AboutController {

    @GetMapping("/about")
    public String about(){
        return "about";
    }
}
