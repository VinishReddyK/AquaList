package thevoidcommunity.netlify.aqualist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class NewPond extends AppCompatActivity {
    Button b1;
    EditText ed1;
    void CreateNewPOnd(String name)
    {
        if(name.equalsIgnoreCase(""))
        {
            Toast toast= Toast.makeText(this,"Can Not Be Empty",Toast.LENGTH_SHORT);
            toast.getView().setBackgroundResource(R.drawable.customoast);
            View view = toast.getView();
            TextView text = (TextView)view.findViewById(android.R.id.message);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                text.setTextAppearance(R.style.toastTextStyle);
            }
            toast.show();
            return;
        }
        String[] pathnames;
        File f1 = new File(getExternalFilesDir("pond"),"");
        pathnames = f1.list();
        for (String pathname : pathnames) {
            if(pathname.equalsIgnoreCase(name))
            {
                Toast toast= Toast.makeText(this,"File Already Exists ",Toast.LENGTH_SHORT);
                toast.getView().setBackgroundResource(R.drawable.customoast);
                View view = toast.getView();
                TextView text = (TextView)view.findViewById(android.R.id.message);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    text.setTextAppearance(R.style.toastTextStyle);
                }
                toast.show();
                return;
            }
        }
        File f = new File(getExternalFilesDir("pond/"+name),"");
        Toast toast= Toast.makeText(this,"File Created ",Toast.LENGTH_SHORT);
        toast.getView().setBackgroundResource(R.drawable.customoast);
        View view = toast.getView();
        TextView text = (TextView)view.findViewById(android.R.id.message);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            text.setTextAppearance(R.style.toastTextStyle);
        }
        toast.show();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pond);
        b1=findViewById(R.id.button1);
        ed1=findViewById(R.id.edittext1);
        ed1.setGravity(Gravity.CENTER);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateNewPOnd(ed1.getText().toString());
            }
        });
    }
}