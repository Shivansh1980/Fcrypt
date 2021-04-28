package com.example.fcrypt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class encryptor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_main);

        ImageSlider imageSlider = findViewById(R.id.slider);
        List<SlideModel> sliderModels = new ArrayList<>();
        sliderModels.add(new SlideModel("Downloads","1 Image"));
        sliderModels.add(new SlideModel("Downloads","2 Image"));
        sliderModels.add(new SlideModel("Downloads","3 Image"));
        sliderModels.add(new SlideModel("Downloads","4 Image"));
        sliderModels.add(new SlideModel("Downloads","5 Image"));
        imageSlider.setImageList(sliderModels,true);

    }
}