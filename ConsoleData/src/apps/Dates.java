package apps;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

/**
 * Tato trida reprezentuje pouzivane prikazy pro datumy
 * @author @version @param @return @deprecated @since @throws @exception @see (@link)
 */
public class Dates {
    public static void main(String[] args){
        localDate();
        localTime();
        localDateTime();
    }

    private static void localDate(){
        LocalDate xmas= LocalDate.of(2021,12,24);
        LocalDate xmas2=LocalDate.parse("2021-12-24");

        LocalDate tomorrow=LocalDate.now().plusDays(1);
        LocalDate anotherDate = LocalDate.now().minus(1, ChronoUnit.MONTHS);

        DayOfWeek dayOfWeek = LocalDate.parse("2021-29-12").getDayOfWeek();
        System.out.println(dayOfWeek);

        boolean isLeapYear = LocalDate.now().isLeapYear();

        boolean isBefore = LocalDate.parse("2021-07-12").isBefore(LocalDate.parse("2021-08-12"));
        boolean isAfter = LocalDate.parse("2021-07-12").isAfter(LocalDate.parse("2021-08-12"));

        LocalDate firstDayOfMonth = LocalDate.parse("2021-12-24").with(TemporalAdjusters.firstDayOfMonth());
    }
    private static void localTime(){
        LocalTime now = LocalTime.now();
        LocalTime sixThirty= LocalTime.parse("06:30");
        LocalTime sixThirty2= LocalTime.of(6,30);

        LocalTime sevenThirty= LocalTime.parse("06:30").plus(1,ChronoUnit.HOURS);

        int six=LocalTime.parse("06:30").getHour();
        Boolean isBefore=LocalTime.parse("06:30").isBefore(LocalTime.parse("07:30"));

        LocalTime maxTime=LocalTime.MAX;
    }
    private static void localDateTime(){
        LocalDateTime.now();
        LocalDateTime.of(2015, Month.JANUARY,20,6,30);
        LocalDateTime localDateTime = LocalDateTime.parse("2021-10-20T06:30:00");
        localDateTime=localDateTime.plusHours(1);
        localDateTime=localDateTime.minusHours(2);
        System.out.println(localDateTime);
    }
}
