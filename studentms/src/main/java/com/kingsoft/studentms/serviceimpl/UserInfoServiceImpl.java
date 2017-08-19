package com.kingsoft.studentms.serviceimpl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kingsoft.studentms.basic.ExcelUtil;
import com.kingsoft.studentms.dao.IUserInfoDao;
import com.kingsoft.studentms.md5.EncodeMd5;
import com.kingsoft.studentms.model.UserInfo;
import com.kingsoft.studentms.service.IUserInfoService;

@Service("userInfoService")
public class UserInfoServiceImpl implements IUserInfoService {

	@Resource
	private IUserInfoDao userInfoDao;

	/**
	 * @登录查询用户
	 */
	@Override
	public UserInfo login(String username, String password) {
		// TODO Auto-generated method stub
		UserInfo userInfo = userInfoDao.login(username, EncodeMd5.enMD5U32(password));
		
		return userInfo;
	}

	/**
	 * @单个用户手动添加
	 */
	@Override
	public String addSingle(UserInfo userInfo) {
		// TODO Auto-generated method stub
		// 空教校验
		if (userInfo.getUsername() == null || userInfo.getUsername().trim().equals(""))
			return "用户名不能为空";
		if (userInfo.getPassword() == null || userInfo.getPassword().trim().equals(""))
			return "密码不能为空";
		if (userInfo.getSex() == null || userInfo.getSex().trim().equals(""))
			return "性别不能为空";
		if (userInfo.getUserType() == null || userInfo.getUserType().trim().equals(""))
			return "用户类型不能为空";

		// 长度校验
		if (userInfo.getUsername().length() > 16)
			return "用户名长度不能超过16位";
		if (userInfo.getPassword().length() > 32)
			return "密码长度不能超过32位";
		if (userInfo.getSex().length() > 2)
			return "性别长度不能超过2位";
		if (userInfo.getUserType().length() > 2)
			return "用户类型长度不能超过2位";

		// 匹配数据字段校验
		if (isChinese(userInfo.getUsername()))
			return "用户名不能含有中文字符";
		if (isChinese(userInfo.getPassword()))
			return "密码不能喊有中文";
		if (!userInfo.getSex().equals("1") && !userInfo.getSex().equals("2"))
			return "性别不合法";
		if (!userInfo.getUserType().equals("1") && !userInfo.getUserType().equals("2")
				&& !userInfo.getUserType().equals("3"))
			return "用户类型选择不合法";

		// 查重校验
		if (addCheack(userInfo.getUsername()))
			return "用户已经存在";

		// 结束校验添加用户
		userInfo.setValid("1"); // 配置用户的合法
		userInfo.setPassword(EncodeMd5.enMD5U32(userInfo.getPassword()));
		int success = userInfoDao.add(userInfo);
		if (success > 0)
			return null;
		else
			return "error";
	}

	/**
	 * @实现Excel表格导入
	 * @先暂时不用此方法
	 */
	@Override
	public String importExcel(InputStream inputStream, MultipartFile multipartFile) {
		// TODO Auto-generated method stub

		try {
			List<List<Object>> listob = ExcelUtil.getBankListByExcel(inputStream, multipartFile.getOriginalFilename());

			// 数句装配
			List<UserInfo> userList = new ArrayList<UserInfo>();
			UserInfo userInfo;
			for (List<Object> userob : listob) {
				userInfo = new UserInfo();
				userInfo.setUsername(String.valueOf(userob.get(0)));
				userInfo.setPassword(EncodeMd5.enMD5U32(String.valueOf(userob.get(1))));
				if (String.valueOf(userob.get(2)).equals("男"))
					userInfo.setSex("1");
				else
					userInfo.setSex("2");
				if (String.valueOf(userob.get(3)).trim().equals("管理员"))
					userInfo.setUserType("1");
				else if (String.valueOf(userob.get(3)).trim().equals("老师"))
					userInfo.setUserType("2");
				else
					userInfo.setUserType("3");

				userInfo.setValid("1");
				userList.add(userInfo);
			}

			// 批量导入前校验每一条数据是否合法，合法就往下执行，否则返回message
			String message = addForeachCheack(userList);
			if (message != null)
				return message;

			// sql编程如果在插入的过程中出错，那么执行rollback
			userInfoDao.addForeach(userList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @批量导入逐一校验
	 * @param userInfo
	 * @return
	 */
	public String addForeachCheack(Object object) {

		List<List<Object>> listob = (List<List<Object>>) object;
		int index = 0;
		// 循环检验
		for (List<Object> userOb : listob) {

			index++;
			// 校验不能为空
			for (int i = 0; i < userOb.size(); i++) {
				if (userOb.get(i) == null || String.valueOf(userOb.get(i)).trim().equals(""))
					return "第" + index + "行第" + i + "列不能为空";
			}

			/*
			 * 校验长度不能超过数据库限定范围 由于数据库设定的字长类型与长度不一致这里有点特殊需要指定校验 excel表格规定使用从0,0开始
			 * 第一列为用户名username 第二列为密码password 第三列为性别sex {1,0} 1男，2女
			 * 第四列为用户类型userType{1,2,3} 1管理员，2老师，3学生
			 */

			// 用户名密码不能含有中文以及其他sex,usertype
			if (isChinese(String.valueOf(userOb.get(0))))
				return "第" + index + "行的第1列用户名不能含有中文";
			if (isChinese(String.valueOf(userOb.get(1))))
				return "第" + index + "行的第2列密码名不能含有中文";
			if (!String.valueOf(userOb.get(2)).trim().equals("1") && !String.valueOf(userOb.get(2)).trim().equals("2"))
				return "第" + index + "行的第3列性别不合法";
			if (!String.valueOf(userOb.get(3)).trim().equals("3")) // 仅限于学生
				return "第" + index + "行的第4列用户类型不合法";

			// 校验数据库表的合法性
			if (String.valueOf(userOb.get(0)).trim().length() > 16)
				return "第" + index + "行的第1列用户名长度不能超过16";
			if (String.valueOf(userOb.get(1)).trim().length() > 32)
				return "第" + index + "行的第2列密码长度不能超过32";

			if (String.valueOf(userOb.get(2)).trim().length() > 2)
				return "第" + index + "行的第3列性别长度不能超过2";
			if (String.valueOf(userOb.get(3)).trim().length() > 2)
				return "第" + index + "行的第4列性别长度不能超过2";
		}
		return null;
	}

	/**
	 * @导出Excel表
	 * 
	 * @Override public XSSFWorkbook exportExcel(String userType) { // TODO
	 *           Auto-generated method stub // 导出用户信息 List<Users> userLists =
	 *           (List<Users>) userDao.getUserList(userType);
	 * 
	 *           System.out.println("userLists  "+userLists.size()); List
	 *           <ExcelBean> excelList = new ArrayList<>(); Map<Integer, List
	 *           <ExcelBean>> map = new LinkedHashMap<>(); XSSFWorkbook
	 *           xssfWorkbook = null;
	 * 
	 *           // 配置表格展示 excelList.add(new ExcelBean("用户ID", "usid", 0));
	 *           excelList.add(new ExcelBean("用户名", "username", 0));
	 *           excelList.add(new ExcelBean("密码", "password", 0));
	 *           excelList.add(new ExcelBean("姓名", "name", 0));
	 *           excelList.add(new ExcelBean("性别", "sex", 0)); excelList.add(new
	 *           ExcelBean("电话", "phonenum", 0)); excelList.add(new
	 *           ExcelBean("邮编", "email", 0)); excelList.add(new
	 *           ExcelBean("用户类型", "userType", 0));
	 * 
	 *           map.put(0, excelList); // 设置页名 String sheetName = "用户信息表"; //
	 *           执行生成Excel表格 try { System.out.println("UsersServiceImpl进入try");
	 *           xssfWorkbook = ExcelUtil.createExcelFile(Users.class,
	 *           userLists, map, sheetName); System.out.println(
	 *           "UsersServiceImpl "+xssfWorkbook); } catch
	 *           (IllegalArgumentException | IllegalAccessException |
	 *           InvocationTargetException | ClassNotFoundException |
	 *           IntrospectionException | ParseException e) { // TODO
	 *           Auto-generated catch block e.printStackTrace(); } return
	 *           xssfWorkbook; }
	 */

	/**
	 * @添加用户查重检查
	 * @param username
	 * @return boolean 该业务不需要在controller层调用，故不需要在接口层写
	 */
	public boolean addCheack(String username) {
		// TODO Auto-generated method stub
		UserInfo userInfo = this.userInfoDao.addCheack(username);
		if (userInfo != null) // 如果用户已经存在，那么不允许注册
			return true;
		return false;
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

}
