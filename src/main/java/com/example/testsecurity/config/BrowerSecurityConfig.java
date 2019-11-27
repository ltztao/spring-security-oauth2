package com.example.testsecurity.config;

import com.example.testsecurity.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true,jsr250Enabled = true,prePostEnabled = true)
public class BrowerSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private MyAuthenticationSuccessHandler successHandler;
    @Autowired
    private MyAuthenticationFailHandler failHandler;

    @Autowired
    private AjaxSessionInformationExpiredStrategy expiredStrategy;

    @Autowired
    private SecurityAuthenticationProvider provider;

    @Autowired
    private RestAuthAccessDeniedHandler deniedHandler;
    @Autowired
    private MyLogoutSuccessHandler      logoutSuccessHandler;
    @Autowired
    private MyAuthenticationEntryPoint  entryPoint;

    /**
     * 如若需从数据库动态判断权限则实现 AccessDecisionManager
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //"/login"不进行权限验证
                .antMatchers("/login").permitAll()
//                .antMatchers("/favicon.ico").permitAll()
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()   //其他的需要登陆后才能访问
                .and()
                .formLogin()
                //loginProcessingUrl用于指定前后端分离的时候调用后台登录接口的名称
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(successHandler)
                .failureHandler(failHandler).permitAll()
                .and()
                //loginProcessingUrl用于指定前后端分离的时候调用后台注销接口的名称
                .logout().logoutUrl("/logout")
                .invalidateHttpSession(true)
                .logoutSuccessHandler(logoutSuccessHandler)

                .and()
                //配置没有权限的自定义处理类
                .exceptionHandling()
                .accessDeniedHandler(deniedHandler)
                .authenticationEntryPoint(entryPoint); // 自定义处理未登录

        // 设置跨域问题
        http.cors().and().csrf().disable();

        http.sessionManagement()
                .maximumSessions(1)  // 同一个账号只能在一个地方登陆
                .expiredSessionStrategy(expiredStrategy);
    }

    /**
     * 自定义验证逻辑
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(provider);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
