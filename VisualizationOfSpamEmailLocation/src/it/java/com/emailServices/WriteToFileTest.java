package com.emailServices;

import static org.junit.Assert.*;

import java.io.IOException;

import com.spamfilter.spam.fileopreation.FileRead;
import org.junit.Test;

public class WriteToFileTest {

	@Test
	public void test() throws IOException {
		StringBuilder data=new StringBuilder("ok dude its over");
		new EmailWriter().storeGeniunFile(data, "ok");
		assertEquals("ok dude its over", new FileRead().read("E:\\workspace\\spamweb1\\mails\\ok.eml"));
	}

}
