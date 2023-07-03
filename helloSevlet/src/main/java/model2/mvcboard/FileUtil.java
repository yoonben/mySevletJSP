package model2.mvcboard;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import com.oreilly.servlet.MultipartRequest;

import common.JSFunction;

public class FileUtil {

	public static void download(HttpServletRequest request, HttpServletResponse response
								, String directory, String originalFilename, String saveFileName) {
		// 업로드파일이 저장된 경로
		// String saveDirectory = "c:/upload";
		// 원본 파일이름
		// String originalFilename = request.getParameter("oName");
		// 저장된 파일이름
		// String saveFileName = request.getParameter("sName");
		
		System.out.print("ofileName : " + originalFilename + "<br>");
		System.out.print("sfileName : " + saveFileName+ "<br>");
		
		try{
			File file = new File(directory, saveFileName);
			System.out.print("file : " + file);
			
			// 파일 입력 스트림 생성
			InputStream inStream = new FileInputStream(file);
			
			// 한글 파일명 깨짐 방지
		    String client = request.getHeader("User-Agent");
			// ie체크
		    if (client.indexOf("WOW64") == -1) {
		        originalFilename = 
	        		new String(originalFilename.getBytes("UTF-8"), "ISO-8859-1");
		    }
		    else {
		        originalFilename = 
	        		new String(originalFilename.getBytes("KSC5601"), "ISO-8859-1");
		    }
			
			
			// 파일 다운로드용 응답 헤더 설정 
		    response.reset();
			
			// 파일 다운로드 창을 띄우기 위한 콘텐츠 타입을 지정
			// octet-stream은 8비트 단위의 바이너리 데이터를 의미
		    response.setContentType("application/octet-stream");
			
			// 원본파일이름으로 다운로드 받을수 있게 설정 하는 부분
		    response.setHeader("Content-Disposition", 
		                       "attachment; filename=\"" + originalFilename + "\"");
		    response.setHeader("Content-Length", "" + file.length() );
		
		    // 출력스트림 초기화
		    // out.clear();
		    // out = PageContext.pushBody();
		    
		    // response 내장 객체로 부터 새로운 출력 스트림을 생성
		    OutputStream outStream = response.getOutputStream();
		    
		    // 출력 스트림에 파일 내용 출력
		    byte b[] = new byte[(int)file.length()];
		    int readBuffer = 0;    
		    while ( (readBuffer = inStream.read(b)) > 0 ) {
		        outStream.write(b, 0, readBuffer);
		    }
		    
		    // 입/출력 스트림 닫음;
		    inStream.close();
		    outStream.close();
		}
		catch (FileNotFoundException e) {
		    JSFunction.alertBack(response, "파일을 찾을 수 없습니다.");
		}
		catch (Exception e){
			JSFunction.alertBack(response, "파일 다운로드중 오류가 발생 하였습니다.");
		}
	    
	}
	
	/**
	 * 파일 업로드
	 * 파일 업로드(multipart/form-date 요청) 처리
	 * @return
	 */
	public static MultipartRequest uploadFile(HttpServletRequest req, String saveDirectory, int maxPostSize) {
		try {
			return new MultipartRequest(req, saveDirectory, maxPostSize, "UTF-8");
		} catch (IOException e) {
			// 업로드 실패
			e.printStackTrace();
			return null;
		}
	}
	
	public static  void deleteFile(String directory, String filename) {
		File file = new File(directory + File.separator + filename);
	
		if(file.exists()) {
			file.delete();
		}
	}
	
	public FileUtil() {
		// TODO Auto-generated constructor stub
	}

}
