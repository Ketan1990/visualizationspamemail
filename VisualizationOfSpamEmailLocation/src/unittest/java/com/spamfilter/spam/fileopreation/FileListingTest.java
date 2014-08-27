package com.spamfilter.spam.fileopreation;

import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

/**
 * Created by ketan on 7/24/2014.
 */
public class FileListingTest {
    @Test
    public void itShouldAbleToGiveLIstOfMailFile() {
        //given
        FileList files=new FileList();
        LinkedList<String> expecteds=new LinkedList<String>();
        expecteds.add("TRAIN_00007.eml");

        //when
        LinkedList<String>actual=files.listOf("E:\\git\\SpamFilter\\testmails\\spam");
        assertEquals(actual.getFirst(), expecteds.getFirst());
    }
}
