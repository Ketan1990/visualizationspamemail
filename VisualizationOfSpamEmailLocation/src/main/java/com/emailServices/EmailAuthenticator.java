package com.emailServices;

import com.sun.mail.imap.IMAPStore;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import java.util.Properties;

/**
 * Created by ketan on 8/24/2014.
 */
public class EmailAuthenticator {
    private final String emailAddress;
    private final String passWord;

    public EmailAuthenticator(String emailAddress, String paasWord) {
        this.emailAddress=emailAddress;
        this.passWord=paasWord;
    }

    public boolean validateEmailUser() {
        Properties properties = newProperties();
        IMAPStore emailStore = getEmailSession(properties);
        try {
            emailStore.connect(emailAddress, passWord);
            return true;
        } catch (MessagingException e) {
            return false;
        }
    }

    public IMAPStore getEmailSession(Properties properties) {
        Session emailSession=Session.getDefaultInstance(properties);
        IMAPStore emailStore = null;
        try {
            emailStore =(IMAPStore)emailSession.getStore("imaps");
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        return emailStore;
    }

    public Properties newProperties() {
        Properties properties=new Properties();
        properties.put("mail.host", "imap.gmail.com");
        return properties;
    }


}
