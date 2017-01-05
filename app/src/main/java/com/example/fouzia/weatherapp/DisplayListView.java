package com.example.fouzia.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.NoCopySpan;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DisplayListView extends AppCompatActivity {

    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    ContactsAdapter contactsAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_listview_layout);
        json_string=getIntent().getExtras().getString("json_data");
        contactsAdapter=new ContactsAdapter(this,R.layout.row_layouts);
        listView=(ListView)findViewById(R.id.listview);
        listView.setAdapter(contactsAdapter);

        try {
            jsonObject=new JSONObject(json_string);
            jsonArray=jsonObject.getJSONArray("contacts");
            int count=0;
            String name,email,address;

            while (count<jsonArray.length()){

                JSONObject jo=jsonArray.getJSONObject(count);
                name=jo.getString("name");
                email=jo.getString("email");
                address=jo.getString("address");

                Contacts contacts=new Contacts(name,email,address);
                contactsAdapter.add(contacts);


                count++;
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
