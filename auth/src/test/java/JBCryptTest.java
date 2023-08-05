import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;

/**
 * @author DyingZhang
 * @date 2023-08-05 下午 12:07
 * @discription
 */
public class JBCryptTest {

    @Test
    public void test1() {
        String hashpw = BCrypt.hashpw("123456", BCrypt.gensalt());
        System.out.println(hashpw);
    }
}
