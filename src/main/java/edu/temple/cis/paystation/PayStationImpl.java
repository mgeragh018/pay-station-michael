package edu.temple.cis.paystation;
import java.util.*;

/**
 * Implementation of the pay station.
 *
 * Responsibilities:
 *
 * 1) Accept payment; 
 * 2) Calculate parking time based on payment; 
 * 3) Know earning, parking time bought; 
 * 4) Issue receipts; 
 * 5) Handle buy and cancel events.
 *
 * This source code is from the book "Flexible, Reliable Software: Using
 * Patterns and Agile Development" published 2010 by CRC Press. Author: Henrik B
 * Christensen Computer Science Department Aarhus University
 *
 * This source code is provided WITHOUT ANY WARRANTY either expressed or
 * implied. You may study, use, modify, and distribute it for non-commercial
 * purposes. For any commercial use, see http://www.baerbak.com/
 */
public class PayStationImpl implements PayStation {
    
    private int insertedSoFar, timeBought, totalMoney;
    private Map<Integer, Integer> coinMap;
    private RateStrategy rateStrategy;

    // Constructor initializes instance variables
    public PayStationImpl(){
        insertedSoFar = timeBought = totalMoney = 0;
        coinMap = new HashMap<>();
        rateStrategy = new LinearOneRate();//Default rate strategy
    }

    private void setRateStrategy(RateStrategy rateStrategy) {
        this.rateStrategy = rateStrategy;
    }

    public void changeRateStrategy(int option) {
        switch (option){
            case 1:
                setRateStrategy(new LinearOneRate());
                System.out.println("Rate Strategy changed to LinearOneRate");
                break;
            case 2:
                setRateStrategy(new ProgressiveRate());
                System.out.println("Rate Strategy changed to ProgressiveRate");
                break;
            case 3:
                setRateStrategy(new AlternatingRate());
                System.out.println("Rate Strategy changed to AlternatingRate");
                break;
            case 4:
                setRateStrategy(new LinearTwoRate());
                System.out.println("Rate Strategy changed to LinearTwoRate");
                break;
            case 5:
                setRateStrategy(new AlternatingTwoRate());
                System.out.println("Rate Strategy changed to AlternatingTwoRate");
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }
    
    @Override
    public void addPayment(int coinValue)
            throws IllegalCoinException {

        switch (coinValue) {
            case 5:
            case 10:
            case 25:
                break;
            default:
                throw new IllegalCoinException("Invalid coin: " + coinValue);
        }

        /*
         * getOrDefault checks if a given key is present in a map
         * @returns the value if it exists, or the 'defaultValue' if it does not
         * add 1 to whatever the result of getOrDefault is and place that value in the map
         */
        coinMap.put(coinValue, coinMap.getOrDefault(coinValue, 0) + 1);

        insertedSoFar += coinValue;
        timeBought = rateStrategy.calculateTimeBought(insertedSoFar);
    }

    @Override
    public int readDisplay() {
        return timeBought;
    }

    @Override
    public Receipt buy() {
        Receipt r = new ReceiptImpl(timeBought);
        totalMoney += insertedSoFar;
        reset();
        return r;
    }

    @Override
    public Map<Integer, Integer> cancel() 
    {
        Map<Integer, Integer> tempMap = coinMap;
        coinMap = new HashMap<>();
        reset();
        return tempMap;
    }
    
    private void reset() {
        timeBought = insertedSoFar = 0;
        coinMap.clear();
    }
    
    @Override
    public int empty()
    {
        int temp = totalMoney;
        totalMoney = 0;
        return temp;
    }
}
