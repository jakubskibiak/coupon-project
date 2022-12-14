package com.fdmgroup.onedayproject.dataimport;

import com.fdmgroup.onedayproject.model.Coupon;
import com.fdmgroup.onedayproject.model.User;
import com.fdmgroup.onedayproject.repository.CouponRepository;
import com.fdmgroup.onedayproject.repository.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataImport implements ApplicationRunner {

    private final UserRepository userRepository;
    private final CouponRepository couponRepository;

    public DataImport(UserRepository userRepository, CouponRepository couponRepository) {
        this.userRepository = userRepository;
        this.couponRepository = couponRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User userOfMagda = new User(null, "username_1", "Magda", "Palica", "magda@palica.pl", 10000.0);
        User userOfRobert = new User(null, "username_2", "Robert", "Palica", "robert@palica.pl", 10000.0);
        userRepository.saveAll(List.of(userOfMagda, userOfRobert));

        Coupon couponNr1 = new Coupon(null, "WAKACJE_A", 10.0, 10, 0, userOfMagda);
        Coupon couponNr2 = new Coupon(null, "SALE_A", 11.0, 10, 0, userOfMagda);
        Coupon couponNr3 = new Coupon(null, "SALE_X", 12.0, 10, 0, userOfMagda);
        Coupon couponNr4 = new Coupon(null, "WAKACJE_B", 10.0, 10, 0, userOfRobert);
        Coupon couponNr5 = new Coupon(null, "SALE_B", 11.0, 10, 0, userOfRobert);
        Coupon couponNr6 = new Coupon(null, "SALE_Y", 12.0, 10, 0, userOfRobert);
        couponRepository.saveAll(List.of(couponNr1, couponNr2, couponNr3, couponNr4, couponNr5, couponNr6));
    }

}
