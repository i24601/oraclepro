package com.javaex.phone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhoneDao {

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "phonedb";
	private String pw = "phonedb";

	private PreparedStatement pstmt = null;
	private Connection conn = null;

	private void connect() {

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	}

	public void close() {
		try {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	public void phoneInsert(PhoneVo vo) {
		connect();
		String query = "";
		query += " insert into ";
		query += " person ";
		query += " values ";
		query += " ( ";
		query += " seq_person_id.nextval, ";
		query += " ?, ";
		query += " ?, ";
		query += " ? ";
		query += " ) ";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getHp());
			pstmt.setString(3, vo.getCompany());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();

	}

	public void deletePhone(int i) {
		connect();
		String query = "";
		query += " delete from person ";
		query += " where person_id = ? ";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, i);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
	}

	public void updatePhone(PhoneVo vo) {
		connect();
		String query = "";
		query += " update person ";
		query += " set name = ?, ";
		query += " hp = ?, ";
		query += " company = ? ";
		query += " where person_id = ? ";

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getHp());
			pstmt.setString(3, vo.getCompany());
			pstmt.setInt(4, vo.getPerson_id());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		close();

	}

	public List<PhoneVo> search(String str) {
		connect();
		List<PhoneVo> pList = new ArrayList<PhoneVo>();
		ResultSet rs = null;
		try {

			String query = "";
			query += " select ";
			query += " person_id, ";
			query += " name, ";
			query += " hp, ";
			query += " company ";
			query += " from person ";

			if (str != "") {
				query += " where name like ? or ";
				query += " hp like ? or";
				query += " company like ? ";

				pstmt = conn.prepareStatement(query);

				pstmt.setString(1, "%" + str + "%");
				pstmt.setString(2, "%" + str + "%");
				pstmt.setString(3, "%" + str + "%");
			}

			else if (str == "") {
				pstmt = conn.prepareStatement(query);
			}

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int person_id = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");
				PhoneVo rs_vo = new PhoneVo(person_id, name, hp, company);
				pList.add(rs_vo);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();

		return pList;
	}
}
