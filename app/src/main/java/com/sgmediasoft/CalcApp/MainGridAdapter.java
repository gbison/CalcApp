package com.sgmediasoft.CalcApp;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainGridAdapter extends BaseAdapter {

    //Use these two variables to tweak the width and height of your colums. Pay attention to
    // layout height and column width inside the widgets XML properties.
    int tweakWidth = 465;
    int tweakHeight = 500;


    //Animals array = index numbers for our view clicks.
    Integer[] Animals = {R.drawable.eagle, R.drawable.elephant, R.drawable.gorilla,
            R.drawable.panda, R.drawable.panther, R.drawable.polar};
    ImageView pic;


    Context context;
    View view;


    @Override
    public int getCount() {
        return Animals.length;
    }

    @Override
    public Object getItem(int position) {
        return Animals[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        pic = new ImageView(context);
        pic.setImageResource(Animals[position]);
        pic.setScaleType(ImageView.ScaleType.CENTER_CROP);
        pic.setLayoutParams(new GridView.LayoutParams(tweakWidth,tweakHeight));
        return pic;
    }

    MainGridAdapter(Main2Activity main2Activity) {
        context = main2Activity; //make sure you declared context as the mainactivity your grid view is in!
    }
}
