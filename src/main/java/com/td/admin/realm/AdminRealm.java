package com.td.admin.realm;

import com.td.admin.dao.AdminDao;
import com.td.admin.entity.Admin;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义Realm
 */
public class AdminRealm extends AuthorizingRealm {

    @Autowired
    private AdminDao adminDao;


    /**
     * 授权
     * @param principalCollection
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 登陆验证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户名
        String account = (String) token.getPrincipal();
        Admin admin = adminDao.findByUsername(account);


        if (admin!=null){
            //1)principal：认证的实体信息，可以是username，也可以是数据库表对应的用户的实体对象
            String username  = admin.getAccount();

            //2)credentials：数据库中的密码
            String pwd  = admin.getPassword();

            //3)realmName：当前realm对象的name，调用父类的getName()方法即可
            String realmName = getName();

            //4)credentialsSalt盐值
            ByteSource credentialsSalt = ByteSource.Util.bytes(username);//使用账号作为盐值

            //根据用户的情况，来构建AuthenticationInfo对象,通常使用的实现类为SimpleAuthenticationInfo
            //5)与数据库中用户名和密码进行比对，密码盐值加密，第4个参数传入realName。
            SimpleAuthenticationInfo info  = new SimpleAuthenticationInfo(username, pwd, credentialsSalt, realmName);

            //将前台需要的值放到session中去，方便使用
            SecurityUtils.getSubject().getSession().setAttribute("loginAdmin",admin);
            return info;
        }else {
            //6.若用户不存在，可以抛出UnknownAccountException
            System.out.println("======不存在该管理员=========>");
            throw new UnknownAccountException("不存在该管理员");//没找到帐号
        }
    }





}
