package com.glsw.gelin.web.admin;

import com.glsw.gelin.po.Card;
import com.glsw.gelin.service.CardService;
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
 * @create: 2020-12-29 10:51
 */
@Controller
@RequestMapping("/admin")
public class CardController {

    @Autowired
    private CardService cardService;

    @GetMapping("/cards")
    public String cards(@PageableDefault(size = 6,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                        Model model){
        model.addAttribute("page",cardService.listCard(pageable));
        return "card";
    }

    @GetMapping("/listcards")
    public String listcards(@PageableDefault(size = 10,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                        Model model){
        model.addAttribute("page",cardService.listCard(pageable));
        return "admin/cards";
    }

    @PostMapping("/cards/inputs")
    public String post(Card card, @RequestParam("file") MultipartFile file, RedirectAttributes attributes, HttpSession session) throws IOException {

        //获取文件的内容
        InputStream is = file.getInputStream();
        //获取原始文件名
        String originalFilename = file.getOriginalFilename();

        //生成一个uuid名称出来
        String uuidFilename = UploadUtils.getUUIDName(originalFilename);

        String savePath = FileHandleUtil.upload(is,"image/",uuidFilename);

        //设置头像图片路径
        card.setPortrait(savePath);

        Card c;
        if(card.getId() == null){
            c = cardService.saveCard(card);
        }else{
            c = cardService.updateCard(card.getId(),card);
        }
        if(c == null){
            //保存不成功
            attributes.addFlashAttribute("message","操作失败");
        }else{
            //保存成功
            attributes.addFlashAttribute("message","操作成功");
        }


        return "redirect:/admin/listcards";
    }

    @GetMapping("/cards/input")
    public String input(Model model){
        model.addAttribute("card",new Card());
        return "admin/cards-input";
    }


    @GetMapping("/cards/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("card",cardService.getCard(id));
        return "admin/cards-input";
    }


    @GetMapping("/cards/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        cardService.deleteCard(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/cards";
    };


    @PostMapping("/cards/search")
    public String search(@PageableDefault(size = 10,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                         String name,
                         Model model){
        model.addAttribute("page",cardService.listCardAndName(name,pageable));
        return "admin/cards :: cardList";
    }


}
