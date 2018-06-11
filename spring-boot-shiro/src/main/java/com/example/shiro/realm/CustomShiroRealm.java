package com.example.shiro.realm;

import com.example.shiro.entity.SysUser;
import com.example.shiro.service.RoleService;
import com.example.shiro.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * @description: 自定义的Shiro Reaml
 * @author: TGL
 * @date: 2018/6/9 21:25
 */
@Slf4j
public class CustomShiroRealm extends AuthorizingRealm{
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("------------执行shiro认证-------------");
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        String userName = token.getUsername();
        String password = String.valueOf(token.getPassword());
        SysUser user = new SysUser();
        user.setUserName(userName);
        user.setPassWord(password);
        SysUser sysUser = null;
        try {
            sysUser = userService.queryUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (sysUser == null) {
            throw new UnknownAccountException("未知用户");
        } else {
            if (sysUser.getUserEnable() != 1) {
                throw new DisabledAccountException("失效用户");
            }
            log.info("------------shiro 凭证认证成功------------");
            // 1). principal: 认证的实体信息. 可以是 userName, 也可以是数据表对应的用户的实体类对象.
            Object principal = sysUser;
            // 2). credentials: 密码.
            Object credentials = sysUser.getPassWord();
            // 3). realmName: 当前 realm 对象的 name. 调用父类的 getName() 方法即可
            String realmName = getName();
            // 4). 盐值.
            String sysUserName = sysUser.getUserName();
            ByteSource credentialsSalt = ByteSource.Util.bytes(sysUserName);
            this.getHashValue(credentials, sysUserName);
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                    principal, credentials, realmName);
            return authenticationInfo;
        }
    }
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("---------------- 执行 Shiro 权限获取 ---------------------");
        Object principal = principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        if (principal instanceof SysUser) {
            SysUser userLogin = (SysUser)principal;
            Integer userId = userLogin.getId();
            try {
                Set<String> roles = roleService.queryRoleByUserId(userId);
                authorizationInfo.addRoles(roles);
                Set<String> permissions = userService.queryPermissionsByUserId(userId);
                authorizationInfo.addStringPermissions(permissions);
                log.info("userName[{}]拥有角色={}，权限={}",
                        userLogin.getUserName(), authorizationInfo.getRoles(), authorizationInfo.getStringPermissions());
                log.info("---------------- Shiro 权限获取成功 ----------------------");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return authorizationInfo;
    }

    // test
    private String getHashValue(Object credentials, String userName) {
        String hashAlgorithmName = "MD5";
        //Object credentials = "123456";
        Object salt = ByteSource.Util.bytes(userName);
        int hashIterations = 1024;
        SimpleHash result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        log.info("userName[{}]加密后的密码HexEncodeed={}", userName, result.toString());
        log.info("userName[{}]加密后的密码base64Encoded={}", userName, result.toBase64());
        return result.toString();

    }
}
