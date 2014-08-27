package com.Geolocationservice;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ketan on 8/25/2014.
 */
public class GeoLocClientTest {
    @Test
    public void test() {
        //required internet Connection to pass the test
        GeoLocClient geoc = new GeoLocClient();
        String actual=geoc.getLocatonDetails("42.104.14.121");
        Assert.assertTrue(actual.contains("\"geoplugin_currencyCode\":\"INR\""));
    }

}
