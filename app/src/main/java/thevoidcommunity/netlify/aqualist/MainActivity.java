package thevoidcommunity.netlify.aqualist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.*;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    TextView tw4;
    Button b1,b2,b3,bt4;
    void Acttxt(String s)
    {
        File f= new File(getExternalFilesDir(""),"ActTxt.txt");
        FileOutputStream fw= null;
        try {
            fw = new FileOutputStream(f);
            fw.write(s.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void openActivity1()
    {
        Intent i=new Intent(this,Enter.class);
        startActivity(i);
    }
    void openActivity2()
    {
        Intent i=new Intent(this,DeleteFiles.class);
        startActivity(i);
    }
    void openActivity4()
    {
        Intent i=new Intent(this,NewPond.class);
        startActivity(i);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1=findViewById(R.id.bt1);
        b2=findViewById(R.id.bt2);
        b3=findViewById(R.id.bt3);
        bt4=findViewById(R.id.bt4);
        tw4=findViewById(R.id.tv2);

        Calendar cw= Calendar.getInstance();
        Date date;
        date=cw.getTime();
        String d= new SimpleDateFormat("dd").format(date);
        tw4.setText(d);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Acttxt("Enter");
                openActivity1();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Acttxt("View");
                openActivity1();
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Acttxt("Delete");
                openActivity2();
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Acttxt("NewPonds");
                openActivity4();
            }
        });

    }
}