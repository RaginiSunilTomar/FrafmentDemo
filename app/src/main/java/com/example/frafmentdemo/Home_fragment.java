package com.example.frafmentdemo;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home_fragment extends Fragment {
    Button button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

      View myview=  inflater.inflate(R.layout.fragment_home,container,false);

         button= myview.findViewById(R.id.button);
           button.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Toast.makeText(getActivity(), "this is my home fragment", Toast.LENGTH_SHORT).show();

                   AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                   builder.setTitle("welcome");
                   builder.setMessage("mood off !!!!");
                   builder.setCancelable(true);
                   builder.show();


               }
           });

return myview;


    }
}
