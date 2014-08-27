package com.emailServices;

import com.spamfilter.utility.MailContainExtractor;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Header;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Flags.Flag;
import javax.mail.search.FlagTerm;

public class EmailReceiver {
	Properties properties = null;
	private Session session = null;
	private Store store = null;
	private Folder inbox = null;
	private String userName = "";// provide user name
	private String password = "";// provide password
	private StringBuilder maildata=new StringBuilder(" ");
	private String msgid=" ";

	public EmailReceiver(String UserName, String password) {
		this.userName=UserName;
		this.password=password;


	}

	public void readMails(String folderName) {
		properties = new Properties();
		properties.setProperty("mail.host", "imap.gmail.com");
		properties.setProperty("mail.port", "995");
		properties.setProperty("mail.transport.protocol", "imaps");
		session = Session.getInstance(properties,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		});
		try {
			store = session.getStore("imaps");
			store.connect();
			inbox = store.getFolder(folderName);
			inbox.open(Folder.READ_ONLY);
			Message messages[] = inbox.search(new FlagTerm(
                    new Flags(Flag.SEEN),false));

			System.out.println("Number of mails = " + messages.length);
			for (int i = 0; i < messages.length; i++) {
				System.out.println("------ok-------");
				Message message = messages[i];
				Address[] from = message.getFrom();
				Enumeration headers = message.getAllHeaders();
				while (headers.hasMoreElements()) {
					Header h = (Header) headers.nextElement();
					maildata.append(h.getName().toString()+":"+h.getValue().toString()+"\n");
				}
				maildata.append("SentDate:"+ message.getSentDate()+"\n");
				maildata.append("Content:"+"\n");      
				getMultiMssage(message);
			    String emaildata=maildata.toString();
				msgid= MailContainExtractor.getMessageID((maildata.toString()));
				new EmailWriter().storeSpamFile(maildata, msgid);
				maildata.delete(0, maildata.length());

			}
			inbox.close(true);
			store.close();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public void getMultiMssage(Message message) {
		try {
			Object content = message.getContent();
			// check for string
			// then check for multipart
			if (content instanceof String) {
				maildata.append(content+"\n");
				//  System.out.println(content);
			} else if (content instanceof Multipart) {
				Multipart multiPart = (Multipart) content;
				getMultiPart(multiPart);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public void getMultiPart(Multipart content) {

		try {
			int multiPartCount = content.getCount();
			for (int i = 0; i < multiPartCount; i++) {
				BodyPart bodyPart = content.getBodyPart(i);
				Object o;

				o = bodyPart.getContent();
				if (o instanceof String) {
					maildata.append(o+"\n");

				} else if (o instanceof Multipart) {
					getMultiPart((Multipart) o);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}
}