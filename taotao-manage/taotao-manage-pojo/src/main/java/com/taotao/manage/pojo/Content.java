package com.taotao.manage.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_content")
public class Content extends BasePojo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_id")
    private Long categoryId;

    private String title;

    @Column(name = "sub_title")
    private String subTitle;

    @Column(name = "title_desc")
    private String titleDesc;

    private String url;

    private String pic;

    private String pic2;

    private String content;

    public Long getId() {
        return id;
    }

    public Content setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public Content setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Content setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public Content setSubTitle(String subTitle) {
        this.subTitle = subTitle;
        return this;
    }

    public String getTitleDesc() {
        return titleDesc;
    }

    public Content setTitleDesc(String titleDesc) {
        this.titleDesc = titleDesc;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Content setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getPic() {
        return pic;
    }

    public Content setPic(String pic) {
        this.pic = pic;
        return this;
    }

    public String getPic2() {
        return pic2;
    }

    public Content setPic2(String pic2) {
        this.pic2 = pic2;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Content setContent(String content) {
        this.content = content;
        return this;
    }
}
