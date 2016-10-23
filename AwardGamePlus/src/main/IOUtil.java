package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
/**
 * 工具类：
 * 
 * @author xianJieHao
 *
 */
public class IOUtil {
	/**
	 * 读取list.txt文件数据到List中
	 * @param file
	 * @return 
	 */
	public static List<Person> loadData(File file) {
		// 判断list.txt是否存在
		if (!file.exists()) {
			System.out.println("文件不存在导致程序退出！");
			throw new RuntimeException("文件不存在导致程序退出");
		} else {
			BufferedReader br = null;
			try {
				br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
				List<Person> personList = new ArrayList<Person>();
				String line = null;
				//读取文件时的判断条件：
				//1.读取到的行非空NULL
				//2.读取的行非空行（""）
				//3.读取的行必须符合信息格式：姓名，号码 如 张三,1673828727
				while ((line = br.readLine()) != null) {
					if(!line.equals("") && line.matches(".+(,|，)?\\d+")){
						personList.add(new Person(line));
					}
				}
				return personList;
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		}
		return null;
	}	

}
