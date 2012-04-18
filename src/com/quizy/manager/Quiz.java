package com.quizy.manager;

import android.app.Activity;
import android.os.Bundle;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Quiz extends Activity
{
    public final static String DEBUG_TAG = "QuizManager";

    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.main );
        setTitle( "QuizManager" );

        TextView tv = (TextView) findViewById( R.id.questions_area );
        tv.setMovementMethod( new ScrollingMovementMethod() );
        tv.setScrollbarFadingEnabled( true );
        tv.setScrollContainer( true );

        tv.setText( "Mid dignissim dapibus augue risus penatibus mus non ac, rhoncus enim cursus tincidunt cursus pulvinar cum porta egestas! Mattis porttitor ac tortor magna natoque rhoncus et ut ac. Tempor turpis turpis, non! Tortor egestas dictumst! Elementum? Vel ridiculus non sagittis, mattis in, eros scelerisque augue? Tortor auctor natoque duis sed mauris tortor hac tristique, lectus vel vel dictumst et diam. Sed elementum porttitor urna. Turpis montes non dapibus, cras dapibus lectus pid vel, ultrices cras cum augue? Sociis scelerisque magnis scelerisque velit a vel! Sit! Vel cum. Elementum? Pid urna? Rhoncus sociis amet, et integer vel etiam adipiscing sit, nisi integer in urna aliquet nisi non, ultrices sagittis. Ac amet, sit sed ac aenean, eu odio! Mauris augue. Dolor montes.\n" +
                "\n" +
                "Elit ac pid integer? Nec! Sociis mattis phasellus aenean. Sed nisi urna quis integer? Purus ridiculus elit nec nascetur magna cursus dignissim ut? A ridiculus adipiscing montes scelerisque, a eu ac quis, cursus, dapibus placerat, massa lectus elementum nec a, ridiculus, et urna, sit, porta rhoncus, mauris! Facilisis tempor nec, urna cum natoque. Risus cras, non aenean parturient in? In montes? Ac dignissim dignissim turpis sed ut! Nec sed, augue non, nunc tristique in urna, ultricies sit? Lundium urna. Turpis? Platea auctor, a, porttitor duis augue. Auctor in est arcu nunc cras nunc mattis montes! Sociis et phasellus rhoncus! Egestas integer eros tincidunt, amet? Nisi auctor odio? Magna phasellus pulvinar tempor nec diam auctor, elementum nunc nec sit in.\n" +
                "\n" +
                "Hac, parturient in phasellus. Tincidunt? Et eu est sed urna magna, egestas egestas ultrices eros, auctor magna dapibus velit dapibus, aenean, pulvinar integer augue ac, aliquet porta vel pid enim hac, dolor amet etiam in enim arcu ut, adipiscing, ac integer! A placerat, nec ridiculus lectus augue lundium ac elit augue, urna et? Ut lacus, eros amet augue odio, phasellus lundium vut aliquam hac facilisis urna turpis, scelerisque cum placerat mauris ac rhoncus montes! Quis porta cum magna lectus, amet ridiculus nascetur etiam? Et augue odio velit elit et! Platea mattis nisi aliquam lacus turpis turpis, et etiam et, elementum vut est, magna! Porta eu tincidunt, phasellus? Vut nisi! Odio ut in enim, magna massa ut enim eu placerat.\n" +
                "\n" +
                "Hac, dictumst a velit, tempor integer pid. Lorem sed pid. Dolor! Mid habitasse. Turpis augue porttitor velit! In, ridiculus facilisis et parturient odio! A ridiculus rhoncus. Dictumst quis proin, lundium dolor, parturient! Tincidunt. Odio dis? Adipiscing tincidunt placerat? Aenean dis integer, tortor tincidunt! Urna scelerisque? Augue lectus natoque dignissim habitasse eros a quis scelerisque, nunc, amet eros platea odio? Etiam. Elit. Sed. Dolor auctor pulvinar, aliquet urna. Dictumst elementum, tempor lundium! Montes turpis eu, mus lundium nisi, pid tristique, mauris, et cum, nunc nec in vel urna vut parturient proin turpis integer facilisis duis? Scelerisque enim odio lacus! Egestas enim porta porta vel est cras? Augue tincidunt sed! Dictumst pulvinar lectus nunc, sit ridiculus a, platea. Dapibus nunc, cum.\n" +
                "\n" +
                "A ultrices eros ultrices elementum montes aenean turpis adipiscing nunc integer mattis duis duis platea, risus pulvinar turpis in et nisi, auctor pulvinar! Parturient, magna integer, turpis platea amet! Habitasse proin? Auctor, elit. Pid montes parturient, nunc, non rhoncus? Vut etiam etiam enim dis porta etiam rhoncus mid hac, massa aenean. In in mauris rhoncus urna scelerisque phasellus! Elit, auctor tristique nascetur nisi, ultrices magna ridiculus cursus magna. Amet diam tincidunt arcu montes ac sociis sagittis eu ac dis parturient quis. Eu ac, aliquam porta mattis nunc ultrices dis amet lectus sit diam egestas arcu! Velit. Tempor non magna nascetur urna! Sit elementum! Placerat lectus, ridiculus aenean, est phasellus vel et porttitor non! Tincidunt porttitor facilisis dis integer turpis." );

        final TextView tv1 = ( TextView ) findViewById( R.id.textView1 );
        tv1.setTextSize( TypedValue.COMPLEX_UNIT_DIP, 14 );

        /*SharedPreferences preferences = getSharedPreferences( "QuizManagerPreferences",  0 );*/
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences( this );
        int quizTimeLimit             = ( ( preferences.contains( "timer" ) ? Integer.parseInt( preferences.getString( "timer", "60" ) ) : 60 ) * 60 * 1000 );
        boolean useTimer              = preferences.getBoolean( "use_timer", true );

        /*SQLiteDatabase db = openOrCreateDatabase( "QuizManager", MODE_PRIVATE, null );

          Cursor c = db.rawQuery( "", null );

          db.close();*/

        if ( useTimer )
        {
            new CountDownTimer( quizTimeLimit, 1000 )
            {
                public void onTick( long millisUntilFinished )
                {
                    tv1.setText( this.formatTime( millisUntilFinished ) );
                }

                private String formatTime( long millisUntilFinished )
                {
                    long seconds = millisUntilFinished / 1000;
                    long minutes = seconds / 60;

                    seconds = seconds % 60;
                    minutes = minutes % 60;

                    String sec = String.valueOf( seconds );
                    String min = String.valueOf( minutes );

                    if ( seconds < 10 )
                    {
                        sec = "0" + seconds;
                    }

                    if ( minutes < 10 )
                    {
                        min = "0" + minutes;
                    }

                    return min + " : " + sec;
                }

                public void onFinish()
                {
                    tv1.setText( "done!" );
                    finish();
                    return;
                }
            }.start();
        }
        else
        {
            LinearLayout ll   = (LinearLayout) findViewById( R.id.mainView );
            RelativeLayout rl = (RelativeLayout) findViewById( R.id.timer_element );
            ll.removeView( rl );
        }
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu )
    {
        MenuInflater inflator = getMenuInflater();
        inflator.inflate( R.menu.app_menu, menu );

        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item )
    {
        if ( item.getItemId() == R.id.about )
        {
            showDialog(1);

            //Intent intent = new Intent( QuizManager.this, About.class );

            Log.d( DEBUG_TAG, "Settings where clicked!" );

            //startActivity( intent );

            /*NotificationManager nm = ( NotificationManager ) getSystemService( NOTIFICATION_SERVICE );
               Notification notify    = new Notification( android.R.drawable.stat_notify_more, "30 seconds ago you look into about this application", (long) ( System.currentTimeMillis() + 50000 ) );
               Context context        = QuizManager.this;
               String title           = "You where notified!";
               String details         = "You where notified about looking into About QuizManager!";
               Intent intent          = new Intent( context, QuizManager.class );
               PendingIntent pi       = PendingIntent.getActivity( context, 0, intent, 0 );
               notify.setLatestEventInfo( context, title, details, pi );

               nm.notify( 0, notify );*/
        }
        else if ( item.getItemId() == R.id.preferences )
        {
            Intent intent = new Intent( Quiz.this, Preferences.class );
            startActivity( intent );
        }

        return super.onOptionsItemSelected( item );
    }

    @Override
    public Dialog onCreateDialog( int id )
    {
        Dialog  dia = new Dialog( Quiz.this );

        dia.requestWindowFeature( Window.FEATURE_LEFT_ICON );
        dia.setTitle( "About app" );
        dia.setCanceledOnTouchOutside( true );
        dia.setContentView( R.layout.about );
        dia.setFeatureDrawableResource( Window.FEATURE_LEFT_ICON, android.R.drawable.ic_menu_info_details );

        return dia;
    }

	/*@Override
		protected void onResume()
		{
			super.onResume();

			try
			{
				SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences( this );

				Log.d( DEBUG_TAG, "Where changes made to preferences? " + settings.getBoolean( "changed", false ) );

				if ( settings.getBoolean( "changed", false ) )
				{
					Editor edit = settings.edit();
					edit.putBoolean( "changed", false );
					edit.commit();

					Log.d( DEBUG_TAG, "Was it acknowledged, that preferences where changed? " + ( ! settings.getBoolean( "changed", false ) ) );

					startActivity( getIntent() );
					finish();
				}
			}
			catch ( Exception e )
			{

			}
		}*/
}