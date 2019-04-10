package Dao;
import java.util.List;

import Dto.TestBoardDto;

public interface ITestBoardDao {
	public  List<TestBoardDto> getAllTestBoard();
	//getAllTestBoard()-return list
	public TestBoardDto getTestBoard(int seq);
	//getTestBoard()-return TestBoardDto
	public boolean addTestBoard(TestBoardDto dto);
	//addTestBoard()-return boolean
	public boolean updateTestBoard(TestBoardDto dto);
	//updateTestBoard()-return boolean
	public boolean delTestBoard(int seq);
	//delTestBoard()-return boolean
	public boolean muldelTestBoard(String[] chks);
	//muldelTestBoard()-return boolean

}
