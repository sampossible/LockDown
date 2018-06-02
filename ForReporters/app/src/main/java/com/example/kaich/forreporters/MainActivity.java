package com.example.kaich.forreporters;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Spinner mSpinnerRooms;
    private ArrayAdapter<CharSequence> mArrayAdapter;
    private TextView mThreatLevelTextView = (TextView) findViewById(R.id.threatLevelTextView);
    private TextView mLowTextView = (TextView) findViewById(R.id.lowTextView);
    private TextView mHighTextView = (TextView) findViewById(R.id.highTextView);
    private SeekBar mThreatLevelSeekBar;
    private TextView mOccupantTextView = (TextView) findViewById(R.id.occupantTextView);
    private SeekBar mOccupantsSeekBar;
    private String report = "Skyline High School";

    //reports will be in SchoolName.RoomNumber.ThreatLevel.Occupants

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSpinnerRooms = (Spinner) findViewById(R.id.spinnerRooms);
        mArrayAdapter = ArrayAdapter.createFromResource(this,R.array.rooms,android.R.layout.simple_spinner_item);
        mArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerRooms.setAdapter(mArrayAdapter);
        mSpinnerRooms.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                report.concat("." + (String)parent.getItemAtPosition(position));
                mThreatLevelSeekBar.setEnabled(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mThreatLevelSeekBar = (SeekBar) findViewById(R.id.threatLevelSeekBar);
        mThreatLevelSeekBar.setEnabled(false);
        mThreatLevelSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                report.concat("." + progress + "");
                mOccupantsSeekBar.setEnabled(true);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mOccupantsSeekBar = (SeekBar) findViewById(R.id.occupantsSeekBar);
        mOccupantsSeekBar.setEnabled(false);
        mOccupantsSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mOccupantTextView.setText("Number of Occupants: " + progress);
                report.concat("." + progress + "")
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                sendReport();
                Toast.makeText(MainActivity.this, "Report Sent to First Responders", Toast.LENGTH_LONG);
                mSpinnerRooms.setEnabled(false);
                mThreatLevelSeekBar.setEnabled(false);
                mOccupantsSeekBar.setEnabled(false);
            }
        });
    }

    public void sendReport(){
        System.out.println(report);
        //send report to website
    }

    public String getReport() {
        return report;
    }
}
