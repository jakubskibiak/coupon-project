
package com.fdmgroup.onedayproject.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fdmgroup.onedayproject.exception.CouponsExceededException;
import com.fdmgroup.onedayproject.exception.TotalPriceToLowException;
import com.fdmgroup.onedayproject.model.Coupon;
import com.fdmgroup.onedayproject.model.User;
import com.fdmgroup.onedayproject.service.IUserService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserCouponsController.class)
class UserCouponsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IUserService mockUserService;

    @Test
    void testDetails() throws Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/details").accept(MediaType.TEXT_HTML)).andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getForwardedUrl()).contains("details.jsp");
    }

    @Test
    void testSearchDetails() throws Exception {
        // Setup
        // Configure IUserService.getUserDetails(...).
        final User user = new User(0, "username", "firstName", "lastName", "email", 0.0);
        when(mockUserService.getUserDetails("username")).thenReturn(user);

        // Configure IUserService.getUserCoupons(...).
        final List<Coupon> coupons = List.of(new Coupon(0, "code", 0.0, 0, 0, new User(0, "username", "firstName", "lastName", "email", 0.0)));
        when(mockUserService.getUserCoupons("username")).thenReturn(coupons);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/search-details").param("username", "username").with(csrf()).accept(MediaType.TEXT_HTML)).andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getForwardedUrl()).contains("details.jsp");
    }

    @Test
    void testSearchDetails_IUserServiceGetUserCouponsReturnsNoItems() throws Exception {
        // Setup
        // Configure IUserService.getUserDetails(...).
        final User user = new User(0, "username", "firstName", "lastName", "email", 0.0);
        when(mockUserService.getUserDetails("username")).thenReturn(user);

        when(mockUserService.getUserCoupons("username")).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/search-details").param("username", "username").with(csrf()).accept(MediaType.TEXT_HTML)).andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getForwardedUrl()).contains("details.jsp");
    }

    @Test
    void testRedeemCoupon1() throws Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/redeem-coupon").accept(MediaType.TEXT_HTML)).andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getForwardedUrl()).contains("redeem.jsp");
    }

    @Test
    void testRedeemCoupon2() throws Exception, CouponsExceededException, TotalPriceToLowException {
        // Setup
        // Configure IUserService.redeemCouponForUser(...).
        final Coupon coupon = new Coupon(0, "code", 0.0, 0, 0, new User(0, "username", "firstName", "lastName", "email", 0.0));
        when(mockUserService.redeemCouponForUser("username", "code")).thenReturn(coupon);

        // Run the test
        final MockHttpServletResponse response = mockMvc
            .perform(post("/redeem-coupon").param("username", "username").param("code", "code").with(csrf()).accept(MediaType.TEXT_HTML))
            .andReturn()
            .getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getForwardedUrl()).contains("success.jsp");
    }



    @Test
    void testRedeemCoupon2_IUserServiceThrowsTotalPriceToLowException() throws Exception, CouponsExceededException, TotalPriceToLowException {
        // Setup
        when(mockUserService.redeemCouponForUser("username", "code")).thenThrow(TotalPriceToLowException.class);

        // Run the test
        final MockHttpServletResponse response = mockMvc
            .perform(post("/redeem-coupon").param("username", "username").param("code", "code").with(csrf()).accept(MediaType.TEXT_HTML))
            .andReturn()
            .getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getForwardedUrl()).contains("error.jsp");
    }
}
