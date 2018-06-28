package util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.lang3.StringUtils;

/**
 * 基于http client 4.2.3叮咚云相关接口
 *
 * @author liangyang
 * @date 
 * @version 1.0
 */
public class SendMsgUtil 
{
		// 获取账户信息url 
	 	private static String URL_GET_USER_INFO = "https://api.dingdongcloud.com/v2/user/get";

	    // 单条短信发送url
	    private static String URL_SEND_SINGLE = "https://api.dingdongcloud.com/v2/sms/single_send";

	    // 编码格式。发送编码格式统一用UTF-8
	    private static String ENCODING = "UTF-8";

	    /**
	     * 查询账户信息接口
	     *
	     * @param apikey
	     * @return
	     */
	    public static String getUserInfo(String apikey) {

	        NameValuePair[] data = { new NameValuePair("apikey", apikey) };
	        return HttpRequestUtil.postRequest(URL_GET_USER_INFO, data);
	    }


	    /**
	     * 发送单条短信
	     *
	     * @param apikey
	     *            apikey
	     * @param mobile
	     *            手机号码(唯一，不许多个)
	     * @param content
	     *            短信发送内容（必须经过utf-8格式编码)
	     * @return json格式字符串
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
	     * 生成6位验证码
	     * @return
	     */
	    public static String createRandomVcode(){  
	        //验证码  
	        String vcode = "";  
	        for (int i = 0; i < 6; i++) {  
	            vcode = vcode + (int)(Math.random() * 9); 
	        }  
	        return vcode;  
	    } 
}
