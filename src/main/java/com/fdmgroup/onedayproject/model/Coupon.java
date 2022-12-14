package com.fdmgroup.onedayproject.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "COUPONS"
//        , uniqueConstraints = { @UniqueConstraint(columnNames = { "code", "users_user_id" }) }
)
public class Coupon {

    @Id
    @GeneratedValue
    @Column(name = "coupon_id")
    private Integer id;

    private String code;
    private double value;
    private int allowedUsages;
    private int currentUsages;
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    public Coupon() {
    }
    public Coupon(Integer id, String code, double value, int allowedUsages, int currentUsages, User user) {
        this.id = id;
        this.code = code;
        this.value = value;
        this.allowedUsages = allowedUsages;
        this.currentUsages = currentUsages;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getAllowedUsages() {
        return allowedUsages;
    }

    public void setAllowedUsages(int allowedUsages) {
        this.allowedUsages = allowedUsages;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCurrentUsages() {
        return currentUsages;
    }

    public void setCurrentUsages(int currentUsages) {
        this.currentUsages = currentUsages;
    }
}
