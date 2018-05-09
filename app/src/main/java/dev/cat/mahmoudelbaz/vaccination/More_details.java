package dev.cat.mahmoudelbaz.vaccination;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

public class More_details extends AppCompatActivity {

    ImageView section, topLogo, bottomIcon, detailsBg , zoomIcon;
    Button indication, administration, contraindications, sectionNamebtn;
    TextView sectionName,txtView , sectionRef;
    String json = null;
    String icon, name, ref, bg, btnBg, textColor,detailNametxt;
    SharedPreferences shared;
    int currentId, detailsId;
    int scale = 0;


    String indicationtxt, administrationtxt, contraintxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_details);

        shared = getSharedPreferences("id", Context.MODE_PRIVATE);

        currentId = shared.getInt("vaccineId", 0);
        detailsId = shared.getInt("detailsId", 0);

        txtView = findViewById(R.id.txtView);
        txtView.setMovementMethod(new ScrollingMovementMethod());
        section = findViewById(R.id.imgSection);
        topLogo = findViewById(R.id.imgTopLogo);
        bottomIcon = findViewById(R.id.imgBottomIcon);
        detailsBg = findViewById(R.id.imgDetailsBg);
        sectionNamebtn = findViewById(R.id.imgSectionName);
        sectionName = findViewById(R.id.txtSectionName);
        zoomIcon = findViewById(R.id.imgZoomIcon);

        section.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        indication = findViewById(R.id.btnIndication);
        administration = findViewById(R.id.btnAdministration);
        contraindications = findViewById(R.id.btnContraindications);

           sectionRef = findViewById(R.id.txtref);
           sectionRef.setMovementMethod(new ScrollingMovementMethod());

        Picasso.with(getApplicationContext()).load(R.drawable.sec8).into(topLogo);
        Picasso.with(getApplicationContext()).load(R.drawable.bottomicon).into(bottomIcon);

        setData();

        zoomIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (scale==0){
                    sectionRef.setTextSize(18);
                    sectionRef.scrollTo(0,0);
                    zoomIcon.setImageResource(R.drawable.zoomout_icon);
                    scale = 1;
                }
                else if (scale == 1){
                    sectionRef.setTextSize(10);
                    sectionRef.scrollTo(0,0);
                    zoomIcon.setImageResource(R.drawable.zoomin_icon);
                    scale = 0;
                }
            }
        });


        indication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtView.setText(Html.fromHtml(indicationtxt));
                indication.setAlpha((float) 0.5);
                administration.setAlpha((float) 1);
                contraindications.setAlpha((float) 1);
            }
        });

        administration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtView.setText(Html.fromHtml(administrationtxt));
                indication.setAlpha((float) 1);
                administration.setAlpha((float) 0.5);
                contraindications.setAlpha((float) 1);
            }
        });

        contraindications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtView.setText(Html.fromHtml(contraintxt));
                indication.setAlpha((float) 1);
                administration.setAlpha((float) 1);
                contraindications.setAlpha((float) 0.5);
            }
        });

    }


    private void setData() {
        try {
            InputStream is = getAssets().open(currentId + ".json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            JSONObject obj = new JSONObject(json);
            JSONArray vaccinesArray = obj.getJSONArray("vaccines");

            icon = obj.getString("icon");
            name = obj.getString("name");
            ref = obj.getString("ref");
            bg = obj.getString("bg");
            btnBg = obj.getString("btnBg");
            textColor = obj.getString("textColor");

            int logo = getResources().getIdentifier(icon, "drawable", getPackageName());
            int color = getResources().getColor(getResources().getIdentifier(textColor, "color", getPackageName()));
            int colorstatus = getResources().getColor(getResources().getIdentifier(textColor+"status", "color", getPackageName()));
//
//            int listBg = getResources().getColor(getResources().getIdentifier(btnBg, "color", getPackageName()));
            int listBg = getResources().getIdentifier(btnBg, "drawable", getPackageName());
            int sectionBg = getResources().getIdentifier(bg, "drawable", getPackageName());


            JSONObject currentObj = vaccinesArray.getJSONObject(detailsId);
            indicationtxt = currentObj.getString("indication");
            administrationtxt = currentObj.getString("administration");
            contraintxt = currentObj.getString("contrain");
            detailNametxt = currentObj.getString("name");

            indication.setTextColor(color);
            indication.setBackgroundResource(listBg);
            administration.setTextColor(color);
            administration.setBackgroundResource(listBg);

            contraindications.setTextColor(color);
            contraindications.setBackgroundResource(listBg);

            sectionName.setText(Html.fromHtml(name));
            sectionName.setTextColor(color);

            sectionNamebtn.setText(Html.fromHtml(detailNametxt));
            sectionNamebtn.setTextColor(color);
            sectionNamebtn.setBackgroundResource(listBg);

               sectionRef.setText(Html.fromHtml(ref));
                sectionRef.setTextColor(color);

            txtView.setTextColor(color);

            Picasso.with(getApplicationContext()).load(logo).into(section);
            Picasso.with(getApplicationContext()).load(sectionBg).fit().centerCrop().into(detailsBg);


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(colorstatus);
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }
}
