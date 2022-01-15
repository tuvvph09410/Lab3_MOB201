package com.example.lab3_mob201.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lab3_mob201.Entity.Contacts;
import com.example.lab3_mob201.R;

import java.util.List;

public class ItemListViewAdapter extends BaseAdapter {
    private Context context;
    private List<Contacts> contactsList;

    public ItemListViewAdapter(Context context, List<Contacts> contactsList) {
        this.context = context;
        this.contactsList = contactsList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return this.contactsList.size();
    }

    @Override
    public Object getItem(int i) {
        return this.contactsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        LayoutInflater inflater;
        if (view == null) {
            viewHolder = new ViewHolder();
            inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_listview_contacs, null);
            viewHolder.tvId = view.findViewById(R.id.tv_id);
            viewHolder.tvName = view.findViewById(R.id.tv_name);
            viewHolder.tvNumber = view.findViewById(R.id.tv_number);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Contacts contacts = (Contacts) getItem(i);
        viewHolder.tvId.setText(contacts.getId());
        viewHolder.tvName.setText(contacts.getName());
        viewHolder.tvNumber.setText(contacts.getNumber());

        return view;
    }

    public class ViewHolder {
        TextView tvId, tvName, tvNumber;
    }
}
