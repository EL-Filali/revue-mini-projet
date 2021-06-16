package ma.revue.config;

import com.google.gson.Gson;
import ma.revue.dto.LoginRequestDTO;
import ma.revue.exceptions.LoginException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {
        setErrorResponse(HttpStatus.UNAUTHORIZED, httpServletResponse,"Email ou mot de passe  sont incorrects");
    }

    public void setErrorResponse(HttpStatus status, HttpServletResponse response, String message){
        response.setStatus(status.value());
        response.setContentType("application/json");
        HashMap<String,String> errors=new HashMap<String,String>();
        errors.put("message",message);
        try {
            String json =new Gson().toJson(errors);
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
