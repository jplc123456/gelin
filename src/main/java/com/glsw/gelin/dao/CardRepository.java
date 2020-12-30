package com.glsw.gelin.dao;

import com.glsw.gelin.po.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CardRepository extends JpaRepository<Card,Long> {
    @Query("select c from Card c where c.name like CONCAT('%',?1,'%') or ?1 is null")
    Page<Card> findSearch(String name, Pageable pageable);
}
