package com.fdmgroup.onedayproject.exception;

/**
 * @author pl098jakskib, 14.12.2022 CRIF IT Solutions Poland
 */
public class TotalPriceToLowException extends DetailedException {
    public TotalPriceToLowException(String message, String detailedMessage) {
        super(message, detailedMessage);
    }

}
