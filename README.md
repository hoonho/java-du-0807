# 14일차

<br/>

## 교통 카드 시스템

```java
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
```

<br/><br/>

## 도서 대출 시스템 시뮬레이션
```java
package example;

import java.time.LocalDate;
import java.util.*;

/*
 * 도서관 대출 시스템 시뮬레이션
 * 
 * 1. 도서 목록 5개로 구성, 각각 대출 가능 여부를 가짐
 * 2. 사용자는 최대 3권까지 대출 가능
 * 3. 대출 시 오늘 날짜 기준으로 14일 후가 반납일로 지정됨
 * 4. 대출 완료 시 대출 도서 목록과 반납일을 표시
 * 5. 이미 대출 중인 책은 대출 불가 처리
 */

public class LibrarySystem {
  
  static class Book {
    String title;
    boolean isAvailable;
    
    Book(String title) {
      this.title = title;
      this.isAvailable = true;
    }

    public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);

      Book[] books = {
        new Book("해리포터와 마법사의 돌"),
        new Book("어린 왕자"),
        new Book("1984"),
        new Book("자바의 정석"),
        new Book("삼국지")
      };

      List<String> borrowed = new ArrayList<>();

      System.out.println("=== 도서 목록 ===");
      for (int i = 0; i < books.length; i++) {
        System.out.println((i + 1) + ". " + books[i].title +
        (books[i].isAvailable ? " (대출 가능)" : " (대출 중)"));
      }

      while (borrowed.size() < 3) {
        System.out.print("대출할 책 번호 (종료: 0): ");
        int choice = sc.nextInt();

        if (choice == 0) break;

        if (choice < 1 || choice > books.length) continue;

        Book selected = books[choice - 1];

        if (!selected.isAvailable) {
          System.out.println("이미 대출 중인 책입니다.");
          continue;
        }

        borrowed.add(selected.title);
        selected.isAvailable = false;
        System.out.println("[" + selected.title + "] 대출 완료!");
      }

      LocalDate today = LocalDate.now();
      LocalDate dueDate = today.plusDays(14);

      System.out.println("\n=== 대출 내역 ===");
      for (String title : borrowed) {
        System.out.println("- " + title);
      }
      System.out.println("반납일: " + dueDate);
    }
  }
}
```

<br/><br/>

## 카페 주문 시스템 시뮬레이션
```java
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
```

<br/><br/>

## 카페 주문 시스템 GUI
```java
package example;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

/*
 * 카페 주문 GUI 프로그램
 * 
 * 1. AWT GUI로 메뉴 선택, 수량 입력, 주문 버튼 제공
 * 2. 메뉴는 이름/가격/할인 가능 여부로 구성
 * 3. 멤버십 할인: 할인 가능한 메뉴는 10% 할인
 * 4. 총합 20,000원 이상: 추가 1,000원 할인
 * 5. 영수증은 TextArea에 상세히 출력 (메뉴명, 수량, 단가, 소계, 할인 내역 등)
 */

public class CafeOrderGUI extends Frame implements ActionListener {
  
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

  Menu[] menus = {
    new Menu("아메리카노", 4500, true),
    new Menu("카페라떼", 5000, true),
    new Menu("치즈케이크", 6000, false),
    new Menu("샌드위치", 7500, true),
  };

  Checkbox[] menuCheckboxs = new Checkbox[menus.length];
  TextField[] quantityFields = new TextField[menus.length];
  Checkbox membershipCheckbox;
  Button orderButton;
  TextArea receiptArea;

  public CafeOrderGUI() {
    setTitle("카페 주문 시스템");
    setSize(500,500);
    setLayout(new BorderLayout());

    Panel menuPanel = new Panel(new GridLayout(menus.length + 1, 3));
    menuPanel.add(new Label("메뉴"));
    menuPanel.add(new Label("수량"));
    menuPanel.add(new Label("선택"));

    for (int i = 0; i < menus.length; i++) {
      menuCheckboxs[i] = new Checkbox(menus[i].name + " (" + menus[i].price + "원)");
      quantityFields[i] = new TextField("0");
      menuPanel.add(menuCheckboxs[i]);
      menuPanel.add(quantityFields[i]);
      menuPanel.add(new Label(menus[i].discountable ? "할인 가능" : "할인 불가"));
    }

    add(menuPanel, BorderLayout.NORTH);

    Panel optionPanel = new Panel();
    membershipCheckbox = new Checkbox("멤버십 보유 (10% 할인)");
    orderButton = new Button("주문하기");
    orderButton.addActionListener(this);
    optionPanel.add(membershipCheckbox);
    optionPanel.add(orderButton);
    add(optionPanel, BorderLayout.CENTER);

    receiptArea = new TextArea();
    receiptArea.setEditable(false);
    add(receiptArea, BorderLayout.SOUTH);

    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        dispose();
      }
    });

    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    Map<String, Integer> orderMap = new LinkedHashMap<>();
    boolean hasMembership = membershipCheckbox.getState();
    int subTotal = 0;
    StringBuilder receipt = new StringBuilder();
    receipt.append("===== [ 영수증 ] =====\n");

    for (int i = 0; i < menus.length; i++) {
      if (menuCheckboxs[i].getState()) {
        try {
          int qauntity = Integer.parseInt(quantityFields[i].getText().trim());
          if (qauntity <= 0) continue;

          Menu menu = menus[i];
          int unitPrice = menu.price;
          int itemTotal = unitPrice * qauntity;

          if (hasMembership && menu.discountable) {
            itemTotal *= 0.9;
          }

          subTotal += itemTotal;

          orderMap.put(menu.name, qauntity);
          receipt.append(String.format("%-10s x %d개 | 단가 %d원 | 소계: %,d원\n", 
          menu.name, qauntity, unitPrice, itemTotal));

        } catch (NumberFormatException ex) {
          receiptArea.setText("수량을 숫자로 정확히 입력하세요.");
          return;
        }
      }
    }

    if (orderMap.isEmpty()) {
      receiptArea.setText("주문할 항목을 선택해주세요.");
      return;
    }

    int discount = (subTotal >= 20000) ? 1000 : 0;
    int total = subTotal - discount;

    receipt.append("------------------------------------\n");
    receipt.append(String.format("소계: %,d원\n", subTotal));
    receipt.append(String.format("추가 할인: %,d원\n", discount));
    receipt.append(String.format("총 결제 금액: %,d원\n", total));
    receipt.append("====================================");

    receiptArea.setText(receipt.toString());
  }

  public static void main(String[] args) {
    new CafeOrderGUI();
  }
}
```