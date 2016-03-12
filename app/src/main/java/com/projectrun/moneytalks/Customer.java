package com.projectrun.moneytalks;

import android.util.Log;

import java.math.BigInteger;

/**
 * Created by M on 12/03/2016.
 */
public class Customer {
    private double totalAmount,charityTotal,clothingTotal,creditRepaymentTotal,debtTotal,entertainmentTotal, foodTotal,gasTotal,healthCareTotal,housingTotal,insuranceTotal,mobileTotal,salaryTotal,savingsTotal,transportTotal,waterTotal;
    private BigInteger customerNumber;
    private double charityPercentage,clothingPercentage,creditPercentage,debtPercentage,entertainmentPercentage, foodPercentage,gasPercentage,healthCarePercentage,housingPercentage,insurancePercentage,mobilePercentage,salaryPercentage,savingsPercentage,transportPercentage,waterPercentage;


    public Customer(BigInteger number){
        customerNumber = number;
        initialiseTotals();
    }
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
    }

    public void calculateTotals(double tableAmount, String tag){
        double positive = tableAmount * -1;
        totalAmount += positive;
        switch(tag){
            case "Charity": charityTotal += positive;
                break;
            case "Clothing": clothingTotal += positive;
                break;
            case "Credit Repayment": creditRepaymentTotal += positive;
                break;
            case "Debt": debtTotal += positive;
                break;
            case "Entertainment": entertainmentTotal += positive;
                break;
            case "Food": foodTotal += positive;
                break;
            case "Gas": gasTotal += positive;
                break;
            case "Health Care": healthCareTotal += positive;
                break;
            case "Housing": housingTotal += positive;
                break;
            case "Insurance": insuranceTotal += positive;
                break;
            case "Mobile": mobileTotal += positive;
                break;
            case "Salary": salaryTotal += positive;
                break;
            case "Savings": savingsTotal += positive;
                break;
            case "Transport": transportTotal += positive;
                break;
            case "Water": waterTotal += positive;
                break;
            default: break;
        }
    }

    public void calculatePercentages(){
        charityPercentage = (charityTotal/totalAmount) * 100;
        clothingPercentage = (clothingTotal/totalAmount) * 100;
        creditPercentage = (creditRepaymentTotal/totalAmount) * 100;
        debtPercentage = (debtTotal/totalAmount) * 100;
        entertainmentPercentage = (entertainmentTotal/totalAmount) * 100;
        foodPercentage = (foodTotal/totalAmount) * 100;
        gasPercentage = (gasTotal/totalAmount) * 100;
        healthCarePercentage = (healthCareTotal/totalAmount) * 100;
        housingPercentage = (housingTotal/totalAmount) * 100;
        insurancePercentage = (insuranceTotal/totalAmount) * 100;
        mobilePercentage = (mobileTotal/totalAmount) * 100;
        salaryPercentage = (salaryTotal/totalAmount) * 100;
        savingsPercentage = (savingsTotal/totalAmount) * 100;
        transportPercentage = (transportTotal/totalAmount) * 100;
        waterPercentage = (waterTotal/totalAmount) * 100;
    }
    public void printPercentages(){
        Log.d("Charity: ",""+charityPercentage);
    }
}
