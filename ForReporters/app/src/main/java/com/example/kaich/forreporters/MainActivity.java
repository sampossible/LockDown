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
    private TextView mThreatLevelTextView;
    private TextView mLowTextView;
    private TextView mHighTextView;
    private SeekBar mThreatLevelSeekBar;
    private TextView mOccupantsTextView;
    private SeekBar mOccupantsSeekBar;

    private String report = "Huntsville High School";   //reports will be in SchoolName.RoomNumber.ThreatLevel.Occupants
    private String range;
    private int threatLevel;

    private TextView mOutput; //for testing purposes


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
                ((TextView) parent.getChildAt(0)).setTextSize(24);
                if(((String)parent.getItemAtPosition(position)).equals("What room are you located in?")){

                }else{
                    report += ("." + (String)parent.getItemAtPosition(position));
                    mThreatLevelSeekBar.setEnabled(true);
                }

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
                threatLevel = progress;
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
                if(progress < 3){
                    range = "1 - 4";
                }else if (progress < 6){
                    range = "5 - 10";
                }else if (progress < 9){
                    range = "11 - 18";
                }else{
                    range = "18+";
                }
                mOccupantsTextView.setText("Number of Occupants: " + range);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                report += "." + threatLevel;
                report += ("." + range);
                sendReport();
                Toast.makeText(MainActivity.this, "Report Sent to First Responders", Toast.LENGTH_LONG).show();
                mSpinnerRooms.setEnabled(false);
                mThreatLevelSeekBar.setEnabled(false);
                mOccupantsSeekBar.setEnabled(false);
            }
        });

        mThreatLevelTextView = (TextView) findViewById(R.id.threatLevelTextView);
        mThreatLevelTextView.setFocusable(false);
        mOccupantsTextView = (TextView) findViewById(R.id.occupantsTextView);
        mOccupantsTextView.setFocusable(false);
        mHighTextView = (TextView) findViewById(R.id.highTextView);
        mLowTextView = (TextView) findViewById(R.id.lowTextView);
        mOutput = (TextView) findViewById(R.id.output);
    }

    public void sendReport(){
        mOutput.setText(report);
        //send report to website
    }

    public String getReport() {
        return report;
    }
}
