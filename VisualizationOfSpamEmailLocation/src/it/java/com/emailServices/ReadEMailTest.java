package com.emailServices;

import org.junit.Test;

public class ReadEMailTest {

	@Test
	public void test() {
		EmailReceiver sample = new EmailReceiver("rockexample123@gmail.com","Rock12345");
		sample.readMails("Inbox");
	}

}
