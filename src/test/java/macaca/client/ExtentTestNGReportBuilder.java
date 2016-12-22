package macaca.client;

import com.alibaba.fastjson.JSONObject;
import com.util.extent.ExtentTestNGITestListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

/**
 * Created by sammy on 16-12-20.
 */
public class ExtentTestNGReportBuilder {
    MacacaClient driver = new MacacaClient();

    @BeforeClass
    public synchronized void beforeClass() throws Exception {
        JSONObject porps = new JSONObject();
        porps.put("platformName", "android");
        porps.put("reuse", 1);
        porps.put("app", "https://npmcdn.com/android-app-bootstrap@latest/android_app_bootstrap/build/outputs/apk/android_app_bootstrap-debug.apk");
        JSONObject desiredCapabilities = new JSONObject();
        desiredCapabilities.put("desiredCapabilities", porps);
        driver.initDriver(desiredCapabilities);
        ExtentTestNGITestListener.driver = driver;
    }

    @AfterClass
    public synchronized void afterClass() throws Exception{
        driver.quit();
    }


    @Test
    public void test_1() throws Exception{
        assert 1==0;
    }

    @Test
    public void test_2() {
        assert 1 == 1;
    }

}
