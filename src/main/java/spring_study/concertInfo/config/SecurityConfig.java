package spring_study.concertInfo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import spring_study.concertInfo.jwt.JwtAuthenticationFilter;
import spring_study.concertInfo.jwt.JwtTokenProvider;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .httpBasic().disable()
                .csrf().disable()       //rest api 이기 때문에 basic auth 와 csrf 보안을 사용하지 않는다는 설정
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //JWT 를 사용하기 때문에 세션을 사용하지 않는다는 설정
                .and()
                .authorizeRequests()
                .antMatchers("/members/login").permitAll()  //해당 API 에 대해서 모든 요청을 허가한다는 설정
                .antMatchers("/members/test").hasRole("USER")   //USER 권한이 있어야 요청할수 있다는 설정
                //.anyRequest().authenticated()   //이 밖에 모든 요청에 대해서 인증을 필요로 한다는 설정
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
                        //JWT 인증을 위하여 직접 구현한 필터를 UsernamePasswordAuthenticationFilter 전에 실행하겠다는 설정
        return httpSecurity.build();
    }

    //JWT 를 사용하기 위해서 기본적으로 password encoder 가 필요한데, 여기서는 By crypt encoder 를 사용한다.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
