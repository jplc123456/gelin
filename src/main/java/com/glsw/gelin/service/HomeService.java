package com.glsw.gelin.service;

import com.glsw.gelin.po.Home;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HomeService {
    Page<Home> listHome(Pageable pageable);
    Home getHome(Long id);
    Home saveHome(Home home);
    Home updateHome(Long id, Home home);
    void deleteHome(Long id);
}
