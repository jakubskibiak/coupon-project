package com.fdmgroup.onedayproject.service;

import com.fdmgroup.onedayproject.exception.CouponNotFoundException;
import com.fdmgroup.onedayproject.exception.CouponsExceeded;
import com.fdmgroup.onedayproject.exception.TotalPriceToLowException;
import com.fdmgroup.onedayproject.exception.UserNotFoundException;
import com.fdmgroup.onedayproject.model.Coupon;
import com.fdmgroup.onedayproject.model.User;
import com.fdmgroup.onedayproject.repository.CouponRepository;
import com.fdmgroup.onedayproject.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final CouponRepository couponRepository;

    public UserServiceImpl(UserRepository userRepository, CouponRepository couponRepository) {
        this.userRepository = userRepository;
        this.couponRepository = couponRepository;
    }

    @Override
    public List<Coupon> getUserCoupons(String userName) {
        return couponRepository.findAllByUserUsernameIgnoreCase(userName);
    }

    @Override
    public User getUserDetails(String userName) throws Exception {
        return userRepository.findUserByUsernameIgnoreCase(userName).orElseThrow(() -> new Exception("No such user"));
    }

    @Override
    public Coupon redeemCouponForUser(String username, String code) throws Exception, CouponsExceeded, TotalPriceToLowException {
        Coupon coupon = couponRepository.findByUserUsernameIgnoreCaseAndCodeIgnoreCase(username, code).orElseThrow(() -> new CouponNotFoundException("Coupon not found"));
        User user = userRepository.findUserByUsernameIgnoreCase(username).orElseThrow(() -> new UserNotFoundException("User not found"));
        validateIfPossibleToRedeem(user, coupon);
        coupon.setAllowedUsages(coupon.getAllowedUsages() - 1);
        coupon.setCurrentUsages(coupon.getCurrentUsages() + 1);
        user.setTotalPrice(user.getTotalPrice() - coupon.getValue());
        couponRepository.save(coupon);
        userRepository.save(user);
        return coupon;

    }

    private void validateIfPossibleToRedeem(User user, Coupon coupon) throws TotalPriceToLowException, CouponsExceeded {
        if (user.getTotalPrice() < coupon.getValue()) {
            throw new TotalPriceToLowException("Total price is too low. Coupon value needs to lower than total price");
        }
        if (coupon.getAllowedUsages() <= 0) {
            throw new CouponsExceeded("Exceeded allowed coupon usages");
        }
    }
}
