package solid;

//public class Main {
//	public static void main(String[] args)
//    {
//        Rectangle rectangle = new Square();
//        rectangle.setWidth(10);
//        rectangle.setHeight(5);
//        
//        System.out.println(rectangle.getArea()); //25
//    }
//}

//Square의 setHeight에서 너비, 높이를 모두 5로 할당하여 넓이 25 출력
//직사각형과 정사각형이 상속관계가 될 수 없다. 두 도형 모두 사각형의 한 종류일 뿐, 
//하나가 다른 하나를 완전히 포함하지는 못한다



//올바른 코드
public class Main {
    public static void main(String[] args)
    {
        Shape rectangle = new Rectangle(10, 5);
        Shape square = new Square(5);
        System.out.println(rectangle.getArea());
        System.out.println(square.getArea());
    }
}



