package model2.mvcboard;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileUtils;

import com.oreilly.servlet.MultipartRequest;

import common.JSFunction;
import dao.MvcBoardDao;


public class WriteController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("../14MVCBoard/Write.jsp");
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String saveDirectory = "C:/upload";
		
		// 파일 업로드
		MultipartRequest mr = FileUtil.uploadFile(req, "C:/upload", 1024*1000);
		
		if(mr == null) {
			//파일 업로드 실패
			JSFunction.alertBack(resp, "파일 저장중 오류가 발생하였습니다");
			return;
		}
		
		// 파일 업로드 외 저장
		// from값을 DTO에 저장
		MVCBoardDto dto = new MVCBoardDto();
		
		dto.setName(mr.getParameter("name"));
		dto.setTitle(mr.getParameter("title"));
		dto.setContent(mr.getParameter("content"));
		dto.setPass(mr.getParameter("pass"));
		
		// 원본 파일 저장된 파일이름
		
		// 1. 새로운 파일명 생성
		// 동일한 파일명이 업로드 되는 경우, 기존파일이 소실될 위험이 있으므로 파일명을 변경합니다.
		String fileName = mr.getFilesystemName("attachedFile");
		
		if(fileName != null) {
			
			// 첨부파일의 확장자
			String ext = fileName.substring(fileName.lastIndexOf("."));
			
			// H(시간) : 0~25, S : millisecond 
			String now = new SimpleDateFormat("yyyyMMDD_HmsS").format(new Date());
			String oFileName = fileName.substring(0,fileName.lastIndexOf("."));
			
			String newFileName = oFileName +"_"+ now + ext;
			System.out.println("원본파일명 : "+ fileName);
			System.out.println("신규파일명 : "+ newFileName);
			
			// 2. 파일명 변경
			File oldFile = new File(saveDirectory + File.separator + fileName);
			File newFile = new File(saveDirectory + File.separator + newFileName);
			oldFile.renameTo(newFile);
			
			dto.setOfile(fileName);	// 원본 파일명
			dto.setSfile(newFileName);	// 저장된 파일명
		}
		
		MvcBoardDao dao = new MvcBoardDao();
		int res = dao.isert(dto);
		
		if(res>0) {
			JSFunction.alertLocation("작성되었습니다.", "../mvcboard/list.do", resp);
		}else {
			JSFunction.alertBack(resp, "작성중 오류가 발생하였습니다. 관리자에게 문의해주세요");
		}
	}
	
	public WriteController() {
		// TODO Auto-generated constructor stub
	}
	
	

}
