package test;

import org.junit.Test;

import util.SendMsgUtil;

/**
 * 单元测试用
 * @author Singleton
 *
 */
public class SengMsgTest 
{
	@Test
	public void sendMsg()
	{
		// 修改为自己的apikey. apikey可在官网（https://www.dingdongcloud.com)登录后获取
        String apikey = "2f37423649a64d4d4b22fd7a637c2360";

        // 修改为要发送的手机号
        String mobile = "15273592968";

        /**************** 查账户信息调用示例 *****************/
         System.out.println(SendMsgUtil.getUserInfo(apikey));

        /**************** 发送验证码短信 *****************/
        // 设置要发送的内容(内容必须和设置的某个模板匹配。）
        String yzmContent = "【秘密行动】尊敬的用户，您的验证码为"+ SendMsgUtil.createRandomVcode() +"，请在10分钟内输入。请勿告诉其他人!";

        // 发验证码短信调用示例
        System.out.println(SendMsgUtil.sendSingle(apikey, mobile, yzmContent));
	}

}
