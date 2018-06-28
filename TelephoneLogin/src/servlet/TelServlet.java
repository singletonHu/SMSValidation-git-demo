package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.SendMsgUtil;

/**
 * 手机验证码核心控制、发送短信、返回结果
 * @author Singleton
 *
 */
public class TelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TelServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String opr = request.getParameter("opr");
		if ("code".equals(opr))
		{
			PrintWriter out = response.getWriter();
			// 修改为自己的apikey. apikey可在官网（https://www.dingdongcloud.com)登录后获取
	        String apikey = "2f37423649a64d4d4b22fd7a637c2360";

	        // 修改为要发送的手机号
	        String mobile = request.getParameter("inMobile");
	        
	        // 获取生成的验证码
	        String validateCode = SendMsgUtil.createRandomVcode();
	        // 将手机号和验证码存入session
	        request.getSession().setAttribute("validateCode", validateCode);
	        request.getSession().setAttribute("mobile", mobile);
	        
	        /**************** 发送验证码短信 *****************/
	        // 设置要发送的内容(内容必须和设置的某个模板匹配。）
	        String yzmContent = "【秘密行动】尊敬的用户，您的验证码为"+ validateCode +"，请在10分钟内输入。请勿告诉其他人!";
	        
	        // 发验证码短信调用示例
	        out.print(SendMsgUtil.sendSingle(apikey, mobile, yzmContent));
	        
	        
	        
	        /*避免浪费短信条数，使用查询账户数据测试使用*/
	        /*String info = SendMsgUtil.getUserInfo(apikey);
	        System.out.println(validateCode);
	        out.print(info);*/
		}
		else if ("login".equals(opr))
		{
			// 拿到页面输入的手机号和验证码
			String inValidateCode = request.getParameter("inValidateCode");
			String inMobile = request.getParameter("inMobile");
			
			// 拿到存储在session中的手机号和验证码
			String validateCode = (String) request.getSession().getAttribute("validateCode");
			String mobile = (String) request.getSession().getAttribute("mobile");
			
			// 验证手机号和验证码是否正确
			if (!inMobile.isEmpty())
			{
				if (!inValidateCode.isEmpty())
				{
					if(inMobile.equals(mobile))
					{
						if (inValidateCode.equals(validateCode))
						{
							response.sendRedirect("success.jsp");
						}
						else
						{
							PrintWriter out = response.getWriter();
							out.print("<script language='javascript'>alert('验证码错误！');window.location.href='login.jsp';</script>");
						}
					}
					else
					{
						PrintWriter out = response.getWriter();
						out.print("<script language='javascript'>alert('该手机号尚未注册！');window.location.href='login.jsp';</script>");
					}
				}
				else
				{
					PrintWriter out = response.getWriter();
					out.print("<script language='javascript'>alert('验证码不能为空！');window.location.href='login.jsp';</script>");
				}
			}
			else
			{
				PrintWriter out = response.getWriter();
				out.print("<script language='javascript'>alert('手机号不能为空！');window.location.href='login.jsp';</script>");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
