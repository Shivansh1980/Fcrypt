package com.example.fcrypt.FileManipulation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.io.File;

public class FileManipulation {
    Uri file_uri;
    File my_file;
    FileManipulation(Uri file_uri){
        this.file_uri = file_uri;
        my_file = new File(file_uri.getPath());
    }

    Bitmap getBitmapOfFile(){
        return BitmapFactory.decodeFile(my_file.getAbsolutePath());
    }

}
