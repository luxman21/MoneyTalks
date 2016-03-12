package com.projectrun.moneytalks;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
    private ArrayAdapter<BigInteger> adapter;
    public ArrayList<BigInteger> customerReference;
    private Customer currentCustomer;
    public ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentCustomerNumber = new BigInteger("0");
        customers = new ArrayList<Customer>();
        readCsv(this);
        iterateList();

        customerReference = new ArrayList<>();
        getCustomerReference();
        listView = (ListView)findViewById(R.id.left_drawer);
        adapter = new ArrayAdapter<BigInteger>(this, R.layout.list_row , R.id.customerReferenceList , customerReference);
        listView.setAdapter(adapter);


    }

    // method to add customer reference into the array
    public void getCustomerReference () {
        if(customers.size() != 0 ) {
            for ( int i = 0 ; i < customers.size() ; i++ ) {

                customerReference.add(customers.get(i).getCustomerNumber());
            }
        }
    }


    //takes all data from file
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
        //loops through every line of data
        for(int i = 0; i < lines.size(); i++){
            strings = lines.get(i);
            customerNumberData = new BigInteger(strings[0]);
            amount = Double.parseDouble(strings[2]);
            //checks if we should continue with the same customer or change
            if(customerNumberData.compareTo(currentCustomerNumber)!=0){
                Log.d("Code starts", "pls");
                //checks if it's the first customer or not, if not calculate percentages appropriately, then calculate personality percentages from this.
                if(currentCustomer != null){
                currentCustomer.calculatePercentages();
                currentCustomer.printPercentages();
                currentCustomer.setPersonalityNumbers();
                }
                //changes customer number to check, we've moved on to the next customer.
                currentCustomerNumber = customerNumberData;
                //makes new customer with new customer number.
                currentCustomer = new Customer(currentCustomerNumber);
                //adds customer to the arraylist
                customers.add(currentCustomer);
            }
            //checks money transferred is negative so income is not considered
            if(amount < 0){
                currentCustomer.calculateTotals(amount, strings[3]);

            }
        }
        //when iteration is done, calculate for last customer.
        currentCustomer.calculatePercentages();
        currentCustomer.printPercentages();
        currentCustomer.setPersonalityNumbers();
    }


}
