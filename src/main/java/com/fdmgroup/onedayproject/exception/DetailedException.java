package com.fdmgroup.onedayproject.exception;

/**
 * @author pl098jakskib, 16.12.2022 CRIF IT Solutions Poland
 */
public class DetailedException extends Exception {
    private String messageToDisplay;

    public DetailedException(String msg, String messageToDisplay) {
        super(msg);
        this.messageToDisplay = messageToDisplay;
    }

    public String getMessageToDisplay() {
        return messageToDisplay;
    }

}
