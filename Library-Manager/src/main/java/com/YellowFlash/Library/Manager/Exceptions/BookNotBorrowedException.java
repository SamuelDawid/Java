package com.YellowFlash.Library.Manager.Exceptions;

public class BookNotBorrowedException extends RuntimeException {
    public BookNotBorrowedException(String title)
    {
        super("Book is not borrowed: "+title);
    }
}
