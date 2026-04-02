package com.YellowFlash.Library.Manager.Exceptions;

public class MemberNotFoundException extends RuntimeException{
    public MemberNotFoundException(Long id){
        super("Member wiht id "+ id +" not found");
    }
    public MemberNotFoundException(String email){
        super("Member with " + email + "Not Found");
    }
}
