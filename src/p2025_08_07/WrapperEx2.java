package p2025_08_07;

// p522
// 박싱과 언박싱
public class WrapperEx2 {

	public static void main(String[] args) {
		
		// 박싱(boxing)
		Integer obj1 = new Integer(10);
		Integer obj2 = new Integer("200");
		Integer obj3 = new Integer("300");
		
		// 언박싱(unboxing)
		int value1 = obj1.intValue();
		int value2 = obj2.intValue();		// 자료형 변환 : "200"   -->  200
		int value3 = obj3.intValue();       // 자료형 변환 : "300"   -->  300
		
		System.out.println(value1);
		System.out.println(value2);
		System.out.println(value3);

	}

}
