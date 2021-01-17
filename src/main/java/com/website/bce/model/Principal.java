package com.website.bce.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="principals")
public class Principal {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private String subject;
    private String message;
    private String sendingTime;
    private String os;
    private String ip;

    public Principal(String name, String email, String subject, String message, String sendingTime) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.subject = subject;
        this.message = message;
        this.sendingTime = sendingTime;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSendingTime() {
        return sendingTime;
    }

    public void setSendingTime(String sendingTime) {
        this.sendingTime = sendingTime;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
