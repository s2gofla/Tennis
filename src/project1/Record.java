package project1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Record {

	// 매개변수로 받은 String 데이터를 파일에 쓰기
	static void writeScoreBoard(String str) {

		File file = new File(".\\src\\project1\\tennis.txt");

		// fw를 자동 close() 하게하려고 try("요기") 에 객체 생성
		try (FileWriter fw = new FileWriter(file,true)){   //true가 덧붙이기 해줌
			
			fw.write(str + "\n");
			
		} catch (IOException e) {
			e.printStackTrace();
		} 

	}

}
