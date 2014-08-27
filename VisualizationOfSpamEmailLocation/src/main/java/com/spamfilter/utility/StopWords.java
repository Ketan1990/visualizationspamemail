package com.spamfilter.utility;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Created by Ketan on 7/20/2014.
 */

public class StopWords{
    private static final String BUNDLE_NAME = "com.spamfilter.resources.stopWords"; //$NON-NLS-1$

    private static final ResourceBundle RESOURCE_BUNDLE;

    static {
        RESOURCE_BUNDLE = ResourceBundle
                .getBundle(BUNDLE_NAME);
    }

    private StopWords() {
    }

    public static String getString(String key) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }
}
