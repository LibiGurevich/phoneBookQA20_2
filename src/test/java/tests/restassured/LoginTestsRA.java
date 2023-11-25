package tests.restassured;

import dto.UserDtoLombok;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTestsRA extends BaseRA{

    @Test
    public void loginStatusCodeTest(){
        Assert.assertEquals(userApi.getStatusCodeResponseLogin(user), 200, "status code not 200 for login test with correct data");
    }

    @Test
    public void loginStatusCodeTest2(){
        Assert.assertEquals(userApi.getStatusCodeResponseLogin(user), 200, "status code not 200 for login test with correct data");
    }

    @Test
    public void testToken(){
        System.out.println("token " + userApi.getTokenFromLoginResponse(user));
    }

    @Test
    public void negativeLogin(){
        userApi.setResponseLoginNull();

        UserDtoLombok user1 = UserDtoLombok.builder()
                .username("qwerty@qwer.ty")
                .password("Qwerty!!")
                .build();

        int statusCode = userApi.getStatusCodeResponseLogin(user1);
        userApi.setResponseLoginNull();
        Assert.assertEquals(statusCode, 401);


    }
}
