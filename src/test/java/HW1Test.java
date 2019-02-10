import com.newsoftvalley.homework.hw1.AccountServlet;
import org.junit.Test;

public class HW1Test {

    @Test
    public void test() {
        AccountServlet accountServlet = new AccountServlet();
        System.out.println(accountServlet.getId("/123"));
    }

}
