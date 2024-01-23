package com.louis.springbootinit.mapper;
import java.util.Date;

import com.louis.springbootinit.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author louis
 * @version 1.0
 * @date 2024/1/23 14:45
 */
@SpringBootTest
public class UserRegisterTest {
    @Resource
    private UserMapper userMapper;
    @Test
    public void RegisterTest() {
        User user = new User();
        user.setUser_name("hjy");
        user.setUser_gender("男");
        user.setUser_position("实验室负责人");
        user.setUser_institution("广中医中药学院");
        user.setLab_id(0);
        user.setUser_tel("12345678");
        user.setUser_acct("12345678");
        user.setUser_pwd("12345678");
        userMapper.insert(user);
        System.out.println("success");
    }
}
