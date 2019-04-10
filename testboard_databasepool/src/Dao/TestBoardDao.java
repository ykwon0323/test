package Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import Dto.TestBoardDto;
import Properties.DBProperties;

public class TestBoardDao extends DBProperties implements ITestBoardDao{

	@Override
	public List<TestBoardDto> getAllTestBoard() {
		dbLoading();
		dbConnecting();
		List<TestBoardDto> list = new ArrayList<TestBoardDto>();
		try {
			sql =" SELECT SEQ, WRITER, TITLE, CONTS, REGDATE " + 
					" FROM HK.TESTBOARD ";
			psmt=conn.prepareStatement(sql);
			rs=psmt.executeQuery();

			while(rs.next()) {
				TestBoardDto dto = new TestBoardDto();
				dto.setSeq(rs.getInt("seq"));
				dto.setWriter(rs.getString("writer"));
				dto.setTitle(rs.getString("title"));
				dto.setConts(rs.getString("conts"));
				dto.setRegDate(rs.getDate("regdate"));
				list.add(dto);
				System.out.println(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("getAllTestBoard() 실패");
		}finally {
			dbClose();
		}
		return list;
	}

	@Override
	public TestBoardDto getTestBoard(int seq) {
		dbLoading();
		dbConnecting();
		TestBoardDto dto = new TestBoardDto();
		try {
			sql = " SELECT SEQ , WRITER , TITLE , CONTS , REGDATE "+
					" FROM HK.TESTBOARD WHERE SEQ = ? ";
			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			rs=psmt.executeQuery();
			while(rs.next()) {
				dto.setSeq(rs.getInt("seq"));
				dto.setWriter(rs.getString("writer"));
				dto.setTitle(rs.getString("title"));
				dto.setConts(rs.getString("conts"));
				dto.setRegDate(rs.getDate("regdate"));
				System.out.println(dto);
			}
		} catch (SQLException e) {

			e.printStackTrace();
			System.out.println("getTestBoard() 실패");
		}finally {
			dbClose();
		}
		return dto;
	}

	@Override
	public boolean addTestBoard(TestBoardDto dto) {
		dbLoading();
		dbConnecting();
		int count = 0;
		sql = "INSERT INTO HK.TESTBOARD (SEQ,WRITER,TITLE,CONTS,REGDATE) "
				+" VALUES(TESTBOARD_SEQ.NEXTVAL , ? , ? , ? , SYSDATE)";
				
		try {
				psmt=conn.prepareStatement(sql);
		 		psmt.setString(1, dto.getWriter());
		 		psmt.setString(2, dto.getTitle());
		 		psmt.setString(3, dto.getConts());
		 		count=psmt.executeUpdate();
		}catch (SQLException e){
			e.printStackTrace();
			System.out.println("addTestBoard() 실패");
			
		}finally {
			dbClose();
		}

		return count>0?true:false;
	}

	@Override
	public boolean updateTestBoard(TestBoardDto dto) {
		dbLoading();
		dbConnecting();
		int count = 0;
		sql = "UPDATE HK.TESTBOARD "
			+"	SET WRITER=?, TITLE=?, CONTS=?, REGDATE=SYSDATE "
			+"	WHERE SEQ= ? ";
		try {
			
			psmt.setString(1, dto.getWriter());
			System.out.println("1");
	 		psmt.setString(2, dto.getTitle());
	 		System.out.println("2");
	 		psmt.setString(3, dto.getConts());
	 		System.out.println("3");
	 		psmt.setInt(4, dto.getSeq());
	 		System.out.println("4");
	 		count=psmt.executeUpdate();
	 		System.out.println("5");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("updateTestBoard() 실패");
		}finally {
			dbClose();
			
		}
		
		
		
		return count>0?true:false;
	}

	@Override
	public boolean delTestBoard(int seq) {
		dbLoading();
		dbConnecting();
		int count = 0;
		
		String sql = "DELETE FROM HK.TESTBOARD " + 
				" WHERE SEQ= ? " ;
		try {
			psmt.setInt(1, seq);
			count=psmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("deleteTestBoard() 실패");
			e.printStackTrace();
			
		}finally {
			dbClose();
			
		}
		
		
		return count>0?true:false;
	}

	@Override
	public boolean muldelTestBoard(String[] chks) {
		boolean isS=true;
		int [] count=null;
		dbLoading();
		dbConnecting();
		
		
		try {
			
			conn.setAutoCommit(false);
			String sql = "DELETE FROM HK.TESTBOARD " + 
					" WHERE SEQ= ? " ;
			psmt=conn.prepareStatement(sql);
			for (int i = 0; i < chks.length; i++) {
				psmt.setString(1, chks[i]);
				psmt.addBatch();//batch개념:동일한 작업을 여러번 실행할때 준비를 시켰다가 한번에 쭉 실행시킴
			}
			count=psmt.executeBatch();
			conn.commit();

		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			e.printStackTrace();
			System.out.println("muldelTestBoard실패");
		}finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
			
				e.printStackTrace();
			}dbClose();
			for (int i = 0; i < count.length; i++) {
				if(count[i]!=-2) {//-2가 아니면 쿼리실패임
					isS=false;
					break;
				}
		
			}
			}
		
	
			return isS;
}
}
