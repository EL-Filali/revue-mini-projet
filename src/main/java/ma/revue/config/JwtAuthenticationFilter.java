package ma.revue.config;



import com.google.gson.Gson;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import ma.revue.beans.User;
import ma.revue.services.CustomUserDetailsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;


public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private CustomUserDetailsServices customUserDetailsService;
    @Value("${security.token_prefix}")
    private String TOKEN_PREFIX;
    @Value("${security.header_string}")
    private String HEADER_STRING;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

      try {


          String jwt = getJWTFromRequest(httpServletRequest);

          if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt, httpServletRequest)) {
              Long userId = tokenProvider.getUserIdFromJWT(jwt);
              User userDetails = customUserDetailsService.loadUserById(userId);

              UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                      userDetails, null, userDetails.getAuthorities());

              authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
              SecurityContextHolder.getContext().setAuthentication(authentication);

          }


      }catch (ExpiredJwtException ex){
          setErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, httpServletResponse," JWT token Expiré");
      }catch (UnsupportedJwtException ex){
          setErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, httpServletResponse," JWT token non supporté");
      }catch (IllegalArgumentException ex){
          setErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, httpServletResponse,"JWT claims string est vide");
      }catch (SignatureException ex){
          setErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, httpServletResponse,"JWT pas bien signé");
      } catch (MalformedJwtException ex) {
          setErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, httpServletResponse,"JWT token pas bien formé");
      }



        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }



    private String getJWTFromRequest(HttpServletRequest request){
        String bearerToken = request.getHeader(HEADER_STRING);

        if(StringUtils.hasText(bearerToken)&&bearerToken.startsWith(TOKEN_PREFIX)){
            return bearerToken.substring(7);
        }

        return null;
    }
    public void setErrorResponse(HttpStatus status, HttpServletResponse response,String message){
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
