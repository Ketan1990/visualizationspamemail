package com.spamfilter.spam.spamclassification;

import com.Mongo.MongoQueryEngine;
import com.spamfilter.dataaccesslayer.QueryEngine;
import com.spamfilter.spam.fileopreation.FileRead;

/**
 * Created by ketan on 8/23/2014.
 */
public class SpamClassifier {
    private  FileRead fileRead;
    private SpamDetection spamDetection;
    private QueryEngine queryEngine;

    public SpamClassifier(){
       fileRead=new FileRead();
       queryEngine=new MongoQueryEngine();
   }

    public  boolean isSpamClassified(String emailFile) {
        FileRead fr=new FileRead();
        String maildata=fr.read(emailFile);
        Boolean spam= new SpamDetection(queryEngine).isSpam(maildata);
        return spam;

    }
}
