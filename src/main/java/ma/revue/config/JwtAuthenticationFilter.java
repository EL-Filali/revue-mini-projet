package ma.revue.config;



import ma.revue.beans.User;
import ma.revue.services.CustomUserDetailsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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



            String jwt = getJWTFromRequest(httpServletRequest);

            if(StringUtils.hasText(jwt)&& tokenProvider.validateToken(jwt,httpServletRequest)) {
                Long userId = tokenProvider.getUserIdFromJWT(jwt);
                User userDetails = customUserDetailsService.loadUserById(userId);

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authentication);

            }
                /*       try {


        }catch (Exception ex){
            logger.error("Could not set user authentication in security context", ex);
        }
    */

        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }



    private String getJWTFromRequest(HttpServletRequest request){
        String bearerToken = request.getHeader(HEADER_STRING);

        if(StringUtils.hasText(bearerToken)&&bearerToken.startsWith(TOKEN_PREFIX)){
            return bearerToken.substring(7, bearerToken.length());
        }

        return null;
    }
}
