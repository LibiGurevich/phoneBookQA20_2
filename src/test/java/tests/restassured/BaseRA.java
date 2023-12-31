package tests.restassured;

import api.ContactsService;
import api.UserApi;
import dto.ContactDto;
import dto.UserDtoLombok;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;
import utils.RandomUtils;

public class BaseRA {
  UserApi userApi = new UserApi();
  ContactsService contactsService = new ContactsService();
  String token = "";
  RandomUtils randomUtils = new RandomUtils();
  SoftAssert softAssert = new SoftAssert();

  UserDtoLombok user = UserDtoLombok.builder()
          .username("qwerty@qwer.ty")
          .password("Qwerty!1")
          .build();

  @BeforeSuite
  public void preCondApiRA() {
    token = userApi.getTokenFromLoginResponse(user);
  }

  public ContactDto createNewContact() {
    String phoneNumber = randomUtils.generateStringDigits(12);
    ContactDto contact1 = ContactDto.builder()
            .address("sjdfkbwi")
            .description("bdfigyeg")
            .email("sjdfnsjhb@mail.com")
            .id("0")
            .lastName("sjkdbfh")
            .name("nbsefdj")
            .phone(phoneNumber)
            .build();
    return contact1;
  }
}