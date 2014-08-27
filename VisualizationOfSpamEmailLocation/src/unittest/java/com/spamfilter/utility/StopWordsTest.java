package com.spamfilter.utility;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class StopWordsTest {
    @Test
    public void test(){
        String s= StopWords.getString("regX");
        assertTrue(s.contains("as"));
    }

}