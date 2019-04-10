package Dto;

import java.util.Date;

public class TestBoardDto {

	
	int seq;
	String writer;
	String title;
	String conts;
	Date regDate;
	public TestBoardDto() {
		
	}
	
	public TestBoardDto(int seq, String writer, String title, String conts) {
		super();
		this.seq = seq;
		this.writer = writer;
		this.title = title;
		this.conts = conts;
	}

	public TestBoardDto(String writer, String title, String conts) {
		super();
		this.writer = writer;
		this.title = title;
		this.conts = conts;
	}
	public int getSeq() {
		return seq;
	}
	public TestBoardDto setSeq(int seq) {
		this.seq = seq;
		return this;
	}
	public String getWriter() {
		return writer;
	}
	public TestBoardDto setWriter(String writer) {
		this.writer = writer;
		return this;
	}
	public String getTitle() {
		return title;
	}
	public TestBoardDto setTitle(String title) {
		this.title = title;
		return this;
	}
	
	public String getConts() {
		return conts;
	}
	public TestBoardDto setConts(String conts) {
		this.conts = conts;
		return this;
	}
	public Date getRegDate() {
		return regDate;
	}
	public TestBoardDto setRegDate(Date regDate) {
		this.regDate = regDate;
		return this;
	}
	
}
