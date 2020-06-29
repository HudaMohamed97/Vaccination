package dev.cat.mahmoudelbaz.vaccination;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {


    ImageView start,mainTop,mainBg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = findViewById(R.id.imgStart);
        mainTop = findViewById(R.id.imgMainTop);
        mainBg = findViewById(R.id.imgMainBg);

        Picasso.with(getApplicationContext()).load(R.drawable.start).into(start);
        Picasso.with(getApplicationContext()).load(R.drawable.maintop).into(mainTop);
        Picasso.with(getApplicationContext()).load(R.drawable.main).fit().centerCrop().into(mainBg);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Home.class));
            }
        });
    }
}
