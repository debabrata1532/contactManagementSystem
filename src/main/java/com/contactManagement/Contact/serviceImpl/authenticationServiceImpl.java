package com.contactManagement.Contact.serviceImpl;


import com.contactManagement.Contact.model.authBody;
import com.contactManagement.Contact.securityConf.generateJwt;
import com.contactManagement.Contact.securityConf.jwtResponse;
import com.contactManagement.Contact.service.authenticationService;
import com.contactManagement.Contact.service.userDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class authenticationServiceImpl implements authenticationService {


    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    com.contactManagement.Contact.service.userDetails userDetails;

    @Autowired
    generateJwt jwt;
    @Override
    public ResponseEntity<?> authenticate(authBody requestBody) throws Exception {
        String user = "";
        String password = "";
        String token = "";

        user = requestBody.getUsername();
        password = requestBody.getPassword();

        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user,password));
//            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user,password));

        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            throw new Exception("Bad Credential");

        }

        UserDetails userD = this.userDetails.loadUserByUsername("user");

        token = jwt.generateToken(userD);

        return ResponseEntity.ok(new jwtResponse(token));
    }
}
