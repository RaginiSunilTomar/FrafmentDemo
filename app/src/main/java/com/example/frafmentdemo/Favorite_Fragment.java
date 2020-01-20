package com.example.frafmentdemo;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.speech.RecognizerIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;

public class Favorite_Fragment extends Fragment {

    Calendar ccc;
    SimpleDateFormat sss;
    String timesys;
    final static int STORAGE_PERMISSION_CODE = 1;
    public static TextView outputTextview;
    final static private int REQ_CODE_SPEECH_INPUT = 45;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        outputTextview = view.findViewById(R.id.textView); //output textview

        Button openMicBUtton = view.findViewById(R.id.speechbtn);
        openMicBUtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speaks();
            }
        });

        Button saveText = view.findViewById(R.id.button2);

        saveText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                writedatainfile(outputTextview.getText().toString());

            }
        });
        return view;
    }

    private void speaks() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);// Capture Additional Languages // Capture Free Form word
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());// Locale is default language
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hi speak something");

        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {

                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS); // set  the values of speech input to results getting from recognizer intent

                    outputTextview.setText(result.get(0));
                }
                break;


            }
        }


    }

    private void writedatainfile(String textt) {

        ccc = Calendar.getInstance();
        sss = new SimpleDateFormat("dd-mm-yy HH:mm:ss");
        timesys = sss.format(ccc.getTime());
        timesys = "ExternalData" + timesys + ".txt";
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File myFile = new File(folder, timesys);
        writeData(myFile, textt);

    }


    public void writeData(File myfile, String result) {
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(myfile);
            fileOutputStream.write(result.getBytes());
            Toast.makeText(getActivity(), "Done" + myfile.getAbsolutePath(), Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            Toast.makeText(getActivity(), "Catch BLOck", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } finally {


            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }


}
