package com.kingsoft.studentms.serviceimpl;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.kingsoft.studentms.dao.IUsersDao;
import com.kingsoft.studentms.md5.EncodeMd5;
import com.kingsoft.studentms.model.Users;
import com.kingsoft.studentms.service.IUsersService;

@Transactional
@Service("usersService") // 注入绑定controller注入的usersService
public class UsersServiceImpl implements IUsersService {

	@Resource
	private IUsersDao userDao;
	
	
	public Users getUserByUsername(String username){
		System.out.println(username);
		return userDao.getUserByUsername(username);
	}
	
	
	public boolean doRegister(Users users){
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//杨超
	public Users login(String username, String password) {
		// TODO Auto-generated method stub
		// 密码采用MD5加密在进行数据查找
		Users user = this.userDao.login(username, EncodeMd5.enMD5U32(password));
		return user;
	}

	public boolean cheackRegister(String username) {
		// TODO Auto-generated method stub
		Users user = this.userDao.cheackRegister(username);
		if (user != null) // 如果用户已经存在，那么不允许注册
			return true;
		return false;
	}

	public String register(String username, String password) {
		// TODO Auto-generated method stub
		int userType;
		// 对于注册信息进行校验
		if (username == null || username.trim().equals(""))
			return "用户名不能为空";
		if (password == null || password.trim().equals(""))
			return "密码不能为空";
		if (isChinese(username))
			return "账号不能含有中文字符";
		if (isChinese(password))
			return "密码不能含有中文字符";
		if (username.length() > 16)
			return "用户名长度不宜超过16";
		if (password.length() > 32)
			return "密码长度不宜超过32";
		if (cheackRegister(username)) // 检查用户是否存在
			return "该用户已经存在，请另选用户名";
		// 随机获取用户类型1,2
		Random r = new Random();
		userType = r.nextInt(2) + 1;// 返回[0,2)集合中的整数，注意不包括2

		System.out.println("MD5加密： "+EncodeMd5.enMD5U32(password));
		// 用户打包
		Users user = new Users();
		user.setUsername(username);
		user.setPassword(EncodeMd5.enMD5U32(password));
		user.setUserType(String.valueOf(userType));
		int success = this.userDao.register(user);
		if (success > 0)
			return null;
		return "error";
	}

	public boolean isChinese(String str) {
		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher m = p.matcher(str);
		return m.find();
	}
}
