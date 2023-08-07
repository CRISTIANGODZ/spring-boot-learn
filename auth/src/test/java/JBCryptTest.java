import com.dyingzhang.auth.utils.JWTUtils;
import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Random;

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

    public static String generateVerificationCode(int length) {
        // 定义验证码字符集合，这里使用数字字符
        String characters = "0123456789";
        StringBuilder code = new StringBuilder();

        Random random = new Random();
        int charactersLength = characters.length();

        // 生成指定长度的验证码
        for (int i = 0; i < length; i++) {
            // 从字符集合中随机选择一个字符，并添加到验证码中
            int index = random.nextInt(charactersLength);
            code.append(characters.charAt(index));
        }

        return code.toString();
    }

    @Test
    public void test3() {
        String s = generateVerificationCode(6);
        System.out.println(s);
    }
}
