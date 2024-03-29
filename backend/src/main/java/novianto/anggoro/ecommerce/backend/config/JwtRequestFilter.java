package novianto.anggoro.ecommerce.backend.config;

import io.jsonwebtoken.ExpiredJwtException;
import novianto.anggoro.ecommerce.backend.service.JwtService;
import novianto.anggoro.ecommerce.backend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        final String header = httpServletRequest.getHeader("Authorization");

        String jwtToken = null;
        String userName = null;

        if (header != null && header.startsWith("Bearer ")){
            // set substring 7 karena start setelah huruf Bearer .
            jwtToken = header.substring(7);

            try {
                userName = jwtUtil.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException exception){
                System.out.println("Gagal Mendapatkan JWT Token");
            } catch (ExpiredJwtException expiredJwtException){
                System.out.println("JWT Token Expired");
            }
        } else {
            System.out.println("JWT Token tidak bisa jalan dengan Bearer");
        }

        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = jwtService.loadUserByUsername(userName);

            if (jwtUtil.validateToken(jwtToken, userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
