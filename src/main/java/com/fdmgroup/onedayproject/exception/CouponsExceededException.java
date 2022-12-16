package com.fdmgroup.onedayproject.exception;

/**
 * @author pl098jakskib, 14.12.2022
 * CRIF IT Solutions Poland
 */
public class CouponsExceededException extends DetailedException {
    public CouponsExceededException(String message, String detailedMessage) {
        super(message, detailedMessage);
    }
}
