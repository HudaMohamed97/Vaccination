package dev.cat.mahmoudelbaz.vaccination;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Vaccine_details extends AppCompatActivity {

    ArrayList<Vaccine_item> vaccines = new ArrayList<Vaccine_item>();
    GridView list;
    VaccineAdapter adapter;
    ImageView section, topLogo, bottomIcon, detailsBg, zoomIcon;
    String json = null;
    String icon, name, ref, bg, btnBg, textColor;
    TextView sectionName, sectionRef;
    SharedPreferences shared;
    int currentId;
    int scale = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_details);


        shared = getSharedPreferences("id", Context.MODE_PRIVATE);

        currentId = shared.getInt("vaccineId", 0);

        section = findViewById(R.id.imgSection);
        topLogo = findViewById(R.id.imgTopLogo);
        bottomIcon = findViewById(R.id.imgBottomIcon);
        detailsBg = findViewById(R.id.imgDetailsBg);
        zoomIcon = findViewById(R.id.imgZoomIcon);

        sectionName = findViewById(R.id.txtSectionName);
        //   sectionRef = findViewById(R.id.txtref);
        //   sectionRef.setMovementMethod(new ScrollingMovementMethod());
        list = (GridView) findViewById(R.id.vaccinesgridView);

        section.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

//        zoomIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (scale==0){
//                    sectionRef.setTextSize(18);
//                    sectionRef.scrollTo(0,0);
//                    zoomIcon.setImageResource(R.drawable.zoomout_icon);
//                    scale = 1;
//                }
//                else if (scale == 1){
//                    sectionRef.setTextSize(10);
//                    sectionRef.scrollTo(0,0);
//                    zoomIcon.setImageResource(R.drawable.zoomin_icon);
//                    scale = 0;
//                }
//            }
//        });

        setData();


        Picasso.with(getApplicationContext()).load(R.drawable.sec8).into(topLogo);
        Picasso.with(getApplicationContext()).load(R.drawable.bottomicon).into(bottomIcon);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Vaccine_details.this, More_details.class);
                SharedPreferences.Editor myEdit = shared.edit();
                myEdit.putInt("detailsId", vaccines.get(i).getId());
                myEdit.commit();
                startActivity(intent);
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
            int colorstatus = getResources().getColor(getResources().getIdentifier(textColor + "status", "color", getPackageName()));
//            int listBg = getResources().getColor(getResources().getIdentifier(btnBg, "color", getPackageName()));
            int listBg = getResources().getIdentifier(btnBg, "drawable", getPackageName());
            int sectionBg = getResources().getIdentifier(bg, "drawable", getPackageName());

            for (int i = 0; i < vaccinesArray.length(); i++) {
                JSONObject currentObj = vaccinesArray.getJSONObject(i);
                vaccines.add(new Vaccine_item(currentObj.getInt("id"), currentObj.getString("name"), listBg, color));

            }

            sectionName.setText(Html.fromHtml(name));
            sectionName.setTextColor(color);
            //   sectionRef.setText(Html.fromHtml(ref));
            //    sectionRef.setTextColor(color);
            Picasso.with(getApplicationContext()).load(logo).into(section);
            Picasso.with(getApplicationContext()).load(sectionBg).fit().centerCrop().into(detailsBg);
            adapter = new VaccineAdapter(name, false, Vaccine_details.this, vaccines);
            list.setAdapter(adapter);

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
