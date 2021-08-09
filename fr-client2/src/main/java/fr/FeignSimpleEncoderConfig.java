package fr;

import feign.codec.Encoder;
import feign.form.FormEncoder;
import org.springframework.context.annotation.Bean;

public class FeignSimpleEncoderConfig {
    @Bean
    public Encoder encoder() {
        return new FormEncoder();
    }
}
