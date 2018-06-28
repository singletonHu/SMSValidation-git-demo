package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.SendMsgUtil;

/**
 * �ֻ���֤����Ŀ��ơ����Ͷ��š����ؽ��
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
			// �޸�Ϊ�Լ���apikey. apikey���ڹ�����https://www.dingdongcloud.com)��¼���ȡ
	        String apikey = "2f37423649a64d4d4b22fd7a637c2360";

	        // �޸�ΪҪ���͵��ֻ���
	        String mobile = request.getParameter("inMobile");
	        
	        // ��ȡ���ɵ���֤��
	        String validateCode = SendMsgUtil.createRandomVcode();
	        // ���ֻ��ź���֤�����session
	        request.getSession().setAttribute("validateCode", validateCode);
	        request.getSession().setAttribute("mobile", mobile);
	        
	        /**************** ������֤����� *****************/
	        // ����Ҫ���͵�����(���ݱ�������õ�ĳ��ģ��ƥ�䡣��
	        String yzmContent = "�������ж����𾴵��û���������֤��Ϊ"+ validateCode +"������10���������롣�������������!";
	        
	        // ����֤����ŵ���ʾ��
	        out.print(SendMsgUtil.sendSingle(apikey, mobile, yzmContent));
	        
	        
	        
	        /*�����˷Ѷ���������ʹ�ò�ѯ�˻����ݲ���ʹ��*/
	        /*String info = SendMsgUtil.getUserInfo(apikey);
	        System.out.println(validateCode);
	        out.print(info);*/
		}
		else if ("login".equals(opr))
		{
			// �õ�ҳ��������ֻ��ź���֤��
			String inValidateCode = request.getParameter("inValidateCode");
			String inMobile = request.getParameter("inMobile");
			
			// �õ��洢��session�е��ֻ��ź���֤��
			String validateCode = (String) request.getSession().getAttribute("validateCode");
			String mobile = (String) request.getSession().getAttribute("mobile");
			
			// ��֤�ֻ��ź���֤���Ƿ���ȷ
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
							out.print("<script language='javascript'>alert('��֤�����');window.location.href='login.jsp';</script>");
						}
					}
					else
					{
						PrintWriter out = response.getWriter();
						out.print("<script language='javascript'>alert('���ֻ�����δע�ᣡ');window.location.href='login.jsp';</script>");
					}
				}
				else
				{
					PrintWriter out = response.getWriter();
					out.print("<script language='javascript'>alert('��֤�벻��Ϊ�գ�');window.location.href='login.jsp';</script>");
				}
			}
			else
			{
				PrintWriter out = response.getWriter();
				out.print("<script language='javascript'>alert('�ֻ��Ų���Ϊ�գ�');window.location.href='login.jsp';</script>");
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
