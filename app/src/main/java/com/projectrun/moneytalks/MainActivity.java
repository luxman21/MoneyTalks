package com.projectrun.moneytalks;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.opencsv.CSVReader;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String[]> lines;
    private BigInteger currentCustomerNumber;
    private ArrayList<Customer> customers;
    private ArrayAdapter<BigInteger> adapter;
    private DrawerLayout drawerLayout;
    public ArrayList<BigInteger> customerReference;
    private Customer currentCustomer;
    public ListView listView;
    private TextView p1Text,p1Percentage,p2Text,p2Percentage,p3Text,p3Percentage,p4Text,p4Percentage,p5Text,p5Percentage;
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentCustomerNumber = new BigInteger("0");
        customers = new ArrayList<Customer>();
        readCsv(this);
        iterateList();
        //setting the scrollview to invisible at the beginning of the activity
        //scrollView = (ScrollView)findViewById(R.id.scrollviewContainer);
       // scrollView.setVisibility(View.INVISIBLE);

        customerReference = new ArrayList<>();
        getCustomerReference();
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        listView = (ListView)findViewById(R.id.left_drawer);
        adapter = new ArrayAdapter<BigInteger>(this, R.layout.list_row , R.id.customerReferenceList , customerReference);
        listView.setAdapter(adapter);
        listView.bringToFront();
        initialiseTextViews();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Customer c = customers.get(position);

                String[] customerPersonalities = c.getPersonalityOrder();
                Double[] customerPersonalityNumbers = c.getPersonalityPercentages();
                TextView[] stringTextViews = {p1Text,p2Text,p3Text,p4Text,p5Text};
                TextView[] percentTextViews = {p1Percentage,p2Percentage,p3Percentage,p4Percentage,p5Percentage};
                for(int i = 0; i < 5; i++){
                    stringTextViews[i].setText(customerPersonalities[i]);
                    percentTextViews[i].setText(String.valueOf(customerPersonalityNumbers[i]+"%"));
                }
            }
        });
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
    public void initialiseTextViews(){
    p1Percentage = (TextView) findViewById(R.id.personalitynumber1);
    p1Text = (TextView) findViewById(R.id.personality1);
    p2Percentage = (TextView) findViewById(R.id.personalitynumber2);
    p2Text = (TextView) findViewById(R.id.personality2);
    p3Percentage = (TextView) findViewById(R.id.personalitynumber3);
    p3Text = (TextView) findViewById(R.id.personality3);
    p4Percentage = (TextView) findViewById(R.id.personalitynumber4);
    p4Text = (TextView) findViewById(R.id.personality4);
    p5Percentage = (TextView) findViewById(R.id.personalitynumber5);
    p5Text = (TextView) findViewById(R.id.personality5);
    }
}