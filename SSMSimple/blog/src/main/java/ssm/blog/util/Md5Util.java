package ssm.blog.util;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

public class Md5Util {
	//��ܰ��ʾ���ǵ���ע��������������ݿ�ǰҲ�ǵü���Ŷ���ṩһ��utils����
	//����shiro���ܣ����ؼ��ܺ�Ľ��
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
