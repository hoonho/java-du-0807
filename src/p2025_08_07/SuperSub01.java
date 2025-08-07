package p2025_08_07;

class Parent {						// 부모 클래스
	public void parentPrn() {
		System.out.println("슈퍼 클래스 메서드는 상속된다.");
	}
}

class Child extends Parent {		// 자식 클래스
	public void childPrn() {
		System.out.println("서브 클래스 메서드는 슈퍼가 사용 못한다.");
	}
}

class SuperSub01 {
	public static void main(String[] args) {
		Child c = new Child(); 
		c.parentPrn(); 			// 상속 받은 메소드 사용
		c.childPrn();           // 자기 클래스의 메소드 사용
		System.out.println("-------------------------------------->> ");
		
		Parent p = new Parent(); 
		p.parentPrn(); 			// 부모 클래스에서 자기 자신의 메서드 호출
//		p.childPrn(); 			// 부모 클래스에서 자식 클래스의 메소드에 접근할 수 없다.
	}
}