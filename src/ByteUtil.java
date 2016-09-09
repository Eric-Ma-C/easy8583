
public class ByteUtil {
	public static int BCD2Int(String byteString,boolean isASCII){//  ��0x12 12  0x0123 123
		if(byteString.length() == 2){
			char c1 = byteString.charAt(0);
			char c2 = byteString.charAt(1);
			
			int ret = (c1-'0')*10+c2-'0';
			return (ret%2==0 || isASCII) ? ret:ret+1;//���򳤶�Ϊ����ѹ��ʱ�貹�㣬��һλ
			
		}else if(byteString.length() == 4){
			char c1 = byteString.charAt(0);
			char c2 = byteString.charAt(1);
			char c3 = byteString.charAt(2);
			char c4 = byteString.charAt(3);
			
			int ret = (c1-'0')*1000+(c2-'0')*100+(c3-'0')*10+c4-'0';
			
			return (ret%2==0 || isASCII) ? ret:ret+1;//���򳤶�Ϊ����ѹ��ʱ�貹�㣬��һλ
		}else{
			System.out.println("-----BCD2Int error,�򳤶Ƚ�������-----");
			return -1;
		}
		
	}
	
	public static int HexByte2Int(byte data){//  ��0x0A 10    0x03 3
		if(data <= '9' && data >= '0'){
			return data-'0';
		}else if (data <= 'Z' && data >= 'A') {
			return data-'A'+10;
		}else if (data <= 'z' && data >= 'a') {
			return data-'a'+10;
		}else {
			System.out.println("-----HexByte2Int error,������ʮ�����ƣ�-----");
			return -1;
		}
	}
	
}
