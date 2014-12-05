package com.hijackerstudios.timespan;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.melnykov.fab.FloatingActionButton;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener {


    TextView Fdate, Ftime, Tdate, Ttime, Calculatedtime;
    FloatingActionButton fabz;
    SwipeRefreshLayout swipeLayout;
    String DateOne, TimeOne, DateTwo, TimeTwo;
    AdView AdViewz;

    public Boolean dateOne, oneTime, dateTwo, twoTime;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fdate = (TextView) findViewById(R.id.date_one);
        Ftime = (TextView) findViewById(R.id.time_one);
        Tdate = (TextView) findViewById(R.id.date_two);
        Ttime = (TextView) findViewById(R.id.time_two);
        Calculatedtime = (TextView) findViewById(R.id.calcTime);
        fabz = (FloatingActionButton) findViewById(R.id.fab);
        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        AdViewz = (AdView) findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder().build();
        AdViewz.loadAd(adRequest);



        swipeLayout.setOnRefreshListener(MainActivity.this);
        swipeLayout.setColorSchemeResources(R.color.material_blue_500,
                R.color.material_orange,
                R.color.material_grey);

        dateOne = false;
        oneTime = false;
        dateTwo = false;
        twoTime = false;


        Fdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Runnable clickButon = new Runnable() {
                    @Override
                    public void run() {

                        final Calendar c = Calendar.getInstance();
                        int years = c.get(Calendar.YEAR);
                        int months = c.get(Calendar.MONTH);
                        int days = c.get(Calendar.DAY_OF_MONTH);
                        final long minYear = -95576800000000L;

                        DatePickerDialog dpd = new DatePickerDialog(MainActivity.this,
                                new DatePickerDialog.OnDateSetListener() {

                                    @Override
                                    public void onDateSet(DatePicker view, int year,
                                                          int month, int day) {
                                        Fdate.setText(year+ "-" + (month+1) + "-" + day);
                                        DateOne = Fdate.getText().toString();
                                        dateOne = true;

                                    }
                                }, years, months, days);
                        dpd.getDatePicker().setMinDate(minYear);
                        dpd.show();

                    }
                };
                Fdate.postDelayed(clickButon, 700);
            }
        });

        Ftime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Runnable clickButton = new Runnable() {
                    @Override
                    public void run() {

                        final Calendar instance = Calendar.getInstance();

                        TimePickerDialog tpd = new TimePickerDialog(MainActivity.this,
                                new TimePickerDialog.OnTimeSetListener() {

                                    @Override
                                    public void onTimeSet(TimePicker view, int n, int n2) {

                                        String lowerCase = null;
                                        while (true) {
                                            try {
                                                final Date parse = new SimpleDateFormat("hh:mm").parse(n + ":" + n2);
                                                final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm a");
                                                lowerCase = simpleDateFormat.format(parse).toLowerCase();
                                                Ftime.setText(lowerCase);
                                                TimeOne = Ftime.getText().toString();
                                                oneTime = true;
                                            } catch (Exception ex) {
                                                continue;
                                            }
                                            break;
                                        }
                                    }
                                }, instance.get(Calendar.HOUR_OF_DAY), instance.get(Calendar.MINUTE), false);
                        tpd.show();

                    }
                };
                Ftime.postDelayed(clickButton,700);




            }
        });

        Tdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Runnable clickButton = new Runnable() {
                        @Override
                        public void run() {

                            final Calendar c = Calendar.getInstance();
                            int years = c.get(Calendar.YEAR);
                            int months = c.get(Calendar.MONTH);
                            int days = c.get(Calendar.DAY_OF_MONTH);
                            long minYear = -95576800000000L;

                            DatePickerDialog dpd = new DatePickerDialog(MainActivity.this,
                                    new DatePickerDialog.OnDateSetListener() {

                                        @Override
                                        public void onDateSet(DatePicker view, int year,
                                                              int month, int day) {
                                            Tdate.setText(year + "-" + (month +1) + "-" + day);
                                            DateTwo = Tdate.getText().toString();
                                            dateTwo = true;
                                        }
                                    }, years, months, days);
                            dpd.getDatePicker().setMinDate(minYear);
                            dpd.show();

                        }
                    };
                    Tdate.postDelayed(clickButton,700);

            }
        });

        Ttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Runnable clickButton = new Runnable() {
                    @Override
                    public void run() {

                        final Calendar instance = Calendar.getInstance();

                        TimePickerDialog tpd = new TimePickerDialog(MainActivity.this,
                                new TimePickerDialog.OnTimeSetListener() {

                                    @Override
                                    public void onTimeSet(TimePicker view, int n, int n2) {

                                        String lowerCase = null;
                                        while (true) {
                                            try {
                                                final Date parse = new SimpleDateFormat("hh:mm").parse(n + ":" + n2);
                                                final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm a");
                                                lowerCase = simpleDateFormat.format(parse).toLowerCase();
                                                Ttime.setText(lowerCase);
                                                TimeTwo = Ttime.getText().toString();
                                                twoTime = true;
                                            } catch (Exception ex) {
                                                continue;
                                            }
                                            break;
                                        }
                                    }
                                }, instance.get(Calendar.HOUR_OF_DAY), instance.get(Calendar.MINUTE), false);
                        tpd.show();

                    }
                };
                Ttime.postDelayed(clickButton,700);
            }
        });

        fabz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (dateOne && oneTime && dateTwo && twoTime) {

                    CalAnswer();

                    Toast.makeText(MainActivity.this, "Swipe down to refresh", Toast.LENGTH_LONG).show();

                }else{

                    Toast.makeText(MainActivity.this, "Missing values!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void CalAnswer(){

        String CombinedOne, CombinedTwo;

        CombinedOne = DateOne + " " + TimeOne;
        CombinedTwo = DateTwo + " " + TimeTwo;

        String text = CombinedOne;
        String text2 = CombinedTwo;
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd hh:mm a").withLocale(Locale.getDefault());
        DateTime oldDate = formatter.parseDateTime(text);
        DateTime newDate = formatter.parseDateTime(text2);
        Period period = new Period(new DateTime(oldDate), new DateTime(newDate), PeriodType.yearMonthDayTime());
        //fixed a bug in above line

        long elapsedYears = period.getYears();
        long elapsedMonths = period.getMonths();
        long elapsedDays = period.getDays();
        long elapsedHours = period.getHours();
        long elapsedMinutes = period.getMinutes();

        String calTime = String.format(
                "%d years, %d months, %d days, %d hours and %d minutes.",
                elapsedYears, elapsedMonths, elapsedDays, elapsedHours, elapsedMinutes);

        Calculatedtime.setText(calTime);
        Calculatedtime.setTextSize(24);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeLayout.setRefreshing(false);
                recreate();
            }
        }, 3000);
    }
}
