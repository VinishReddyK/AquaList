package thevoidcommunity.netlify.aqualist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DeleteDateFile extends AppCompatActivity {
        TextView tv1,tv2;
        Button b1;
    void openActivity()
    {
        Intent i=new Intent(this,DeleteFiles.class);
        startActivity(i);
        this.finish();
    }
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
    String Get_Date()
    {
        String s="";
        File f=new File(getExternalFilesDir(""),"RootTxt.txt");
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            br.readLine();
            s=br.readLine();
            String[] s1= s.split("/");
            return s1[0]+"_"+s1[1]+"_"+s1[2];
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return s;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_date_file);
        tv2=findViewById(R.id.text5);
        tv2.setGravity(Gravity.CENTER);
        tv1=findViewById(R.id.textView7);
        b1=findViewById(R.id.but5);

        int flag=0;

        tv1.setText(Get_Pond_name()+"---"+Get_Date());
        File f=new File(getExternalFilesDir("pond/"+Get_Pond_name()),Get_Date()+".txt");
        Boolean b;
        try {
            b=f.createNewFile();
            if(b==true) {
                tv1.setVisibility(View.INVISIBLE);
                tv2.setText("No data Found In This Date");
                b1.setText("Return");
                flag=1;
                f.delete();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int finalFlag = flag;
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File f=new File(getExternalFilesDir("pond/"+Get_Pond_name()),Get_Date()+".txt");
                if(finalFlag ==0)
                    f.delete();
                EnterDate.a.finish();
                Enter.a.finish();
                DeleteFiles.a.finish();
                openActivity();
            }
        });
    }
}