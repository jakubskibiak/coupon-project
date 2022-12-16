package com.fdmgroup.onedayproject.exception;

/**
 * @author pl098jakskib, 14.12.2022
 * CRIF IT Solutions Poland
 */
public class UserNotFoundException extends DetailedException {
    public UserNotFoundException(String message, String detailedMessage) {
        super(message, detailedMessage);
    }

}
