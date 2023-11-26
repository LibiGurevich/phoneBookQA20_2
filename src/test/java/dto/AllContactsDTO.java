package dto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class AllContactsDTO {
    ContactDto[] contacts;

    public AllContactsDTO() {}

    public AllContactsDTO(ContactDto[] contacts) {
        this.contacts = contacts;
    }

    public ContactDto[] getContacts() {
        return contacts;
    }

    public void setContacts(ContactDto[] contacts) {
        this.contacts = contacts;
    }

}


