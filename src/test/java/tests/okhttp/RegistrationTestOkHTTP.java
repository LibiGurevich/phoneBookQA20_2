package tests.okhttp;

import com.google.gson.Gson;
import dto.AuthResponseDTO;
import dto.UserDtoLombok;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.RandomUtils;

import java.io.IOException;

public class RegistrationTestOkHTTP {

    public static final MediaType JSON = MediaType.get("application/json");
    Gson gson = new Gson();
    OkHttpClient okHttpClient = new OkHttpClient();
    String baseUrl = "https://contactapp-telran-backend.herokuapp.com";

    @Test
    public void registrationPositive(){

        RandomUtils randomUtils = new RandomUtils();
        String email = randomUtils.generateEmail(7);
        UserDtoLombok user = UserDtoLombok.builder()
                .username(email)
                .password("Qwerty!1")
                .build();
        RequestBody requestBody = RequestBody.create(gson.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(baseUrl + "/v1/user/registration/usernamepassword")
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

    @Test
    public void registrationNegative409(){

        UserDtoLombok user = UserDtoLombok.builder()
                .username("qwerty@qwer.ty")
                .password("Qwerty!1")
                .build();
        RequestBody requestBody = RequestBody.create(gson.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(baseUrl + "/v1/user/registration/usernamepassword")
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
            Assert.fail("got response with status code: " + response.code());


        }else{
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
            Assert.assertEquals(response.code(), 409, "response not 409");
        }
    }
}
