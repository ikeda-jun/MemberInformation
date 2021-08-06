package jp.co.aforce.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.aforce.bean.Info;
import jp.co.aforce.dao.InfoDAO;
import jp.co.aforce.tool.Page;


@WebServlet(urlPatterns = { "/src/update" })
public class Update extends HttpServlet {

	public void doPost(

			HttpServletRequest request, HttpServletResponse response

	) throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		Page.header(out);
		

		try {
			
			String memberNo = request.getParameter("memberNo");
			String name = request.getParameter("name");
			//入力チェッカ
			if (name.isEmpty() || request.getParameter("age").isEmpty() || request.getParameter("birthYear").isEmpty() || request.getParameter("birthMonth").isEmpty() || request.getParameter("birthDay").isEmpty()) {
				out.println("入力されていない項目があります。");
				return;
			}
			
			int age = Integer.parseInt(request.getParameter("age"));
			
			int birth_year = Integer.parseInt(request.getParameter("birthYear"));
			
			int birth_month = Integer.parseInt(request.getParameter("birthMonth"));
			
			int birth_day = Integer.parseInt(request.getParameter("birthDay"));
			
			
			Info m=new Info();
			
			m.setMember_no(memberNo);
			m.setName(name);
			m.setAge(age);
			m.setBirth_year(birth_year);
			m.setBirth_month(birth_month);
			m.setBirth_day(birth_day);
			
			InfoDAO dao = new InfoDAO();		
			dao.update(m);
			
			out.println("更新に成功しました。");

			

		} catch (Exception e) {

			//データのエラー調整用
			//e.printStackTrace(out);
			out.println("更新に失敗しました。");
			
		}
		
		Page.footer(out);
		
	}

}