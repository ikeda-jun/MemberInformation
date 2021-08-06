package jp.co.aforce.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.aforce.bean.Info;
import jp.co.aforce.dao.InfoDAO;
import jp.co.aforce.tool.Page;

@WebServlet(urlPatterns = { "/src/regist" })
public class Regist extends HttpServlet {

	public void doPost(

			HttpServletRequest request, HttpServletResponse response

	) throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		Page.header(out);

		try {
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddhhmmss");
			String strDate = dateFormat.format(date);

			String name = request.getParameter("name");
			//入力チェッカー
			if (name.isEmpty() || request.getParameter("age").isEmpty() || request.getParameter("birthYear").isEmpty()
					|| request.getParameter("birthMonth").isEmpty() || request.getParameter("birthDay").isEmpty()) {
				out.println("入力されていない項目があります。");
				return;
			}

			int age = Integer.parseInt(request.getParameter("age"));

			int birth_year = Integer.parseInt(request.getParameter("birthYear"));

			int birth_month = Integer.parseInt(request.getParameter("birthMonth"));

			int birth_day = Integer.parseInt(request.getParameter("birthDay"));

			Info m = new Info();
			m.setMember_no("A" + strDate);
			//データのエラー調整用
			//m.setMember_no("A210609042911");  
			m.setName(name);
			m.setAge(age);
			m.setBirth_year(birth_year);
			m.setBirth_month(birth_month);
			m.setBirth_day(birth_day);

			InfoDAO dao = new InfoDAO();
			int line = dao.insert(m);

			if (line > 0) {

				out.println("登録に成功しました。");

			}

		} catch (Exception e) {

			//データのエラー調整用
			//e.printStackTrace(out);
			out.println("登録に失敗しました。");

		}
		Page.footer(out);

	}

}
