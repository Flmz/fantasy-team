package ru.smn.fantasyteam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.smn.fantasyteam.properties.RsaProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaProperties.class)
public class FantasyApplication {
    public static void main(String[] args) {
        SpringApplication.run(FantasyApplication.class, args);
    }
}
