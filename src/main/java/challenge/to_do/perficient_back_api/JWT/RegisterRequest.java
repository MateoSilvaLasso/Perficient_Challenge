package challenge.to_do.perficient_back_api.JWT;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor

public class RegisterRequest {
    private String userName;
    private String password;
    private String firtsName;
    private String LastName;
    private String country;
    private String phone;
    public RegisterRequest(String userName, String password, String firtsName, String LastName, String country, String phone) {
        this.userName = userName;
        this.password = password;
        this.firtsName = firtsName;
        this.LastName = LastName;
        this.country = country;
        if (!isValidEmail(userName)) {
            throw new IllegalArgumentException("Invalid username format");
        }
    }

    private static boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        return email.matches(regex);
    }
}
