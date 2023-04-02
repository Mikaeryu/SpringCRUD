package app.service.impl;

import app.model.User;
import app.service.UserService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @BeforeAll
    void printBeforeAll() {
        System.out.println("Before all: " + this);
    }

    @BeforeEach
    void prepare() {
        System.out.println("Before each: " + this);
    }

    @Test
    void userListIsNotEmpty_whenUserServiceGetIt() {
        System.out.println("Test 1: " + this);
        List<User> userList = userService.getUserList();
        Assertions.assertFalse(userList.isEmpty(), "User list should not be empty");
    }

    @Test
    void userListSizeChange_ifUserAdded() {
        System.out.println("Test 2: " + this);
        List<User> userList = userService.getUserList();
        var user = new User(); user.setLogin("new_user"); user.setPassword("new_pass");
        long userNum = userList.size();
        user = userService.saveUser(user);
        Assertions.assertTrue(userService.getUserList().size() == userNum + 1);
        userService.deleteUser(user.getId());
    }

    @AfterEach
    void printThis() {
        System.out.println("After each: " + this);
    }

    @AfterAll
    void printAfterAll() {
        System.out.println("After all: " + this);
    }
}
