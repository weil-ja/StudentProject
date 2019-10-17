package com.taotao.sso.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(min = 6, max = 20, message = "用户名的长度在6-20之间")
    private String username;

    @JsonIgnore //序列化时忽略该字段
    @Length(min = 6, max = 20, message = "密码的长度在6-20之间")
    private String password;

    @Length(min = 11, max = 11, message = "手机号的长度必须是11位")
    private String phone;

    @Email(message = "邮箱地址格式不正确")
    private String email;

    private Date created;

    private Date updated;

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public User setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public Date getCreated() {
        return created;
    }

    public User setCreated(Date created) {
        this.created = created;
        return this;
    }

    public Date getUpdated() {
        return updated;
    }

    public User setUpdated(Date updated) {
        this.updated = updated;
        return this;
    }
}
