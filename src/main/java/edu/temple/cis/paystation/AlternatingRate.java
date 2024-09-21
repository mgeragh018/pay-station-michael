package edu.temple.cis.paystation;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class AlternatingRate implements RateStrategy {
    private final RateStrategy weekdayStrategy = new ProgressiveRate();
    private final RateStrategy weekendStrategy = new LinearOneRate();

    @Override
    public int calculateTimeBought(int insertedSoFar) {
        DayOfWeek day = LocalDate.now().getDayOfWeek();
        if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
            return weekendStrategy.calculateTimeBought(insertedSoFar);
        } else {
            return weekdayStrategy.calculateTimeBought(insertedSoFar);
        }
    }
}
