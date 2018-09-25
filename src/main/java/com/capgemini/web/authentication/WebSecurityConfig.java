package com.capgemini.web.authentication;


import com.capgemini.data.PersonRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String ENCODED_PASSWORD = "$2a$10$AIUufK8g6EFhBcumRRV2L.AQNz3Bjp7oDQVFiO5JJMBFZQ6x2/R/2";
    private PersonRepository personRepository = new PersonRepository();

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        personRepository.addInitialAccounts();
        String username = "Thom@moosjes.nl";
        String password = personRepository.getSinglePerson("Thom@moosjes.nl").getPassword();

        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder())
                  .withUser(username).password(password).roles("USER");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


