package com.glsw.gelin.service;

import com.glsw.gelin.NotFoundException;
import com.glsw.gelin.dao.HomeRepository;
import com.glsw.gelin.po.Home;
import com.glsw.gelin.util.MyBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @program: gelin
 * @description:
 * @author: 作者
 * @create: 2020-12-31 11:09
 */
@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private HomeRepository homeRepository;

    @Override
    public Page<Home> listHome(Pageable pageable) {
        return homeRepository.findAll(pageable);
    }

    @Override
    public Home getHome(Long id) {
        return homeRepository.findById(id).get();
    }

    @Override
    public Home saveHome(Home home) {
        return homeRepository.save(home);
    }

    @Override
    public Home updateHome(Long id, Home home) {
        Home h = homeRepository.findById(id).get();
        if(h == null){
            throw new NotFoundException("该活动不存在！");
        }
        BeanUtils.copyProperties(home,h, MyBeanUtils.getUnllPropertyNames(home));
        return homeRepository.save(h);
    }

    @Override
    public void deleteHome(Long id) {
        homeRepository.deleteById(id);
    }
}
