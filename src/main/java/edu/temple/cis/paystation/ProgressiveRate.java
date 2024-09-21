package edu.temple.cis.paystation;

public class ProgressiveRate implements RateStrategy {

    @Override
    public int calculateTimeBought(int insertedSoFar) {
        int time = 0;

        // Calculate time based on different ranges of inserted money
        if (insertedSoFar < 150) {
            // First rate: 2 minutes per 5 cents
            time = (insertedSoFar * 2) / 5;
        } else if (insertedSoFar < 350) {
            // Second rate: (3/10) minutes per cent for amounts between 150 and 350
            time = (int) ((insertedSoFar - 150) * 0.3) + 60; // Cast to int for whole minutes
        } else {
            // Third rate: 20 minutes plus (1/5) minutes per cent over 350
            time = ((insertedSoFar - 350) / 5) + 20;
        }

        return time;
    }
}

