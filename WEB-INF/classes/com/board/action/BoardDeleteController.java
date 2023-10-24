package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDTO;
import com.board.model.SBoardDAO;
import com.board.model.SBoardDAOImpl;

/**
 * Servlet implementation class BoardDeleteController
 */
@WebServlet("/board/delete")
public class BoardDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int num = Integer.parseInt(request.getParameter("num"));
		SBoardDAO sdao = SBoardDAOImpl.getInstance();
		int flag = sdao.boardDelete(num);
		/* 1) controller 창 자체에서 alert 띄우는 방법 -> 비동기 함수 안썼을 경우 이렇게 처리 -> 전체 페이지가 새로고침 됨.. */
//		if(flag == 0) { // 삭제오류 (삭제 안 됨) 
//			response.setContentType("text/html;charset=utf-8");
//			PrintWriter out = response.getWriter();
//			out.println("<script>");
//			out.println("alert('삭제할 수 없습니다.')");
//			out.println("history.back()"); // 이전 페이지로 돌아가라
//			out.println("</script>");	
//		} else {
//			response.sendRedirect("boardlist");
//		}
		
		/* 2) 비동기함수($.ajax 또는 $.getJSON) 사용 -> 호출한 곳에 값을 다시 가져감 -> flag 값(0 또는 1) 가져가서 처리 -> boardDetail.jsp에서 비동기함수로 넘어왔다면 사용 가능 */
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(flag);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
