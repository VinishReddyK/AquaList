package thevoidcommunity.netlify.aqualist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class View extends AppCompatActivity {
    Toast toast;
    TextView ed1,ed2,ed3,c1,c2,c3,c4,c5,c6,textView,tv,tv1;
    TextView textView1,textView2,textView3,textView4;
    Button b,b9,b10;
    void  Toastmsg(String msg)
    {
        toast= Toast.makeText(this,msg,Toast.LENGTH_SHORT);
        toast.getView().setBackgroundResource(R.drawable.customoast);
        android.view.View view = toast.getView();
        TextView text = (TextView)view.findViewById(android.R.id.message);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            text.setTextAppearance(R.style.toastTextStyle);
        }
        toast.show();
        return;
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
    Float Get_total(File file)
    {
        Float sum = new Float(0.0);
        String [] pathname=file.list();
        Arrays.sort(pathname);
        for(int i=0;i<pathname.length;i++)
        {
            try {
                FileReader fr = new FileReader(new File(getExternalFilesDir("pond/"+Get_Pond_name()),pathname[i]));
                System.out.println(new File(getExternalFilesDir("pond/"+Get_Pond_name()),pathname[i]));
                BufferedReader br = new BufferedReader(fr);
                String[] s1;
                s1 = br.readLine().split("-");
                String[] s2;
                s2 = br.readLine().split("-");
                String[] s3;
                s3 = br.readLine().split("-");
                sum=sum+Float.parseFloat(s1[0])+Float.parseFloat(s2[0])+Float.parseFloat(s3[0]);
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
            if(pathname[i].equalsIgnoreCase(Get_Date()+".txt")) {
                break;
            }
        }
        return sum;
    }
    void SetTxt(File file)
    {
        Boolean b;
        try {
            File f= new File(getExternalFilesDir(""),"RootTxt.txt");
            FileReader fileReader=new FileReader(f);
            BufferedReader bufferedReader= new BufferedReader(fileReader);
            textView4.setText(bufferedReader.readLine()+"--"+bufferedReader.readLine());
            b=file.createNewFile();
            if(b==true) {
                textView.setVisibility(android.view.View.VISIBLE);
                ed1.setVisibility(android.view.View.INVISIBLE);
                ed2.setVisibility(android.view.View.INVISIBLE);
                ed3.setVisibility(android.view.View.INVISIBLE);
                c1.setVisibility(android.view.View.INVISIBLE);
                c2.setVisibility(android.view.View.INVISIBLE);
                c3.setVisibility(android.view.View.INVISIBLE);
                c4.setVisibility(android.view.View.INVISIBLE);
                c5.setVisibility(android.view.View.INVISIBLE);
                c6.setVisibility(android.view.View.INVISIBLE);
                textView1.setVisibility(android.view.View.INVISIBLE);
                textView2.setVisibility(android.view.View.INVISIBLE);
                textView3.setVisibility(android.view.View.INVISIBLE);
                tv.setVisibility(android.view.View.INVISIBLE);
                tv1.setVisibility(android.view.View.INVISIBLE);
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
            tv.setText("Total : "+String.valueOf(Float.parseFloat(s1[0])+Float.parseFloat(s2[0])+Float.parseFloat(s3[0])));
            tv1.setText("GT : "+String.valueOf(Get_total(new File(getExternalFilesDir("pond"),Get_Pond_name()))));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    void openNextActivity()
    {
        Intent i=new Intent(this,Enter.class);
        startActivity(i);
        this.finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        ed1=findViewById(R.id.edittext);
        ed2=findViewById(R.id.edittext2);
        ed3=findViewById(R.id.edittext3);
        tv=findViewById(R.id.tv);
        tv1=findViewById(R.id.tv10);

        b=findViewById(R.id.button);
        c1=findViewById(R.id.c1);
        c2=findViewById(R.id.c2);
        c3=findViewById(R.id.c3);
        c4=findViewById(R.id.c4);
        c5=findViewById(R.id.c5);
        c6=findViewById(R.id.c6);
        textView=findViewById(R.id.textView2);
        textView1=findViewById(R.id.tve1);
        textView2=findViewById(R.id.tve);
        textView3=findViewById(R.id.tve2);
        textView4=findViewById(R.id.textView8);
        b9=findViewById(R.id.button9);
        b10=findViewById(R.id.button10);

        ed1.setGravity(Gravity.CENTER);
        ed2.setGravity(Gravity.CENTER);
        ed3.setGravity(Gravity.CENTER);
        c1.setGravity(Gravity.CENTER);
        c2.setGravity(Gravity.CENTER);
        c3.setGravity(Gravity.CENTER);
        c4.setGravity(Gravity.CENTER);
        c5.setGravity(Gravity.CENTER);
        c6.setGravity(Gravity.CENTER);
        tv.setGravity(Gravity.CENTER);
        tv1.setGravity(Gravity.CENTER);
        SetTxt(new File(getExternalFilesDir("pond/"+Get_Pond_name()),Get_Date()+".txt"));

        b.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Enter.a.finish();
                EnterDate.a.finish();
                openNextActivity();
            }
        });
        b9.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                update_pond(-1);
                SetTxt(new File(getExternalFilesDir("pond/"+Get_Pond_name()),Get_Date()+".txt"));
            }
        });
        b10.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                update_pond(1);
                SetTxt(new File(getExternalFilesDir("pond/"+Get_Pond_name()),Get_Date()+".txt"));
            }
        });
    }
}