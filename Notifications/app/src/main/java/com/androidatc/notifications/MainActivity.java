package com.androidatc.notifications;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {


    private CharSequence mTitle;

    private Button btnMaxPriorityNotification;
    private Button btnHighPriorityNotification;
    private Button btnDefaultPriorityNotification;
    private Button btnLowPriorityNotification;
    private Button btnMinPriorityNotification;
    private Button btnDefaultNotification;
    private Button btnOldTypeNotification;
    private Button btnBigTextNotification;
    private Button btnBigImageNotification;
    private Button btnInboxTypeNotification;
    public Button btnNougatNotification;

    private int NOTIF_REF=1;
    private NotificationManager manager;
    private int count=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //setting up the buttons
        btnMaxPriorityNotification = (Button)findViewById(R.id.btnMaxPriorityNotification);
        btnHighPriorityNotification = (Button)findViewById(R.id.btnHighPriorityNotification);
        btnDefaultPriorityNotification = (Button)findViewById(R.id.btnDefaultPriorityNotification);
        btnLowPriorityNotification = (Button)findViewById(R.id.btnLowPriorityNotification);
        btnMinPriorityNotification = (Button)findViewById(R.id.btnMinPriorityNotification);
        btnDefaultNotification = (Button)findViewById(R.id.btnDefaultNotification);
        btnOldTypeNotification = (Button)findViewById(R.id.btnOldTypeNotification);
        btnBigTextNotification = (Button)findViewById(R.id.btnBigTextNotification);
        btnBigImageNotification = (Button)findViewById(R.id.btnBigImageNotification);
        btnInboxTypeNotification = (Button)findViewById(R.id.btnInboxTypeNotification);
        btnNougatNotification=(Button)findViewById(R.id.btnNougatNotification);

        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        btnMaxPriorityNotification.setOnClickListener(MainActivity.this);
        btnHighPriorityNotification.setOnClickListener(MainActivity.this);
        btnDefaultPriorityNotification.setOnClickListener(MainActivity.this);
        btnLowPriorityNotification.setOnClickListener(MainActivity.this);
        btnMinPriorityNotification.setOnClickListener(MainActivity.this);
        btnDefaultNotification.setOnClickListener(MainActivity.this);
        btnOldTypeNotification.setOnClickListener(MainActivity.this);
        btnBigTextNotification.setOnClickListener(MainActivity.this);
        btnBigImageNotification.setOnClickListener(MainActivity.this);
        btnInboxTypeNotification.setOnClickListener(MainActivity.this);

        btnNougatNotification.setOnClickListener(MainActivity.this);
        handleNotification(getIntent());

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    @TargetApi(16)
    public void onClick(View v){
        Notification notif = null;
        Notification.Builder builder = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setWhen(System.currentTimeMillis())
                .setContentText("Android Notifications");
        switch(v.getId()) {
            case R.id.btnMaxPriorityNotification:
                builder.setContentTitle("Maximun Priority Notification").setPriority(Notification.PRIORITY_MAX);
                sendNotification(builder.build());
                break;
            case R.id.btnHighPriorityNotification:
                builder.setContentTitle("High Priority Notification").setPriority(Notification.PRIORITY_HIGH);
                sendNotification(builder.build());
                break;
            case R.id.btnLowPriorityNotification:
                builder.setContentTitle("Low Priority Notification").setPriority(Notification.PRIORITY_LOW);
                sendNotification(builder.build());
                break;
            case R.id.btnMinPriorityNotification:
                builder.setContentTitle("Minimun Priority Notification").setPriority(Notification.PRIORITY_MIN);
                sendNotification(builder.build());
                break;

            case R.id.btnDefaultNotification:
                notif = getDefaultNotification(builder);
                sendNotification(notif);
                break;

            case R.id.btnOldTypeNotification:
                notif = getOldNotification();
                sendNotification(notif);
                break;

            case R.id.btnBigTextNotification:
                notif = getBigTextStyle(builder);
                sendNotification(notif);
                break;

            case R.id.btnBigImageNotification:
                notif = getBigPictureStyle(builder);
                sendNotification(notif);
                break;

            case R.id.btnInboxTypeNotification:
                inboxStyleNotifications();
                break;

            case R.id.btnNougatNotification:
                getNougatStyleNotification(builder);
                break;
        }
    }

    @TargetApi(16)
    public void handleNotification(Intent intent)
    {
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if(getMessageText(intent)!=null){
            Notification repliedNotificaciton =
                    new Notification.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentText("Your reply has been submitted succesfully")
                    .build();
            manager.notify(0123,repliedNotificaciton);
        }
    }

    private CharSequence getMessageText(Intent intent){
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if(remoteInput!=null)
        {
            return remoteInput.getCharSequence("Reply");
        }
        return null;
    }

    @TargetApi(20)
    private Notification getNougatStyleNotification(Notification.Builder builder)
    {
        android.app.RemoteInput remoteInput = new android.app.RemoteInput.Builder("Reply")
                .setLabel("")
                .build();
        //Create the reply action and add the remote input.
        Intent resultIntent = new Intent(this, MainActivity.class);
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        Notification.Action action =
                new Notification.Action.Builder(R.mipmap.ic_launcher,
                        "Reply...",resultPendingIntent)
                .addRemoteInput(remoteInput)
                .build();
        return new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Billy Wonka")
                .setContentText("hey, want to go for dinner tonight?")
                .addAction(action).build();
    }

    public void sendNotification(Notification notif){
        notif.defaults=Notification.DEFAULT_ALL;
        manager.notify(NOTIF_REF++, notif);
    }

    @TargetApi(16)
    private Notification getDefaultNotification(Notification.Builder builder){
        builder
                .setSmallIcon(R.mipmap.ic_launcher)
                .setWhen(System.currentTimeMillis())
                .setContentTitle("Default Notification")
                .setContentText("This is random text for default type notifications")
                .setContentInfo("Info");



        return builder.build();
    }

    @TargetApi(16)
    private Notification getBigTextStyle(Notification.Builder builder){
        Bitmap icon = BitmapFactory.decodeResource(this.getResources(), R.mipmap.big_image);
        builder
                .setContentTitle("Reduced BigText title")
                .setContentText("Reduced content")
                .setContentInfo("Info")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(icon);

        return  new Notification.BigTextStyle(builder)
                .bigText("")
                .setBigContentTitle("Android ATC")
                .setSummaryText(getResources().getString(R.string.summary_text))
                .build();

    }

    @TargetApi(16)
    private Notification getBigPictureStyle(Notification.Builder builder){
        Bitmap icon = BitmapFactory.decodeResource(this.getResources(), R.mipmap.big_image);
        builder
                .setContentTitle("Reduced BigPicture title")
                .setContentText("Reduced content")
                .setContentInfo("Info")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(icon);

        return  new Notification.BigPictureStyle(builder)
                .bigPicture(icon)
                .bigLargeIcon(icon)
                .setBigContentTitle("Expanded BigPicture title")
                .setSummaryText(getResources().getString(R.string.summary_text))
                .build();
    }


    private void inboxStyleNotifications(){
        int ID=1;
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this).setSmallIcon(R.mipmap.ic_launcher);
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        inboxStyle.setSummaryText(getResources().getString(R.string.summary_text));
        mBuilder.setStyle(inboxStyle);
        mBuilder.setNumber(1);

        NotificationManager mNotificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(ID, mBuilder.build());
    }

    public Notification getOldNotification() {
        Notification notif = new Notification(R.mipmap.ic_launcher, null, System.currentTimeMillis());
        // notif.setLatestEventInfo(this, "old title", "Old notification content text", PendingIntent.getActivity(this, 0, new Intent(), 0));
        return notif;
    }
}
