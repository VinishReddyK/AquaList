package thevoidcommunity.netlify.aqualist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EnterDate extends AppCompatActivity {
    public static Activity a;

    TextView tv1;
    CalendarView calender;
    Button b,bb;
    void openActivity()
    {
        Intent i=new Intent(this,WnatDays.class);
        startActivity(i);
    }
    void  Toastmsg(String msg)
    {
        Toast toast= Toast.makeText(this,msg,Toast.LENGTH_LONG);
        toast.getView().setBackgroundResource(R.drawable.customoast);
        View view = toast.getView();
        TextView text = (TextView)view.findViewById(android.R.id.message);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            text.setTextAppearance(R.style.toastTextStyle);
        }
        toast.show();
        return;
    }
    void openNextActivity()
    {
        String s="";
        File f=new File(getExternalFilesDir(""),"ActTxt.txt");
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            s=br.readLine();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        if(s.equalsIgnoreCase("Enter"))
        {
        Intent i=new Intent(this,DataEntry.class);
        startActivity(i);
        }
        if(s.equalsIgnoreCase("View"))
        {
            Intent i=new Intent(this, thevoidcommunity.netlify.aqualist.View.class);
            startActivity(i);
        }
        if(s.equalsIgnoreCase("Delete_Date_File"))
        {
            Intent i=new Intent(this, DeleteDateFile.class);
            startActivity(i);
        }
    }
    void SaveFile(String sn)
    {
        File f=new File(getExternalFilesDir(""),"RootTxt.txt");
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            StringBuilder ssb = new StringBuilder();
            String t = "";
                ssb.append(br.readLine());
                ssb.append("\n");
                ssb.append(sn);
            FileOutputStream fw=new FileOutputStream(f);
            fw.write(ssb.toString().getBytes());
        }
            catch(Exception e)
            {
                System.out.println(e);
            }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_date);
        a=this;
        tv1=findViewById(R.id.datetext);
        calender=findViewById(R.id.calendarView2);
        b=findViewById(R.id.button1);
        bb=findViewById(R.id.button5);
        final Calendar cale=Calendar.getInstance();
        Date dt;
        //cale.add(Calendar.DATE, +1);
        dt=cale.getTime();
        String d= new SimpleDateFormat("yyyy/MM/dd").format(dt);
        String d1=new SimpleDateFormat("yyyy / MM / dd").format(dt);
        File f=new File(getExternalFilesDir(""),"RootTxt.txt");
        tv1.setText(d1);
        SaveFile(d);
        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                month++;
                String day="",mont="";
                final Calendar cale=Calendar.getInstance();
                Date dt;
                dt=cale.getTime();
                String d= new SimpleDateFormat("yyyy/MM/dd").format(dt);
                if((String.valueOf(dayOfMonth)).length()<2 && (String.valueOf(month)).length()<2) {
                    day = "0" + String.valueOf(dayOfMonth);
                    mont="0"+String.valueOf(month);
                    String date = year+"/"+mont+"/"+day;
                    String date1 = year+" / "+mont+" / "+day;
                    SaveFile(date1);
                    tv1.setText(date);
                    if(date.compareTo(d)>0)
                    {
                        Toastmsg("Can U Guess Future :p");
                        b.setVisibility(View.INVISIBLE);
                    }
                    else {
                        b.setVisibility(View.VISIBLE);
                    }
                    return;
                }
                if((String.valueOf(dayOfMonth)).length()<2) {
                    day = "0" + String.valueOf(dayOfMonth);
                    String date = year + "/" + month + "/" + day;
                    String date1 = year + " / " + month + " / " + day;
                    SaveFile(date);
                    tv1.setText(date1);
                    if(date.compareTo(d)>0)
                    {
                        Toastmsg("Can U Guess Future :p");
                        b.setVisibility(View.INVISIBLE);
                    }
                    else {
                        b.setVisibility(View.VISIBLE);
                    }
                    return;
                }
                if((String.valueOf(month)).length()<2) {
                    mont = "0" + String.valueOf(month);
                    String date = year +"/"+mont+"/"+dayOfMonth;
                    String date1 = year +" / "+mont+" / "+dayOfMonth;
                    SaveFile(date);
                    tv1.setText(date1);
                    if(date.compareTo(d)>0)
                    {
                        Toastmsg("Can U Guess Future :p");
                        b.setVisibility(View.INVISIBLE);
                    }
                    else {
                        b.setVisibility(View.VISIBLE);
                    }
                    return;
                }

                String date = year+"/"+month+"/"+dayOfMonth;
                String date1 = year+" / "+month+" / "+dayOfMonth;
                SaveFile(date);
                tv1.setText(date1);
                if(date.compareTo(d)>0)
                {
                    Toastmsg("Can U Guess Future :p");
                    b.setVisibility(View.INVISIBLE);
                }
                else {
                    b.setVisibility(View.VISIBLE);
                }
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openNextActivity();
            }
        });
        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity();
            }
        });
    }
}