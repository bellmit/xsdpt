package com.nju.sdpt.data.dynamicdDatabases;


import com.alibaba.druid.pool.DruidDataSource;
import com.nju.sdpt.util.StringUtil;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 * 加密的数据源
 *
 */

public class EncryptDataSource extends BasicDataSource {
//public class EncryptDataSource extends DruidDataSource {

    @Override
	public void setPassword(String password) {
		password = encryptPassword(password);
		super.setPassword(password);
	}
		
	/**
	 * 加密数据库密码的工具
	 * @param password
	 * @return
	 */
	private String encryptPassword(String password){
		
		String result = password;
		if(StringUtil.indexOf(password, ",")<0){
			return result;
		}
		String[] separate=password.split(",");
		String fydm=separate[0];
		String xlh=separate[1];
		if (StringUtil.equals(fydm, "000000")){
			result = xlh;
		}else{
			getpasswd oo=new getpasswd();
			result=oo.passwd(fydm,xlh);	
		}
		return result;
	}
}

