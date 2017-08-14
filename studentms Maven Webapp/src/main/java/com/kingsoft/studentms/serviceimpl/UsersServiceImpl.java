package com.kingsoft.studentms.serviceimpl;

import java.beans.IntrospectionException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kingsoft.studentms.basic.ExcelBean;
import com.kingsoft.studentms.basic.ExcelUtil;
import com.kingsoft.studentms.dao.IUsersDao;
import com.kingsoft.studentms.md5.EncodeMd5;
import com.kingsoft.studentms.model.Users;
import com.kingsoft.studentms.service.IUsersService;

@Transactional
@Service("usersService") // 注入绑定controller注入的usersService
public class UsersServiceImpl implements IUsersService {

	@Resource
	private IUsersDao userDao;

	/**
	 * @登录方法
	 * @param username
	 * @param password
	 * @return user
	 */
	@Override
	public Users login(String username, String password) {
		// TODO Auto-generated method stub
		// 密码采用MD5加密在进行数据查找
		Users user = this.userDao.login(username, EncodeMd5.enMD5U32(password));
		return user;
	}

	/**
	 * @登录检查
	 * @param username
	 * @return boolean
	 */
	@Override
	public boolean cheackRegister(String username) {
		// TODO Auto-generated method stub
		Users user = this.userDao.cheackRegister(username);
		if (user != null) // 如果用户已经存在，那么不允许注册
			return true;
		return false;
	}

	/**
	 * @注册方法
	 * @param username
	 * @param password
	 * @return string
	 */
	@Override
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

		System.out.println("MD5加密： " + EncodeMd5.enMD5U32(password));
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

	/**
	 * @检查密码账号是否包含中文
	 * @param str
	 * @return boolean
	 */
	public boolean isChinese(String str) {
		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher m = p.matcher(str);
		return m.find();
	}

	/**
	 * @实现Excel表格导入
	 */
	@Override
	public void importExcel(InputStream inputStream, MultipartFile multipartFile) {
		// TODO Auto-generated method stub
		try {
			List<List<Object>> listob = ExcelUtil.getBankListByExcel(inputStream, multipartFile.getOriginalFilename());
			List<Users> userList = new ArrayList<Users>();
			Users user;

			for (List<Object> userob : listob) {
				user = new Users();
				user.setUsername(String.valueOf(userob.get(0)));
				user.setPassword(EncodeMd5.enMD5U32(String.valueOf(userob.get(1))));
				user.setUserType(String.valueOf(userob.get(2)));
				userList.add(user);
			}
			userDao.insertfoeach(userList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @导出Excel表
	 */
	@Override
	public XSSFWorkbook exportExcel(String userType) {
		// TODO Auto-generated method stub
		// 导出用户信息
		List<Users> userLists = (List<Users>) userDao.getUserList(userType);
		
		System.out.println("userLists  "+userLists.size());
		List<ExcelBean> excelList = new ArrayList<>();
		Map<Integer, List<ExcelBean>> map = new LinkedHashMap<>();
		XSSFWorkbook xssfWorkbook = null;

		// 配置表格展示
		excelList.add(new ExcelBean("用户ID", "usid", 0));
		excelList.add(new ExcelBean("用户名", "username", 0));
		excelList.add(new ExcelBean("密码", "password", 0));
		excelList.add(new ExcelBean("姓名", "name", 0));
		excelList.add(new ExcelBean("性别", "sex", 0));
		excelList.add(new ExcelBean("电话", "phonenum", 0));
		excelList.add(new ExcelBean("邮编", "email", 0));
		excelList.add(new ExcelBean("用户类型", "userType", 0));

		map.put(0, excelList);
		// 设置页名
		String sheetName = "用户信息表";
		// 执行生成Excel表格
		try {
			System.out.println("UsersServiceImpl进入try");
			xssfWorkbook = ExcelUtil.createExcelFile(Users.class, userLists, map, sheetName);
			System.out.println("UsersServiceImpl "+xssfWorkbook);
		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException | ClassNotFoundException
				| IntrospectionException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return xssfWorkbook;
	}
}
