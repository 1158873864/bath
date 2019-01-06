package bath.config.filter;

import bath.security.jwt.JwtService;
import bath.security.jwt.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private JwtService jwtService;

    @Autowired
    public JwtAuthenticationTokenFilter() {
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain) throws ServletException, IOException {

//        System.out.println("request:"+request);
//        System.out.println("response:"+response);
        String authHeader = request.getHeader(this.tokenHeader);
//        System.out.println("headers:"+request.getHeaders("Authorization"));
//        System.out.println("Authorizationheader:"+request.getHeader("Authorization"));
//        System.out.println("contentheader:"+request.getHeader("content-type"));
        String url=request.getRequestURI();
//        System.out.println("url:"+request.getRequestURI());
//        System.out.println("authheader:"+authHeader);
        if(url.equals("/getOpenIdAndSessionKey")||url.equals("/loginAdmin")){
            chain.doFilter(request, response);
        }
        else{
//            if (authHeader != null && authHeader.startsWith(tokenHead)) {
//                System.out.println("cando");
//                final String authToken = authHeader.substring(tokenHead.length());
//                String username = jwtService.getUsernameFromToken(authToken);
//                System.out.println(authToken);
//                if (authToken.length() > 0) {
//                    UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
//                    if (jwtService.validateToken(authToken)) {
//                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
//                                userDetails, null, userDetails.getAuthorities());
//                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(
//                                request));
//                        SecurityContextHolder.getContext().setAuthentication(authentication);
//                        System.out.println("yes");
//                        //chain.doFilter(request, response);
//                    }
//                }
//
//            }
            chain.doFilter(request, response);
        }




    }
}
