package com.example.assignment3;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private EditText editText;
    private ListView listView;
    private ArrayList<String> items = new ArrayList<>();
    private ArrayAdapter<String> aa;
    int itemPosition = -1;
    private TextToSpeech tts;

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

        File file = new File(getFilesDir(), "list.txt");
        if (file.exists()) {
            try {
                InputStream in = new FileInputStream(file);
                InputStreamReader ins = new InputStreamReader(in);
                BufferedReader reader = new BufferedReader(ins);

                String line;
                while ((line = reader.readLine()) != null) {
                    items.add(line);
                }

                reader.close();
                aa.notifyDataSetChanged();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Error: Could not read list", Toast.LENGTH_LONG).show();
            }
        }

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.US);
                }
            }
        });
        tts.stop();
        tts.shutdown();
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

    private void saveList() {
        try {
            File file = new File(getFilesDir(), "list.txt");
            FileOutputStream fos = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fos);

            for (String item : items) {
                osw.write(item + "\n");
            }
            osw.close();
            fos.close();

            Toast.makeText(this, "List saved successfully!", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error: List saved unsuccessfully.", Toast.LENGTH_LONG).show();
        }
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
                    tts.speak(listItem + " added", TextToSpeech.QUEUE_FLUSH, null);
                }
                return true;
            case R.id.delete:
                String deletedItem = items.get(itemPosition);
                items.remove(itemPosition);
                aa.notifyDataSetChanged();
                editText.setText("");
                tts.speak(deletedItem + " deleted", TextToSpeech.QUEUE_FLUSH, null);
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
                saveList();
                return true;
            case R.id.exit:
                saveList();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}