public class Datatype{
    public static void main(String []args) {
        byte b = 127;
        int i = b; //자동형변환

        long l = 1234567890L;
        float f = 12.3F;
        double d = 12.3;

        f = i; //자동형변환
        f = l; //자동형변환

        b = (byte)d; //강제형변환
        System.out.println(b); //12
        //모두 컴파일오류 없이 정상 출력되어야 한다


        //문자형
        char c;
        c = '가'; //'A'; //'A' - 65  'a' - 97  '0' - 48
	       //'가'; //44033 - 65
        i = c; //자동형변환
        System.out.println(i);

        //i = i+1;
        i++; //66
        c = (char)i; //강제형변환 B
        System.out.println(c);


    }
}