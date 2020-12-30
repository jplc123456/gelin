package com.glsw.gelin.service;

import com.glsw.gelin.NotFoundException;
import com.glsw.gelin.dao.CardRepository;
import com.glsw.gelin.po.Card;
import com.glsw.gelin.util.MyBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * @program: gelin
 * @description:
 * @author: 作者
 * @create: 2020-12-29 10:36
 */
@Service
public class CardServiceImpl implements CardService{

    @Autowired
    private CardRepository cardRepository;



    @Override
    public Card getCard(Long id) {
        return cardRepository.findById(id).get();
    }

    @Override
    public Page<Card> listCard(Pageable pageable) {
        return cardRepository.findAll(pageable);
    }

    @Override
    public Page<Card> listCardAndName( String name,Pageable pageable) {
        return cardRepository.findSearch(name,pageable);
    }

    @Override
    public Card saveCard(Card card) {
        if (card.getId() == null){
            card.setCreateTime(new Date());
            card.setUpdateTime(new Date());
        }else{
            card.setUpdateTime(new Date());
        }
        return cardRepository.save(card);
    }

    @Transactional
    @Override
    public Card updateCard(Long id, Card card) {
        Card c = cardRepository.findById(id).get();
        if(c == null){
            throw new NotFoundException("该活动不存在！");
        }
        BeanUtils.copyProperties(card,c, MyBeanUtils.getUnllPropertyNames(card));
        c.setUpdateTime(new Date());
        return cardRepository.save(c);
    }
    @Transactional
    @Override
    public void deleteCard(Long id) {
        cardRepository.deleteById(id);
    }

}
