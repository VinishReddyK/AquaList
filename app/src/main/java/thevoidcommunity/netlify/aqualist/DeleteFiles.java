package thevoidcommunity.netlify.aqualist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class DeleteFiles extends AppCompatActivity {
    public static Activity a;
    Button b1,b2;
    void openNextActivity()
    {
        Intent i=new Intent(this,Enter.class);
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_files);
        b1=findViewById(R.id.button2);
        b2=findViewById(R.id.button3);
        a=this;

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Acttxt("Delete_File");
                openNextActivity();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Acttxt("Delete_Date_File");
                openNextActivity();
            }
        });

    }
}