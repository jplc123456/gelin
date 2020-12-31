package com.glsw.gelin.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @program: gelin
 * @description:
 * @author: 作者
 * @create: 2020-12-31 10:28
 */
@Entity
@Table(name = "t_home")
public class Home {
    @Id
    @GeneratedValue
    private Long id;
    private String publicityVideo;
    private String title1;
    private String content1;
    private String title2;
    private String content2;

    public Home() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPublicityVideo() {
        return publicityVideo;
    }

    public void setPublicityVideo(String publicityVideo) {
        this.publicityVideo = publicityVideo;
    }

    public String getTitle1() {
        return title1;
    }

    public void setTitle1(String title1) {
        this.title1 = title1;
    }

    public String getContent1() {
        return content1;
    }

    public void setContent1(String content1) {
        this.content1 = content1;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public String getContent2() {
        return content2;
    }

    public void setContent2(String content2) {
        this.content2 = content2;
    }

    @Override
    public String toString() {
        return "Home{" +
                "id=" + id +
                ", publicityVideo='" + publicityVideo + '\'' +
                ", title1='" + title1 + '\'' +
                ", content1='" + content1 + '\'' +
                ", title2='" + title2 + '\'' +
                ", content2='" + content2 + '\'' +
                '}';
    }
}
