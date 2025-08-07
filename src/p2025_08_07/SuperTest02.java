package p2025_08_07;

// 부모 클래스의 필드는 자식 클래스에 상속된다.
class Point2D2 {						// 부모 클래스
	protected int x = 10;				// 필드
	protected int y = 20;
}

class Point3D2 extends Point2D2 {		// 자식 클래스
	protected int z = 30;

	public void print() {
		
		// x와 y 필드는 부모 클래스로 부터 상속 받아서 사용하고 있다.
		System.out.println(x + ", " + y + ", " + z); 
	}
}

class SuperTest02 {
	public static void main(String[] args) {
		Point3D2 pt = new Point3D2();
		pt.print();
	}
}