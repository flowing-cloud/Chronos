package com.chronos.service;

import com.chronos.model.Authority;
import com.chronos.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Classname UserDetailsServiceImpl
 * @Description 自定义一个UserDetailsService接口实现类进行用户认证信息封装
 * @Date 2019-3-5 16:08
 * @Created by CrazyStone
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private CustomerService customerService;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        // 通过业务方法获取用户及权限信息
        Customer customer = customerService.getCustomer(s);
        List<Authority> authorities = customerService.getCustomerAuthority(s);
        // 对用户权限进行封装
        List<SimpleGrantedAuthority> list = authorities.stream().map(authority -> new SimpleGrantedAuthority(authority.getAuthority())).collect(Collectors.toList());
        // 返回封装的UserDetails用户详情类
        if(customer!=null){
            UserDetails userDetails= new User(customer.getUsername(),customer.getPassword(),list);
            return userDetails;
        } else {
            // 如果查询的用户不存在（用户名不存在），必须抛出此异常
            throw new UsernameNotFoundException("当前用户不存在！");
        }
    }
}
