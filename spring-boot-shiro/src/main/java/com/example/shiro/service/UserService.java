package com.example.shiro.service;

import com.example.shiro.entity.SysUser;

import java.util.Set;

/**
 * @description:
 * @author: TGL
 * @date: 2018/6/10 14:33
 */
public interface UserService {
    public SysUser queryUser(SysUser sysUser) throws Exception;;
    public int saveUser(SysUser sysUser) throws Exception;
    public Set<String> queryPermissionsByUserId(Integer userId) throws Exception;
}
