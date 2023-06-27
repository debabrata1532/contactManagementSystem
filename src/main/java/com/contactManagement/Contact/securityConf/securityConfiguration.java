package com.contactManagement.Contact.securityConf;


import com.contactManagement.Contact.service.userDetails;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@OpenAPIDefinition(
        info =@Info(
                title = "Contact Management",
                version = "1.0.0"
        )

)
@io.swagger.v3.oas.annotations.security.SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class securityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String[] AUTH_WHITELIST = {
            // -- swagger ui
            "/authentication", "/v2/api-docs","/swagger-ui.html", "/webjars/**", "/v3/api-docs/**",
            "/swagger-resources/**", "/swagger-ui/**", "/test"};
    @Autowired
    jwtFilter jwtFilter;
    @Autowired
    private userDetails userDetails;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("nictr").password(passwordEncoder().encode("3f37fa47-8a07-47f0-899a-9b56ee5a3b9c")).roles("ADMIN");
        auth.userDetailsService(userDetails);

    }

    @Override
    protected void configure(HttpSecurity httpsecurity) throws Exception {

        httpsecurity.csrf().disable()
                .cors().disable()
                .authorizeRequests().antMatchers(AUTH_WHITELIST).permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        httpsecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();

        return NoOpPasswordEncoder.getInstance();
    }



//    @Bean
//    public OpenAPI customizeOpenAPI() {
//        final String securitySchemeName = "bearerAuth";
//        return new OpenAPI()
//                .addSecurityItem(new SecurityRequirement()
//                        .addList(securitySchemeName))
//                .components(new Components()
//                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
//                                .name(securitySchemeName)
//                                .type(SecurityScheme.Type.HTTP)
//                                .scheme("bearer")
//                                .bearerFormat("JWT")));
//
//
//
//
//
//    }


}
