package com.glsw.gelin.web;

import com.glsw.gelin.po.Comment;
import com.glsw.gelin.po.User;
import com.glsw.gelin.service.BlogService;
import com.glsw.gelin.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @program: gelin
 * @description:
 * @author: 作者
 * @create: 2020-12-15 17:31
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;


    @Autowired
    private BlogService blogService;

    @Value("${comment.avatar}")
    private String avatar;

    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model){
        model.addAttribute("comments",commentService.listCommentByBlogId(blogId));
        return "blog :: commentList";
    }

    @PostMapping("/comments")
    public String post(Comment comment, RedirectAttributes attributes, HttpSession session){
        Long id = comment.getBlog().getId();
        comment.setBlog(blogService.getBlog(id));
        User user = (User) session.getAttribute("user");
        if(user != null){
            if(user.getType() == 1){
                comment.setAdminComment(true);
//                comment.setNickname(user.getNickname());
            }
        }
        comment.setAvatar(user.getAvatar());
        comment.setEmail(user.getEmail());
        comment.setNickname(user.getNickname());
        commentService.saveComment(comment);
        return "redirect:/comments/" + id;
    }
}
