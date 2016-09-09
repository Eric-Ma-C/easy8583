
public class ByteUtil {
	public static int BCD2Int(String byteString,boolean isASCII){//  如0x12 12  0x0123 123
		if(byteString.length() == 2){
			char c1 = byteString.charAt(0);
			char c2 = byteString.charAt(1);
			
			int ret = (c1-'0')*10+c2-'0';
			return (ret%2==0 || isASCII) ? ret:ret+1;//若域长度为奇数压缩时需补零，多一位
			
		}else if(byteString.length() == 4){
			char c1 = byteString.charAt(0);
			char c2 = byteString.charAt(1);
			char c3 = byteString.charAt(2);
			char c4 = byteString.charAt(3);
			
			int ret = (c1-'0')*1000+(c2-'0')*100+(c3-'0')*10+c4-'0';
			
			return (ret%2==0 || isASCII) ? ret:ret+1;//若域长度为奇数压缩时需补零，多一位
		}else{
			System.out.println("-----BCD2Int error,域长度解析错误！-----");
			return -1;
		}
		
	}
	
	public static int HexByte2Int(byte data){//  如0x0A 10    0x03 3
		if(data <= '9' && data >= '0'){
			return data-'0';
		}else if (data <= 'Z' && data >= 'A') {
			return data-'A'+10;
		}else if (data <= 'z' && data >= 'a') {
			return data-'a'+10;
		}else {
			System.out.println("-----HexByte2Int error,参数非十六进制！-----");
			return -1;
		}
	}
	
}
