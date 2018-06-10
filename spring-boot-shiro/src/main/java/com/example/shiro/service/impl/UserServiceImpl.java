package com.example.shiro.service.impl;

import com.example.shiro.entity.SysUser;
import com.example.shiro.mapper.SysResourcesMapper;
import com.example.shiro.mapper.SysUserMapper;
import com.example.shiro.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @description:
 * @author: TGL
 * @date: 2018/6/10 14:35
 */
@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysResourcesMapper sysResourcesMapper;
    @Override
    public SysUser queryUser(SysUser sysUser) throws Exception {
        return sysUserMapper.selectOne(sysUser);
    }

    @Override
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public int saveUser(SysUser sysUser) throws Exception {
        return sysUserMapper.insertSelective(sysUser);
    }

    @Override
    public Set<String> queryPermissionsByUserId(Integer userId) throws Exception {
        Set<String> permissions = sysResourcesMapper.findResNameByUserId(userId);
        Set<String> result = new HashSet<>();
        for (String permission : permissions) {
            if (StringUtils.isBlank(permission)) {
                continue;
            }
            permission = StringUtils.trim(permission);
            // 
            result.addAll(Arrays.asList(permission.split("\\s*;\\s*")));
        }
        return result;
    }
}
