package util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.lang3.StringUtils;

/**
 * ����http client 4.2.3��������ؽӿ�
 *
 * @author liangyang
 * @date 
 * @version 1.0
 */
public class SendMsgUtil 
{
		// ��ȡ�˻���Ϣurl 
	 	private static String URL_GET_USER_INFO = "https://api.dingdongcloud.com/v2/user/get";

	    // �������ŷ���url
	    private static String URL_SEND_SINGLE = "https://api.dingdongcloud.com/v2/sms/single_send";

	    // �����ʽ�����ͱ����ʽͳһ��UTF-8
	    private static String ENCODING = "UTF-8";

	    /**
	     * ��ѯ�˻���Ϣ�ӿ�
	     *
	     * @param apikey
	     * @return
	     */
	    public static String getUserInfo(String apikey) {

	        NameValuePair[] data = { new NameValuePair("apikey", apikey) };
	        return HttpRequestUtil.postRequest(URL_GET_USER_INFO, data);
	    }


	    /**
	     * ���͵�������
	     *
	     * @param apikey
	     *            apikey
	     * @param mobile
	     *            �ֻ�����(Ψһ��������)
	     * @param content
	     *            ���ŷ������ݣ����뾭��utf-8��ʽ����)
	     * @return json��ʽ�ַ���
	     */
	    public static String sendSingle(String apikey, String mobile, String content) {

	        if (StringUtils.isNotBlank(content)) {
	            try {
	                content = URLEncoder.encode(content, ENCODING);
	            } catch (UnsupportedEncodingException e) {
	                e.printStackTrace();
	            }
	        }

	        NameValuePair[] data = { new NameValuePair("apikey", apikey),

	        new NameValuePair("mobile", mobile),

	        new NameValuePair("content", content) };

	        return HttpRequestUtil.postRequest(URL_SEND_SINGLE, data);
	    }
	    
	    /**
	     * ����6λ��֤��
	     * @return
	     */
	    public static String createRandomVcode(){  
	        //��֤��  
	        String vcode = "";  
	        for (int i = 0; i < 6; i++) {  
	            vcode = vcode + (int)(Math.random() * 9); 
	        }  
	        return vcode;  
	    } 
}
