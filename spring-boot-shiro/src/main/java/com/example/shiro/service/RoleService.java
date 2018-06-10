package com.example.shiro.service;

import java.util.Set;

/**
 * @description:
 * @author: TGL
 * @date: 2018/6/10 15:20
 */
public interface RoleService {

    public Set<String> queryRoleByUserId(Integer userId) throws Exception;
}
