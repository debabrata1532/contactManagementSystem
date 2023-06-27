package com.contactManagement.Contact.securityConf;

import com.contactManagement.Contact.service.userDetails;
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
public class jwtFilter extends OncePerRequestFilter {

    @Autowired
    generateJwt jwt;

    @Autowired
    com.contactManagement.Contact.service.userDetails userDetails;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        //get jwt token from header



        String jwtTokenHeader = request.getHeader("Authorization");
        String userName = null;
        String token = null;

        if(jwtTokenHeader != null && jwtTokenHeader.startsWith("Bearer ")){

            token = jwtTokenHeader.substring(7);

            try{
                userName = jwt.getUsernameFromToken(token);
            }catch(Exception e){
                e.printStackTrace();
            }

            UserDetails userDetails = this.userDetails.loadUserByUsername(userName);
            if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null){

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }else{

            }




        }

        filterChain.doFilter(request,httpServletResponse);



        // verify jwt token

    }
}
