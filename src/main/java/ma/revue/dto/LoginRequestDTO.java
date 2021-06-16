package ma.revue.dto;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class LoginRequestDTO {

    @NotNull(message = "Non null")
    private String email ;
    @NotNull(message = "Non null")
    private String password ;

    public String getEmail() {
        return email;
    }

    public void setEmail(String username) {
        this.email = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
