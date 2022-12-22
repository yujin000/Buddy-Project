package com.fivet.buddy.util;

import org.springframework.stereotype.Component;

import java.util.Random;
@Component
public class RandomKeyUtil {
    // 숫자+영문 20자리 생성(폴더key 값)
    public String folderKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 20; i++) {
            if (rnd.nextBoolean()) {
                key.append((char) ((int) (rnd.nextInt(26)) + 97));
            } else {
                key.append((rnd.nextInt(10)));
            }
        }
        return key.toString();
    }
}
