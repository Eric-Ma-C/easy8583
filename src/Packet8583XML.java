//
///**
// * @Auth eric_ma
// * @time 2016年8月8日 下午12:43:01
// * @说明     储存xml报文格式配置文件数据的类
// * 
// * 
// *     /* 1.域在当前报文(储存为string类型)的偏移量
//			 * 2.域名称
//			 * 3.数据域长度,以string偏移量为单位
//			 * 4.实际长度（如果是变长）
//			 * 5.是否变长标志0：否 2：2位变长, 3：3位变长
//			 * 6.域数据储存方式 0-string, 1-int,2-binary 3-ASCII码/*一个ascii码占两个偏移量 长度*2
//			 * 7.存放具体值
//			 * 8.域结尾在当前报文(储存为string类型)的偏移量
//			 //FLD 1 // new Field(0,"BIT MAP,EXTENDED ", 8, 0, 0, 2, null,0), 
// */
//
//public class Packet8583XML {
//	
//	private class fieldInfo
//	private int offsetBegin;
//	private String name;
//	private int RealLen;
//	private int varLenFlag;
//	private int dataType;
//	private String data;
//	private int offsetEnd;
//
//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public Short getAge() {
//		return age;
//	}
//
//	public void setAge(Short age) {
//		this.age = age;
//	}
//}
