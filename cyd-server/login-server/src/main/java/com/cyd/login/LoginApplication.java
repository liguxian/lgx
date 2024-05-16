package com.cyd.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Hello world!
 *
 */
@SpringBootApplication(scanBasePackages = {"com.cyd.login.**","com.cyd.common"})

@EnableMongoRepositories(basePackages = {"com.cyd.login.**","com.cyd.common"})
public class LoginApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(LoginApplication.class);
    }
}
