package com.epicode.U2_W3_D5_ViaggiAziendali.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary uploader() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dylzt2lyt");
        config.put("api_key", "935256457145368");
        config.put("api_secret", "mk3_846q6AQEiAE5j0Xq0nHjEEM");
        return new Cloudinary(config);
    }


}