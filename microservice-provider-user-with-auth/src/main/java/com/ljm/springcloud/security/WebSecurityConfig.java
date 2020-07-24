package com.ljm.springcloud.security;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author liaojiamin
 * @Date:Created in 15:27 2020/7/7
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 所有的请求，都需要经过HTTP basic认证
     * */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        // 明文编码器。这是一个不做任何操作的密码编码器，是Spring提供给我们做明文测试的。
        // A password encoder that does nothing. Useful for testing where working with plain text
        return NoOpPasswordEncoder.getInstance();
    }

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsService).passwordEncoder(this.passwordEncoder());
    }

    /**
     * 模拟两个账户：
     * ① 账号是user，密码是password1，角色是user-role
     * ② 账号是admin，密码是password2，角色是admin-role
     */
    @Component
    class CustomUserDetailsService implements UserDetailsService{
        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            if(StringUtils.equals("user", username)){
                return new SecurityUser("user", "password1", "user-role");
            }else if(StringUtils.equals("admin", username)){
                return new SecurityUser("admin", "password2", "admin-role");
            }else {
                return null;
            }
        }
    }

    class SecurityUser implements UserDetails{
        private static final long serialVersionUID = 1L;
        private Long id;
        private String username;
        private String password;
        private String role;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public SecurityUser(String username, String password, String role){
            super();
            this.username = username;
            this.password = password;
            this.role = role;
        }
        public SecurityUser(){}

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(this.role);
            authorities.add(authority);
            return authorities;
        }

        @Override
        public String getPassword() {
            return this.password;
        }

        @Override
        public String getUsername() {
            return this.username;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }


    }



}
