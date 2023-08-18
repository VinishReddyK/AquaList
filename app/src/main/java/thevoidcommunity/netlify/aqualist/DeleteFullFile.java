package thevoidcommunity.netlify.aqualist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class DeleteFullFile extends AppCompatActivity {
    public static Activity a;
    TextView textView,tv;
    Button button;
    void openActivity()
    {
        Intent i=new Intent(this,DeleteFiles.class);
        startActivity(i);
        this.finish();
    }
    String getTxt()
    {
        String s="";
        File f= new File(getExternalFilesDir(""),"RootTxt.txt");
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br= new BufferedReader(fr);
            s=br.readLine();
            return s;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    void DeleteFile(File f)
    {
        String[] pathnames;
        pathnames = f.list();
        Arrays.sort(pathnames);
        for (int i=0;i<pathnames.length;i++)
        {
            File file=new File(f+"/"+pathnames[i]);
            file.delete();
        }
        f.delete();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_full_file);
        textView=findViewById(R.id.textView7);
        tv=findViewById(R.id.text5);
        tv.setGravity(Gravity.CENTER);
        button=findViewById(R.id.but5);

        a=this;

        textView.setText(getTxt());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteFile(new File(getExternalFilesDir("pond"),getTxt()));
                DeleteFiles.a.finish();
                Enter.a.finish();
                openActivity();
            }
        });

    }
}