package com.accountsystem.springdataaccountsystemlab.exceptions;

public class EntityMissingException extends RuntimeException {
    public EntityMissingException(String msg) {
        super(msg);
    }
}
