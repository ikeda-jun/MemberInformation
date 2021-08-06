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


@WebServlet(urlPatterns = { "/src/delete" })
public class Delete extends HttpServlet {

	public void doPost(

			HttpServletRequest request, HttpServletResponse response

	) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
	

		Page.header(out);
		

		try {
			String member_no = request.getParameter("memberNo");
			String name = request.getParameter("name");
			if (name.isEmpty()) {
				out.println("入力されていない項目があります。");
				return;
			}
			if (request.getParameter("age").isEmpty()) {
				out.println("入力されていない項目があります。");
				return;
			}
			int age = Integer.parseInt(request.getParameter("age"));
			if (request.getParameter("birthYear").isEmpty()) {
				out.println("入力されていない項目があります。");
				return;
			}
			int birth_year = Integer.parseInt(request.getParameter("birthYear"));
			if (request.getParameter("birthMonth").isEmpty()) {
				out.println("入力されていない項目があります。");
				return;
			}
			int birth_month = Integer.parseInt(request.getParameter("birthMonth"));
			if (request.getParameter("birthDay").isEmpty()) {
				out.println("入力されていない項目があります。");
				return;
			}
			int birth_day = Integer.parseInt(request.getParameter("birthDay"));
			
			
			Info m=new Info();
			
			m.setMember_no(member_no);
			m.setName(name);
			m.setAge(age);
			m.setBirth_year(birth_year);
			m.setBirth_month(birth_month);
			m.setBirth_day(birth_day);
			
			
			InfoDAO dao = new InfoDAO();		
			dao.delete(m);
			
			out.println("削除に成功しました。");


		} catch (Exception e) {

			//データのエラー調整用
			//e.printStackTrace(out);
			out.println("削除に失敗しました。");

		}
		
		Page.footer(out);
		
	}

}
