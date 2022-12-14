package com.fdmgroup.onedayproject.repository;

import com.fdmgroup.onedayproject.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {
    List<Coupon> findAllByUserUsernameIgnoreCase(String username);
    Optional<Coupon> findByUserUsernameIgnoreCaseAndCodeIgnoreCase(String username, String code);
}
