package com.bridgelabz.bookstoreapp.exception;

public class BookStoreException extends RuntimeException{
    public enum ExceptionType {
        EMAIL_NOT_FOUND,
        PASSWORD_INVALID,

    }

    public BookStoreException.ExceptionType type;


    public BookStoreException(String message,BookStoreException.ExceptionType type){
        super(message);
        this.type = type;
    }
    public BookStoreException(String message){
        super(message);
    }
}
