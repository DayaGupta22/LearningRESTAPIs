package com.TechieSpring.LearningRESTAPis;


import com.TechieSpring.LearningRESTAPis.entities.User;
import com.TechieSpring.LearningRESTAPis.services.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class LearningRestaPisApplicationTests {
@Autowired
    private JwtService jwtService;
	@Test
	void contextLoads() {
        User user = new User(5L,"Dayanantd@gmail.com","Daya@12rty123","daytyta");
        String token = jwtService.generateToken(user);
        System.out.println(token);
        Long id = jwtService.getUserIdFromtoken(token);
        System.out.println(id);

    }


}
