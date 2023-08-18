package thevoidcommunity.netlify.aqualist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.Arrays;

public class WnatDays extends AppCompatActivity {
    TextView tv;
    Button b;

    String Get_Pond_name()
    {
        String s="";
        File f=new File(getExternalFilesDir(""),"RootTxt.txt");
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            return br.readLine();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return s;
    }
    void SetTex()
    {
        StringBuilder db=new StringBuilder();
        File f=new File(getExternalFilesDir("pond"),Get_Pond_name());
        try {
            String[] d=f.list();
            System.out.println(d[0]);
            Arrays.sort(d);
            for(String s: d)
            {
                String[] g=s.split("\\.");
                db.append(g[0]);
                db.append("\n");
            }
            tv.setText(db.toString());
        }catch (Exception e)
        {
            System.out.println(e);
        }
    }

    void end()
    {
        this.finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wnat_days);
        tv=findViewById(R.id.datetext1);
        b=findViewById(R.id.button4);

        tv.setGravity(Gravity.CENTER);

        SetTex();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                end();
            }
        });
    }
}