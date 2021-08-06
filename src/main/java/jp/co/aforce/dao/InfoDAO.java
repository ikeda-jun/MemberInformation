package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jp.co.aforce.bean.Info;

public class InfoDAO extends DAO {

	public List<Info> search(String memberNo) throws Exception {

		List<Info> list = new ArrayList<>();

		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(

				"select * from members where member_no = ?");

		
		st.setString(1, memberNo);

		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			
			Info member = new Info();
			member.setMember_no(rs.getString("member_no"));
			member.setName(rs.getString("name"));
			member.setAge(rs.getInt("age"));
			member.setBirth_year(rs.getInt("birth_year"));
			member.setBirth_month(rs.getInt("birth_month"));
			member.setBirth_day(rs.getInt("birth_day"));
			
			//とってきた値を上記のように
//			st.setString(1, member.getName());
//			st.setInt(2, member.getAge());
//			st.setInt(3, member.getBirth_year());
//			st.setInt(4, member.getBirth_month());
//			st.setInt(5, member.getBirth_day());
			list.add(member);
		}

		st.close();
		con.close();

		return list;

	}
	
	
	
	
	public int insert(Info member) throws Exception {

		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(

				"insert into members values(?, ?, ?, ?, ?, ?)");
		
		st.setString(1, member.getMember_no());
		st.setString(2, member.getName());
		st.setInt(3, member.getAge());
		st.setInt(4, member.getBirth_year());
		st.setInt(5, member.getBirth_month());
		st.setInt(6, member.getBirth_day());
		
		int line = st.executeUpdate();

		st.close();
		con.close();

		return line;

	}

	public int delete(Info member) throws Exception{
	
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
	
				"delete from members where member_no=? and name=? and age=? and birth_year=? and birth_month=? and birth_day=?");
		
		st.setString(1, member.getMember_no());
		st.setString(2, member.getName());
		st.setInt(3, member.getAge());
		st.setInt(4, member.getBirth_year());
		st.setInt(5, member.getBirth_month());
		st.setInt(6, member.getBirth_day());
		
		int line = st.executeUpdate();
		
		st.close();
		con.close();
	
		return line;
	
}
		
	public int update(Info member) throws Exception{
		
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(

				"update members set name=? , age=? , birth_year=? , birth_month=? , birth_day=? where member_no=? ");
		
		st.setString(1, member.getName());
		st.setInt(2, member.getAge());
		st.setInt(3, member.getBirth_year());
		st.setInt(4, member.getBirth_month());
		st.setInt(5, member.getBirth_day());
		st.setString(6, member.getMember_no());

		
		int line = st.executeUpdate();
		
		st.close();
		con.close();

		return line;
		
	}
	
	
}
