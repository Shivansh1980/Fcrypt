package com.example.fcrypt;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fcrypt.FileManipulation.FileManipulation;
import com.example.fcrypt.FileManipulation.HandleRequests;
import com.squareup.picasso.Picasso;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static android.app.Activity.RESULT_OK;

public class EncryptorFragment extends Fragment {
    private static final int PICK_FILE_REQUEST = 1;
    Button file_button;
    Button get_request_button;
    TextView response_text;
    Uri file_uri;
    Bitmap bitmap;

    public EncryptorFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_encryptor, container, false);
        file_button = root.findViewById(R.id.select_file);
        get_request_button = root.findViewById(R.id.get_request_button);
        response_text = root.findViewById(R.id.response_text);


        file_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, PICK_FILE_REQUEST);
            }
        });
        get_request_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HandleRequests handleRequests = null;
                try {
                    handleRequests = new HandleRequests("http://encryptor-api.herokuapp.com/encryptfiles/encrypt_file/");
                } catch (IOException e) {
                    Toast.makeText(getContext(), e.toString() , Toast.LENGTH_SHORT).show();
                }

            }
        });
        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_FILE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            file_uri = data.getData();
        }
    }
}