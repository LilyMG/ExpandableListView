package com.example.lilit.web;
import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * This is an example usage of the AnimatedExpandableListView class.
 *
 * It is an activity that holds a listview which is populated with 100 groups
 * where each group has from 1 to 100 children (so the first group will have one
 * child, the second will have two children and so on...).
 */
public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFragmentManager().beginTransaction().add(R.id.container, new ListViewFragment()).commit();
    }


}