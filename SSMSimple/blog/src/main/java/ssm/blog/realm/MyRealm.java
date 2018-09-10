package ssm.blog.realm;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import ssm.blog.dao.UserDao;
import ssm.blog.pojo.User;

public class MyRealm extends AuthorizingRealm {
	@Autowired
	private UserDao userDao;

	String password;

	// 用来做授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		String username = (String) principals.getPrimaryPrincipal();
		// 通过用户获取角色信息
		// Set<String> roles=

		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		// 设置角色
		// simpleAuthorizationInfo.setRoles(roles);
		// 设置权限
		// simpleAuthorizationInfo.setStringPermissions(stringPermissions);
		return simpleAuthorizationInfo;
	}

	// 用来做认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		// 1. token 中获取登录的 username! 注意不需要获取password.
		Object principal = token.getPrincipal();

		// 2. 利用 username 查询数据库得到用户的信息.
		User user = userDao.findUserByName((String) principal);
		if (user != null) {
			password = user.getPassword();

			SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUserName(), password, "realname");
			//设置密码盐
			authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes("password"));
			
			return authenticationInfo;
		} else {
			return null;
		}

	}
	
	//init-method 配置. 
    public void setCredentialMatcher(){
        HashedCredentialsMatcher  credentialsMatcher = new HashedCredentialsMatcher();    
        credentialsMatcher.setHashAlgorithmName("MD5");//MD5算法加密
        credentialsMatcher.setHashIterations(1);//1024次循环加密      
        setCredentialsMatcher(credentialsMatcher);
    }

  //用来测试的算出密码password盐值加密后的结果，下面方法用于新增用户添加到数据库操作的，我这里就直接用main获得，直接数据库添加了，省时间
//    public static void main(String[] args) {
//        String saltSource = "password";    
//        String hashAlgorithmName = "MD5";
//        String credentials = "123456";
//        Object salt = new Md5Hash(saltSource);
//        int hashIterations = 1024;            
//        Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
//        System.out.println(result);
//    }
}
