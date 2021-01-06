package ink.across.web.constant;

import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

public class GlobalResponseCode {
    public final static String SUCCESS = "0000";
    public final static String FAILED = "4000";
    public static String URL = null;

    static {
        try {
            URL = ResourceUtils.getURL("classpath:").getPath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
