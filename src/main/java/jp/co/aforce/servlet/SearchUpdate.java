package jp.co.aforce.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.aforce.bean.Info;
import jp.co.aforce.dao.InfoDAO;
import jp.co.aforce.tool.Page;



@WebServlet(urlPatterns = { "/src/SearchUpdate" })
public class SearchUpdate extends HttpServlet {

	public void doPost(

			HttpServletRequest request, HttpServletResponse response

	) throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		Page.header(out);

		try {

			String memberNo = request.getParameter("memberNo");

			InfoDAO dao = new InfoDAO();

			List<Info> List = dao.search(memberNo);
//
			for (Info m : List) {

				out.println(memberNo);
				out.println(m.getName());
				out.println(m.getAge());
				out.println(m.getBirth_year());
				out.println(m.getBirth_month());
				out.println(m.getBirth_day());
				
			}	
				
				request.setAttribute("Info", List);
				request.getRequestDispatcher("../jsp/update.jsp").forward(request, response);
			
			

		} catch (Exception e) {

			e.printStackTrace(out);
			out.println("該当する会員情報が見つかりません。");
		}
		Page.footer(out);

	}

}