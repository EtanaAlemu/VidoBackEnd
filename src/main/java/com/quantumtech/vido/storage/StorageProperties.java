package com.quantumtech.vido.storage;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Paths;

@Configuration
public class StorageProperties {

    @Bean
    @ConfigurationProperties("storage")
    public StorageProperties properties() {
        return new StorageProperties();
    }

    public StorageProperties() {
        this.location = Paths.get(System.getProperty("user.home") + "/Downloads/images/").toString();
        ;
    }

    @Getter
    @Setter
    private String location;

}
