package com.YellowFlash.Library.Manager.Exceptions;

public class BookNotAvailableException extends RuntimeException {
    public BookNotAvailableException(String title) {

        super("Book is already borrowed:" + title);
    }
}
