package com.emailServices;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
// Write email Into File And place File into The Location
public class EmailWriter {

	public void storeGeniunFile(StringBuilder data,String msgID){
		String path= "E:\\git\\SpamFilter\\testmails\\gmailstest\\";
		storeInFile(data,path+msgID);
	}

	public void storeSpamFile(StringBuilder data,String msgID){
		String path= "E:\\git\\SpamFilter\\testmails\\spam\\";
		storeInFile(data,path+msgID);
	}
	private  void  storeInFile(StringBuilder data,String msgID) {
		String maildata=data.toString();
		try {

			File file = new File(msgID+".eml");
			if (!file.exists()) {
				file.createNewFile();
			}

			java.io.FileWriter fw = new java.io.FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(maildata);
			bw.close();

	//		System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}