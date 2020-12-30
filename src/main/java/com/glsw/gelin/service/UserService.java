package com.glsw.gelin.service;

import com.glsw.gelin.po.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    User checkUser(String username,String password);
    User idUser(Long id);
    Page<User> selectUser(Pageable pageable);
    void update(User user);
    void updatePassword(User user);
    void deleteUser(Long id);
}
