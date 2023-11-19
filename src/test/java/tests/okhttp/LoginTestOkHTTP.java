package tests.okhttp;

import com.google.gson.Gson;
import dto.AuthResponseDTO;
import dto.UserDtoLombok;
import okhttp3.MediaType;
import okhttp3.*;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTestOkHTTP {

    UserDtoLombok user = UserDtoLombok.builder()
            .username("qwerty@qwer.ty")
            .password("Qwerty!1")
            .build();

    public static final MediaType JSON = MediaType.get("application/json");

    Gson gson = new Gson();
    OkHttpClient okHttpClient = new OkHttpClient();
    String baseUrl = "https://contactapp-telran-backend.herokuapp.com";

    @Test
    public void loginPositive(){
        RequestBody requestBody = RequestBody.create(gson.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(baseUrl + "/v1/user/login/usernamepassword")
                .post(requestBody)
                .build();

        Response response;
        try {
            response = okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(response == null){
            Assert.fail("got null response");
        }else if(response.isSuccessful()){
String responseJson;
            try {
                responseJson = response.body().string();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            AuthResponseDTO authResponseDTO = gson.fromJson(responseJson, AuthResponseDTO.class);
            System.out.println(authResponseDTO.getToken());
            System.out.println(response.code());
            System.out.println(response.message());
            Assert.assertEquals(response.code(), 200, "response not 200");

        }else{
            System.out.println(response.code() + " error");
            Assert.fail("response not successful");
        }
    }

}