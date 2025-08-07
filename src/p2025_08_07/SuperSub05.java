package p2025_08_07;

// 메소드 오버라이딩(Method Overriding)
// : 부모 클래스로 상속받은 메소드를 자식 클래스에서 재정의해서 사용하는 것.
// 1. 자식 클래스로 객체를 생성한 다음에 메소드를 호출하면, 메소드 오버라이딩된 메소드만 호출된다.
// 2. 부모 클래스에서 상속 해주는 메소드는 더 이상 사용할 수 없는 은닉 메소드가 된다.
// 3. 부모 클래스의 은닉 메소드를 자식 클래스에서 사용할때는 super.parentPrn() 으로 호출할 수 있다.

// 4. super는 부모 클래스를 의미하는 레퍼런스 변수이다.
// 5. 부모 클래스의 은닉된 메소드를 호출할때는 자식 클래스의 메소드 안에서 super.parentPrn() 으로
//    호출해야 된다.

class Parent5 {						// 부모 클래스
	public void parentPrn() {		// 은닉 메소드
		System.out.println("슈퍼 클래스 : ParentPrn 메서드");
	}
}

class Child5 extends Parent5 {		// 자식 클래스
	public void parentPrn() {		// 메소드 오버라이딩(Method Overriding)
		super.parentPrn();           // 부모 클래스의 은닉 메소드 호출
		System.out.println("서브 클래스 : ParentPrn 메서드");
	}

	public void childPrn() {
		super.parentPrn();           // 부모 클래스의 은닉 메소드 호출
		System.out.println("서브 클래스 : ChildPrn 메서드");
	}
}

class SuperSub05 {
	public static void main(String[] args) {
		Child5 c = new Child5();
		c.parentPrn();			// 메소드 오버라이딩된 메소드가 호출된다.
		c.childPrn();
		
	}
}
