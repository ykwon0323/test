package Controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.TestBoardDao;
import Dto.TestBoardDto;

/**
 * Servlet implementation class BoardControl
 */
@WebServlet("/Controller.do")
public class BoardControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardControl() {
        super();
     
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String command=request.getParameter("command");
		
		TestBoardDao  dao = new TestBoardDao();
		
		if(command.equals("list")) {
			
			
			//리스트 불러오기 ->list.jsp
			List<TestBoardDto> list = dao.getAllTestBoard();
			request.setAttribute("list", list);
			request.getRequestDispatcher("list.jsp").forward(request, response);
		
		
		}else if(command.equals("writeform")) {
			
			
			//글 추가페이지로 넘기기 ->addboard.jsp
			response.sendRedirect("addboard.jsp");
		
		}else if(command.equals("write")) {
			//글 추가하기 /list.jsp로 이동
			String writer=request.getParameter("writer");
			String title=request.getParameter("title");
			String conts=request.getParameter("conts");
			boolean isS = dao.addTestBoard(new TestBoardDto(writer,title,conts));
			if(isS) {
				response.sendRedirect("Controller.do?command=list");
			}else {
				request.setAttribute("msg", "글쓰기실패");
				dispatch("error.jsp", request, response);
			}
			
			
			
		}else if(command.equals("detail")) {
			//상세보기 ->detail.jsp
		
			String seq=request.getParameter("seq");
			TestBoardDto dto = dao.getTestBoard(Integer.parseInt(seq));
			request.setAttribute("dto", dto);
			dispatch("detail.jsp", request, response);
			
		}else if(command.equals("updateform")) {
			//수정페이지로 넘기기 ->update.jsp
		
			TestBoardDto dto = new TestBoardDto();
			dto.setSeq(Integer.parseInt(request.getParameter("seq")))
				.setWriter(request.getParameter("writer"));
			request.setAttribute("dto", dto);
			request.getRequestDispatcher("update.jsp").forward(request, response);
			
			
		}else if(command.equals("update")) {
			//수정하기 ->detail.jsp로 이동
			System.out.println(request.getParameter("seq"));
			int seq = Integer.parseInt(request.getParameter("seq"));
			String writer = request.getParameter("writer");
			String title = request.getParameter("title");
			String conts = request.getParameter("conts");
			TestBoardDto dto = new TestBoardDto(seq,writer,title,conts);
			boolean isS =dao.updateTestBoard(dto);
			if(isS) {
				request.setAttribute("dto", dto);
				dispatch("detail.jsp", request, response);
			}else {
				request.setAttribute("dto", dto);
				dispatch("update.jsp", request, response);
			}
			
			
			
		}else if(command.equals("delete")) {
			//삭제하기 ->list.jsp로 이동
			int seq=Integer.parseInt(request.getParameter("seq"));
			boolean isS=dao.delTestBoard(seq);
			if(isS) {

				jsForward("글삭제성공", "Controller.do?command=list", response);
			}else {
				response.sendRedirect("Controller.do?command=detail&seq="+seq+"");
			
			}
			
		}else if(command.equals("muldel")) {
			//체크삭제하기 ->list.jsp로 이동
			String[] chks=request.getParameterValues("chk");
			boolean isS=dao.muldelTestBoard(chks);
			
			if(isS) {
				jsForward("글을 삭제했습니다.","Controller.do?command=list",response);
			}else {
				request.setAttribute("msg", "글 여러개 삭제 실패");
				dispatch("error.jsp", request, response);
			}
		}
		
		
	}
	public void dispatch(String url,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch=request.getRequestDispatcher(url);
		dispatch.forward(request,response);
	}
	
	
	//자바스크립트를 실행해서 이동하는 메서드(PrintWriter 객체: java에서 브라우저로 text를 실행시키는 (출력)기능)
	public void jsForward(String msg,String url,HttpServletResponse response) throws IOException {
		String str=
				"<script type='text/javascript'>"+
				"alert('"+msg+"');"+
				"location.href='"+url+"';"+
				"</script>";
		PrintWriter pw=response.getWriter();
		pw.print(str);
	}
	

	

}


























