package thevoidcommunity.netlify.aqualist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Arrays;

public class Enter extends AppCompatActivity {
    public static Activity a;
    EditText ed1;
    Button b1;
    TextView textView;
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
        if(s.equalsIgnoreCase("Delete_File"))
        {
            Intent i=new Intent(this,DeleteFullFile.class);
            startActivity(i);
            return;
        }
        Intent i=new Intent(this,EnterDate.class);
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
    void PrintinEditText()
    {
        String[] pathnames;
        File f1 = new File(getExternalFilesDir("pond"),"");
        pathnames = f1.list();
        Arrays.sort(pathnames);
        StringBuilder sb=new StringBuilder();
        for (int i=0;i<pathnames.length;i++) {
            sb.append(String.valueOf(i+1)+" . "+pathnames[i]);
            sb.append("\n");
        }
        textView.setText(sb.toString());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        a=this;
        ed1=findViewById(R.id.edittext1);
        ed1.setGravity(Gravity.CENTER);

        ed1.setRawInputType(Configuration.KEYBOARD_12KEY);

        b1=findViewById(R.id.button1);
        textView=findViewById(R.id.datetext);
        textView.setGravity(Gravity.CENTER);
        PrintinEditText();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String no;
                no=ed1.getText().toString();
                if(no.equalsIgnoreCase(""))
                {
                    Toastmsg("Can Not Be Empty");
                    return;
                }
                int g=0;
                try {
                    g=Integer.parseInt(no);
                }
                catch (NumberFormatException e)
                {
                    Toastmsg("Enter Only Numbers");
                    return;
                }
                g=Integer.parseInt(no);
                if(g<1)
                {
                    Toastmsg("Enter Number grater than one");
                    return;
                }
                String[] pathnames;
                File f1 = new File(getExternalFilesDir("pond"),"");
                pathnames = f1.list();
                Arrays.sort(pathnames);
                if(g>pathnames.length)
                {
                    Toastmsg("Out Of Range");
                    return;
                }
                File f2= new File(getExternalFilesDir(""),"PondNo.txt");
                File f= new File(getExternalFilesDir(""),"RootTxt.txt");
                FileOutputStream fw= null,fw2=null;
                try {
                    fw = new FileOutputStream(f);
                    fw2 = new FileOutputStream(f2);
                    fw2.write(String.valueOf(g).getBytes());
                    fw.write(pathnames[g-1].getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                openNextActivity();
            }
        });
    }
}