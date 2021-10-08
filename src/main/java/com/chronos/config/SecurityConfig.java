package com.chronos.config;



import com.chronos.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import javax.sql.DataSource;

/**
 *  SecurityConfig
 *  MVC Security管理配置的自定义WebSecurityConfigurerAdapter类
 */
@EnableWebSecurity  // 开启MVC security安全支持
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    /**
     * 用户授权管理自定义配置
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 自定义用户授权管理
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                // 需要对static文件夹下静态资源进行统一放行
                .antMatchers("/","/login/**","/assets/**","/back/**").permitAll()
                .antMatchers("/detail/ordinary/**").hasRole("ordinary")
                .antMatchers("/detail/privilege/**").hasRole("privilege")
                .anyRequest().authenticated();

        // 自定义用户登录控制
        http.formLogin()
                .loginPage("/userLogin").permitAll()
                .usernameParameter("name").passwordParameter("pwd")
                .defaultSuccessUrl("/")
                .failureUrl("/userLogin?error");

        // 自定义用户退出控制
        http.logout()
                .logoutUrl("/mylogout")
                .logoutSuccessUrl("/");

        // 定制Remember-me记住我功能
        http.rememberMe()
                .rememberMeParameter("rememberme")
                .tokenValiditySeconds(200)
                // 对cookie信息进行持久化管理
                .tokenRepository(tokenRepository());

        // 可以关闭Spring Security默认开启的CSRF防护功能
        http.csrf().disable();

    }
    /**
     * 持久化Token存储
     */
    @Bean
    public JdbcTokenRepositoryImpl tokenRepository(){
        JdbcTokenRepositoryImpl jr=new JdbcTokenRepositoryImpl();
        jr.setDataSource(dataSource);
        return jr;
    }


    /**
     * 用户身份认证自定义配置
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //  密码需要设置编码器
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // 使用JDBC进行身份认证
        String userSQL ="select username,password,valid from t_customer " +
                "where username = ?";
        String authoritySQL="select c.username,a.authority from t_customer c,t_authority a,"+
                "t_customer_authority ca where ca.customer_id=c.id " +
                "and ca.authority_id=a.id and c.username =?";
        auth.jdbcAuthentication().passwordEncoder(encoder)
                .dataSource(dataSource)
                .usersByUsernameQuery(userSQL)
                .authoritiesByUsernameQuery(authoritySQL);

        // 使用UserDetailsService进行身份认证
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
    }

}

