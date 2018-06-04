package com.movie.ex.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.movie.ex.DTO.BoardDTO;

public class BoardDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String id = "movieUser";
	String pw = "7465";
	public BoardDAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/******************************************************************************************
	 * 게시판 삽입
	 ******************************************************************************************/
	public int insert(String title, String content) {
		conn = getConnection();
		
		String sql = "insert into mboard (writer_no,title,content) values(?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 4141);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconn(conn,pstmt,rs);
		}
		return 0;
	}
	/******************************************************************************************
	 * 게시판 수정
	 ******************************************************************************************/
	public int update(BoardDTO dto) {
		return 0;
	}
	/******************************************************************************************
	 * 게시판 삭제
	 ******************************************************************************************/
	public int delete(int no) {
		return 0;
	}
	/******************************************************************************************
	 * 게시판 모든 글 가져오기
	 ******************************************************************************************/

	/******************************************************************************************
	 * 게시판 상위 5개 글 가져오기
	 ******************************************************************************************/
	public ArrayList<BoardDTO> getUpperTen(){
		ArrayList<BoardDTO> dtos = new ArrayList<>();
		conn = getConnection();
		String sql = "select * from mboard order by no desc limit 5";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setNo(rs.getInt("no"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setWriter(rs.getString("writer_no"));
				dto.setDate(rs.getTimestamp("wdate"));
				dtos.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			disconn(conn,pstmt,rs);
		}
		return dtos;
	}
	/******************************************************************************************
	 * 게시판 한개 내용 가져오기
	 ******************************************************************************************/
	
	/******************************************************************************************
	 * Connection 객체 생성
	 ******************************************************************************************/
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/movieDB?serverTimezone=UTC", id, pw);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/******************************************************************************************
	 * stream 닫기
	 ******************************************************************************************/
	public void disconn(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		
			try {
				if(pstmt!=null)	pstmt.close();
				if(conn!=null) conn.close();
				if(rs!=null) rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
