package com.example.fcrypt.FileManipulation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.io.File;

public class FileManipulation {
    Uri file_uri;

    FileManipulation(Uri file_uri){
        this.file_uri = file_uri;
    }

    Bitmap getBitmapOfFile(){
        File my_file = new File(file_uri.getPath());
        return BitmapFactory.decodeFile(my_file.getAbsolutePath());
    }
}
