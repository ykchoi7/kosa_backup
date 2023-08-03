class Copy implements Cloneable { //extends Object
	int i;
	int[] arr = {1,2,3};
	Object copy() {
		Object obj = null;
		try {
			obj = this.clone(); //복제본
			int[] arrCopy = (int[]) arr.clone(); //deep copy를 하기위해 참조하는 배열도 clone
			Copy copy = (Copy) obj;
			copy.arr = arrCopy;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return obj;
	}
}

public class CloneTest {
	public static void main(String[] args) {
		Copy origin = new Copy();	//원본객체
		origin.i = 9;
		origin.arr[0] = 9;
		
//		origin.clone() //clone()은 protected이기 때문에 자식클래스에서만 접근 가능
		Object obj = origin.copy(); //원본을 복제한것
		Copy copy = (Copy) obj;
		
		System.out.println("복제본 객체의 i=" + copy.i); //CloneNotSupportedException 발생 
													//->Copy클래스에서 Cloneable Interface를 꼭 구현해야한다!! (보안상의 제약이 있기 때문에 interface구현한 것만 카피를 허용한다)
		System.out.println("복제본 객체의 arr[0]=" + copy.arr[0]); //9
		
		copy.i = 7; //복제본의 i에 7대입
		copy.arr[0] = 7; //복제본의 배열에 7을 대입하면 origin도 같은 배열을 참조하기 때문에 원본,복제 모두 변경
		System.out.println("원본 객체의 i=" + origin.i); //9
		System.out.println("원본 객체의 arr[0]=" + origin.arr[0]); //7->shallow copy
																//9->deep copy
		System.out.println("복제본 객체의 i=" + copy.i); //7
		System.out.println("복제본 객체의 arr[0]=" + copy.arr[0]); //7
	}
}

//Thus, this method performs a "shallow copy" of this object, not a "deep copy" operation.
//shallow copy만 하면 원본과 독립적인 배열이 아닌 연결되어있음
//deep copy를 하고 싶으면 원본 copy, 참조하고 있는 배열도 copy -> 새로운 배열을 새로운 참조배열에 연결


