package com.hg.seckill.config.shiro;

import com.alibaba.fastjson.JSON;
import com.hg.seckill.dao.UserMapper;
import com.hg.seckill.model.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

/**
 * Created by YE
 * 2018-11-26 15:05
 */
public class CustomRealm extends AuthorizingRealm {

    @Resource
    private UserMapper userMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();

        User user = userMapper.selectByPrimaryKey(Long.valueOf(username));
        if (user == null) {
            return null;
        }
        String password = user.getPassword();
        String salt = user.getSalt();
        SimpleAuthenticationInfo authenticationInfo =
                new SimpleAuthenticationInfo(username, password, getName());
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(salt));
        return authenticationInfo;
    }
}
