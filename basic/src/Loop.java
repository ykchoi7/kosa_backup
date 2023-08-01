import java.util.Iterator;
import java.util.Scanner;

public class Loop {
    public static void main(String[] args) {
        /*
        System.out.println("JAVA");
        System.out.println("JAVA");
        System.out.println("JAVA");
        System.out.println("JAVA");
        System.out.println("JAVA");
        */

        //while 반복문
//        int i = 0;
//        while ( i<5 ) {
//            System.out.println("JAVA" + i);
//            i++; //조건을 만족한다면 java 출력하고 i 하나씩 증가
//        }
        
        //for 반복문
        int i;
        for(i=0; i<5; i++) {
        	System.out.println("JAVA" + i);
        }

        /*
        int sum = 0;
        sum += 1; //sum = sum+1;
        sum += 2; //sum = sum+2;
        sum += 3; //sum = sum+3;
        sum += 4;
        sum += 5;
        sum += 6;
        sum += 7;
        sum += 8;
        sum += 9;
        sum += 10;
        */

        //while문으로 진행해보기
        i = 1;
        int sum = 0;
        while (i <= 10) {
            sum += i;
            i++;
        }
        System.out.println("1~10까지의 합 " + sum); //55
        
        //for문으로 반복하기
        int sum1 = 0;
        for (i=1; i<=10; i++) {
        	sum1 += i;
        }
        System.out.println("1~10까지의 합 " + sum1);

        //1~100까지의 합
        i = 1;
        sum = 0;
        while (i <= 100) {
            sum += i ;
            i++;
        }
        System.out.println("1~100까지의 합 " + sum); //5050
        
        //for문으로 작성하기
        for (i=1, sum=0; i<=100; i++) {
        	sum += i;
        }
        System.out.println("1~100까지의 합 " + sum);

        //홀수합, 짝수합
        i = 1;
        int oddSum = 0; //홀수합
        int evenSum = 0; //짝수합
        System.out.println("1~100의 홀수합, 짝수합을 계산하여 출력하시오");

//        while (i <= 100) {
//            if (i%2 == 0) {
//                evenSum += i;
//                i++;
//            } else {
//                oddSum += i;
//                i++;
//            }
//            //i++;
//        }
        
        for (i=1; i<=100; i++) {
        	if (i%2 == 0) {
        		evenSum += i;
        	} else {
        		oddSum += i;
        	}
		}
        
        System.out.println("짝수합 =" + evenSum); //2550
        System.out.println("홀수합 =" + oddSum);  //2500

        //1~100까지의 숫자
        i = 1;
        while (i<=100) {
            if (i>1) {
                System.out.print(",");
            }
            System.out.print(i);
            i++;
        }
        System.out.println();
        
        
        //값치환
        int a, b, temp;
        a = 10;
        b = 20;
        
        temp = a;
        a = b;
        b = temp;
        
        System.out.println("a=" +a + ", b=" + b);

        
        //char형 출력하기
//        char ch = 'A';
//        System.out.println(ch); //A
//        ch += 1;
//        
//        System.out.println(ch); //B
//        ch++;
        
        //while문으로
        char ch = 'A';
        while (ch <= 'Z') {
        	System.out.print(ch + ",");
        	//ch++;
        	ch += 1;
        	//ch = ch+1; //컴파일오류 => 산술연산자는 적어도 4바이트의 메모리가 필요한데 char형은 불가 (int이상부터 적용가능)
        }
        System.out.println();
        
        //for문으로
        for (ch='A'; ch <='Z'; ch++) {
        	System.out.print(ch + " ");
        }
        System.out.println();
    
        
        //피보나치수열
        //System.out.println("피보나치 수열");
        //1, 1, 2, 3, 5, 8, 13, 21, 34, ........ => (전전수+전수)의 규칙
        
        int bbNum = 1; //전전수
        int bNum = 0; //전수
        int cNum = bbNum + bNum; //현재수 1
//        
//        bbNum = bNum; //전수를 전전수에 대입 전전수=0
//        bNum = cNum; //현재수를 전수에 대입 전수=1
//        cNum = bbNum + bNum; //현재수 1
//                
//        bbNum = bNum; //전수를 전전수에 대입 전전수=1
//        bNum = cNum; //현재수를 전수에 대입 전수=1
//        cNum = bbNum + bNum; //현재수 2
//        
//        bbNum = bNum; //전수를 전전수에 대입 전전수=1
//        bNum = cNum; //현재수를 전수에 대입 전수=2
//        cNum = bbNum + bNum; //현재수 3
        
        System.out.println("피보나치 수열 20개의 숫자를 출력하시오");

        int j = 0;
        while (j < 20) {
        	cNum = bNum + bbNum; //현재수 = 전수+전전수
        	System.out.print(cNum + ",");
        	bbNum = bNum; //전수를 전전수에 대입
        	bNum = cNum;  //현재수를 전수에 대입
        	j++;
        }
        System.out.println();
        
        
        //1~10까지 숫자 출력하기
        System.out.println("1~10까지 출력하시오");
        
        //k=1부터 시작할 때
//        int k = 1;
//        while(k <= 10) {
//        	System.out.print(k);
//        	k++;
//        }
        
        //k=0부터 시작할 때
        int k = 0;
        while(k < 10) {
        	k++;
        	System.out.println(k); //k=0부터 시작하면 +1을 먼저 해주고 print
        }
        
        //for문으로 작성하기
        for (k=0; k<10; ) {
        	k++;
        	System.out.print(k + " ");
        }
        //**이것이 대표적으로 while문을 쓰는 사례!!** 증감식이 내용부분에 나오고 for문 조건식에는 빠지면 보기 이상함
        //이럴 땐 while문으로 작성하는 것이 통상
        
        
        //do~while 반복문 학습 - 메시지 입력받기
        Scanner sc = new Scanner(System.in);
        System.out.println("메시지를 입력하세요. 작업을 중단하려면 exit을 입력하세요");
//        String line = sc.nextLine();
//        if (!line.equals("exit")) { //문자열 비교 equals로! == 아님!!
//        	System.out.println("입력한 메시지: " + line);
//            System.out.println("메시지를 입력하세요. 작업을 중단하려면 exit을 입력하세요");
//            line = sc.nextLine();
//        }
        
        //do~while문으로 진행 (비교문이 먼저인 반복문과 다름!)
        String line; //외부에서 변수를 선언해주어야 do와 while문 둘다에서 사용이 가능하다
        do {
        	System.out.println("메시지를 입력하세요. 작업을 중단하려면 exit을 입력하세요");
            line = sc.nextLine();
        	System.out.println("입력한 메시지: " + line);
        } while (!line.equals("exit"));
        System.out.println("입력이 중단되었습니다.");
        
        
    }
}
