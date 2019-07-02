package org.my.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 使用spring security对swagger文档进行保护，避免外网暴露
 * 账号密码配置在yml文件security标签下
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 关闭springmvc post提交CSRF 保护：
        http.csrf().disable();
        http.authorizeRequests()
                // swagger页面需要添加登录校验
                .antMatchers("/swagger-ui.html", "/docs.html", "/doc.html").authenticated()
                // 普通的接口不需要校验
                .antMatchers("/**").permitAll().and().formLogin();
    }
}