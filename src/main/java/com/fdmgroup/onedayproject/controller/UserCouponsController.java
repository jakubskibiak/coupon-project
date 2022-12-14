package com.fdmgroup.onedayproject.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fdmgroup.onedayproject.exception.CouponNotFoundException;
import com.fdmgroup.onedayproject.exception.CouponsExceededException;
import com.fdmgroup.onedayproject.exception.DetailedException;
import com.fdmgroup.onedayproject.exception.TotalPriceToLowException;
import com.fdmgroup.onedayproject.exception.UserNotFoundException;
import com.fdmgroup.onedayproject.model.Coupon;
import com.fdmgroup.onedayproject.model.User;
import com.fdmgroup.onedayproject.service.IUserService;

@Controller
public class UserCouponsController {

    private final IUserService userService;

    public UserCouponsController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/details")
    public String details(ModelMap model) {
        return "/details";
    }

    @PostMapping("/search-details")
    public String searchDetails(ModelMap model, String username) throws Exception {
        User userDetails = userService.getUserDetails(username);
        List<Coupon> userCoupons = userService.getUserCoupons(username);
        model.addAttribute("userDetails", userDetails);
        model.addAttribute("userCoupons", userCoupons);
        return "/details";
    }

    @GetMapping("/redeem-coupon")
    public String redeemCoupon() {
        return "/redeem";
    }

    @PostMapping("/redeem-coupon")
    public String redeemCoupon(ModelMap model, String username, String code) throws Exception {
        Coupon coupon = userService.redeemCouponForUser(username, code);
        model.addAttribute("coupon", coupon);
        return "/success";
    }

    @ExceptionHandler({ CouponNotFoundException.class, CouponsExceededException.class, TotalPriceToLowException.class, UserNotFoundException.class })
    public ModelAndView handleError(Exception ex) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", ex instanceof DetailedException ? ((DetailedException) ex).getMessageToDisplay() : ex.getMessage());
        mav.setViewName("error");
        return mav;
    }
}
