import java.util.Scanner;

public class ArrayTest {

	public static void main(String[] args) {
		int[] arr;
		arr = new int[4]; //멤버변수일 때 null값으로 자동 초기화
		arr[0] = 10; //국어점수
		arr[1] = 20; //수학점수
		arr[2] = 33; //영어점수
		arr[3] = 40; //과학점수
		System.out.println(arr.length);
		
		int totalScore = 0;
//		totalScore += arr[0];
//		totalScore += arr[1];
//		totalScore += arr[2];
		
		//반복문으로 합 구하기
//		for (int i=0; i<arr.length; i++) {
//			totalScore += arr[i];
//		}
		for (int score: arr) {  //향상된 for문
			totalScore += score;
		}
		float avg = (float)totalScore / arr.length; 
		//실수 결과를 얻으려면 연산식에 있는 변수 적어도 하나는 float타입으로 변환해주어야한다
		System.out.println("total score = " + totalScore + ", average = " + avg);
		
		
		//2차원 배열
		int[][] arrTwo = new int[3][2];
//		arrTwo[0][0] = 1;
//		arrTwo[0][1] = 2;
//		//-> 이런식으로 하나하나 넣는 방식이 있음
		
		int num = 1;
		int rowLength = arrTwo.length; //3
		for (int row=0; row<rowLength; row++) {
			int colLength = arrTwo[row].length; //2
			for (int col=0; col<colLength; col++) {
				arrTwo[row][col] = num;
				num++;
				System.out.print(arrTwo[row][col] + " ");
			}
			System.out.println();
		}
		System.out.println(arrTwo); //값이 아닌 주소값이 출력됨
		
		
		//각 배열의 크기가 다를 때
		int[][] arrTwo2 = new int[3][]; //행별 열수 다르게
		arrTwo2[0] = new int[1];
		arrTwo2[1] = new int[2];
		arrTwo2[2] = new int[3];
		System.out.println(arrTwo2.length); //3
		System.out.println(arrTwo2[0].length); //1
		System.out.println(arrTwo2[1].length); //2
		System.out.println(arrTwo2[2].length); //3
		
		int num1 = 1;
		for (int i=0; i<arrTwo2.length; i++) {
			int len = arrTwo2[i].length;
			for (int j=0; j<len; j++) {
				arrTwo2[i][j] = num1;
				num1++;
				System.out.print(arrTwo2[i][j] + " ");
			}
			System.out.println();
		}
		
		
		//숫자의 출현횟수 구하기
		int[] arr1 = {1, 3, 7, 10, 5, 4, 2, 3, 7, 6, 2};
		System.out.println("숫자의 출현 횟수를 출력하시오");
		//출력결과) 1의 출현횟수-1회, 2의 출현횟수-2회, ....
		
		int[] arr2 = new int[10]; //출현횟수를 저장할 배열
		//arr2[arr1[0]-1]++; //arr2배열의 [0]은 1의 출현횟수
		
		for (int i = 0; i < arr1.length; i++) {
			arr2[arr1[i]-1]++;
		}
		for (int j = 0; j < arr2.length; j++) {
			System.out.println((j+1) + "의 출현횟수 - " + arr2[j] + "회");			
		}
		
		
		//배열에서 최대값 구하기
		int[] arr3 = {5, 4, 7, 1, 2};
		System.out.println("최대값을 계산하시오");
		
		int max = arr3[0];
		for (int i = 0; i < arr3.length; i++) {
			if (arr3[0] < arr3[i]) {
				max = arr3[i];
			}
		}
		System.out.println(max);
		
		
		//**정렬하기**
		int[] arr4 = {5, 4, 7, 1, 2};
		System.out.println("작은 수부터 정렬하시오");
		
		//선택정렬 - n^2의 시간복잡도
		for (int i = 0; i < arr4.length; i++) {
			max = i;
			for (int j = 0; j < arr4.length; j++) {
				if (arr4[max] < arr4[j]) {
					int temp = arr4[max];
					arr4[max] = arr4[j];
					arr4[j] = temp;
				}
			}
		}
		for (int i = 0; i < arr4.length; i++) {
			System.out.print(arr4[i] + " ");
		}
		System.out.println();
		
		//삽입정렬 - n^2의 시간복잡도 (최고는 nlogn)
		
		
		//버블정렬 - n^2의 시간복잡도
		for (int i = 0; i < arr4.length; i++) {
			for (int j = 0; j < arr4.length-1; j++) {
				if (arr4[j] > arr4[j+1]) {
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
		for (int i = 0; i < arr4.length; i++) {
			System.out.print(arr4[i] + " ");
		}
		System.out.println();
		
		
		//제어 예약어 - break, continue
		int[] lotto = new int[6];
		for (int i = 0; i < lotto.length; i++) {
			lotto[i] = (int)(Math.random()*45 + 1); //1 <= <46			
			for (int j = 0; j < i; j++) { //중복 확인
				if (lotto[i] == lotto[j]) { //중복된 경우
					i--;
					break; //가장 근접한 반복문을 빠져나옴 (j반복문을 아예 빠져나옴)
//					continue; //건너뛰기 이기 때문에 다른거 실행 없이 j++ 진행
				}
//				System.out.println("중복 안됨");
			}
		}
		
		for (int value : lotto) {
			System.out.print(value + ", ");
		}
		System.out.println();
		
		
		//2차원 배열의 합
		int[][] arrTwo3; //선언
		arrTwo3 = new int[5][];
		
		//행별 칼럼 생성
//		arrTwo3[0] = new int[1];
//		arrTwo3[1] = new int[2];
//		arrTwo3[2] = new int[3];
//		arrTwo3[3] = new int[4];
//		arrTwo3[4] = new int[5];
		
		for (int i = 0; i < arrTwo3.length; i++) {
			arrTwo3[i] = new int[i+1];
		}
		
		for (int i = 0; i < arrTwo3.length; i++) {
			int len = arrTwo3[i].length;
			for (int j = 0; j < len; j++) {
				if (j == 0 || j == len-1) { //각 행의 첫번째나 마지막 부분은 1 넣기
					arrTwo3[i][j] = 1;
				} else {					//나머지에는 위와 위왼쪽 값을 더한 값 넣기
					arrTwo3[i][j] = arrTwo3[i-1][j-1] + arrTwo3[i-1][j];
				}
				System.out.print(arrTwo3[i][j] + " ");
			}
			System.out.println();
		}
		
		//향상된 for문으로 만들기
		for (int[] valueArr : arrTwo3) {
			for (int value : valueArr) {
				System.out.print(value + " ");
			}
			System.out.println();
		}
		
		
		//과목 점수 구하기
		String[] subject = {"국어", "수학", "영어"};
		int subjectLength = subject.length;
		
		int[][] arrTwo4 = new int[10][subjectLength]; //최대 10명의 학생 점수(국어, 수학, 영어)
//		arrTwo4[0][0] = 10; //1번 학생의 국어점수 10
//		arrTwo4[0][1] = 5;  //1번 학생의 수학점수 5
//		arrTwo4[0][2] = 7;  //1번 학생의 영어점수 7
		
		Scanner sc = new Scanner(System.in);
		int no = 0; //1번 학생부터
		
		while(no < 10) {
			System.out.println("점수 입력을 진행하겠습니까? [y/n]");
			String yn = sc.next();
			if (yn.equals("n")) {
				break;
			}
			
			//반복문으로 넣기
			for (int i = 0; i < subject.length; i++) {
				System.out.println((no+1) + "번 학생의 " + subject[i] + "점수 : ");
				arrTwo4[no][i] = sc.nextInt();
			}
			
//			System.out.print((no+1) + "학생의 국어점수 : ");
//			int k = sc.nextInt();
//			arrTwo4[no][0] = k;
//			
//			System.out.print((no+1) + "학생의 수학점수 : ");
//			int m = sc.nextInt();
//			arrTwo4[no][1] = m;
//			
//			System.out.print((no+1) + "학생의 영어점수 : ");
//			int e = sc.nextInt();
//			arrTwo4[no][2] = e;
			
			no++; //1~10번 학생까지 반복				
		}
		System.out.println("입력이 완료되었습니다.");				
		
		//학생들의 점수 출력하기
		//1번 학생점수 : 국어 -, 수학 -, 영어 -, 총점 -, 평균 -
		//2번 학생점수 : 국어 -, 수학 -, 영어 -, 총점 -, 평균 -	
		//*꼭 10명이 다 입력되지 않더라고 입력된 사람의 수에 맞게 계산이 되어야한다
		int[] totalScoreSubject = new int[3]; //모든 학생의 과목별 총점
		System.out.println("학생들의 점수 출력하기");
		
		for (int k = 0; k < no; k++) { //학생 수만큼 반복
			System.out.print((k+1)+ "번 학생의 점수: ");
			float sum = 0;
			for (int i = 0; i < subjectLength; i++) { //과목 수만큼 반복
				sum += arrTwo4[k][i];
				totalScoreSubject[i] += arrTwo4[k][i];
				System.out.print(subject[i] + " " + arrTwo4[k][i] + " ");
			}
			System.out.print(", 총점 " + sum + ", 평균 " + sum/3 + "\n");
		}
		
		//과목별 평균
		for (int i = 0; i < subjectLength; i++) {
			System.out.println(subject[i] + "과목 평균 : " + (float)totalScoreSubject[i]/no);
		}

	}
}
