package app.service.impl;

import app.model.User;
import app.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    void getUserListThenCheckItIsNotEmpty() {
        List<User> userList = userService.getUserList();
        Assertions.assertNotNull(userList);
    }
}
