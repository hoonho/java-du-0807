package p2025_08_07;

class TestClass2 {
	private Object member;

	public void setValue(Object value) {
		member = value;
	}

	public Object getValue() {
		return member;
	}
}

class GenericTest02 {
	public static void main(String[] args) {
		TestClass2 obj01 = new TestClass2();
		
		// Object value = new Integer(3);			// 박싱 + 업캐스팅(자동 형변환)
		// Object value = 3;                        // 자동박싱 + 업캐스팅
		obj01.setValue(3);
		System.out.println("되돌리는 값은->" + obj01.getValue());
		
		// 다운 캐스팅(강제 형변환) + 언박싱
		int n = ((Integer)(obj01.getValue())).intValue();	
//---------------------------------------------------------------------------	
		// Object value = new Double(3.4);			// 박싱 + 업캐스팅
		// Object value = 3.4;                      // 자동박싱 + 업캐스팅
		obj01.setValue(3.4);
		System.out.println("되돌리는 값은->" + obj01.getValue());
		
		// 다운캐스킹 + 언박싱
		double d = ((Double)(obj01.getValue())).doubleValue();
//-----------------------------------------------------------------------------		
		// Object value = new String("이해할 수 있다."); // 박싱 + 업캐스팅
		// Object value = "이해할 수 있다.";             // 자동박싱 + 업캐스팅
		obj01.setValue("이해할 수 있다.");
		System.out.println("되돌리는 값은->" + obj01.getValue());
		
		// 다운 캐스팅(강제 형변환)
		String str = (String) obj01.getValue();

	}
}
