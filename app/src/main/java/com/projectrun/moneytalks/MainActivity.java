package com.projectrun.moneytalks;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String[]> lines;
    private BigInteger currentCustomerNumber;
    private ArrayList<Customer> customers;
    private Customer currentCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentCustomerNumber = new BigInteger("0");
        customers = new ArrayList<Customer>();
        readCsv(this);
        iterateList();
    }
    public void readCsv(Context context) {
        ArrayList<String[]> questionList = new ArrayList<String[]>();
        AssetManager assetManager = context.getAssets();
        try {
            InputStream csvStream = assetManager.open("NationwideData.csv");
            InputStreamReader csvStreamReader = new InputStreamReader(csvStream);
            CSVReader csvReader = new CSVReader(csvStreamReader);
            String[] line;

            // throw away the header
            csvReader.readNext();

            while ((line = csvReader.readNext()) != null) {
                questionList.add(line);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        lines = questionList;
    }
    public void iterateList(){
        //iterates through list
        String[] strings;
        double amount;
        BigInteger customerNumberData;
        for(int i = 0; i < lines.size(); i++){
            strings = lines.get(i);
            customerNumberData = new BigInteger(strings[0]);
            amount = Double.parseDouble(strings[2]);
            if(customerNumberData.compareTo(currentCustomerNumber)!=0){
                Log.d("Code starts", "pls");
                if(currentCustomer != null){
                currentCustomer.calculatePercentages();
                currentCustomer.printPercentages();
                }
                currentCustomerNumber = customerNumberData;
                currentCustomer = new Customer(currentCustomerNumber);
                customers.add(currentCustomer);
            }

            if(amount < 0){
                currentCustomer.calculateTotals(amount, strings[3]);

            }
        }
        currentCustomer.calculatePercentages();
        currentCustomer.printPercentages();
    }


}
