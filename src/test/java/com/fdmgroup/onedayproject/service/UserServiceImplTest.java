
package com.fdmgroup.onedayproject.service;

import com.fdmgroup.onedayproject.model.Coupon;
import com.fdmgroup.onedayproject.model.User;
import com.fdmgroup.onedayproject.repository.CouponRepository;
import com.fdmgroup.onedayproject.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private CouponRepository mockCouponRepository;

    private UserServiceImpl userServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        userServiceImplUnderTest = new UserServiceImpl(mockUserRepository, mockCouponRepository);
    }

    @Test
    void testGetUserCoupons() {
        // Setup
        // Configure CouponRepository.findAllByUserUsernameIgnoreCase(...).
        final List<Coupon> coupons = List.of(new Coupon(0, "code", 0.0, 0, 0, new User(0, "username", "firstName", "lastName", "email", 0.0)));
        when(mockCouponRepository.findAllByUserUsernameIgnoreCase("userName")).thenReturn(coupons);

        // Run the test
        final List<Coupon> result = userServiceImplUnderTest.getUserCoupons("userName");

        // Verify the results
        Assertions.assertNotNull(result);
    }

}
