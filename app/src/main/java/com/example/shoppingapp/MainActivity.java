package com.example.shoppingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner cate,pName;
    TextView price,total;
    EditText qty;
    ImageView prodImage;
    Button add,order;
    static double totalAmount=0;
    String category[]={"Electronic","Groceries","Phones","Purse","Clothes"};
    ArrayList<Product> prodList=new ArrayList<>();
    ArrayList<Product>tempList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fillData();

        cate=findViewById(R.id.spCat);
        pName=findViewById(R.id.spProduct);
        price=findViewById(R.id.txvPrice);
        total=findViewById(R.id.txvTotal);
        qty=findViewById(R.id.txvQty);
        prodImage=findViewById(R.id.imgProd);
        add=findViewById(R.id.btnAdd);
        order=findViewById(R.id.btnOrder);

        //create array adapter using the makes array
        ArrayAdapter aa=new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,category);
        cate.setAdapter(aa);

        cate.setOnItemSelectedListener(new SpinnerEvents());
        pName.setOnItemSelectedListener(new SpinnerEvents());
        add.setOnClickListener(new ButtonEvents());
        order.setOnClickListener(new ButtonEvents());
    }
    //a class for spinner implementation
    private class SpinnerEvents implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if(parent.getId()==R.id.spCat)
            {
                String []names=getProductName(category[position]);
                ArrayAdapter aa2=new ArrayAdapter(MainActivity.this,R.layout.support_simple_spinner_dropdown_item,names);
                pName.setAdapter(aa2);
            }else{
                price.setText(String.valueOf(tempList.get(position).getPrice()));
                prodImage.setImageResource(tempList.get(position).getImage());

            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
    private class ButtonEvents implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnAdd:
                    int p1=tempList.get(pName.getSelectedItemPosition()).getQty();
                    int currentQty = 0;
                    double amount=0;
                    double currentPrice = Double.parseDouble(price.getText().toString());

                    if (qty.getText().toString().isEmpty()) {
                        Toast.makeText(getBaseContext(), "Please enter the quantity", Toast.LENGTH_LONG).show();

                    } else {
                         currentQty=Integer.parseInt(qty.getText().toString());
                         if (currentQty > p1) {
                            Toast.makeText(getBaseContext(), "Sorry,Available quantity is:" + p1, Toast.LENGTH_SHORT).show();
                        }else{
                             String name=tempList.get(pName.getSelectedItemPosition()).getProdName();
                            p1 = Integer.parseInt(qty.getText().toString());
                            amount = currentPrice * p1 * 1.13;
                            Toast.makeText(getBaseContext(), "Item is added into cart is: "+name+"\nQuantity is: "+p1, Toast.LENGTH_LONG).show();
                        }
                    }

                    totalAmount+=amount;
                    total.setText(String.format("%.2f",totalAmount));
                    break;

                case R.id.btnOrder:
                    Toast.makeText(getBaseContext(),"Thank you...!, your total is: "+String.format("%.2f",totalAmount),Toast.LENGTH_LONG).show();
                    break;

            }
        }
    }

    public void fillData(){
        prodList.add(new Product(category[0],"Television",20000,R.mipmap.tv,10));
        prodList.add(new Product(category[0],"Refrigerator",35000,R.mipmap.fridge,8));
        prodList.add(new Product(category[0],"Washing machine",28000,R.mipmap.wm,10));
        prodList.add(new Product(category[1],"Rice",200,R.mipmap.rice,15));
        prodList.add(new Product(category[1],"Flour",500,R.mipmap.flour,25));
        prodList.add(new Product(category[1],"Oil",2500,R.mipmap.oil,5));
        prodList.add(new Product(category[2],"iPhone 12",95000,R.mipmap.iphone12,12));
        prodList.add(new Product(category[2],"galaxy A 51",55000,R.mipmap.a51,8));
        prodList.add(new Product(category[2],"Vivo Y 91",15000,R.mipmap.y91,2));
        prodList.add(new Product(category[3],"Lavie",4500,R.mipmap.lavie,22));
        prodList.add(new Product(category[3],"Lacostte",9500,R.mipmap.lecostee,9));
        prodList.add(new Product(category[3],"Allen Solly",4000,R.mipmap.allensolly,20));
        prodList.add(new Product(category[4],"Skirt",1000,R.mipmap.skirt,18));
        prodList.add(new Product(category[4],"Denim",1500,R.mipmap.jeans,10));
        prodList.add(new Product(category[4],"Top",2000,R.mipmap.top,8));
        prodList.add(new Product(category[4],"Jacket",8000,R.mipmap.jacket,11));

    }
    public String []getProductName(String name){
        tempList.clear();
        String prodNames[];
        for(Product pr:prodList)
            if(pr.getCat().equals(name))
                tempList.add(pr);
        prodNames=new String[tempList.size()];
        for(int i=0;i<prodNames.length;i++)
            prodNames[i]=tempList.get(i).getProdName();
        return  prodNames;
    }
}