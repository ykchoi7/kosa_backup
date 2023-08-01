//주석문
//한줄주석
/*여러줄주석*/

public class Operator{
    public static void main(String[] args){
        //산술연산자 -> 대입연산자 (우선순위)
        int a, b, c;
        a = 2;
        b = 3;
        c = a+b; //산술 연산 후에 대입연산됨
        System.out.println(c); //5
        
        System.out.println(c+"1"); //int + String 5+"1" => 결합연산자 "51"
        System.out.println(c+1+"1"); // int+int+String => int끼리만 숫자 더해짐 => "61"
        System.out.println("2" + c+1); //String+int+int => "251"
        System.out.println("2" + (c+1)); // "26" => int끼리의 합을 구해서 뒤에 붙이려면 () 괄호 안에 넣기

        System.out.println(a/b); //정수 나누기 정수 -> 정수 0
        System.out.println(c%a); //나머지값 = 1
        //System.out.println(a/0); //정수를 0으로 나눈 결과 -> 컴파일은 되지만, ArithmeticException 예외발생 후 프로그램 종료

        float f1, f2;
        f1 = 2F;
        f2 = 0F;
        System.out.println(f1/f2); //***실수를 0으로 나눈 결과 -> Infinity, 시스템이 종료되지도 않음

        //대입연산자
        a = 2;
        a += 2; //a = a+2; 와 같은 의미     
        System.out.println(a); //4
        a *= 3; //a = a*3; 과 같은 의미
        System.out.println(a); //12

        //단항연산자
        a = 2;
        a++; //a=a+1; 또는 a+=1;과 같음 **현업에서는 이렇게 쓰는게 좋은 코드, 다른 연산과 섞어쓰지 말기**
        System.out.println(a); //3

        a = 2;
        b = a++; //b에 a를 대입하고 +1을 해라
        System.out.println(a); //3
        System.out.println(b); //2

        a = 2;
        b = ++a; //a에 +1을 하고 b에 대입
        System.out.println(a); //3
        System.out.println(b); //3

        //*참고*
        byte by = 127;
        by++;
        System.out.println(by); //-128 -> 8비트로 표현할 때 127은 01111111(맨앞은 부호비트 0-양수, 1-음수)
			//	  +1을 하면 10000000 -> 맨앞 부호비트 1로 음수가 된다 & 십진수로 128

        //비교연산자 -> true/false형으로 결과 출력
        System.out.println(1>0); //true
        System.out.println(1<0); //false
        System.out.println(a%2==1); //true
        System.out.println(a%2 != 0); //true

        //논리연산자
        System.out.println(1>0 && a%2==1); //true
        System.out.println(1<0 && a%2==1); //false
        System.out.println(1<0 && a%2!=1); //false

        System.out.println(1>0 || a%2==1); //true
        System.out.println(1<0 || a%2==1); //true
        System.out.println(1<0 || a%2!=1); //false

        System.out.println(1>0 & a%2==1); //true
        System.out.println(1<0 & a%2==1); //false
        System.out.println(1<0 & a%2!=1); //false
        //두 조건의 결과가 같다 틀리다로 true/false 나오는 것은 xor 연산자

        System.out.println(1&2);  //정수끼리 비교하는 것은 비트연산자
        System.out.println(true&false); //true false를 비교하는 것은 논리연산자

        System.out.println(!(a%2 ==1)); //false (괄호안이 true)

        //삼항연산자
        System.out.println(a%2==1 ? "홀수" : "짝수");  //홀수
        
        //연산우선순위
        System.out.println(1+2*3/4); //2*3 -> 6/4 -> 1+1 -> 2
    }
}










