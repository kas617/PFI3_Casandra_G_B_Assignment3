
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

import android.R;

// changed stuff!!!!
/**
 * A simple {@link android.app.Fragment} subclass.
 */
public class SearchFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private Spinner spinnerFrom;
    private Spinner spinnerTo;
private String searchURL;

    private ArrayList<Journey> myItems = new ArrayList<Journey>();
    private Adapter myAdapter;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:
                // String searchURL = Constants.getURL("80000", "93070", 14); //Malmö C = 80000,  Malmö Gatorg 80100, Hässleholm C 93070


                spinnerFrom.setOnItemSelectedListener(this);
                spinnerTo.setOnItemSelectedListener(this);

                int fromStation = spinnerFrom.getSelectedItemPosition();
                int toSTation = spinnerTo.getSelectedItemPosition();
                //String searchURL = Constants.getURL("80000", "93070", 10); //Malmö C = 80000,  Malmö GAtorg 80100, Hässleholm C 93070
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
        // Inflate the layout for this fragment
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
        /* TextView tw = (TextView) getView().findViewById(R.id.boo);
        tw.setText("search_finished" + myItems.size());
        for (Journey j : myItems) {
            tw.append("From" + j.getStartStation().getStationName()
                    + " To: " + j.getEndStation()
                    + " leaves : " + j.getTimeToDeparture() + "\n");
        }*/
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // String[] courses = getActivity().getResources().getStringArray(R.array.stationNumbers);
        // String course = courses[position];
        // Log.i("ExpFragment", "Course. " + course);
        // String searchURL = Constants.getURL("80000", "93070", 14);
        // TEMP MyAsyncTask myAsyncTask = new MyAsyncTask();
        // TEMP myAsyncTask.execute(searchURL);



        // TextView tw = (TextView) getView().findViewById(R.id.boo);
    // Debugging
        // tw.append(
//                 "Heyo!");
// FROM ONCREATE

        int fromStation = spinnerFrom.getSelectedItemPosition();
        int toSTation = spinnerTo.getSelectedItemPosition();
        //String searchURL = Constants.getURL("80000", "93070", 10); //Malmö C = 80000,  Malmö GAtorg 80100, Hässleholm C 93070
        String[] stationNo = getResources().getStringArray(R.array.stationNumbers);
        String searchURL = Constants.getURL(stationNo[fromStation], stationNo[toSTation], 14);
        new MyAsyncTask().execute(searchURL);
        // FROM ONCREATE!

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



    private class MyAsyncTask extends AsyncTask<String, Void, Long> {

        @Override
        protected Long doInBackground(String... params) {
            Journeys journeys = Parser.getJourneys(params[0]); //There can be many in the params Array
            String param1 = params[0];
            myItems.clear();
            myItems.addAll(journeys.getJourneys());
            return null;
        }


        protected void onPostExecute(Long result) { //Called when the AsyncTask is all done
            //myAdapter.notifyDataSetInvalidated();
            myAdapter.notifyDataSetChanged();
            // searchFinished();
            Log.i("isak", "myItems sixe: " + String.valueOf(myItems.size()));
        }
    }
}