package test;

import org.junit.Test;

import util.SendMsgUtil;

/**
 * ��Ԫ������
 * @author Singleton
 *
 */
public class SengMsgTest 
{
	@Test
	public void sendMsg()
	{
		// �޸�Ϊ�Լ���apikey. apikey���ڹ�����https://www.dingdongcloud.com)��¼���ȡ
        String apikey = "2f37423649a64d4d4b22fd7a637c2360";

        // �޸�ΪҪ���͵��ֻ���
        String mobile = "15273592968";

        /**************** ���˻���Ϣ����ʾ�� *****************/
         System.out.println(SendMsgUtil.getUserInfo(apikey));

        /**************** ������֤����� *****************/
        // ����Ҫ���͵�����(���ݱ�������õ�ĳ��ģ��ƥ�䡣��
        String yzmContent = "�������ж����𾴵��û���������֤��Ϊ"+ SendMsgUtil.createRandomVcode() +"������10���������롣�������������!";

        // ����֤����ŵ���ʾ��
        System.out.println(SendMsgUtil.sendSingle(apikey, mobile, yzmContent));
	}

}
