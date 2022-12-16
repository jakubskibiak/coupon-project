
package com.fdmgroup.onedayproject.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.onedayproject.exception.CouponNotFoundException;
import com.fdmgroup.onedayproject.exception.CouponsExceededException;
import com.fdmgroup.onedayproject.exception.TotalPriceToLowException;
import com.fdmgroup.onedayproject.exception.UserNotFoundException;
import com.fdmgroup.onedayproject.model.Coupon;
import com.fdmgroup.onedayproject.model.User;
import com.fdmgroup.onedayproject.repository.CouponRepository;
import com.fdmgroup.onedayproject.repository.UserRepository;

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
        final List<Coupon> coupons = List.of(prepareExemplaryCoupon());
        when(mockCouponRepository.findAllByUserUsernameIgnoreCase("userName")).thenReturn(coupons);

        // Run the test
        final List<Coupon> result = userServiceImplUnderTest.getUserCoupons("userName");

        // Verify the results
    }

    private Coupon prepareExemplaryCoupon() {
        return new Coupon(0, "code", 10.0, 10, 10, prepareExemplaryUser());
    }

    @Test
    void testGetUserCoupons_CouponRepositoryReturnsNoItems() {
        // Setup
        when(mockCouponRepository.findAllByUserUsernameIgnoreCase("userName")).thenReturn(Collections.emptyList());

        // Run the test
        final List<Coupon> result = userServiceImplUnderTest.getUserCoupons("userName");

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetUserDetails() throws Exception {
        // Setup
        final User expectedResult = prepareExemplaryUser();

        // Configure UserRepository.findUserByUsernameIgnoreCase(...).
        final Optional<User> user = Optional.of(prepareExemplaryUser());
        when(mockUserRepository.findUserByUsernameIgnoreCase("userName")).thenReturn(user);

        // Run the test
        final User result = userServiceImplUnderTest.getUserDetails("userName");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetUserDetails_UserRepositoryReturnsAbsent() {
        // Setup
        when(mockUserRepository.findUserByUsernameIgnoreCase("userName")).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> userServiceImplUnderTest.getUserDetails("userName")).isInstanceOf(Exception.class);
    }

    @Test
    void testRedeemCouponForUser() throws Exception, CouponsExceededException, TotalPriceToLowException {
        // Setup
        // Configure CouponRepository.findByUserUsernameIgnoreCaseAndCodeIgnoreCase(...).
        final Optional<Coupon> coupon = Optional.of(prepareExemplaryCoupon());
        coupon.get().setAllowedUsages(1);
        when(mockCouponRepository.findByUserUsernameIgnoreCaseAndCodeIgnoreCase("username", "code")).thenReturn(coupon);

        // Configure UserRepository.findUserByUsernameIgnoreCase(...).
        final Optional<User> user = Optional.of(prepareExemplaryUser());
        user.get().setTotalPrice(10000.0);
        when(mockUserRepository.findUserByUsernameIgnoreCase("username")).thenReturn(user);

        // Configure CouponRepository.save(...).
        final Coupon coupon1 = prepareExemplaryCoupon();
        when(mockCouponRepository.save(any(Coupon.class))).thenReturn(coupon1);

        // Configure UserRepository.save(...).
        final User user1 = prepareExemplaryUser();
        when(mockUserRepository.save(any(User.class))).thenReturn(user1);

        // Run the test
        final Coupon result = userServiceImplUnderTest.redeemCouponForUser("username", "code");

        // Verify the results
        verify(mockCouponRepository).save(any(Coupon.class));
        verify(mockUserRepository).save(any(User.class));
    }

    private User prepareExemplaryUser() {
        return new User(0, "username", "firstName", "lastName", "email", 0.0);
    }

    @Test
    void testRedeemCouponForUser_CouponRepositoryFindByUserUsernameIgnoreCaseAndCodeIgnoreCaseReturnsAbsent() {
        // Setup
        when(mockCouponRepository.findByUserUsernameIgnoreCaseAndCodeIgnoreCase("username", "code")).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> userServiceImplUnderTest.redeemCouponForUser("username", "code")).isInstanceOf(CouponNotFoundException.class);
    }

    @Test
    void testRedeemCouponForUser_UserRepositoryFindUserByUsernameIgnoreCaseReturnsAbsent() {
        // Setup
        // Configure CouponRepository.findByUserUsernameIgnoreCaseAndCodeIgnoreCase(...).
        final Optional<Coupon> coupon = Optional.of(prepareExemplaryCoupon());
        when(mockCouponRepository.findByUserUsernameIgnoreCaseAndCodeIgnoreCase("username", "code")).thenReturn(coupon);

        when(mockUserRepository.findUserByUsernameIgnoreCase("username")).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> userServiceImplUnderTest.redeemCouponForUser("username", "code")).isInstanceOf(UserNotFoundException.class);
    }


    @Test
    void testRedeemCouponForUser_ThrowsCouponsExceeded() {
        // Setup
        // Configure CouponRepository.findByUserUsernameIgnoreCaseAndCodeIgnoreCase(...).
        final Optional<Coupon> coupon = Optional.of(prepareExemplaryCoupon());
        coupon.get().setAllowedUsages(0);
        when(mockCouponRepository.findByUserUsernameIgnoreCaseAndCodeIgnoreCase("username", "code")).thenReturn(coupon);

        // Configure UserRepository.findUserByUsernameIgnoreCase(...).
        final Optional<User> user = Optional.of(prepareExemplaryUser());
        user.get().setTotalPrice(1000.0);
        when(mockUserRepository.findUserByUsernameIgnoreCase("username")).thenReturn(user);

        // Run the test
        assertThatThrownBy(() -> userServiceImplUnderTest.redeemCouponForUser("username", "code")).isInstanceOf(CouponsExceededException.class);
    }

    @Test
    void testRedeemCouponForUser_ThrowsTotalPriceToLowException() {
        // Setup
        // Configure CouponRepository.findByUserUsernameIgnoreCaseAndCodeIgnoreCase(...).
        final Optional<Coupon> coupon = Optional.of(prepareExemplaryCoupon());
        when(mockCouponRepository.findByUserUsernameIgnoreCaseAndCodeIgnoreCase("username", "code")).thenReturn(coupon);

        // Configure UserRepository.findUserByUsernameIgnoreCase(...).
        final Optional<User> user = Optional.of(prepareExemplaryUser());
        when(mockUserRepository.findUserByUsernameIgnoreCase("username")).thenReturn(user);

        // Run the test
        assertThatThrownBy(() -> userServiceImplUnderTest.redeemCouponForUser("username", "code")).isInstanceOf(TotalPriceToLowException.class);
    }
}
