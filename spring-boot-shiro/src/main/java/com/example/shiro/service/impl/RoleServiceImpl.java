package com.example.shiro.service.impl;

import com.example.shiro.entity.SysRole;
import com.example.shiro.mapper.SysRoleMapper;
import com.example.shiro.service.RoleService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @description:
 * @author: TGL
 * @date: 2018/6/10 15:20
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Override
    public Set<String> queryRoleByUserId(Integer userId) throws Exception {
        List<SysRole> sysRoles = sysRoleMapper.queryRoleByUserId(userId);
        if (CollectionUtils.isNotEmpty(sysRoles)) {
            Set<String> roles = new HashSet<>();
            sysRoles.forEach((SysRole role) -> {
                roles.add(role.getRoleDesc());
            });
        }
        return null;
    }
}
