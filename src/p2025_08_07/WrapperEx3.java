package p2025_08_07;

// p523
// 자동 박싱과 자동 언박싱
public class WrapperEx3 {

	public static void main(String[] args) {
		
		Integer obj1 = new Integer(100);				// 박싱
		Integer obj = 100;								// 자동 박싱
		System.out.println("언박싱:"+ obj1.intValue());  
		System.out.println("자동 언박싱:"+ obj1);  
		
		// 언박싱
		int value1 = obj.intValue();	
		
		// 자동 언박싱
		int value2 = obj;
		
		// 연산시 자동 언박싱
		int result = obj + 100;
		System.out.println("result:"+ result);
		

	}

}
