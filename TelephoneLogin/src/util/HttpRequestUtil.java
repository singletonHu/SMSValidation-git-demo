package util;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 * HttpClient����һ���ǳ���Ҫ���࣬����ģ��http��httpsЭ��
 * ͨ��������ͬ�ķ���ʵ������ģ�ⲻͬ��ʽ��������post��get��delete��option��
 * ���ڷ��Ͷ��ŵĲ�������ʱ֪���������ַ�ʽ������post��get�ҷֱ�ʹ���˲�ͬ�Ĳ������ݷ�ʽ
 * @author Singleton
 */
public class HttpRequestUtil 
{
	/**  
     * HttpClient ģ��POST����  
     * @param url  
     * @param params  
     * @return  
     */  
    public static String postRequest(String url, NameValuePair[] params) 
    {  
        //����HttpClient��ʵ��  
        HttpClient httpClient = new HttpClient();  
  
        //����POST������ʵ��  
        PostMethod postMethod = new PostMethod(url);  
  
  
        /* �����������Ϣ���в��ٷ�������Ҫ��ϸ�˽⣬���ڶ�������ͷ���˽ⲻ�࣬������Ҳ������ͷע����
         * ����ͷ�ǿ��Խ������õģ����������������ӳ�ʱ�ȣ����԰�������
         * /
       
       
        //��������ͷ��Ϣ  
        //postMethod.setRequestHeader("Connection", "close");   
  
  
        //ʹ��Map����ʱ��Ӳ���  
        /*for (Map.Entry<String, String> entry : params.entrySet()) {  
            postMethod.addParameter(entry.getKey(), entry.getValue());  
        }  */
        
        // ��Ҫע�����NameValuePair�����������������ƺ���post�����ã�get����û��setRequestBody�����ƺ�������ʹ�����ַ�ʽ
        // ʹ��NameValuePair�������鴫��ʱ���ò�����ʽ
        postMethod.setRequestBody(params);
        
        // �������ӳ�ʱʱ��
        httpClient.getParams().setConnectionManagerTimeout(10000);
  
        //ʹ��ϵͳ�ṩ��Ĭ�ϵĻָ�����,�����������Դ����õ���Ĭ�ϵ����Դ�����������  
        httpClient.getParams().setBooleanParameter("http.protocol.expect-continue", false);  
  
  
        //���մ�����  
        String result = null;  
        try 
        {  
            //ִ��Http Post����  
            httpClient.executeMethod(postMethod);  
  
            //���ش�����  
            result = postMethod.getResponseBodyAsString();  
        } catch (HttpException e) 
        {  
            // �����������쳣��������Э�鲻�Ի��߷��ص�����������  
            System.out.println("���������URL!");  
            e.printStackTrace();  
        } catch (IOException e) 
        {  
            // ���������쳣  
            System.out.println("���������쳣!");  
            e.printStackTrace();  
        } finally 
        {  
            //�ͷ�����  
            postMethod.releaseConnection();  

            
            //�ر�HttpClientʵ��  
            if (httpClient != null) 
            {  
                ((SimpleHttpConnectionManager) httpClient.getHttpConnectionManager()).shutdown();  
                httpClient = null;  
            }  
        }  
        return result;  
    }  
  
  
    /**  
     *  HttpClient ģ��GET����  
      * ����˵��  
      * @Discription:��չ˵��  
      * @param url  
      * @param params  
      * @return String  
     */  
    public static String getRequest(String url, Map<String, String> params) 
    {  
        //����HttpClientʵ��  
        HttpClient client = new HttpClient();  
  
  
        //ƴ�Ӳ���  
        String paramStr = "";  
        for (String key : params.keySet()) 
        {  
            paramStr = paramStr + "&" + key + "=" + params.get(key);  
        }  
        paramStr = paramStr.substring(1);  
  
  
        //����GET������ʵ��  
        System.out.println(url + "&" + paramStr);
        GetMethod method = new GetMethod(url + "&" + paramStr);  
  
  
        try 
        {
			System.out.println(method.getURI());
		} catch (URIException e1)
        {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        //���շ��ؽ��  
        String result = null;  
        try 
        {  
            //ִ��HTTP GET��������  
            client.executeMethod(method);  
  
  
            //���ش�����  
            result = method.getResponseBodyAsString();  
        } catch (HttpException e) 
        {  
            // �����������쳣��������Э�鲻�Ի��߷��ص�����������  
            System.out.println("���������URL!");  
            e.printStackTrace();  
        } catch (IOException e) 
        {  
            // ���������쳣  
            System.out.println("���������쳣!");  
            e.printStackTrace();  
        } finally 
        {  
            //�ͷ�����  
            method.releaseConnection();  
  
  
            //�ر�HttpClientʵ��  
            if (client != null)
            {  
                ((SimpleHttpConnectionManager) client.getHttpConnectionManager()).shutdown();  
                client = null;  
            }  
        }  
        return result;  
    }  
}
