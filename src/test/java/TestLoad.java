import com.zhangsy.project.base.BaseTest;
import org.testng.annotations.Test;

/**
 * @Auther: zhangsy
 * @Date: 2019/1/16 13:37
 * @Description:
 */
public class TestLoad {

    @Test
    public void testLoadProperty() {
        BaseTest baseTest = new BaseTest();
        System.out.println(baseTest.properties.getProperty("HOST"));
    }
}


