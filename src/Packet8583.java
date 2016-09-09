import java.util.ArrayList;

public class Packet8583 {
	
	private static int LENGTH_TPDU = 10;
	private static int LENGTH_PACKET_HEAD = 12;
	private static int LENGTH_BITMAP = 16;
	
	
	private static final int MAX_FIELD_NUM = 64;

	private String TPDU = new String();
	private String packetHead = new String();//报文头
	private String type = new String();      //报文类型
	private String bitMapString = new String();  //位图数据
	private String data = new String();//位图+域  Field的offset以此为标准
	private ArrayList<Boolean> bitMapList = new ArrayList<Boolean>();
	
	private Field[] packetFields = {
			/* 1.域在当前报文(储存为string类型)的偏移量
			 * 2.域名称
			 * 3.数据域长度,以string偏移量为单位
			 * 4.实际长度（如果是变长）
			 * 5.是否变长标志0：否 2：2位变长, 3：3位变长
			 * 6.域数据储存方式 0-string, 1-int,2-binary 3-ASCII码/*一个ascii码占两个偏移量 长度*2
			 * 7.存放具体值
			 * 8.域结尾在当前报文(储存为string类型)的偏移量*/
			/* FLD 1 */ new Field(0,"BIT MAP,EXTENDED ", 8, 0, 0, 2, null,0), 
			/* FLD 2 */ new Field(0,"主帐号PRIMARY ACCOUNT NUMBER ", 19, 0, 2, 0, null,0), 
			/* FLD 3 */ new Field(0,"交易处理码PROCESSING CODE ", 6, 0, 0, 0, null,0), 
			/* FLD 4 */ new Field(0,"交易金额 AMOUNT, TRANSACTION ", 12, 0, 0, 1, null,0), 
			/* FLD 5 */ new Field(0,"!NO USE ", 12, 0, 0, 0, null,0), 
			/* FLD 6 */ new Field(0,"!NO USE ", 12, 0, 0, 0, null,0), 
			/* FLD 7 */ new Field(0,"!交易日期和时间TRANSACTION DATE AND TIME ", 10, 0, 0, 0, null,0), 
			/* FLD 8 */ new Field(0,"!NO USE ", 8, 0, 0, 0, null,0), 
			/* FLD 9 */ new Field(0,"!NO USE ", 8, 0, 0, 0, null,0), 
			/* FLD 10 */ new Field(0,"!NO USE ", 8, 0, 0, 0, null,0), 
			/* FLD 11 */ new Field(0,"系统跟踪号SYSTEM TRACE AUDIT NUMBER ", 6, 0, 0, 1, null,0), 
			/* FLD 12 */ new Field(0,"本地交易时间TIME, LOCAL TRANSACTION ", 6, 0, 0, 0, null,0), 
			/* FLD 13 */ new Field(0,"本地交易日期DATE, LOCAL TRANSACTION ", 4, 0, 0, 0, null,0), 
			/* FLD 14 */ new Field(0,"有效期DATE, EXPIRATION ", 4, 0, 0, 0, null,0), 
			/* FLD 15 */ new Field(0,"结算日期DATE, SETTLEMENT ", 4, 0, 0, 0, null,0), 
			/* FLD 16 */ new Field(0,"!NO USE ", 4, 0, 0, 0, null,0), 
			/* FLD 17 */ new Field(0,"!获取日期DATE, CAPTURE ", 4, 0, 0, 0, null,0), 
			/* FLD 18 */ new Field(0,"!商户类型MERCHANT'S TYPE ", 4, 0, 0, 0, null,0), 
			/* FLD 19 */ new Field(0,"!NO USE ", 3, 0, 0, 0, null,0), 
			/* FLD 20 */ new Field(0,"!NO USE ", 3, 0, 0, 0, null,0), 
			/* FLD 21 */ new Field(0,"!NO USE ", 3, 0, 0, 0, null,0), 
			/* FLD 22 */ new Field(0,"服务点输入方式POINT OF SERVICE ENTRY MODE ", 3+1, 0, 0, 0, null,0), /*align="left"*/
			/* FLD 23 */ new Field(0,"NO USE (卡片序列号)", 3+1, 0, 0, 0, null,0), /*align="right"*/
			/* FLD 24 */ new Field(0,"!NO USE ", 3, 0, 0, 0, null,0), 
			/* FLD 25 */ new Field(0,"服务点类型代码POINT OF SERVICE CONDITION CODE ", 2, 0, 0, 0, null,0), 
			/* FLD 26 */ new Field(0,"NO USE (服务点PIN获取码)", 2, 0, 0, 0, null,0), 
			/* FLD 27 */ new Field(0,"!NO USE ", 1, 0, 0, 0, null,0), 
			/* FLD 28 */ new Field(0,"!Field28 ", 6, 0, 0, 0, null,0), 
			/* FLD 29 */ new Field(0,"!NO USE ", 8, 0, 1, 0, null,0), 
			/* FLD 30 */ new Field(0,"!NO USE ", 8, 0, 1, 0, null,0), 
			/* FLD 31 */ new Field(0,"!NO USE ", 8, 0, 1, 0, null,0), 
			/* FLD 32 */ new Field(0,"收单机构标识码ACQUIRER INSTITUTION ID. CODE ", 11+1, 0, 2, 0, null,0), /*若域长度为奇数压缩时需补零，多一位*/
			/* FLD 33 */ new Field(0,"!向前机构标识码FORWARDING INSTITUTION ID. CODE ", 11+1, 0, 2, 0, null,0), /*若域长度为奇数压缩时需补零，多一位*/
			/* FLD 34 */ new Field(0,"!NO USE ", 28, 0, 2, 0, null,0), 
			/* FLD 35 */ new Field(0,"二磁道数据TRACK 2 DATA ", 37+1, 0, 2, 0, null,0), /*若域长度为奇数压缩时需补零，多一位*/
			/* FLD 36 */ new Field(0,"三磁道数据TRACK 3 DATA ",104, 0, 3, 3, null,0), 
			/* FLD 37 */ new Field(0,"检索索引号RETRIEVAL REFERENCE NUMBER ", 12, 0, 0, 3, null,0), 
			/* FLD 38 */ new Field(0,"授权码AUTH. IDENTIFICATION RESPONSE ", 6, 0, 0, 3, null,0), 
			/* FLD 39 */ new Field(0,"返回码RESPONSE CODE ", 2, 0, 0, 3, null,0), 
			/* FLD 40 */ new Field(0,"!NO USE ", 3+1, 0, 0, 0, null,0), 
			/* FLD 41 */ new Field(0,"受卡机终端标识码CARD ACCEPTOR TERMINAL ID. ", 8, 0, 0, 3, null,0), 
			/* FLD 42 */ new Field(0,"受卡方标识码CARD ACCEPTOR IDENTIFICATION CODE ", 15, 0, 0, 3, null,0), 
			/* FLD 43 */ new Field(0,"!受卡方名称地址CARD ACCEPTOR NAME LOCATION ", 40, 0, 0, 3, null,0), 
			/* FLD 44 */ new Field(0,"附加响应数据ADDITIONAL RESPONSE DATA ", 25, 0, 2, 3, null,0), 
			/* FLD 45 */ new Field(0,"!NO USE （第一磁道数据）", 76, 0, 2, 0, null,0), 
			/* FLD 46 */ new Field(0,"!NO USE ",999, 0, 3, 0, null,0), 
			/* FLD 47 */ new Field(0,"FIELD47 ",999, 0, 3, 3, null,0), 
			/* FLD 48 */ new Field(0,"附加数据－私有ADDITIONAL DATA --- PRIVATE ",999, 0, 3, 1, null,0), 
			/* FLD 49 */ new Field(0,"交易货币代码CURRENCY CODE,TRANSACTION ", 3, 0, 0, 3, null,0), 
			/* FLD 50 */ new Field(0,"!CURRENCY CODE,SETTLEMENT ", 3, 0, 0, 3, null,0), 
			/* FLD 51 */ new Field(0,"!NO USE ", 3, 0, 0, 0, null,0), 
			/* FLD 52 */ new Field(0,"个人标识码(PIN)PERSONAL IDENTIFICATION NUMBER DATA ", 8*2, 0, 0, 2, null,0), /*8字节加密数据*/
			/* FLD 53 */ new Field(0,"安全控制信息(密钥索引等)SECURITY RELATED CONTROL INformATION", 16, 0, 0, 0, null,0), 
			/* FLD 54 */ new Field(0,"附加金额ADDITIONAL AMOUNTS ",20, 0, 3, 3, null,0), 
			/* FLD 55 */ new Field(0,"NO USE (IC卡数据域)",999, 0, 3, 3, null,0), 
			/* FLD 56 */ new Field(0,"!NO USE ",999, 0, 3, 0, null,0), 
			/* FLD 57 */ new Field(0,"!NO USE ",999, 0, 3, 0, null,0), 
			/* FLD 58 */ new Field(0,"NO USE ",999, 0, 3, 3, null,0), 
			/* FLD 59 */ new Field(0,"!NO USE ",999, 0, 3, 0, null,0), 
			/* FLD 60 */ new Field(0,"NO USE ", 17+1, 0, 3, 0, null,0), 
			/* FLD 61 */ new Field(0,"NO USE ",999, 0, 3, 0, null,0), 
			/* FLD 62 */ new Field(0,"NO USE ", 11+1, 0, 3, 3, null,0), 
			/* FLD 63 */ new Field(0,"NO USE ", 11+1, 0, 3, 3, null,0), 
			/* FLD 64 */ new Field(0,"报文鉴别码(MAC)MESSAGE AUTHENTICATION CODE", 8*2, 0, 0, 2, null,0), /*8字节MAC数据*/

	};

	Packet8583(String packet) {
		TPDU = packet.substring(0, LENGTH_TPDU);
		packetHead = packet.substring(LENGTH_TPDU, LENGTH_TPDU + LENGTH_PACKET_HEAD);
		type = packet.substring(LENGTH_TPDU + LENGTH_PACKET_HEAD, LENGTH_TPDU + LENGTH_PACKET_HEAD + 4);
		bitMapString = packet.substring(LENGTH_TPDU + LENGTH_PACKET_HEAD + 4, LENGTH_TPDU + LENGTH_PACKET_HEAD + 4 + LENGTH_BITMAP);
		data = packet.substring(LENGTH_TPDU + LENGTH_PACKET_HEAD + 4);//包括位图！
		
		System.out.println("TPDU:" + TPDU);
		System.out.println("packetHead:" + packetHead);
		System.out.println("type:" + type);
		System.out.println("bitMap:" + bitMapString);
		System.out.println();
		System.out.println();

		initBitMapList(bitMapString);//初始化位图
		initFieldInfo();//初始化各域信息

		System.out.println("TPDU:" + TPDU);
		System.out.println("packetHead:" + packetHead);
		System.out.println("type:" + type);
		System.out.println("bitMap:" + bitMapString);
		System.out.println();
		System.out.println();

		
	}

	private void initBitMapList(String bitMapString) {
		// byte[] ret = new byte[8];
		byte[] bitMap = bitMapString.getBytes();
		byte tmp;
		for (int i = 0; i < 16; i++) {// 处理16byte 字符串表示
			for (int j = 0; j < 4; j++) {
				tmp = bitMap[i];
				
					if (((ByteUtil.HexByte2Int(tmp) << j) & 0x08) == 0) {
						bitMapList.add(false);
					} else {
						bitMapList.add(true);
						System.out.println("域" + (i*4 + j+1) + "：有 ");
					}		
			}
		}

		// for(int i = 0; i < 8; i++ ){//处理8byte 16进制表示
		// for (int j = 0; j < 8; j++) {
		// tmp = bitMap[i];
		// if(((tmp >> j) & 0x01) == 0){
		// bitMapList.add(false);
		// }else{
		// bitMapList.add(true);
		// System.out.print("域"+(i*8+j)+"：有 ");
		// }
		// }
		// }
		System.out.println();

	}
	
	private void initFieldInfo(){
		int lastFieldNum = 0 ;
		
		if(bitMapList.get(0)){
			System.out.println("-----该报文有128个域，暂不支持！-----");
		}else {
			initCurrentFieldInfo(0,0);//位图（1域）特殊处理
		}
		
		
		for(int i=1;i<MAX_FIELD_NUM;i++){
			if(bitMapList.get(i)){
				initCurrentFieldInfo(i,lastFieldNum);
				lastFieldNum = i;
			}
		}
		
	}
	private void initCurrentFieldInfo(int fieldNum,int lastFieldNum){//lastFieldNum表示上一个存在的域编号
//		public int offset_begin; /*  域开头在当前报文(储存为string类型)的偏移量*/
//		public int length_in_byte;/*实际长度（如果是变长）*/
//		public String data; /*存放具体值*/
//		public int offset_end; /*域结尾在当前报文(储存为string类型)的偏移量*/
//		
//		
//		public int length; /*数据域长度,以字节为单位*/
//		public int datatyp; /*域数据类型0 -- string, 1 -- int, 2 -- binary*/
//		public int variable_flag; /*是否变长标志0：否 2：2位变长, 3：3位变长*/
//		public String data_name; /*域名*/
		
		if(fieldNum == 0){
			packetFields[fieldNum].offset_begin = 0;
			packetFields[fieldNum].data = bitMapString;	
			packetFields[fieldNum].offset_end = 16;
			return;
		}
		
		
		
		if(packetFields[fieldNum].variable_flag==0){//定长
			
			packetFields[fieldNum].offset_begin = packetFields[lastFieldNum].offset_end;
			if(packetFields[fieldNum].datatyp == 3){
				//一个ascii码占两个偏移量 长度*2
				packetFields[fieldNum].offset_end = packetFields[fieldNum].offset_begin+packetFields[fieldNum].length*2;
			}else {
				packetFields[fieldNum].offset_end = packetFields[fieldNum].offset_begin+packetFields[fieldNum].length;	
			}
			packetFields[fieldNum].data = data.substring(packetFields[fieldNum].offset_begin, packetFields[fieldNum].offset_end);
					
		}else if(packetFields[fieldNum].variable_flag==2){//两位变长
			
			packetFields[fieldNum].length_in_byte = ByteUtil.BCD2Int(data.substring(packetFields[lastFieldNum].offset_end, packetFields[lastFieldNum].offset_end+2),(packetFields[fieldNum].datatyp == 3));
			packetFields[fieldNum].offset_begin = packetFields[lastFieldNum].offset_end+2;
			if(packetFields[fieldNum].datatyp == 3){
				//一个ascii码占两个偏移量 长度*2
				packetFields[fieldNum].offset_end = packetFields[fieldNum].offset_begin+packetFields[fieldNum].length_in_byte*2;
			}else {
				packetFields[fieldNum].offset_end = packetFields[fieldNum].offset_begin+packetFields[fieldNum].length_in_byte;	
			}
			packetFields[fieldNum].data = data.substring(packetFields[fieldNum].offset_begin, packetFields[fieldNum].offset_end);
			
		}else if(packetFields[fieldNum].variable_flag==3){//三位变长
			
			packetFields[fieldNum].length_in_byte = ByteUtil.BCD2Int(data.substring(packetFields[lastFieldNum].offset_end, packetFields[lastFieldNum].offset_end+4),(packetFields[fieldNum].datatyp == 3));
			packetFields[fieldNum].offset_begin = packetFields[lastFieldNum].offset_end+4;
			if(packetFields[fieldNum].datatyp == 3){
				//一个ascii码占两个偏移量 长度*2
				packetFields[fieldNum].offset_end = packetFields[fieldNum].offset_begin+packetFields[fieldNum].length_in_byte*2;
			}else {
				packetFields[fieldNum].offset_end = packetFields[fieldNum].offset_begin+packetFields[fieldNum].length_in_byte;	
			}
			packetFields[fieldNum].data = data.substring(packetFields[fieldNum].offset_begin, packetFields[fieldNum].offset_end);
			
		}else{
			System.out.println("-----域"+lastFieldNum+"内容解析失败！-----");
		}
		////////////////	DEBUG信息  	   ///////////////////////////////////////////////////////////////////////////////////////
		System.out.println("域" + (fieldNum+1) + "："+packetFields[fieldNum].data_name);
		System.out.println("begin："+packetFields[fieldNum].offset_begin+"    end："+packetFields[fieldNum].offset_end);
		if(packetFields[fieldNum].variable_flag == 0){
			System.out.println("长度(定长)："+packetFields[fieldNum].length);
		}else {
			System.out.println("长度(变长)："+packetFields[fieldNum].length_in_byte);	
		}
		
		if(packetFields[fieldNum].datatyp == 0){
			System.out.println("type：string");
		}else if(packetFields[fieldNum].datatyp == 1){
			System.out.println("type：int");
		}else if (packetFields[fieldNum].datatyp == 2) {
			System.out.println("type：binary");
		}else if (packetFields[fieldNum].datatyp == 3) {
			System.out.println("type：ASCII");
		}
		
		System.out.println("数据："+packetFields[fieldNum].data);
		System.out.println();
		///////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		return;
	}
	
	public void showField() {
		for(int i=0;i<MAX_FIELD_NUM;i++){
			if(bitMapList.get(i)){
				
				System.out.println("域" + (i+1) + "："+packetFields[i].data_name);
				System.out.println("begin："+packetFields[i].offset_begin+"    end："+packetFields[i].offset_end);
				if(packetFields[i].variable_flag == 0){
					System.out.println("长度(定长)："+packetFields[i].length);
				}else {
					System.out.println("长度(变长)："+packetFields[i].length_in_byte);	
				}
				
				if(packetFields[i].datatyp == 0){
					System.out.println("type：string");
				}else if(packetFields[i].datatyp == 1){
					System.out.println("type：int");
				}else if (packetFields[i].datatyp == 2) {
					System.out.println("type：binary");
				}else if (packetFields[i].datatyp == 3) {
					System.out.println("type：ASCII");
				}
				
				System.out.println("数据："+packetFields[i].data);
				System.out.println();
			}
		}
	}

	
}
