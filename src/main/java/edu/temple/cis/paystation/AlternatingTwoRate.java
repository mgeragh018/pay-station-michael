package edu.temple.cis.paystation;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class AlternatingTwoRate implements RateStrategy{

    private final RateStrategy weekdayStrategy = new LinearOneRate();

    @Override
    public int calculateTimeBought(int insertedSoFar) {

        // Get the current day of the week
        DayOfWeek day = LocalDate.now().getDayOfWeek();
        if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
            return 0; //free on weekends
        } else {
            return weekdayStrategy.calculateTimeBought(insertedSoFar);
        }
    }
}
