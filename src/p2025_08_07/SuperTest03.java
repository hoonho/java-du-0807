package p2025_08_07;

// 1. 부모 클래스에 있는 필드를 자식 클래스에서 재정의(동일한 이름의 변수)하면,
//    자식 클래스에서 재정의한 필드만 사용 가능하다.
// 2. 부모 클래스의 필드는 더 이상 상속이 되지 않기 때문에 은닉변수가 된다.
// 3. 자식 클래스에서 x, y를 재정의하면 부모 클래스의 x, y는 은닉변수가 된다.
class Point2D3 {					// 부모 클래스		
	protected int x = 10; 
	protected int y = 20; 	// 은닉 변수
}

class Point3D3 extends Point2D3 {	// 자식 클래스
	protected int x = 40; 
	protected int y = 50; 

	protected int z = 30;

	public void print() {
		System.out.println(x + ", " + y + ", " + z);   // 40, 50, 30
	}
}

class SuperTest03 {
	public static void main(String[] args) {
		Point3D3 pt = new Point3D3();
		pt.print();
	}
}