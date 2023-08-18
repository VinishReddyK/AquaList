package thevoidcommunity.netlify.aqualist;

import androidx.appcompat.app.AppCompatActivity;

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
import java.io.IOException;
import java.util.Arrays;

public class DataEntry extends AppCompatActivity {
    EditText ed1,ed2,ed3,c1,c2,c3,c4,c5,c6;
    TextView textView;
    Button b,bb,bn,b8;

    int setTextToFiles()
    {
        int flag=0;
        String p_name=Get_Pond_name();
        String date=Get_Date();
        String s1 ="";
        String s2 ="";
        String s3 ="";
        String cc1="";
        String cc2="";
        String cc3="";
        String cc4="";
        String cc5="";
        String cc6="";
        try {
            s1 = ed1.getText().toString();
            s2 = ed2.getText().toString();
            s3 = ed3.getText().toString();
            cc1 = c1.getText().toString();
            cc2 = c2.getText().toString();
            cc3 = c3.getText().toString();
            cc4 = c4.getText().toString();
            cc5 = c5.getText().toString();
            cc6 = c6.getText().toString();

            if(s1.equals("") && s2.equals("") && s3.equals("") && cc1.equals("") && cc2.equals("") && cc3.equals("") && cc4.equals("") && cc5.equals("") && cc6.equals(""))
            {
                File f = new File(getExternalFilesDir("pond/"+p_name),date);
                if(f.createNewFile()==false)
                {
                    f.delete();
                    return 0;
                }
                f.delete();
                return 0;
            }

            Float.parseFloat(s1);
            Float.parseFloat(s2);
            Float.parseFloat(s2);
            Float.parseFloat(cc1);
            Float.parseFloat(cc2);
            Float.parseFloat(cc3);
            Float.parseFloat(cc4);
            Float.parseFloat(cc5);
            Float.parseFloat(cc6);

        }catch (NumberFormatException e)
        {
            flag=1;
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(flag==1)
        {
            return 2;
        }
        s1=s1+"-"+cc1+"-"+cc2;
        s2=s2+"-"+cc3+"-"+cc4;
        s3=s3+"-"+cc5+"-"+cc6;

        String s=s1+"\n"+s2+"\n"+s3;
        File f = new File(getExternalFilesDir("pond/"+p_name),date);

        FileOutputStream fw= null;
        try {
            new FileOutputStream(f,false).close();
            fw = new FileOutputStream(f);

            fw.write(s.getBytes());
        } catch (Exception e) {
            System.out.println("OOPS  :  " + e);
        }

        return 0;
    }
    void update_pond(int j)
    {
        String[] pathnames;
        File f1= new File(getExternalFilesDir(""),"PondNo.txt");
        File f2= new File(getExternalFilesDir(""),"RootTxt.txt");
        File f3 = new File(getExternalFilesDir("pond"),"");
        pathnames = f3.list();
        Arrays.sort(pathnames);
        try {

            FileReader fr1=new FileReader(f1);
            FileReader fr2=new FileReader(f2);


            BufferedReader br1=new BufferedReader(fr1);
            BufferedReader br2=new BufferedReader(fr2);
            StringBuilder sb= new StringBuilder();

            j=j+Integer.parseInt(br1.readLine());
            System.out.println(j);
            if(j<=0)
            {
                Toastmsg("Reached The Top");
                return;
            }
            if(j>pathnames.length)
            {
                Toastmsg("Reached The Bottom");
                return;
            }

            br2.readLine();
            sb.append(pathnames[j-1]);
            sb.append("\n");
            sb.append(br2.readLine());
            FileOutputStream fos=new FileOutputStream(f2);

            fos.write(sb.toString().getBytes());
            fos.close();
            FileOutputStream fose=new FileOutputStream(f1);
            fose.write(String.valueOf(j).getBytes());
        }catch (Exception e)
        {
            System.out.println(e);
        }
    }
    void setTextBlank()
    {
        ed1.setText("");
        c1.setText("");
        c2.setText("");
        ed2.setText("");
        c3.setText("");
        c4.setText("");
        ed3.setText("");
        c5.setText("");
        c6.setText("");
    }

    void SetTxt(File file)
    {
        ed1.setText("");
        c1.setText("");
        c2.setText("");
        ed2.setText("");
        c3.setText("");
        c4.setText("");
        ed3.setText("");
        c5.setText("");
        c6.setText("");

        Boolean b;
        System.out.println(file);
        File f= new File(getExternalFilesDir(""),"RootTxt.txt");
        try {
            FileReader fileReader=new FileReader(f);
            BufferedReader bufferedReader= new BufferedReader(fileReader);
            b=file.createNewFile();
            textView.setText(bufferedReader.readLine()+"--"+bufferedReader.readLine());
            if(b==true) {
                file.delete();
                return;
            }
            FileReader fr=new FileReader(file);
            BufferedReader br= new BufferedReader(fr);
            String[] s1;
            s1 = br.readLine().split("-");
            String[] s2;
            s2 = br.readLine().split("-");
            String[] s3;
            s3 = br.readLine().split("-");


            ed1.setText(s1[0]);
            c1.setText(s1[1]);
            c2.setText(s1[2]);
            ed2.setText(s2[0]);
            c3.setText(s2[1]);
            c4.setText(s2[2]);
            ed3.setText(s3[0]);
            c5.setText(s3[1]);
            c6.setText(s3[2]);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void  Toastmsg(String msg)
    {
        Toast toast= Toast.makeText(this,msg,Toast.LENGTH_SHORT);
        toast.getView().setBackgroundResource(R.drawable.customoast);
        View view = toast.getView();
        TextView text = (TextView)view.findViewById(android.R.id.message);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            text.setTextAppearance(R.style.toastTextStyle);
        }
        toast.show();
        return;
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
            return s1[0]+"_"+s1[1]+"_"+s1[2]+".txt";
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return s;
    }
    void openNextActivity()
    {
        Intent i=new Intent(this,Enter.class);
        startActivity(i);
        this.finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);
        ed1=findViewById(R.id.edittext);
        ed2=findViewById(R.id.edittext2);
        ed3=findViewById(R.id.edittext3);
        b=findViewById(R.id.button7);
        c1=findViewById(R.id.c1);
        c2=findViewById(R.id.c2);
        c3=findViewById(R.id.c3);
        c4=findViewById(R.id.c4);
        c5=findViewById(R.id.c5);
        c6=findViewById(R.id.c6);
        textView=findViewById(R.id.textView6);
        bb=findViewById(R.id.button6);
        bn=findViewById(R.id.button);
        b8=findViewById(R.id.button8);

        ed1.setRawInputType(Configuration.KEYBOARD_12KEY);
        ed3.setRawInputType(Configuration.KEYBOARD_12KEY);
        ed2.setRawInputType(Configuration.KEYBOARD_12KEY);
        c1.setRawInputType(Configuration.KEYBOARD_12KEY);
        c2.setRawInputType(Configuration.KEYBOARD_12KEY);
        c3.setRawInputType(Configuration.KEYBOARD_12KEY);
        c4.setRawInputType(Configuration.KEYBOARD_12KEY);
        c5.setRawInputType(Configuration.KEYBOARD_12KEY);
        c6.setRawInputType(Configuration.KEYBOARD_12KEY);

        ed1.setGravity(Gravity.CENTER);
        ed2.setGravity(Gravity.CENTER);
        ed3.setGravity(Gravity.CENTER);
        c1.setGravity(Gravity.CENTER);
        c2.setGravity(Gravity.CENTER);
        c3.setGravity(Gravity.CENTER);
        c4.setGravity(Gravity.CENTER);
        c5.setGravity(Gravity.CENTER);
        c6.setGravity(Gravity.CENTER);

        SetTxt(new File(getExternalFilesDir("pond/"+Get_Pond_name()),Get_Date()));

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int j=setTextToFiles();
                if(j==2) { Toastmsg("Enter Only Numbers"); return; }
                if(j==0){
                }
                Enter.a.finish();
                EnterDate.a.finish();
                openNextActivity();
            }
        });
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int j=setTextToFiles();
                if(j==1) {}// return; }
                    update_pond(1);
                    SetTxt(new File(getExternalFilesDir("pond/"+Get_Pond_name()),Get_Date()));

            }
        });
        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int j=setTextToFiles();
                if(j==1) { }

                    update_pond(-1);
                    SetTxt(new File(getExternalFilesDir("pond/"+Get_Pond_name()),Get_Date()));

            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextBlank();
            }
        });
    }
}