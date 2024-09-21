package edu.temple.cis.paystation;

public class LinearOneRate implements RateStrategy{
    @Override
    public int calculateTimeBought(int insertedSoFar) {
        return (insertedSoFar / 5) * 2;
    }

}
