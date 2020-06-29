package dev.cat.mahmoudelbaz.vaccination;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class Home extends AppCompatActivity {

    ArrayList<Vaccine_item> vaccines = new ArrayList<Vaccine_item>();
    GridView list;
    VaccineAdapter adapter;
    ImageView bottomHome, homeBg;
    SharedPreferences shared;
    private int READ_STORAGE_PERMISSION_REQUEST_CODE = 100;
    private static final String AUTHORITY = "dev.cat.mahmoudelbaz.vaccination";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        shared = getSharedPreferences("id", Context.MODE_PRIVATE);

        bottomHome = findViewById(R.id.imgBottomHome);
        homeBg = findViewById(R.id.imgHomeBg);

        Picasso.with(getApplicationContext()).load(R.drawable.bottom).into(bottomHome);
        Picasso.with(getApplicationContext()).load(R.drawable.main).fit().centerCrop().into(homeBg);


        list = (GridView) findViewById(R.id.vaccinesgridView);

        vaccines.add(new Vaccine_item(0, "", R.drawable.sec1, R.color.sec1color));
        vaccines.add(new Vaccine_item(1, "", R.drawable.sec2, R.color.sec1color));
        vaccines.add(new Vaccine_item(2, "", R.drawable.sec3, R.color.sec1color));
        vaccines.add(new Vaccine_item(3, "", R.drawable.sec4, R.color.sec1color));
        vaccines.add(new Vaccine_item(4, "", R.drawable.sec5, R.color.sec1color));
        vaccines.add(new Vaccine_item(5, "", R.drawable.sec6, R.color.sec1color));
        vaccines.add(new Vaccine_item(6, "", R.drawable.sec7, R.color.sec1color));
        vaccines.add(new Vaccine_item(7, "", R.drawable.sec8, R.color.sec1color));
        vaccines.add(new Vaccine_item(8, "", R.drawable.sec9, R.color.sec1color));

        adapter = new VaccineAdapter("", true, Home.this, vaccines);
        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (vaccines.get(i).getId() != 7) {
                    Intent intent = new Intent(Home.this, Vaccine_details.class);
                    SharedPreferences.Editor myEdit = shared.edit();
                    myEdit.putInt("vaccineId", vaccines.get(i).getId());
                    myEdit.commit();
                    startActivity(intent);
                } else if (vaccines.get(i).getId() == 7)
                    LoadPdfFile("Ministry of National Guard Health affairs");

            }
        });

        bottomHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (checkPermissionForReadExtertalStorage() != true)
                    try {
                        requestPermissionForReadExtertalStorage();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                LoadPdfFile("AdultImmunizationGuidelines");
            }
        });
    }


    public boolean checkPermissionForReadExtertalStorage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int result = getApplicationContext().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
            return result == PackageManager.PERMISSION_GRANTED;
        }
        return false;
    }

    public void requestPermissionForReadExtertalStorage() throws Exception {
        try {
            ActivityCompat.requestPermissions((Activity) getApplicationContext(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    READ_STORAGE_PERMISSION_REQUEST_CODE);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    static private void copy(InputStream in, File dst) throws IOException {
        FileOutputStream out = new FileOutputStream(dst);
        byte[] buf = new byte[1024];
        int len;

        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }

        in.close();
        out.close();
    }

    private void LoadPdfFile(String fileName) {

     /*   File f = new File(getFilesDir(), fileName + ".pdf");


            AssetManager assets=getAssets();
            try {
                copy(assets.open(fileName + ".pdf"), f);
            }
            catch (IOException e) {
                Log.e("FileProvider", "Exception copying from assets", e);
            }
*/

       /* Intent i=
                new Intent(Intent.ACTION_VIEW, FileProvider.getUriForFile(this, AUTHORITY, f));

        i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        startActivity(i);
        finish();*/

        Intent myIntent = new Intent(Home.this, pdfviewer.class);
        myIntent.putExtra("key", fileName); //Optional parameters
        Home.this.startActivity(myIntent);
    }

}
