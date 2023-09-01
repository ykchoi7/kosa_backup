package solid;

//public class Square extends Rectangle {
//    @Override
//    public void setHeight(int height) {
//        super.setWidth(height);
//        super.setHeight(height);
//    }
//    @Override
//    public void setWidth(int width) {
//        super.setWidth(width);
//        super.setHeight(width);
//    }
//}


//올바른 코드
class Rectangle extends Shape
{
    public Rectangle(int width, int height)
    {
        setWidth(width);
        setHeight(height);
    }
}

class Square extends Shape
{
    public Square(int length)
    {
        setWidth(length);
        setHeight(length);
    }
}
