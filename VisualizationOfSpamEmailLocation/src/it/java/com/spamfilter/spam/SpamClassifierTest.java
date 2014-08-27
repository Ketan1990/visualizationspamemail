package com.spamfilter.spam;

import com.Mongo.MongoConfig;
import com.spamfilter.spam.labelemailtraining.EmailType;
import com.spamfilter.spam.labelemailtraining.SpamFilterTrainer;
import com.spamfilter.spam.spamclassification.SpamClassifier;


import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by ketan on 8/23/2014.
 */
public class SpamClassifierTest {
    private SpamFilterTrainer spamFilterTraining;

    @Test
    public void shouldClassifyWheterMailIsSpam(){
        //given
        spamFilterTraining=new SpamFilterTrainer(EmailType.Geniune);
        spamFilterTraining.doSpamTraining("E:\\git\\SpamFilter\\testmails\\gmailstest");
        spamFilterTraining=new SpamFilterTrainer(EmailType.spamMail);
        spamFilterTraining.doSpamTraining("E:\\git\\SpamFilter\\testmails\\spam");
        //when
        SpamClassifier spamClassification=new SpamClassifier();
        //then


        assertTrue(spamClassification.isSpamClassified("E:\\git\\SpamFilter\\testmails\\spam\\TRAIN_00007.eml"));
    }
    @Test
    public void shouldClassifyWheterMailIsNOtSpamO(){
        //given
        spamFilterTraining=new SpamFilterTrainer(EmailType.Geniune);
        spamFilterTraining.doSpamTraining("E:\\git\\SpamFilter\\testmails\\gmailstest");
        spamFilterTraining=new SpamFilterTrainer(EmailType.spamMail);
        spamFilterTraining.doSpamTraining("E:\\git\\SpamFilter\\testmails\\spam");
        //when
        SpamClassifier spamClassification=new SpamClassifier();
        //then


        assertFalse(spamClassification.isSpamClassified("E:\\git\\SpamFilter\\testmails\\gmailstest\\508102099.RPBLNBTE.36445.635320417278915000@GEN2.center.com.eml"));
    }
    @After
    public void tearDown(){
        MongoConfig.deleteDB();
    }

}
