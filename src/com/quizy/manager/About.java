package com.quizy.manager;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class About extends Activity
{
    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.about );

        TextView tv = (TextView) findViewById( R.id.textView1 );
        tv.setText( R.string.about_app );
    }
}