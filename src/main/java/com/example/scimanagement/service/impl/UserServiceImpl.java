package com.example.scimanagement.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.scimanagement.dto.LoginFormDTO;
import com.example.scimanagement.dto.Result;
import com.example.scimanagement.entity.User;
import com.example.scimanagement.mapper.userMapper;
import com.example.scimanagement.service.IUserService;
import com.example.scimanagement.utils.RegexUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

import static com.example.scimanagement.dto.Result.fail;

@Service
@Repository
public class UserServiceImpl extends ServiceImpl<userMapper, User> implements IUserService {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public Result login(LoginFormDTO loginForm) {
        //1.对手机号进行校验
        String phone = loginForm.getPhone()+"";
        if(RegexUtils.isPhoneInvalid(phone)){
            return fail("手机号格式不正确");
        }

        //2.先从redis中查询用户
        if(!Boolean.TRUE.equals(redisTemplate.hasKey(phone))){
            //2.从数据库查询用户
            User user = query().eq("phone_number",phone).one();
            //3.判断用户是否存在
            if(user==null) {
                return Result.fail("用户不存在");
            }
            //4.校验密码
            if(!user.getPassword().equals(loginForm.getPassword())){
                return fail("密码错误");
            }
            redisTemplate.opsForHash().put(phone,"name",user.getName());
            redisTemplate.opsForHash().put(phone,"password",loginForm.getPassword());
            redisTemplate.expire(phone,30, TimeUnit.MINUTES);
            return Result.ok(user);
        }
        //3.redis中存在用户，对密码进行校验
        if(!redisTemplate.opsForValue().get(phone).equals(loginForm.getPassword())){
            return fail("密码错误");
        }
        User user = new User();
        user.setName((String) redisTemplate.opsForHash().get(phone,"name"));
        user.setPassword((String) redisTemplate.opsForHash().get(phone,"password"));
        return Result.ok(user);
    }

    @Override
    public Result register(LoginFormDTO loginForm) {
        //1.对手机号进行校验
        String phone = loginForm.getPhone();
        if(RegexUtils.isPhoneInvalid(phone)){
            return fail("手机号格式不正确");
        }
        //2.先从redis中查询用户
        if(Boolean.TRUE.equals(redisTemplate.hasKey(phone))){
            return fail("用户已存在");
        }
        //3.再从数据库查询用户
        User user = query().eq("phone_number",phone).one();
        //4.判断用户是否存在
        if(user!=null) {
            return fail("用户已存在");
        }else {
            //4.保存用户
            user = new User();
            user.setPhone_number(loginForm.getPhone());
            user.setPassword(loginForm.getPassword());
            user.setName("用户"+loginForm.getPhone());
            save(user);
            return Result.ok(user);
        }

    }


}
