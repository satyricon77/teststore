package org.volovich;

import org.volovich.utils.PropertyLoader;

/**
 * Created by Serhii Bryt
 * 28.03.2024 14:00
 **/
public class AppConstants {
    public final static Boolean HEADLESS_MODE_PROP = Boolean.parseBoolean(PropertyLoader.loadProperty("at.headless"));
    public static final String BASE_URL = PropertyLoader.loadProperty("base.URL");
}
