//SRP 위반 코드
package solid;


public class Animal {
	private String animal;

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public void cry () {

        if(animal == "Dog") { // 강아지
            System.out.println("bark!");
        }
        else if(animal == "Cat") { // 고양이
            System.out.println("meow..");
        }
    }
}

//두 기능이 분리되어 있지 않고 하나의 메서드가 두 기능을 모두 가지고 있으므로 
//단일 책임 원칙을 위반


//SRP 적용 코드 - 클래스로 분리하여 cry()라는 메소드 재정의
//abstract class Animal {
//    abstract void cry();
//}
//
//class Dog extends Animal {
//    @Override
//    void cry() {
//        System.out.println("bark!!!");
//    }
//}
//
//class Cat extends Animal {
//    @Override
//    void cry() {
//        System.out.println("Meow...");
//    }
//}
