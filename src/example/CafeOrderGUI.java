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
