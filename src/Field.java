
/**
 * @Auth eric_ma
 * @time 2016��8��8�� ����2:30:57
 * @˵��  8583��������
 */
public class Field {
	public int offset_begin; /*  ���ڵ�ǰ����(����Ϊstring����)��ƫ����*/
	public String data_name; /*����*/
	public int length; /*�����򳤶�,���ֽ�Ϊ��λ*/
	public int length_in_byte;/*ʵ�ʳ��ȣ�����Ǳ䳤��*/
	public int variable_flag; /*�Ƿ�䳤��־0���� 2��2λ�䳤, 3��3λ�䳤*/
	public int datatyp; /*����������0 -- string, 1 -- int, 2 -- binary  3--ASCII  һ��ascii��ռ����ƫ���� ����*2*/
	public String data; /*��ž���ֵ*/
	public int offset_end; /*���β�ڵ�ǰ����(����Ϊstring����)��ƫ����*/
	
	Field(int offset_begin,String data_name,int length,int length_in_byte,int variable_flag,int datatyp,String data,int offset_end){
		this.offset_begin = offset_begin; /*���ڵ�ǰ����(����Ϊstring����)��ƫ����*/
		this.data_name = data_name; /*����*/
		this.length = length; /*�����򳤶�,���ֽ�Ϊ��λ*/
		this.length_in_byte = length_in_byte;/*ʵ�ʳ��ȣ�����Ǳ䳤��*/
		this.variable_flag = variable_flag; /*�Ƿ�䳤��־0���� 2��2λ�䳤, 3��3λ�䳤*/
		this.datatyp = datatyp; /*0 -- string, 1 -- int, 2 -- binary*/
		this.data = data; /*��ž���ֵ*/
		this.offset_end = offset_end; /*���β�ڵ�ǰ����(����Ϊstring����)��ƫ����*/
	}
	
}
