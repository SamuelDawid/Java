package com.YellowFlash.Library.Manager.Exceptions;

public class MemberDeactivatedException extends RuntimeException {
    public MemberDeactivatedException(Long id) {

        super("Member " + id + "is Deactivated");
    }
}
