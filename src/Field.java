
/**
 * @Auth eric_ma
 * @time 2016年8月8日 下午2:30:57
 * @说明  8583报文域定义
 */
public class Field {
	public int offset_begin; /*  域在当前报文(储存为string类型)的偏移量*/
	public String data_name; /*域名*/
	public int length; /*数据域长度,以字节为单位*/
	public int length_in_byte;/*实际长度（如果是变长）*/
	public int variable_flag; /*是否变长标志0：否 2：2位变长, 3：3位变长*/
	public int datatyp; /*域数据类型0 -- string, 1 -- int, 2 -- binary  3--ASCII  一个ascii码占两个偏移量 长度*2*/
	public String data; /*存放具体值*/
	public int offset_end; /*域结尾在当前报文(储存为string类型)的偏移量*/
	
	Field(int offset_begin,String data_name,int length,int length_in_byte,int variable_flag,int datatyp,String data,int offset_end){
		this.offset_begin = offset_begin; /*域在当前报文(储存为string类型)的偏移量*/
		this.data_name = data_name; /*域名*/
		this.length = length; /*数据域长度,以字节为单位*/
		this.length_in_byte = length_in_byte;/*实际长度（如果是变长）*/
		this.variable_flag = variable_flag; /*是否变长标志0：否 2：2位变长, 3：3位变长*/
		this.datatyp = datatyp; /*0 -- string, 1 -- int, 2 -- binary*/
		this.data = data; /*存放具体值*/
		this.offset_end = offset_end; /*域结尾在当前报文(储存为string类型)的偏移量*/
	}
	
}
