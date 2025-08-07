package p2025_08_07;

import java.util.StringTokenizer;

class StringTest04 {

	public static void main(String[] args) {
		StringTokenizer str = new StringTokenizer("이순신#을지문덕#강감찬#광개토대왕", "#");

		// 토큰(token) : 구분기호(#)로 분리된 문자 
		// ex) 이순신, 을지문덕, 강감찬, 광개토대왕		
		
		// 파싱된 문자열(토큰)이 모두 몇 개인지 되는지 알려줌
		int cnt = str.countTokens();
		System.out.println("파싱할 문자열의 총갯수-> " + cnt);

//		System.out.println(str.nextToken());			// 이순신
//		System.out.println(str.nextToken());			// 을지문덕
//		System.out.println(str.nextToken());			// 강감찬
//		System.out.println(str.nextToken());			// 광개토대왕
//		System.out.println(str.nextToken());			// 가져올 토큰이 없으면 예외가 발생한다.		
		
		// 토큰이 있을 경우에만 토큰을 구해온다.
		while (str.hasMoreTokens()) { 					// 토큰이 있으면
			System.out.print(str.nextToken()+"\t");		// 토큰을 1개씩 구해온다.
		}

	} 
	
}