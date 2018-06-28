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
 * HttpClient类是一个非常重要的类，可以模拟http和https协议
 * 通过构建不同的方法实例可以模拟不同方式的请求，如post、get、delete、option等
 * 对于发送短信的参数我暂时知道的有两种方式，对于post和get我分别使用了不同的参数传递方式
 * @author Singleton
 */
public class HttpRequestUtil 
{
	/**  
     * HttpClient 模拟POST请求  
     * @param url  
     * @param params  
     * @return  
     */  
    public static String postRequest(String url, NameValuePair[] params) 
    {  
        //构造HttpClient的实例  
        HttpClient httpClient = new HttpClient();  
  
        //创建POST方法的实例  
        PostMethod postMethod = new PostMethod(url);  
  
  
        /* 设置请求的信息还有不少方法，需要详细了解，由于对于请求头的了解不多，我这里也把请求头注释了
         * 请求头是可以进行设置的，还有其他的如连接超时等，可以按需设置
         * /
       
       
        //设置请求头信息  
        //postMethod.setRequestHeader("Connection", "close");   
  
  
        //使用Map传参时添加参数  
        /*for (Map.Entry<String, String> entry : params.entrySet()) {  
            postMethod.addParameter(entry.getKey(), entry.getValue());  
        }  */
        
        // 需要注意的是NameValuePair对象数组这样传参似乎是post才能用，get由于没有setRequestBody方法似乎并不能使用这种方式
        // 使用NameValuePair对象数组传参时设置参数方式
        postMethod.setRequestBody(params);
        
        // 设置连接超时时间
        httpClient.getParams().setConnectionManagerTimeout(10000);
  
        //使用系统提供的默认的恢复策略,设置请求重试处理，用的是默认的重试处理：请求三次  
        httpClient.getParams().setBooleanParameter("http.protocol.expect-continue", false);  
  
  
        //接收处理结果  
        String result = null;  
        try 
        {  
            //执行Http Post请求  
            httpClient.executeMethod(postMethod);  
  
            //返回处理结果  
            result = postMethod.getResponseBodyAsString();  
        } catch (HttpException e) 
        {  
            // 发生致命的异常，可能是协议不对或者返回的内容有问题  
            System.out.println("请检查输入的URL!");  
            e.printStackTrace();  
        } catch (IOException e) 
        {  
            // 发生网络异常  
            System.out.println("发生网络异常!");  
            e.printStackTrace();  
        } finally 
        {  
            //释放链接  
            postMethod.releaseConnection();  

            
            //关闭HttpClient实例  
            if (httpClient != null) 
            {  
                ((SimpleHttpConnectionManager) httpClient.getHttpConnectionManager()).shutdown();  
                httpClient = null;  
            }  
        }  
        return result;  
    }  
  
  
    /**  
     *  HttpClient 模拟GET请求  
      * 方法说明  
      * @Discription:扩展说明  
      * @param url  
      * @param params  
      * @return String  
     */  
    public static String getRequest(String url, Map<String, String> params) 
    {  
        //构造HttpClient实例  
        HttpClient client = new HttpClient();  
  
  
        //拼接参数  
        String paramStr = "";  
        for (String key : params.keySet()) 
        {  
            paramStr = paramStr + "&" + key + "=" + params.get(key);  
        }  
        paramStr = paramStr.substring(1);  
  
  
        //创建GET方法的实例  
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
        //接收返回结果  
        String result = null;  
        try 
        {  
            //执行HTTP GET方法请求  
            client.executeMethod(method);  
  
  
            //返回处理结果  
            result = method.getResponseBodyAsString();  
        } catch (HttpException e) 
        {  
            // 发生致命的异常，可能是协议不对或者返回的内容有问题  
            System.out.println("请检查输入的URL!");  
            e.printStackTrace();  
        } catch (IOException e) 
        {  
            // 发生网络异常  
            System.out.println("发生网络异常!");  
            e.printStackTrace();  
        } finally 
        {  
            //释放链接  
            method.releaseConnection();  
  
  
            //关闭HttpClient实例  
            if (client != null)
            {  
                ((SimpleHttpConnectionManager) client.getHttpConnectionManager()).shutdown();  
                client = null;  
            }  
        }  
        return result;  
    }  
}
