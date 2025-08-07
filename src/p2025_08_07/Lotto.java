package p2025_08_07;

import java.util.Arrays;

class Lotto {

	public static void main(String[] args) {

		int num[] = new int[6];

		for (int i = 0; i < num.length; i++) {

			num[i] = (int) (Math.random() * 45) + 1;     // 1 ~ 45
//			num[0]=5, num[1]=5,
			for (int j = 0; j < i; j++) {
				if (num[i] == num[j]) {		// 중복 발생
					i--;
					break;					// 안쪽 for을 빠져 나간다.
				} 

			} 
		} 

		
		// 오름차순 정렬 : 버블정렬(자신의 옆자리에 있는 것과 비교)
		int temp = 0;
		for (int i = 0; i < num.length; i++){
			for (int j = i+1; j < num.length; j++) {
				if(num[i] > num[j]){
					temp = num[i];
					num[i] = num[j];
					num[j] = temp;
				}
			}
		}

		Arrays.sort(num); // 오름차순 정렬(1,2,3...)

		for (int k : num) {
			System.out.print(k + "\t");
		}
	}
}


// 난수 발생 공식 
// 난수 =(정수화)(Math.random()*(상한값-하한값+1)) + 하한값
  
