package com.motos.api.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {

        return new Cloudinary(
            ObjectUtils.asMap(
                "cloud_name", "deqvca9zb",
                "api_key", "586312722539956",
                "api_secret", "OxSWj1_7dEev31ChpEeDTp4a6zY"
            )
        );
    }
}
