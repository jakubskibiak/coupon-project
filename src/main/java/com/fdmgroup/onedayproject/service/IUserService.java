package com.fdmgroup.onedayproject.service;

import java.util.List;

import com.fdmgroup.onedayproject.exception.CouponsExceededException;
import com.fdmgroup.onedayproject.exception.TotalPriceToLowException;
import com.fdmgroup.onedayproject.model.Coupon;
import com.fdmgroup.onedayproject.model.User;

public interface IUserService {

    List<Coupon> getUserCoupons(String userName);

    User getUserDetails(String userName) throws Exception;

    Coupon redeemCouponForUser(String username, String code) throws Exception, CouponsExceededException, TotalPriceToLowException;
}
