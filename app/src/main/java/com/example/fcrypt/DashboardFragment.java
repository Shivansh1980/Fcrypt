package com.example.fcrypt;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DashboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashboardFragment extends Fragment {
    private SliderLayout sliderLayout;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public static DashboardFragment newInstance(String param1, String param2) {
        DashboardFragment fragment = new DashboardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_dashboard, container, false);
        Button encryption_button = view.findViewById(R.id.file_encryption_button);
        encryption_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(getActivity(),R.id.nav_host_fragment_for_nav_home);
                navController.navigate(R.id.action_dashboardFragment_to_encryptorFragment);
            }
        });
        sliderLayout = view.findViewById(R.id.slider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL);
        sliderLayout.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderLayout.setScrollTimeInSec(3);


        setsliderViews();

        return view;
    }

    private void setsliderViews() {
        for (int i=0;i<3;i++){
            DefaultSliderView sliderView=new DefaultSliderView(getContext());

            switch(i){
                case 0:
                    sliderView.setDescription("Encrypt and Decrypt your Files here");
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/fcrypt-4a9d1.appspot.com/o/encrypt_decrypt.jpeg?alt=media&token=5f3513e4-8e3b-4083-823a-2be7714b3afa");
                    break;
                case 1:
                    sliderView.setDescription("Upcoming!!!");
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/fcrypt-4a9d1.appspot.com/o/word_to_pdf.jpeg?alt=media&token=a53695e1-4656-4b72-be3e-713e23280cc7");
                    break;
                case 2:
                    sliderView.setDescription("Upcoming!!!");
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/fcrypt-4a9d1.appspot.com/o/jpg_to_pdf.jpeg?alt=media&token=e9cf4374-249d-4b42-95b5-c217ed70a55b");
                    break;
            }
            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            sliderLayout.addSliderView(sliderView);
        }
    }
}