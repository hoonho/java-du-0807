package report;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.DayOfWeek;

public class DateTimeExample {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String formattedDateTime = now.format(formatter);

        DayOfWeek dayOfWeek = now.getDayOfWeek();
        String koreanDay = getKoreanDayOfWeek(dayOfWeek);

        System.out.println("현재 날짜와 시간: " + formattedDateTime);
        System.out.println("오늘은 " + koreanDay + "입니다.");
    }

    private static String getKoreanDayOfWeek(DayOfWeek day) {
        switch (day) {
            case MONDAY: return "월요일";
            case TUESDAY: return "화요일";
            case WEDNESDAY: return "수요일";
            case THURSDAY: return "목요일";
            case FRIDAY: return "금요일";
            case SATURDAY: return "토요일";
            case SUNDAY: return "일요일";
            default: return "";
        }
    }
}

