package com.example.assignment5;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.view.KeyEvent;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.webkit.WebView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;

    // Locations of colleges
    private final LatLng[] COLLEGE_LOCATIONS = {
            new LatLng(37.776512, -122.450638), // University of San Francisco
            new LatLng(37.721897, -122.478210), // San Francisco State University
            new LatLng(37.726650, -122.449959), // City College of San Francisco
            new LatLng(37.722672, -122.412521)  // University of California, San Francisco
    };

    // Titles and snippets of the colleges
    private final String[] COLLEGE_TITLES = {
            "University of San Francisco",
            "San Francisco State University",
            "City College of San Francisco",
            "University of California, San Francisco"
    };

    private final String[] COLLEGE_SNIPPETS = {
            "Private university",
            "Public university",
            "Community college",
            "Medical school"
    };

    private static final float ZOOM_LEVEL = 12.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapTab);
        mapFragment.getMapAsync(this);

        TabHost tabs = (TabHost) findViewById(R.id.tabhost);
        tabs.setup();

        TabHost.TabSpec spec;

        // Initialize a TabSpec for mapTab and add it to the TabHost
        spec = tabs.newTabSpec("tag1");    //create new tab specification
        spec.setContent(R.id.mapTab);    //add tab view content
        spec.setIndicator("Map");    //put text on tab
        tabs.addTab(spec);             //put tab in TabHost container

        // Initialize a TabSpec for webTab and add it to the TabHost
        spec = tabs.newTabSpec("tag2");        //create new tab specification
        spec.setContent(R.id.webTab);            //add view tab content
        spec.setIndicator("Web");
        tabs.addTab(spec);                    //put tab in TabHost container

        Button button = (Button) findViewById(R.id.btnSearch);
        EditText text = (EditText) findViewById(R.id.editURL);
        WebView webView = (WebView) findViewById(R.id.web_view);

        //intercept URL loading and load in widget
        webView.setWebViewClient(new WebViewClient());

        //set listeners for web tab
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.loadUrl(text.getText().toString());
            }
        });

        button.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == KeyEvent.KEYCODE_ENTER) {
                    webView.loadUrl(text.getText().toString());
                    return true;
                }
                return false;
            }
        });

        // Initialize a TabSpec for tab3 and add it to the TabHost
        spec = tabs.newTabSpec("tag3");        //create new tab specification
        spec.setContent(R.id.infoTab);            //add tab view content
        spec.setIndicator("Info");            //put text on tab
        tabs.addTab(spec);                    //put tab in TabHost container

        Spinner resources = (Spinner) findViewById(R.id.resources);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.student_resources, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        resources.setAdapter(adapter);

        resources.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        tabs.setCurrentTab(1);
                        webView.getSettings().setJavaScriptEnabled(true);
                        webView.loadUrl("https://sfpl.org");

                        String name = parent.getItemAtPosition(position).toString();
                        Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
                    case 2:
                        tabs.setCurrentTab(1);
                        webView.getSettings().setJavaScriptEnabled(true);
                        webView.loadUrl("https://sf.eater.com/maps/best-restaurants-san-francisco-38");

                        name = parent.getItemAtPosition(position).toString();
                        Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        tabs.setCurrentTab(1);
                        webView.getSettings().setJavaScriptEnabled(true);
                        webView.loadUrl("https://www.sfmta.com");

                        name = parent.getItemAtPosition(position).toString();
                        Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        tabs.setCurrentTab(1);
                        webView.getSettings().setJavaScriptEnabled(true);
                        webView.loadUrl("https://www.tripadvisor.com/Attraction_Products-g60713-a_contentId.1188572237276655+268263103-San_Francisco_California.html");

                        name = parent.getItemAtPosition(position).toString();
                        Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        tabs.setCurrentTab(1);
                        webView.getSettings().setJavaScriptEnabled(true);
                        webView.loadUrl("https://www.sftourismtips.com/san-francisco-sports-events.html");

                        name = parent.getItemAtPosition(position).toString();
                        Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
    }

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(COLLEGE_LOCATIONS[0], ZOOM_LEVEL));

        // Add markers for colleges
        for (int i = 0; i < COLLEGE_LOCATIONS.length; i++) {
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(COLLEGE_LOCATIONS[i]);
            markerOptions.title(COLLEGE_TITLES[i]);
            markerOptions.snippet(COLLEGE_SNIPPETS[i]);
            mMap.addMarker(markerOptions);
        }

        mMap.setOnMarkerClickListener(
                new GoogleMap.OnMarkerClickListener() {
                    public boolean onMarkerClick(Marker m) {
                        String title = m.getTitle();
                        String snip = m.getSnippet();
                        Toast.makeText(getApplicationContext(), title + "\n" + snip, Toast.LENGTH_LONG).show();

                        // webView functionality
                        int index = Arrays.asList(COLLEGE_TITLES).indexOf(m.getTitle());
                        // replace all special characters with no space
                        // assuming the colleges main website is "collegename".edu
                        String url = "https://www." + COLLEGE_TITLES[index].toLowerCase().replaceAll("[^a-zA-Z0-9]", "") + ".edu/";

                        TabHost tabs = (TabHost) findViewById(R.id.tabhost);
                        WebView webView = (WebView) findViewById(R.id.web_view);
                        EditText text = (EditText) findViewById(R.id.editURL);

                        tabs.setCurrentTab(1);
                        webView.getSettings().setJavaScriptEnabled(true);
                        webView.loadUrl(url);
                        text.setText(url);

                        return true;
                    }
                }
        );

        //enable a zoom control
        mMap.getUiSettings().setZoomControlsEnabled(true);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}