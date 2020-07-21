package cn.lsl.data.exchange.syncserver.utils;


import org.jasypt.util.text.BasicTextEncryptor;
public class EncryptUtils {
	public static void main(String[] args) {
		String key = "NcQkWcPTf4APOwVLfsbJ9yHpNPPkEcYJ";
		BasicTextEncryptor encryptor = new BasicTextEncryptor();
		encryptor.setPassword(key);
		String beforeUrl = "jdbc:mysql://10.50.66.4:3309/bigdata_data?useUnicode=true&characterEncoding=utf-8&useSSL=true";
		String beforeName = "gsidc";
		String beforePassWord = "gsidc@2019!";
		String url = encryptor.encrypt(beforeUrl);
		String name = encryptor.encrypt(beforeName);
		String password = encryptor.encrypt(beforePassWord);
		System.out.println(url+"----------------");
		System.out.println(name+"----------------");
		System.out.println(password+"----------------");
	}

}