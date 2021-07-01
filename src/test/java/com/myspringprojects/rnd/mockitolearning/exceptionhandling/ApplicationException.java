package com.myspringprojects.rnd.mockitolearning.exceptionhandling;

public class ApplicationException extends RuntimeException {
    public ApplicationException(String s) {
        super(s);
    }
}
