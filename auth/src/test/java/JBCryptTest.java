import com.dyingzhang.auth.utils.JWTUtils;
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

    @Test
    public void test2() {
        String token = JWTUtils.generateToken("123", 1000);
        try {
            Thread.sleep(1000);
            System.out.println(JWTUtils.isTokenExpired(token));;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
