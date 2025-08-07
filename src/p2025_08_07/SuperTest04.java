package p2025_08_07;

class Point2D4 {						// 부모 클래스
	protected int x = 10; 	// 은닉 변수 혹은 쉐도우 변수
	protected int y = 20; 
}

class Point3D4 extends Point2D4 {		// 자식 클래스
	protected int x = 40; 
	protected int y = 50; 

	protected int z = 30;

	public void print() {
		System.out.println(x + ", " + y + ", " + z); 	// 40, 50, 30
	}

//	1. super : 부모 클래스를 의미하는 레퍼런스 변수이다.
//	2. super.x 는 부모 클래스의 은닉된 필드로 접근할 때 사용한다.
//  3. super.x 는 자식 클래스의 메소드 안에서만 사용할 수 있다.
	 
//	   System.out.println(super.x);		// 에러 발생
	
	public void print02() {
		System.out.println(super.x + ", " + super.y + ", " + z); // 10, 20, 30
	}
}

class SuperTest04 {
	public static void main(String[] args) {
		Point3D4 pt = new Point3D4();
		pt.print(); 
		pt.print02(); 
	}
}