package com.security.spring2security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired PasswordEncoder passwordEncoder;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/index","/regix").permitAll()
                .antMatchers("/user/**").hasAnyRole("USER","ADMIN") // 用户和管理员角色都可以访问
                .antMatchers("/admin/**").hasRole("ADMIN"); // 只有管理员角色才能访问
        http.formLogin().loginPage("/login"); // 从逻辑视图-》物理视图
        http.rememberMe(); // 添加“记住我”，无论是spring security自带的登录界面还是自己定制的都可以这么用
        http.logout().logoutSuccessUrl("/index"); // 注销成功跳转到/index
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("myth").password(passwordEncoder().encode("myth123")).roles("USER")
                .and()
                .withUser("institution").password(passwordEncoder().encode("institution123")).roles("ADMIN","USER");


    }

    @Bean // @Bean 说明这个方法的返回值会自动配置进容器
    public PasswordEncoder passwordEncoder(){   // 这个方法的作用是用来加密登录的密码
        return new BCryptPasswordEncoder();
    }


}
