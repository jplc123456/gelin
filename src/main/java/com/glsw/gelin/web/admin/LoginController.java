package com.glsw.gelin.web.admin;

import com.glsw.gelin.po.User;
import com.glsw.gelin.service.UserService;
import com.glsw.gelin.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @program: gelin
 * @description:
 * @author: 作者
 * @create: 2020-12-09 18:40
 */
@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;


    @GetMapping
    public String loginPage(){
        return "admin/login";
    }

    @GetMapping("/uppswd")
    public String uppswd(){
        return "admin/uppswd";
    }


    @PostMapping("/upuserpassword")
    public String upuserpassword(@RequestParam String password1,
                        @RequestParam String password2,
                        @RequestParam String password3,
                        HttpSession session,
                        RedirectAttributes attributes){
        User user = (User) session.getAttribute("user");
        Long id = user.getId();
        User user1 = userService.idUser(id);
        String a = MD5Utils.code(password1);
        String b = user1.getPassword();
        if(a.equals(b) != true){
            attributes.addFlashAttribute("message","原始密码错误！");
            return "redirect:/admin/uppswd";
        }else if(password2.equals(password3) != true){
            attributes.addFlashAttribute("message","两次旧密码不相等！");
            return "redirect:/admin/uppswd";
        }else{
            user1.setPassword(MD5Utils.code(password2));
            userService.updatePassword(user1);
            return "redirect:/";
        }

    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes){
        User user = userService.checkUser(username, password);
        if (user != null){
            user.setPassword(null);
            session.setAttribute("user",user);
            if (user.getType() == 1){
                return "admin/index";
            }else {
                return "redirect:/";
            }
        }else{
            attributes.addFlashAttribute("message","用户名和密码错误！");
            return "redirect:/admin";
        }

    }
    @GetMapping("/users")
    public String users(@PageableDefault(size = 10,sort = {"createTime"}
            ,direction = Sort.Direction.DESC) Pageable pageable
            , Model model){
        Page<User> uu = userService.selectUser(pageable);
        model.addAttribute("page",uu);
        return "admin/users";
    }

    @GetMapping("/users/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        userService.deleteUser(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/users";
    }


    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }

}
