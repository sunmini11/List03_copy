package com.egco428.list03;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String Course_Title = "Course Title";
    public static final String Course_Desc = "Course Description";
    public static final String Course_Number = "courseNumber";
    public static final String Course_Credits = "coursCredits";
    public static final int DETAIL_REQ_CODE = 1001;
    protected List<Course> data;
    //int[] imageCourse = {R.drawable.image10101, R.drawable.image10102, R.drawable.image10103, R.drawable.image10104, R.drawable.image10105, R.drawable.image10106, R.drawable.pic7, R.drawable.pic8};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = DataProvider.getData();
        ListView listView = (ListView)findViewById(R.id.list);
        //ArrayAdapter<Course> courseArrayAdapter = new ArrayAdapter<Course>(this, android.R.layout.simple_list_item_1, data, imageCourse);
        ArrayAdapter<Course> courseArrayAdapter = new CourseArrayAdapter(this,0,data);
        listView.setAdapter(courseArrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Course course = data.get(position);
                displayDetail(course,position);
            }
        });
//        List<Course> data = DataProvider.getData();
//        Iterator<Course> iterator = data.iterator();
//
//        StringBuilder builder = new StringBuilder();
//        while (iterator.hasNext()){
//            Course course = iterator.next();
//            builder.append(course.getTitle()).append("\n");
//
//        }
//        TextView courseTextView = (TextView)findViewById(R.id.courseListView);
//        courseTextView.setText(builder.toString());
    }

    class CourseArrayAdapter extends ArrayAdapter<Course>{
        Context context;
        List<Course> objects;
        public CourseArrayAdapter(Context context,int resource,List <Course> objects){
            super(context, resource, objects);
            this.context = context;
            this.objects = objects;
        }
        @Override public View getView(int position, View convertView, ViewGroup parent){
            Course course = objects.get(position);
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE); //link with interface
            View view = inflater.inflate(R.layout.course_item,null);

            TextView txt = (TextView)view.findViewById(R.id.titleText);
            txt.setText(course.getTitle());

            ImageView image = (ImageView)view.findViewById(R.id.imageCourse);
            int res = context.getResources().getIdentifier("image"+course.getCourseNumber(),"drawable",context.getPackageName());
            image.setImageResource(res);
            return view;
        }
    }

    private void displayDetail(Course course,int position){
        //Log.d("MainActivity", "Displaying Course: "+course.getTitle());
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(Course_Title, course.getTitle());
        intent.putExtra(Course_Desc, course.getDescription());
        intent.putExtra(Course_Number, course.getCourseNumber());
        intent.putExtra(Course_Credits, course.getCredits());
        startActivityForResult(intent, DETAIL_REQ_CODE);
    }

}
