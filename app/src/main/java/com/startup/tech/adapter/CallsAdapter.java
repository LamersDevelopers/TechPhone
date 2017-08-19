package com.startup.tech.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.startup.tech.Object.CallObject;
import com.startup.tech.R;

import java.util.ArrayList;

/**
 * Created by Speed on 05.08.2017.
 */

public class CallsAdapter extends BaseAdapter {
    Context context;
    ArrayList<CallObject> calls;
    LayoutInflater inflanter;

    public CallsAdapter(Context ctx, ArrayList<CallObject> ar) {

        context = ctx;
        calls = ar;
        inflanter = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return calls.size();
    }

    @Override
    public Object getItem(int position) {
        return calls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder vh = new ViewHolder();
        if (view == null) {
            view = inflanter.inflate(R.layout.call_item, parent, false);
        }

        CallObject call = calls.get(position);
        vh.number = (TextView) view.findViewById(R.id.numberCall);
        vh.customerName = (TextView) view.findViewById(R.id.client);
        vh.address = (TextView) view.findViewById(R.id.address);

        vh.number.setText(call.getCode());
        vh.customerName.setText(call.getCustName());
        vh.address.setText(call.getAddress());
        return view;
    }

    class ViewHolder {
        TextView number, customerName, address;
    }
}
