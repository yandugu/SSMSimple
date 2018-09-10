package ssm.blog.util;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

public class Md5Util {
	//温馨提示：记得在注册中密码存入数据库前也记得加密哦，提供一个utils方法
	//进行shiro加密，返回加密后的结果
	public static String md5(String pass){
		String saltSource = "password";    
		String hashAlgorithmName = "MD5";
		Object salt = new Md5Hash(saltSource);
		int hashIterations = 1;    
		Object result = new SimpleHash(hashAlgorithmName, pass, salt, hashIterations);
		String password = result.toString();
		return password;
	}

}
