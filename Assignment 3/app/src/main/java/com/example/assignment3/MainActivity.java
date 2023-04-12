package com.example.assignment3;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private EditText editText;
    private ListView listView;
    private ArrayList<String> items = new ArrayList<>();
    private ArrayAdapter<String> aa;
    int itemPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        editText = (EditText) findViewById(R.id.EditText);
        listView = (ListView) findViewById(R.id.ListView);
        listView.setOnItemClickListener(this);

        aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(aa);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View v, int position, long id) {
        itemPosition = position;
        String listItem = items.get(position);
        editText.setText(listItem);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected (MenuItem item) {
        String listItem;

        switch (item.getItemId()) {
            case R.id.add:
                listItem = editText.getText().toString();
                if (listItem.length() > 0) {
                    items.add(listItem);
                    aa.notifyDataSetChanged();
                    editText.setText("");
                }
                return true;
            case R.id.delete:
                items.remove(itemPosition);
                aa.notifyDataSetChanged();
                editText.setText("");
                return true;
            case R.id.update:
                listItem = editText.getText().toString();
                if (listItem.length() != 0) {
                    items.set(itemPosition, listItem);
                    aa.notifyDataSetChanged();
                    editText.setText("");
                }
                return true;
            case R.id.save:
                return true;
            case R.id.exit:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}