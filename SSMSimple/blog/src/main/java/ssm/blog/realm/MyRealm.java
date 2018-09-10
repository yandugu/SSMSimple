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

	// ��������Ȩ
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		String username = (String) principals.getPrimaryPrincipal();
		// ͨ���û���ȡ��ɫ��Ϣ
		// Set<String> roles=

		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		// ���ý�ɫ
		// simpleAuthorizationInfo.setRoles(roles);
		// ����Ȩ��
		// simpleAuthorizationInfo.setStringPermissions(stringPermissions);
		return simpleAuthorizationInfo;
	}

	// ��������֤
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		// 1. token �л�ȡ��¼�� username! ע�ⲻ��Ҫ��ȡpassword.
		Object principal = token.getPrincipal();

		// 2. ���� username ��ѯ���ݿ�õ��û�����Ϣ.
		User user = userDao.findUserByName((String) principal);
		if (user != null) {
			password = user.getPassword();

			SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUserName(), password, "realname");
			//����������
			authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes("password"));
			
			return authenticationInfo;
		} else {
			return null;
		}

	}
	
	//init-method ����. 
    public void setCredentialMatcher(){
        HashedCredentialsMatcher  credentialsMatcher = new HashedCredentialsMatcher();    
        credentialsMatcher.setHashAlgorithmName("MD5");//MD5�㷨����
        credentialsMatcher.setHashIterations(1);//1024��ѭ������      
        setCredentialsMatcher(credentialsMatcher);
    }

  //�������Ե��������password��ֵ���ܺ�Ľ�������淽�����������û���ӵ����ݿ�����ģ��������ֱ����main��ã�ֱ�����ݿ�����ˣ�ʡʱ��
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
