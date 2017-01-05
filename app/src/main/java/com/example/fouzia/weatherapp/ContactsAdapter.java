package com.example.fouzia.weatherapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Fouzia on 1/5/2017.
 */

public class ContactsAdapter extends ArrayAdapter {

    List list =new ArrayList();


    public ContactsAdapter(Context context, int resource) {
        super(context, resource);
    }


    public void add(Contacts object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        ContactsHolder contactsHolder;

        if(convertView==null)
        {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_layouts, parent, false);

            contactsHolder=new ContactsHolder();
            contactsHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            contactsHolder.tv_email = (TextView) convertView.findViewById(R.id.tv_email);
            contactsHolder.tv_address = (TextView) convertView.findViewById(R.id.tv_address);
            convertView.setTag(contactsHolder);
        }
        else{

            contactsHolder=(ContactsHolder)convertView.getTag();
        }

        Contacts contacts=(Contacts)this.getItem(position);

        contactsHolder.tv_name.setText(contacts.getName());
        contactsHolder.tv_email.setText(contacts.getEmail());
        contactsHolder.tv_address.setText(contacts.getAddress());

        return convertView;

    }




    static class ContactsHolder{

         TextView tv_name;
         TextView tv_email;
         TextView tv_address;


    }
}
