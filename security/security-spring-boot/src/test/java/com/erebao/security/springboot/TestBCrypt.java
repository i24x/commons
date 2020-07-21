package com.erebao.security.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TestBCrypt {

    @Test
    public void testBCrypt() {
        // 对密码进行加密
        String hashpw = BCrypt.hashpw("123456", BCrypt.gensalt());
        System.out.println(hashpw);

        // 校验密码
        boolean checkpw = BCrypt.checkpw("123456", "$2a$10$ouiEfVsfqvOBfv8aJ9ifzuUjGw5v8V/e4qLX0vGmAuXuh/xDzyM1W");
        boolean checkpw2 = BCrypt.checkpw("123456", "$2a$10$3t4O5pKWFTlOWxRx792WreGdHqYrC1sioEW1umbMJBKizsLZBpSGC");
        System.out.println(checkpw);
        System.out.println(checkpw2);
    }
}
