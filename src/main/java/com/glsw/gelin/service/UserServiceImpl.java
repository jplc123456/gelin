package com.glsw.gelin.service;

import com.glsw.gelin.dao.UserRepository;
import com.glsw.gelin.po.User;
import com.glsw.gelin.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * @program: gelin
 * @description:
 * @author: 作者
 * @create: 2020-12-09 18:32
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }

    @Override
    public User idUser(Long id) {
        return userRepository.findById(id).get();
    }

    @Transactional
    @Override
    public Page<User> selectUser(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public void update(User user) {
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setType(2);
        user.setPassword(MD5Utils.code(user.getPassword()));
        userRepository.save(user);
    }
    @Transactional
    @Override
    public void updatePassword(User user) {
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
