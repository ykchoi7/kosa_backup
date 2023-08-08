import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileTest {

	public static void main(String[] args) {
		
		//파일리스트 가져오기
		File f;
		f = new File("D:\\"); // D드라이브에 대한 정보 알아내기
		if (f.isDirectory()) { // if(f.isDirectory()==true) {} 와 동일
			String[] list = f.list();
			for (String sub : list) { // D드라이브에 있는 하위디렉토리명을 모두 불러내기
				System.out.println(sub);
			}
			
			File[] files = f.listFiles(); //배열로 저장하는 방법 .listFiles()
			for (File file : files) {
				String name = file.getName();
				if (file.isFile()) { // d:\\의 하위파일
					long fileSize = file.length();
					long lastModifiedTime = file.lastModified(); //파일을 사용한 최종 사용시간
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a hh:mm"); //숫자를 date포맷으로 출력하기
					String formatStr = sdf.format(new Date(lastModifiedTime));
					System.out.println(name + "\t" + formatStr + "\t" + fileSize);
				} else { // d:\\의 하위폴더
					System.out.println("[" + name + "]");
				}
				
			}
		}
		
		
		//폴더생성
		String folder = "D:\\attache";
		File file = new File(folder);
		if (!file.exists()) { //if (file.exists() == false) {} 와 동일
			boolean result = file.mkdir(); //디렉토리 생성 .mkdir(), 삭제 .delete()
			if (result) {
				System.out.println(folder + "폴더가 생성되었습니다");
			} else {
				System.out.println(folder + "폴더가 생성 안되었습니다");
			}
		} else {
			System.out.println(folder + "폴더가 이미 존재합니다");
		}
		
		
		//파일생성
		String fileName = "a.txt";
		File file1 = new File(file, fileName);
		try {
			if (!file1.exists()) { 
				file1.createNewFile(); //file 만들기
				System.out.println(fileName + "파일이 생성되었습니다");
			} else {
				System.out.println(fileName + "파일이 이미 존재합니다");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
