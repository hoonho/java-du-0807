package example;

import java.time.LocalDate;
import java.util.Random;

/*
 * 1. 카드 정보는 2차원 배열에 저장: [잔액][만료일]
 * 2. 잔액은 1000 ~ 30000 사이 랜덤
 * 3. 만료일은 2026년, 월과 일이 랜덤이며, 한 자리 수일 경우 앞에 0을 붙여서 출력
 * 4. 단말기에 카드를 찍으면:
 *    9개월 이내 만료 -> 1500원 차감 후 1초 뒤 만료일 표시
 *    9개월 이상 남음 -> "승차" 표시.
 * 5. 모든 출력은 3초간 유지
 */

public class TransportCardSystem {
  public static void main(String[] args) throws InterruptedException {
    // 카드 정보: [0] = 잔액, [1] = 만료 월, [2] = 만료 일
    int[][] cards = new int[5][3];
    Random rand = new Random();

    for (int i = 0; i < cards.length; i++) {
      cards[i][0] = rand.nextInt(291) * 100 + 1000;
      cards[i][1] = rand.nextInt(12) + 1;
      cards[i][2] = rand.nextInt(31) + 1;
    }

    for (int i = 0; i < cards.length; i++) {
      System.out.println("==== 카드 " + (i + 1) + " 단말기에 접촉 ====");

      int balance = cards[i][0];
      int expireMonth = cards[i][1];
      int expireDay = cards[i][2];

      LocalDate today = LocalDate.now();
      LocalDate expireDate = LocalDate.of(2026, expireMonth, expireDay);

      if (balance < 1500) {
        System.out.println("잔액 부족: " + balance + "원");
      } else {
        LocalDate threeMonthsLater = today.plusMonths(9);

        if (expireDate.isBefore(threeMonthsLater)) {
          balance -= 1500;
          System.out.println("요금 차감: 1500원 / 남은 잔액: " + balance);
          Thread.sleep(1000);

          String monthStr = (expireMonth < 10) ? "0" + expireMonth : String.valueOf(expireMonth);
          String dayStr = (expireDay < 10) ? "0" + expireDay : String.valueOf(expireDay);
          System.out.println("※ 카드 만료일: 2026-" + monthStr + "-" + dayStr);
        } else {
          balance -= 1500;
          System.out.println("승차 / 남은 잔액: " + balance + "원");
        }
      }
      Thread.sleep(3000);
      System.out.println();
    }
  }
}
