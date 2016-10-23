package main;
/**
 * 抽奖信息实体类
 * 包括姓名 号码
 * @author xianJieHao
 *
 */
public class Person {
	String name;
	String phone;
	public Person(){
		this.name="";
		this.phone ="";
	}
	public Person(String str){
		String[] data;
		if( str.matches(".+,\\d+")){ //以英文字符逗号分割的
		    data = str.split(",");
		 }else{
		    data = str.split("，"); //以中文逗号分割的
		 }
		if(data.length == 2){
		this.name = data[0];
		this.phone = data[1];
		}else{
			System.out.println("Pserson--21--line文件信息格式有错！");
			return;
		}
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Override
	public String toString() {
		return name + " " + phone;
	}
	

}
