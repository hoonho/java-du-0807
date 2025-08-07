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
