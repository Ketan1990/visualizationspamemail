package com.emailServices;

import org.junit.Test;

import static org.junit.Assert.*;

public class EmailAuthenticationTest {
    @Test
    public void itshouldValidateMail() {
        //required internet connection to pass the test

        EmailAuthenticator mail=new EmailAuthenticator("rockexample123@gmail.com", "Rock12345");
        assertTrue(mail.validateEmailUser());

    }

}