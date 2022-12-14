package com.fdmgroup.onedayproject.service;

import com.fdmgroup.onedayproject.exception.CouponsExceeded;
import com.fdmgroup.onedayproject.exception.TotalPriceToLowException;
import com.fdmgroup.onedayproject.model.Coupon;
import com.fdmgroup.onedayproject.model.User;

import java.util.List;

public interface IUserService {

    List<Coupon> getUserCoupons(String userName);

    User getUserDetails(String userName) throws Exception;

    Coupon redeemCouponForUser(String username, String code) throws Exception, CouponsExceeded, TotalPriceToLowException;
}
