
package com.example.marcus.pfi3_casandra_g_b_assignment3;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.Spinner;

import com.example.marcus.pfi3_casandra_g_b_assignment3.control.Constants;
import com.example.marcus.pfi3_casandra_g_b_assignment3.control.Journey;
import com.example.marcus.pfi3_casandra_g_b_assignment3.control.Journeys;
import com.example.marcus.pfi3_casandra_g_b_assignment3.control.Parser;

import java.util.ArrayList;

public class SearchFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private Spinner spinnerFrom;
    private Spinner spinnerTo;
    private String searchURL;

    private ArrayList<Journey> myItems = new ArrayList<Journey>();
    private Adapter myAdapter;

    public SearchFragment() {
        // Requires empty constructor
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:


                spinnerFrom.setOnItemSelectedListener(this);
                spinnerTo.setOnItemSelectedListener(this);

                int fromStation = spinnerFrom.getSelectedItemPosition();
                int toSTation = spinnerTo.getSelectedItemPosition();
                String[] stationNo = getResources().getStringArray(R.array.stationNumbers);
                searchURL = Constants.getURL( stationNo[fromStation], stationNo[toSTation], 14);
                new MyAsyncTask().execute(searchURL);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflates the layout on this fragment
        View view = inflater.inflate(R.layout.fragment_travels,
                container, false);

        spinnerFrom = (Spinner) view.findViewById(R.id.spinner);
        spinnerTo = (Spinner) view.findViewById(R.id.spinner2);

        spinnerFrom.setOnItemSelectedListener(this);
        spinnerTo.setOnItemSelectedListener(this);

        spinnerTo.setSelection(1);


        ExpandableListView ev = (ExpandableListView) view.findViewById(R.id.expandableListView);
        myAdapter = new Adapter(getActivity(), myItems);
        ev.setAdapter(myAdapter);

        return view;
    }

    public void onClick(View v) {

    }

    private void searchFinished() {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        int fromStation = spinnerFrom.getSelectedItemPosition();
        int toSTation = spinnerTo.getSelectedItemPosition();


        String[] stationNo = getResources().getStringArray(R.array.stationNumbers);
        String searchURL = Constants.getURL(stationNo[fromStation], stationNo[toSTation], 14);
        new MyAsyncTask().execute(searchURL);
        // Belongs to On Create

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



    private class MyAsyncTask extends AsyncTask<String, Void, Long> {

        @Override
        protected Long doInBackground(String... params) {
            Journeys journeys = Parser.getJourneys(params[0]);
            String param1 = params[0];
            myItems.clear();
            myItems.addAll(journeys.getJourneys());
            return null;
        }


        protected void onPostExecute(Long result) { //This is called when AsyncTask is done

            myAdapter.notifyDataSetChanged();


            Log.i("Kas", "myItems: " + String.valueOf(myItems.size()));
        }
    }
}