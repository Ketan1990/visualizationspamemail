package com.mongoDB;

import com.mongodb.DBCollection;
import com.Mongo.MongoConfig;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ketan on 7/23/2014.
 */
public class MongoConfigTest {
    @Test
    public void Test1() {
        DBCollection dbCollection = MongoConfig.getCollection("words");
        assertNotNull(dbCollection);
    }
}
