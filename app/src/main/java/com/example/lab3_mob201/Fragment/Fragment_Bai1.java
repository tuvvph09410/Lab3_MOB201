package com.example.lab3_mob201.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.lab3_mob201.Adapter.ItemListViewAdapter;
import com.example.lab3_mob201.Entity.Contacts;
import com.example.lab3_mob201.R;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;


public class Fragment_Bai1 extends Fragment {
    private ListView lvContacts;
    private ItemListViewAdapter itemListViewAdapter;
    private List<Contacts> contactsList;

    public Fragment_Bai1() {
        // Required empty public constructor
    }


    public static Fragment_Bai1 newInstance(String param1, String param2) {
        Fragment_Bai1 fragment = new Fragment_Bai1();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bai1, container, false);
        this.initViewByID(view);
        this.getContacts();
        return view;
    }

    private void getContacts() {
        // kiểm tra và xin quyền
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_CONTACTS}, 999);
        } else {
            // read data
            Uri uri = Uri.parse("content://contacts/people"); // lấy mảng có trong danh bạ
            this.contactsList = new ArrayList<>();
            Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);// lấy con trỏ
            if (cursor.getCount() > 0)//kiểm tra xem danh bạ có bản ghi hay không
            {
                cursor.moveToFirst(); // di chuyển con trỏ đến vị tri ban đầu
                while (!cursor.isAfterLast())//nếu không phải bản ghi cuối cùng
                {
                    // lấy id
                    String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    Log.d("idContacts", id);
                    // lấy name
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                    // lấy số điện thoại
                    Cursor cursorNumber = getActivity().getContentResolver().query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=? AND " +
                                    ContactsContract.CommonDataKinds.Phone.TYPE + "=" +
                                    ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE,
                            new String[]{id}, null);
                    String stringNumber = null;
                    if (cursorNumber.moveToFirst()) {
                        stringNumber = cursorNumber.getString(cursorNumber.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    }
                    cursorNumber.close();
                    int numberInt = Integer.parseInt(stringNumber);
                    Contacts contacts = new Contacts(id, name, numberInt);
                    this.contactsList.add(contacts);
                    cursor.moveToNext(); // di chuyển đến bản ghi tiếp theo
                }
                cursor.close();
                this.itemListViewAdapter = new ItemListViewAdapter(getActivity(), this.contactsList);
                this.lvContacts.setAdapter(itemListViewAdapter);
            }

        }
    }

    private void initViewByID(View view) {
        this.lvContacts = view.findViewById(R.id.lv_contacts);
    }
}