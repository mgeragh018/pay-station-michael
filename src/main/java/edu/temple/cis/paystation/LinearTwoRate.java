package edu.temple.cis.paystation;

public class LinearTwoRate implements RateStrategy{
    @Override
    public int calculateTimeBought(int insertedSoFar) {
        return (insertedSoFar / 5);
    }
}
