package com.example.shiro.mapper;

import com.example.shiro.entity.SysRole;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysRoleMapper extends Mapper<SysRole> {
    List<SysRole> queryRoleByUserId(@Param("userId") Integer userId);
}