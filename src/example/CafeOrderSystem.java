package example;

import java.util.*;

/*
 * 카페 주문 시스템 시뮬레이션
 * 
 * 1. 메뉴는 3개 이상이며 각각 이름, 가격, 할인 기능 여부를 가짐
 * 2. 사용자가 메뉴를 고르면 총합 계산:
 *    할인 가능한 메뉴는 멤버십 카드 소지 시 10% 할인
 *    총 주문 금액이 20000원 이상일 경우 추가로 1000원 할인
 * 3. 결제 후 3초 후에 영수증 출력
 * 4. 주문 내역은 콘솔에 표시되며 총합, 할인 내역, 결제 금액을 포함
 */

public class CafeOrderSystem {
  static class Menu {
    String name;
    int price;
    boolean discountable;

    Menu(String name, int price, boolean discountable) {
      this.name = name;
      this.price = price;
      this.discountable = discountable;
    }
  }

  public static void main(String[] args) throws InterruptedException {
    Scanner sc = new Scanner(System.in);

    Menu[] menus = {
      new Menu("아메리카노", 4500, true),
      new Menu("카페라떼", 5000, true),
      new Menu("치즈케이크", 6000, false),
      new Menu("샌드위치", 7500, true)
    };

    Map<String, Integer> order = new HashMap<>();
    int total = 0;

    System.out.println("=== 카페 메뉴 ===");
    for (int i = 0; i < menus.length; i++) {
      System.out.println((i + 1) + ". " + menus[i].name + " - " + menus[i].price + "원" +
      (menus[i].discountable ? " (할인 가능)" : ""));
    }

    System.out.print("멤버십 카드가 있습니까? (y/n): ");
    boolean hasMembership = sc.nextLine().equalsIgnoreCase("y");

    while (true) {
      System.out.print("주문할 메뉴 번호 (종료: 0): ");
      int choice = sc.nextInt();
      if (choice == 0) break;

      if (choice < 1 || choice > menus.length) continue;

      System.out.print("수량: ");
      int qauntity = sc.nextInt();

      Menu selected = menus[choice - 1];
      int itemTotal = selected.price * qauntity;

      if (hasMembership && selected.discountable) {
        itemTotal *= 0.9;
      }

      total += itemTotal;
      order.put(selected.name, order.getOrDefault(selected.name, 0) + qauntity);
    }

    int discount = 0;
    if (total >= 20000) {
      discount = 1000;
      total -= discount;
    }

    System.out.println("결제 중...");
    Thread.sleep(3000);

    System.out.println("\n=== 영수증 ===");
    for (String item : order.keySet()) {
      System.out.println(item + " x " + order.get(item));
    }
    System.out.println("추가 할인: " + discount + "원");
    System.out.println("총 결제 금액: " + total + "원");
  }
}
