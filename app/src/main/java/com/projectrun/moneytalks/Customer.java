package com.projectrun.moneytalks;

import android.util.Log;

import java.math.BigInteger;
import java.text.DecimalFormat;

/**
 * Created by M on 12/03/2016.
 */
public class Customer {
    private double totalAmount,charityTotal,clothingTotal,creditRepaymentTotal,debtTotal,entertainmentTotal, foodTotal,gasTotal,healthCareTotal,housingTotal,insuranceTotal,mobileTotal,salaryTotal,savingsTotal,transportTotal,waterTotal;
    private BigInteger customerNumber;
    private double charityPercentage,clothingPercentage,creditPercentage,debtPercentage,entertainmentPercentage, foodPercentage,gasPercentage,healthCarePercentage,housingPercentage,insurancePercentage,mobilePercentage,salaryPercentage,savingsPercentage,transportPercentage,waterPercentage;
    private double socialNumber;
    private double foodLoverNumber;
    private double materialisticNumber;
    private double adventurousNumber;



    private double kindNumber;
    private double diff;

    private Double[] personalities;
    private String[] personalityTexts;


    public Customer(BigInteger number){
        customerNumber = number;
        initialiseTotals();
    }
    // sets all values to 0 so they can be incremented later.
    public void initialiseTotals(){
        totalAmount = 0;
        charityTotal = 0;
        clothingTotal = 0;
        creditRepaymentTotal = 0;
        debtTotal = 0;
        entertainmentTotal = 0;
        foodTotal = 0;
        gasTotal = 0;
        healthCareTotal = 0;
        housingTotal = 0;
        insuranceTotal = 0;
        mobileTotal = 0;
        salaryTotal = 0;
        savingsTotal = 0;
        transportTotal = 0;
        waterTotal = 0;
        socialNumber = 0;
        foodLoverNumber = 0;
        materialisticNumber = 0;
        kindNumber = 0;
        adventurousNumber = 0;
    }

    // checks the tags and increments the appropriate total money.
    public void calculateTotals(double tableAmount, String tag){
        double positive = tableAmount * -1;

        switch(tag){
            case "Charity": charityTotal += positive; totalAmount += positive;
                break;
            case "Clothing": clothingTotal += positive; totalAmount += positive;
                break;
            /*case "Credit Repayment": creditRepaymentTotal += positive;
                break;
            case "Debt": debtTotal += positive;
                break;*/
            case "Entertainment": entertainmentTotal += positive;totalAmount += positive;
                break;
            case "Food": foodTotal += positive;totalAmount += positive;
                break;
            /*case "Gas": gasTotal += positive;
                break;
            case "Health Care": healthCareTotal += positive;
                break;
            case "Housing": housingTotal += positive;
                break;
            case "Insurance": insuranceTotal += positive;
                break; */
            case "Mobile": mobileTotal += positive;totalAmount += positive;
                break;
            /* case "Salary": salaryTotal += positive;
                break; */
            case "Savings": savingsTotal += positive;totalAmount += positive;
                break;
            case "Transport": transportTotal += positive;totalAmount += positive;
                break;
            /* case "Water": waterTotal += positive;
                break; */
            default: break;
        }
    }

    //calculates the percentage spent for each tag
    public void calculatePercentages(){
        charityPercentage = (charityTotal/totalAmount) * 100;
        clothingPercentage = (clothingTotal/totalAmount) * 100;
        /*creditPercentage = (creditRepaymentTotal/totalAmount) * 100;
        debtPercentage = (debtTotal/totalAmount) * 100;*/
        entertainmentPercentage = (entertainmentTotal/totalAmount) * 100;
        foodPercentage = (foodTotal/totalAmount) * 100;
        /*gasPercentage = (gasTotal/totalAmount) * 100;
        healthCarePercentage = (healthCareTotal/totalAmount) * 100;
        housingPercentage = (housingTotal/totalAmount) * 100;
        insurancePercentage = (insuranceTotal/totalAmount) * 100;*/
        mobilePercentage = (mobileTotal/totalAmount) * 100;
        salaryPercentage = (salaryTotal/totalAmount) * 100;
        /*savingsPercentage = (savingsTotal/totalAmount) * 100;*/
        transportPercentage = (transportTotal/totalAmount) * 100;
        /*waterPercentage = (waterTotal/totalAmount) * 100;*/
    }
    //testing method, prints things
    public void printPercentages(){
        Log.d("Charity: ",""+charityPercentage);
    }
    //Calculates the personality percentages using appropriate values from each tag.
    public void setPersonalityNumbers(){
        socialNumber = 0.2*charityPercentage + 0.2*clothingPercentage + 0.6*entertainmentPercentage + 0.5*mobilePercentage + 0.3*transportPercentage;
        kindNumber = 0.8*charityPercentage + 0.6*savingsPercentage;
        materialisticNumber = 0.7*clothingPercentage + 0.1*foodPercentage+0.5*mobilePercentage+0.1*transportPercentage;
        foodLoverNumber = 0.15*entertainmentPercentage + 0.85*foodPercentage;
        adventurousNumber = 0.1*clothingPercentage + 0.25*entertainmentPercentage + 0.05*foodPercentage + 0.4*savingsPercentage + 0.6*transportPercentage;
        diff = (100-(socialNumber+kindNumber+materialisticNumber+foodLoverNumber+adventurousNumber))/5;
        socialNumber += diff;
        kindNumber += diff;
        materialisticNumber += diff;
        foodLoverNumber += diff;
        adventurousNumber += diff;
        System.out.println(socialNumber + kindNumber + materialisticNumber + foodLoverNumber + adventurousNumber);


    }

    public BigInteger getCustomerNumber () {
       return customerNumber;
    }

    public double getKindNumber() {
        return kindNumber;
    }

    public double getAdventurousNumber() {
        return adventurousNumber;
    }

    public double getMaterialisticNumber() {
        return materialisticNumber;
    }

    public double getFoodLoverNumber() {
        return foodLoverNumber;
    }

    public double getSocialNumber() {
        return socialNumber;
    }

    public String[] getPersonalityOrder(){
        personalities = new Double[]{kindNumber, adventurousNumber, materialisticNumber, foodLoverNumber, socialNumber};
        personalityTexts = new String[]{"Kind", "Adventurous", "Materialistic", "Food Lover", "Social"};
        for(int i = 0;i < 4;i++)
        for(int j = 1;j < 5;j++){
            if(personalities[j] > personalities[i]){
                Double temp = personalities[j];
                personalities[j] = personalities[i];
                personalities[i] = temp;
                String tempS = personalityTexts[j];
                personalityTexts[j] = personalityTexts[i];
                personalityTexts[i] = tempS;
            }
        }
        return personalityTexts;
    }
    public Double[] getPersonalityPercentages(){
        DecimalFormat df = new DecimalFormat("#.##");
        for(int i = 0; i<personalities.length;i++){
            personalities[i] = Double.valueOf(df.format(personalities[i]));
        }
        return personalities;
    }
}
