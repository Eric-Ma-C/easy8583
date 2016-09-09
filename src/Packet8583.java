import java.util.ArrayList;

public class Packet8583 {
	
	private static int LENGTH_TPDU = 10;
	private static int LENGTH_PACKET_HEAD = 12;
	private static int LENGTH_BITMAP = 16;
	
	
	private static final int MAX_FIELD_NUM = 64;

	private String TPDU = new String();
	private String packetHead = new String();//����ͷ
	private String type = new String();      //��������
	private String bitMapString = new String();  //λͼ����
	private String data = new String();//λͼ+��  Field��offset�Դ�Ϊ��׼
	private ArrayList<Boolean> bitMapList = new ArrayList<Boolean>();
	
	private Field[] packetFields = {
			/* 1.���ڵ�ǰ����(����Ϊstring����)��ƫ����
			 * 2.������
			 * 3.�����򳤶�,��stringƫ����Ϊ��λ
			 * 4.ʵ�ʳ��ȣ�����Ǳ䳤��
			 * 5.�Ƿ�䳤��־0���� 2��2λ�䳤, 3��3λ�䳤
			 * 6.�����ݴ��淽ʽ 0-string, 1-int,2-binary 3-ASCII��/*һ��ascii��ռ����ƫ���� ����*2
			 * 7.��ž���ֵ
			 * 8.���β�ڵ�ǰ����(����Ϊstring����)��ƫ����*/
			/* FLD 1 */ new Field(0,"BIT MAP,EXTENDED ", 8, 0, 0, 2, null,0), 
			/* FLD 2 */ new Field(0,"���ʺ�PRIMARY ACCOUNT NUMBER ", 19, 0, 2, 0, null,0), 
			/* FLD 3 */ new Field(0,"���״�����PROCESSING CODE ", 6, 0, 0, 0, null,0), 
			/* FLD 4 */ new Field(0,"���׽�� AMOUNT, TRANSACTION ", 12, 0, 0, 1, null,0), 
			/* FLD 5 */ new Field(0,"!NO USE ", 12, 0, 0, 0, null,0), 
			/* FLD 6 */ new Field(0,"!NO USE ", 12, 0, 0, 0, null,0), 
			/* FLD 7 */ new Field(0,"!�������ں�ʱ��TRANSACTION DATE AND TIME ", 10, 0, 0, 0, null,0), 
			/* FLD 8 */ new Field(0,"!NO USE ", 8, 0, 0, 0, null,0), 
			/* FLD 9 */ new Field(0,"!NO USE ", 8, 0, 0, 0, null,0), 
			/* FLD 10 */ new Field(0,"!NO USE ", 8, 0, 0, 0, null,0), 
			/* FLD 11 */ new Field(0,"ϵͳ���ٺ�SYSTEM TRACE AUDIT NUMBER ", 6, 0, 0, 1, null,0), 
			/* FLD 12 */ new Field(0,"���ؽ���ʱ��TIME, LOCAL TRANSACTION ", 6, 0, 0, 0, null,0), 
			/* FLD 13 */ new Field(0,"���ؽ�������DATE, LOCAL TRANSACTION ", 4, 0, 0, 0, null,0), 
			/* FLD 14 */ new Field(0,"��Ч��DATE, EXPIRATION ", 4, 0, 0, 0, null,0), 
			/* FLD 15 */ new Field(0,"��������DATE, SETTLEMENT ", 4, 0, 0, 0, null,0), 
			/* FLD 16 */ new Field(0,"!NO USE ", 4, 0, 0, 0, null,0), 
			/* FLD 17 */ new Field(0,"!��ȡ����DATE, CAPTURE ", 4, 0, 0, 0, null,0), 
			/* FLD 18 */ new Field(0,"!�̻�����MERCHANT'S TYPE ", 4, 0, 0, 0, null,0), 
			/* FLD 19 */ new Field(0,"!NO USE ", 3, 0, 0, 0, null,0), 
			/* FLD 20 */ new Field(0,"!NO USE ", 3, 0, 0, 0, null,0), 
			/* FLD 21 */ new Field(0,"!NO USE ", 3, 0, 0, 0, null,0), 
			/* FLD 22 */ new Field(0,"��������뷽ʽPOINT OF SERVICE ENTRY MODE ", 3+1, 0, 0, 0, null,0), /*align="left"*/
			/* FLD 23 */ new Field(0,"NO USE (��Ƭ���к�)", 3+1, 0, 0, 0, null,0), /*align="right"*/
			/* FLD 24 */ new Field(0,"!NO USE ", 3, 0, 0, 0, null,0), 
			/* FLD 25 */ new Field(0,"��������ʹ���POINT OF SERVICE CONDITION CODE ", 2, 0, 0, 0, null,0), 
			/* FLD 26 */ new Field(0,"NO USE (�����PIN��ȡ��)", 2, 0, 0, 0, null,0), 
			/* FLD 27 */ new Field(0,"!NO USE ", 1, 0, 0, 0, null,0), 
			/* FLD 28 */ new Field(0,"!Field28 ", 6, 0, 0, 0, null,0), 
			/* FLD 29 */ new Field(0,"!NO USE ", 8, 0, 1, 0, null,0), 
			/* FLD 30 */ new Field(0,"!NO USE ", 8, 0, 1, 0, null,0), 
			/* FLD 31 */ new Field(0,"!NO USE ", 8, 0, 1, 0, null,0), 
			/* FLD 32 */ new Field(0,"�յ�������ʶ��ACQUIRER INSTITUTION ID. CODE ", 11+1, 0, 2, 0, null,0), /*���򳤶�Ϊ����ѹ��ʱ�貹�㣬��һλ*/
			/* FLD 33 */ new Field(0,"!��ǰ������ʶ��FORWARDING INSTITUTION ID. CODE ", 11+1, 0, 2, 0, null,0), /*���򳤶�Ϊ����ѹ��ʱ�貹�㣬��һλ*/
			/* FLD 34 */ new Field(0,"!NO USE ", 28, 0, 2, 0, null,0), 
			/* FLD 35 */ new Field(0,"���ŵ�����TRACK 2 DATA ", 37+1, 0, 2, 0, null,0), /*���򳤶�Ϊ����ѹ��ʱ�貹�㣬��һλ*/
			/* FLD 36 */ new Field(0,"���ŵ�����TRACK 3 DATA ",104, 0, 3, 3, null,0), 
			/* FLD 37 */ new Field(0,"����������RETRIEVAL REFERENCE NUMBER ", 12, 0, 0, 3, null,0), 
			/* FLD 38 */ new Field(0,"��Ȩ��AUTH. IDENTIFICATION RESPONSE ", 6, 0, 0, 3, null,0), 
			/* FLD 39 */ new Field(0,"������RESPONSE CODE ", 2, 0, 0, 3, null,0), 
			/* FLD 40 */ new Field(0,"!NO USE ", 3+1, 0, 0, 0, null,0), 
			/* FLD 41 */ new Field(0,"�ܿ����ն˱�ʶ��CARD ACCEPTOR TERMINAL ID. ", 8, 0, 0, 3, null,0), 
			/* FLD 42 */ new Field(0,"�ܿ�����ʶ��CARD ACCEPTOR IDENTIFICATION CODE ", 15, 0, 0, 3, null,0), 
			/* FLD 43 */ new Field(0,"!�ܿ������Ƶ�ַCARD ACCEPTOR NAME LOCATION ", 40, 0, 0, 3, null,0), 
			/* FLD 44 */ new Field(0,"������Ӧ����ADDITIONAL RESPONSE DATA ", 25, 0, 2, 3, null,0), 
			/* FLD 45 */ new Field(0,"!NO USE ����һ�ŵ����ݣ�", 76, 0, 2, 0, null,0), 
			/* FLD 46 */ new Field(0,"!NO USE ",999, 0, 3, 0, null,0), 
			/* FLD 47 */ new Field(0,"FIELD47 ",999, 0, 3, 3, null,0), 
			/* FLD 48 */ new Field(0,"�������ݣ�˽��ADDITIONAL DATA --- PRIVATE ",999, 0, 3, 1, null,0), 
			/* FLD 49 */ new Field(0,"���׻��Ҵ���CURRENCY CODE,TRANSACTION ", 3, 0, 0, 3, null,0), 
			/* FLD 50 */ new Field(0,"!CURRENCY CODE,SETTLEMENT ", 3, 0, 0, 3, null,0), 
			/* FLD 51 */ new Field(0,"!NO USE ", 3, 0, 0, 0, null,0), 
			/* FLD 52 */ new Field(0,"���˱�ʶ��(PIN)PERSONAL IDENTIFICATION NUMBER DATA ", 8*2, 0, 0, 2, null,0), /*8�ֽڼ�������*/
			/* FLD 53 */ new Field(0,"��ȫ������Ϣ(��Կ������)SECURITY RELATED CONTROL INformATION", 16, 0, 0, 0, null,0), 
			/* FLD 54 */ new Field(0,"���ӽ��ADDITIONAL AMOUNTS ",20, 0, 3, 3, null,0), 
			/* FLD 55 */ new Field(0,"NO USE (IC��������)",999, 0, 3, 3, null,0), 
			/* FLD 56 */ new Field(0,"!NO USE ",999, 0, 3, 0, null,0), 
			/* FLD 57 */ new Field(0,"!NO USE ",999, 0, 3, 0, null,0), 
			/* FLD 58 */ new Field(0,"NO USE ",999, 0, 3, 3, null,0), 
			/* FLD 59 */ new Field(0,"!NO USE ",999, 0, 3, 0, null,0), 
			/* FLD 60 */ new Field(0,"NO USE ", 17+1, 0, 3, 0, null,0), 
			/* FLD 61 */ new Field(0,"NO USE ",999, 0, 3, 0, null,0), 
			/* FLD 62 */ new Field(0,"NO USE ", 11+1, 0, 3, 3, null,0), 
			/* FLD 63 */ new Field(0,"NO USE ", 11+1, 0, 3, 3, null,0), 
			/* FLD 64 */ new Field(0,"���ļ�����(MAC)MESSAGE AUTHENTICATION CODE", 8*2, 0, 0, 2, null,0), /*8�ֽ�MAC����*/

	};

	Packet8583(String packet) {
		TPDU = packet.substring(0, LENGTH_TPDU);
		packetHead = packet.substring(LENGTH_TPDU, LENGTH_TPDU + LENGTH_PACKET_HEAD);
		type = packet.substring(LENGTH_TPDU + LENGTH_PACKET_HEAD, LENGTH_TPDU + LENGTH_PACKET_HEAD + 4);
		bitMapString = packet.substring(LENGTH_TPDU + LENGTH_PACKET_HEAD + 4, LENGTH_TPDU + LENGTH_PACKET_HEAD + 4 + LENGTH_BITMAP);
		data = packet.substring(LENGTH_TPDU + LENGTH_PACKET_HEAD + 4);//����λͼ��
		
		System.out.println("TPDU:" + TPDU);
		System.out.println("packetHead:" + packetHead);
		System.out.println("type:" + type);
		System.out.println("bitMap:" + bitMapString);
		System.out.println();
		System.out.println();

		initBitMapList(bitMapString);//��ʼ��λͼ
		initFieldInfo();//��ʼ��������Ϣ

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
		for (int i = 0; i < 16; i++) {// ����16byte �ַ�����ʾ
			for (int j = 0; j < 4; j++) {
				tmp = bitMap[i];
				
					if (((ByteUtil.HexByte2Int(tmp) << j) & 0x08) == 0) {
						bitMapList.add(false);
					} else {
						bitMapList.add(true);
						System.out.println("��" + (i*4 + j+1) + "���� ");
					}		
			}
		}

		// for(int i = 0; i < 8; i++ ){//����8byte 16���Ʊ�ʾ
		// for (int j = 0; j < 8; j++) {
		// tmp = bitMap[i];
		// if(((tmp >> j) & 0x01) == 0){
		// bitMapList.add(false);
		// }else{
		// bitMapList.add(true);
		// System.out.print("��"+(i*8+j)+"���� ");
		// }
		// }
		// }
		System.out.println();

	}
	
	private void initFieldInfo(){
		int lastFieldNum = 0 ;
		
		if(bitMapList.get(0)){
			System.out.println("-----�ñ�����128�����ݲ�֧�֣�-----");
		}else {
			initCurrentFieldInfo(0,0);//λͼ��1�����⴦��
		}
		
		
		for(int i=1;i<MAX_FIELD_NUM;i++){
			if(bitMapList.get(i)){
				initCurrentFieldInfo(i,lastFieldNum);
				lastFieldNum = i;
			}
		}
		
	}
	private void initCurrentFieldInfo(int fieldNum,int lastFieldNum){//lastFieldNum��ʾ��һ�����ڵ�����
//		public int offset_begin; /*  ��ͷ�ڵ�ǰ����(����Ϊstring����)��ƫ����*/
//		public int length_in_byte;/*ʵ�ʳ��ȣ�����Ǳ䳤��*/
//		public String data; /*��ž���ֵ*/
//		public int offset_end; /*���β�ڵ�ǰ����(����Ϊstring����)��ƫ����*/
//		
//		
//		public int length; /*�����򳤶�,���ֽ�Ϊ��λ*/
//		public int datatyp; /*����������0 -- string, 1 -- int, 2 -- binary*/
//		public int variable_flag; /*�Ƿ�䳤��־0���� 2��2λ�䳤, 3��3λ�䳤*/
//		public String data_name; /*����*/
		
		if(fieldNum == 0){
			packetFields[fieldNum].offset_begin = 0;
			packetFields[fieldNum].data = bitMapString;	
			packetFields[fieldNum].offset_end = 16;
			return;
		}
		
		
		
		if(packetFields[fieldNum].variable_flag==0){//����
			
			packetFields[fieldNum].offset_begin = packetFields[lastFieldNum].offset_end;
			if(packetFields[fieldNum].datatyp == 3){
				//һ��ascii��ռ����ƫ���� ����*2
				packetFields[fieldNum].offset_end = packetFields[fieldNum].offset_begin+packetFields[fieldNum].length*2;
			}else {
				packetFields[fieldNum].offset_end = packetFields[fieldNum].offset_begin+packetFields[fieldNum].length;	
			}
			packetFields[fieldNum].data = data.substring(packetFields[fieldNum].offset_begin, packetFields[fieldNum].offset_end);
					
		}else if(packetFields[fieldNum].variable_flag==2){//��λ�䳤
			
			packetFields[fieldNum].length_in_byte = ByteUtil.BCD2Int(data.substring(packetFields[lastFieldNum].offset_end, packetFields[lastFieldNum].offset_end+2),(packetFields[fieldNum].datatyp == 3));
			packetFields[fieldNum].offset_begin = packetFields[lastFieldNum].offset_end+2;
			if(packetFields[fieldNum].datatyp == 3){
				//һ��ascii��ռ����ƫ���� ����*2
				packetFields[fieldNum].offset_end = packetFields[fieldNum].offset_begin+packetFields[fieldNum].length_in_byte*2;
			}else {
				packetFields[fieldNum].offset_end = packetFields[fieldNum].offset_begin+packetFields[fieldNum].length_in_byte;	
			}
			packetFields[fieldNum].data = data.substring(packetFields[fieldNum].offset_begin, packetFields[fieldNum].offset_end);
			
		}else if(packetFields[fieldNum].variable_flag==3){//��λ�䳤
			
			packetFields[fieldNum].length_in_byte = ByteUtil.BCD2Int(data.substring(packetFields[lastFieldNum].offset_end, packetFields[lastFieldNum].offset_end+4),(packetFields[fieldNum].datatyp == 3));
			packetFields[fieldNum].offset_begin = packetFields[lastFieldNum].offset_end+4;
			if(packetFields[fieldNum].datatyp == 3){
				//һ��ascii��ռ����ƫ���� ����*2
				packetFields[fieldNum].offset_end = packetFields[fieldNum].offset_begin+packetFields[fieldNum].length_in_byte*2;
			}else {
				packetFields[fieldNum].offset_end = packetFields[fieldNum].offset_begin+packetFields[fieldNum].length_in_byte;	
			}
			packetFields[fieldNum].data = data.substring(packetFields[fieldNum].offset_begin, packetFields[fieldNum].offset_end);
			
		}else{
			System.out.println("-----��"+lastFieldNum+"���ݽ���ʧ�ܣ�-----");
		}
		////////////////	DEBUG��Ϣ  	   ///////////////////////////////////////////////////////////////////////////////////////
		System.out.println("��" + (fieldNum+1) + "��"+packetFields[fieldNum].data_name);
		System.out.println("begin��"+packetFields[fieldNum].offset_begin+"    end��"+packetFields[fieldNum].offset_end);
		if(packetFields[fieldNum].variable_flag == 0){
			System.out.println("����(����)��"+packetFields[fieldNum].length);
		}else {
			System.out.println("����(�䳤)��"+packetFields[fieldNum].length_in_byte);	
		}
		
		if(packetFields[fieldNum].datatyp == 0){
			System.out.println("type��string");
		}else if(packetFields[fieldNum].datatyp == 1){
			System.out.println("type��int");
		}else if (packetFields[fieldNum].datatyp == 2) {
			System.out.println("type��binary");
		}else if (packetFields[fieldNum].datatyp == 3) {
			System.out.println("type��ASCII");
		}
		
		System.out.println("���ݣ�"+packetFields[fieldNum].data);
		System.out.println();
		///////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		return;
	}
	
	public void showField() {
		for(int i=0;i<MAX_FIELD_NUM;i++){
			if(bitMapList.get(i)){
				
				System.out.println("��" + (i+1) + "��"+packetFields[i].data_name);
				System.out.println("begin��"+packetFields[i].offset_begin+"    end��"+packetFields[i].offset_end);
				if(packetFields[i].variable_flag == 0){
					System.out.println("����(����)��"+packetFields[i].length);
				}else {
					System.out.println("����(�䳤)��"+packetFields[i].length_in_byte);	
				}
				
				if(packetFields[i].datatyp == 0){
					System.out.println("type��string");
				}else if(packetFields[i].datatyp == 1){
					System.out.println("type��int");
				}else if (packetFields[i].datatyp == 2) {
					System.out.println("type��binary");
				}else if (packetFields[i].datatyp == 3) {
					System.out.println("type��ASCII");
				}
				
				System.out.println("���ݣ�"+packetFields[i].data);
				System.out.println();
			}
		}
	}

	
}
