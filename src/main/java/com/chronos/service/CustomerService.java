package com.chronos.service;


import com.chronos.model.Authority;
import com.chronos.model.Customer;
import com.chronos.repository.AuthorityRepository;
import com.chronos.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname CustomerService
 * @Description 对用户数据结合Redis缓存进行业务处理
 * @Date 2019-3-5 15:58
 * @Created by CrazyStone
 */
@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private RedisTemplate redisTemplate;

    //根据用户名称字符串返回用户对象
    public Customer findByUserName(String userName){
        return customerRepository.findByUsername(userName);
    }
    public List<Customer> findAllCustomer(){
        return customerRepository.findAllByOrderById();
    }
    //按 id 号返回用户对象
    public Customer findById(Integer id){
        return customerRepository.findById(id).get();
    }
    //保存用户对象
    public boolean saveCustomer(Customer customer){
        customerRepository.save(customer);
        return true ;
    }
    //按 id 号删除用户
    public boolean deleteCustomer(Integer id){
        customerRepository.deleteById(id);
        return true;
    }
    //更新用户信息
    public boolean updateCustomer(Customer customer ){
        customerRepository.deleteById(customer.getId() );
        customerRepository.save(customer);
        return true ;
    }

    // 业务控制：使用唯一用户名查询用户信息
    public Customer getCustomer(String username){
        Customer customer=null;
        Object o = redisTemplate.opsForValue().get("customer_"+username);
        if(o!=null){
            customer=(Customer)o;
        }else {
            customer = customerRepository.findByUsername(username);
            if(customer!=null){
                redisTemplate.opsForValue().set("customer_"+username,customer);
            }
        }
        return customer;
    }
    // 业务控制：使用唯一用户名查询用户权限
    public List<Authority> getCustomerAuthority(String username){
        List<Authority> authorities=null;
        Object o = redisTemplate.opsForValue().get("authorities_"+username);
        if(o!=null){
            authorities=(List<Authority>)o;
        }else {
            authorities=authorityRepository.findAuthoritiesByUsername(username);
            if(authorities.size()>0){
                redisTemplate.opsForValue().set("authorities_"+username,authorities);
            }
        }
        return authorities;
    }
}

