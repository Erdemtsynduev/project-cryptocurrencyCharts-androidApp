package com.erdemtsynduev.profitcoin.screen.search.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.erdemtsynduev.profitcoin.R;
import com.erdemtsynduev.profitcoin.network.model.listallcryptocurrency.Datum;

import java.util.ArrayList;
import java.util.List;

public class SearchListAdapter extends BaseAdapter implements Filterable {

    public Context context;
    public List<Datum> employeeArrayList;
    public List<Datum> orig;

    public SearchListAdapter(Context context, List<Datum> employeeArrayList) {
        super();
        this.context = context;
        this.employeeArrayList = employeeArrayList;
    }

    public class EmployeeHolder {
        TextView name;
        TextView age;
    }

    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final ArrayList<Datum> results = new ArrayList<>();
                if (orig == null)
                    orig = employeeArrayList;
                if (constraint != null) {
                    if (orig != null && orig.size() > 0) {
                        for (final Datum g : orig) {
                            if (g.getName().toLowerCase()
                                    .contains(constraint.toString()))
                                results.add(g);
                        }
                    }
                    oReturn.values = results;
                }
                return oReturn;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,
                                          FilterResults results) {
                employeeArrayList = (ArrayList<Datum>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return employeeArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return employeeArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EmployeeHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_search_list, parent, false);
            holder = new EmployeeHolder();
            holder.name = convertView.findViewById(R.id.txtName);
            holder.age = convertView.findViewById(R.id.txtSymbol);
            convertView.setTag(holder);
        } else {
            holder = (EmployeeHolder) convertView.getTag();
        }

        holder.name.setText(employeeArrayList.get(position).getName());
        holder.age.setText(String.valueOf(employeeArrayList.get(position).getSymbol()));

        return convertView;
    }
}