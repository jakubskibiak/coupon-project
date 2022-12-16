package com.fdmgroup.onedayproject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fdmgroup.onedayproject.exception.CouponNotFoundException;
import com.fdmgroup.onedayproject.exception.CouponsExceededException;
import com.fdmgroup.onedayproject.exception.TotalPriceToLowException;
import com.fdmgroup.onedayproject.exception.UserNotFoundException;
import com.fdmgroup.onedayproject.model.Coupon;
import com.fdmgroup.onedayproject.model.User;
import com.fdmgroup.onedayproject.repository.CouponRepository;
import com.fdmgroup.onedayproject.repository.UserRepository;

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
    public User getUserDetails(String userName) throws UserNotFoundException {
        return userRepository.findUserByUsernameIgnoreCase(userName).orElseThrow(() -> new UserNotFoundException("No such user", "There is no user " + userName));
    }

    @Override
    public Coupon redeemCouponForUser(String username, String code) throws Exception {
        Coupon coupon = couponRepository
            .findByUserUsernameIgnoreCaseAndCodeIgnoreCase(username, code)
            .orElseThrow(() -> new CouponNotFoundException("Coupon not found", "Coupon with name " + code + " not found"));
        User user = userRepository.findUserByUsernameIgnoreCase(username).orElseThrow(() -> new UserNotFoundException("User not found", "User " + username + " does not exists"));
        validateIfPossibleToRedeem(user, coupon);
        coupon.setAllowedUsages(coupon.getAllowedUsages() - 1);
        coupon.setCurrentUsages(coupon.getCurrentUsages() + 1);
        user.setTotalPrice(user.getTotalPrice() - coupon.getValue());
        couponRepository.save(coupon);
        userRepository.save(user);
        return coupon;

    }

    private void validateIfPossibleToRedeem(User user, Coupon coupon) throws TotalPriceToLowException, CouponsExceededException {
        if (user.getTotalPrice() < coupon.getValue()) {
            throw new TotalPriceToLowException("Total price exception", "Total price is too low. Coupon value needs to lower than total price");
        }
        if (coupon.getAllowedUsages() <= 0) {
            throw new CouponsExceededException("Coupon exceeded", "Exceeded allowed coupon usages");
        }
    }
}
