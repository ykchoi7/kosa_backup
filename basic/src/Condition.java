public class Condition{
    public static void main(String[] args){

        /*
        //홀짝 구하기 + 난수 생성
        //int a = 4;
        //난수 생성 Math 라이브러리 활용
        int a = (int)(Math.random()*100);  //default 0.0<= <1.0 의 값
				  //*100 하면 0.0<= <100으로 범위 확장
        System.out.println("a=" + a);

        if( a%2 == 1) {
            System.out.println("홀수");
            System.out.println("제곱값은" + (a*a)); //조건에 충족되면 출력되는 식
        }

        if(a%2 == 1) {
            System.out.println("홀수");
        } else {
            System.out.println("짝수");
        }
        */

         /*
         //띠 구하기 + 데이터 입력받기
         //int year = 2023;
         //사용자에게 입력받기
         System.out.print("출생년도를 입력하세요");
         java.util.Scanner sc = new java.util.Scanner(System.in);
         int year = sc.nextInt();

         System.out.println(year + "년도에 해당하는 동물을 출력하십시오");
         //년도를 12로 나눈 나머지 값이 0이면 원숭이, 1이면 닭, 2이면 개, 3이면 돼지, 4이면 쥐, 5면 소, 
         //6이면 호랑이, 7이면 토끼, 8이면 용, 9이면 뱀, 10면 말, 11이면 양

         int mod = year%12;
         if (mod == 0) {
               System.out.println("원숭이띠입니다");
         } else if (mod==1) {
               System.out.println("닭띠입니다");
         } else if (mod==2) {
                 System.out.println("개띠입니다");
         } else if (mod==3) {
                 System.out.println("돼지띠입니다");
         } else if (mod==4) {
                 System.out.println("쥐띠입니다");
         } else if (mod==5) {
                 System.out.println("소띠입니다");
         } else if (mod==6) {
                 System.out.println("호랑이띠입니다");
         } else if (mod==7) {
                 System.out.println("토끼띠입니다");
         } else if (mod==8) {
                 System.out.println("용띠입니다");
         } else if (mod==9) {
                 System.out.println("뱀띠입니다");
         } else if (mod==10) {
                 System.out.println("말띠입니다");
         } else {
                 System.out.println("양띠입니다");
         }

         */
        
        /*
        //점수 구하기 (if~else문)
        int koScore = 70;
        int engScore = 60;
        int mathScore = 83;
        int totalScore = koScore + engScore + mathScore; //총점
        float avg = totalScore/3; //평균 => float형태로 바꾸었기 때문에 .0까지 나옴 / totalScore/3.0F; 로 실수형 정의해주는 방법도 있음
        System.out.println(avg); //71.333

        //평균값이 90점 이상이면 "A등급"을 출력
        //80점 이상이면 "B등급", 70점 이상이면 "C등급", 60점 이상이면 "D등급", 60점 미만이면 "F등급" 출력

        System.out.println("평균에 해당 등급을 출력하시오");
        if (avg >= 90) {
            System.out.println("A등급");
        } else if (avg >= 80) {
            System.out.println("B등급");
        } else if (avg >= 70) {
            System.out.println("C등급");
        } else if (avg >= 60) {
            System.out.println("D등급");
        } else if (avg <60) {
            System.out.println("F등급");
        }
        */

        /*
        System.out.println("가위바위보게임");
        System.out.println("가위-1, 바위-2, 보-3을 입력하세요 : ");
        java.util.Scanner sc = new java.util.Scanner(System.in);
        int u = sc.nextInt(); //사용자가 낸 값
        
        int r = (int)(Math.random()*3 + 1); //1<= <4 : 컴퓨터가 낸 값
        System.out.println("컴퓨터가 낸 값 : " + r);

        if (u == r) {
            System.out.println("비겼습니다");
        } else if (u<1 || u>3) {
            System.out.println("잘못 입력했습니다");
        } else if ((u ==1 && r ==2) || (u==2 && r==3) || (u==3 && r==1)) { //규칙을 발견하면 (u-(r%3))==1일때 이기는 케이스
            System.out.println("졌습니다");
        } else {
            System.out.println("이겼습니다");
        }
        */


        //switch문
        //짝수홀수 구하기
        int a = 3;
        switch(a%2) {
            case 0:
                System.out.println("짝수"); //break를 하지 않으면 아래 홀수까지 모두 기출
                break;
            case 1:
                System.out.println("홀수");
        }

        int year = 2023;
        int mod = year%12;
        switch (mod) {
        case 0 : 
            System.out.println("원숭이띠");
            break;
        case 1 : 
            System.out.println("닭띠");
            break;
        default : 
            System.out.println("그 외의 동물");
        }

        //캘린더 라이브러리
        java.util.Calendar c;
        c = java.util.Calendar.getInstance(); //현재 날짜 객체
        int month = c.get(java.util.Calendar.MONTH); //월 1월인 경우 0, 2월인 경우 1 반환
        System.out.println(month); //6

        if (month <= 5) { //상반기
            System.out.println("현재" + (month+1) + "월은 상반기입니다");    
        } else {
            System.out.println("현재" + (month+1) + "월은 하반기입니다");  
        }

        switch (month) { //byte, short, int, char, String 자료형만 들어갈 수 있음
        case 0:
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
            System.out.println("현재" + (month+1) + "월은 상반기입니다");
	break;
        default:
	System.out.println("현재" + (month+1) + "월은 하반기입니다");
        }
    }
}












