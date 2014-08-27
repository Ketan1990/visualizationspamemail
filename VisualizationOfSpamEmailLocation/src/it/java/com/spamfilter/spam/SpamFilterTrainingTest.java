package com.spamfilter.spam;

import com.Mongo.MongoConfig;
import com.Mongo.MongoQueryEngine;
import com.spamfilter.dataaccesslayer.SpamDAO;
import com.spamfilter.spam.labelemailtraining.EmailType;
import com.spamfilter.spam.labelemailtraining.SpamFilterTrainer;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SpamFilterTrainingTest {
    SpamFilterTrainer spamFilterTraining;
    @Test
    public void itShouldTrainSpamDatabaseWithSpamMail() {
        spamFilterTraining=new SpamFilterTrainer(EmailType.spamMail);
        spamFilterTraining.doSpamTraining("E:\\git\\SpamFilter\\testmails\\spam");
        assertTrue(new SpamDAO(new MongoQueryEngine()).isPresentSpamId("craig@deersoft.com"));

    }

    @Test
    public void itShouldTrainSpamDatabaseWithGeniuneMail() {
        spamFilterTraining=new SpamFilterTrainer(EmailType.Geniune);
        spamFilterTraining.doSpamTraining("E:\\git\\SpamFilter\\testmails\\gmailstest");
        assertTrue(new SpamDAO(new MongoQueryEngine()).isPresentGenuineId("sqpecials@psychiccenter.com"));
    }



    @After
    public void tearDown(){
        MongoConfig.deleteDB();
    }

}

