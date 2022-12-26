package spring_study.concertInfo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistrar;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import spring_study.concertInfo.typeconverter.DateRangeFormatter;

@Configuration
public class SpringConfig implements WebMvcConfigurer {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new DateRangeFormatter());
    }
}
