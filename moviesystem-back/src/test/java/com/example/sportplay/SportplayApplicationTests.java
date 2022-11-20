package com.example.sportplay;

import com.example.sportplay.bean.Type;
import com.example.sportplay.bean.User;
import com.example.sportplay.service.TypeService;
import com.example.sportplay.service.UserService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SportplayApplicationTests {
    @Autowired
    private TypeService typeService;
    @Test
    void contextLoads() {
        List<Type> types = typeService.getTypes();
        for (int i = 0; i < types.size(); i++) {
            System.out.println(types.get(i));
        }
    }

}
