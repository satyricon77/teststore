package utils;

import com.codeborne.selenide.Screenshots;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotCapture {
    public static String screeShotDestinationPath;

    public static String takeSnapShot(String name) {
        String formatter = timeStamp();
        String destFile = System.getProperty("user.dir") + "/screenshots/" + formatter + ".png";
        screeShotDestinationPath = "/screenshots/" + formatter + ".png";

        try {
            FileUtils.copyFile(Screenshots.takeScreenShotAsFile(), new File(destFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return name;
    }

    public static String timeStamp() {
        return new SimpleDateFormat("yyyy-MM-dd_HH--mm--ss").format(new Date());
    }

}
