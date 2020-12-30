package com.glsw.gelin.po;

import javax.persistence.*;
import java.util.Date;

/**
 * @program: gelin
 * @description:
 * @author: 作者
 * @create: 2020-12-29 10:23
 */
@Entity
@Table(name = "t_card")
public class Card {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String portrait;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    /*从业年数*/
    private String workingTime;
    /*详细介绍*/
    private String detailed;
    /*简介*/
    private String brief;


    public Card() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(String workingTime) {
        this.workingTime = workingTime;
    }

    public String getDetailed() {
        return detailed;
    }

    public void setDetailed(String detailed) {
        this.detailed = detailed;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", portrait='" + portrait + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", workingTime='" + workingTime + '\'' +
                ", detailed='" + detailed + '\'' +
                ", brief='" + brief + '\'' +
                '}';
    }
}
