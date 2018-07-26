package id.amoled.newsgits.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import id.amoled.newsgits.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("Choose a category");

        View headline, bisnis, ent, health, science, sports, tech;

        health = findViewById(R.id.newsHealth);
        bisnis = findViewById(R.id.newsBusiness);
        ent = findViewById(R.id.newsEntertainment);
        science = findViewById(R.id.newsScience);
        sports = findViewById(R.id.newsSports);
        tech = findViewById(R.id.newsTech);
        headline = findViewById(R.id.newsHeadline);

        health.setOnClickListener(this);
        bisnis.setOnClickListener(this);
        ent.setOnClickListener(this);
        science.setOnClickListener(this);
        sports.setOnClickListener(this);
        tech.setOnClickListener(this);
        headline.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.newsHealth: {
                final View gambar = findViewById(R.id.img_health);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                    Intent intent = new Intent(MainActivity.this, HealthActivity.class);
                    ActivityOptionsCompat options = ActivityOptionsCompat
                            .makeSceneTransitionAnimation
                                    (MainActivity.this, gambar, ViewCompat.getTransitionName(gambar));
                    startActivity(intent, options.toBundle());

                } else {

                    Intent intent = new Intent(MainActivity.this, HealthActivity.class);
                    startActivity(intent);

                }

                break;
            }
            case R.id.newsEntertainment: {
                final View gambar = findViewById(R.id.imgEnt);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                    Intent intent = new Intent(MainActivity.this, EntActivity.class);
                    ActivityOptionsCompat options = ActivityOptionsCompat
                            .makeSceneTransitionAnimation
                                    (MainActivity.this, gambar, ViewCompat.getTransitionName(gambar));
                    startActivity(intent, options.toBundle());

                } else {

                    Intent intent = new Intent(MainActivity.this, EntActivity.class);
                    startActivity(intent);

                }
                break;
            }
            case R.id.newsBusiness:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    final View gambar = findViewById(R.id.img_bisnis);

                    Intent intent = new Intent(MainActivity.this, BusinessActivity.class);
                    ActivityOptionsCompat options = ActivityOptionsCompat
                            .makeSceneTransitionAnimation
                                    (MainActivity.this, gambar, ViewCompat.getTransitionName(gambar));
                    startActivity(intent, options.toBundle());

                } else {

                    Intent intent = new Intent(MainActivity.this, BusinessActivity.class);
                    startActivity(intent);

                }
                break;
            case R.id.newsScience:

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    final View gambar = findViewById(R.id.img_science);

                    Intent intent = new Intent(MainActivity.this, ScienceActivity.class);
                    ActivityOptionsCompat options = ActivityOptionsCompat
                            .makeSceneTransitionAnimation
                                    (MainActivity.this, gambar, ViewCompat.getTransitionName(gambar));
                    startActivity(intent, options.toBundle());

                } else {

                    Intent intent = new Intent(MainActivity.this, ScienceActivity.class);
                    startActivity(intent);

                }

                break;
            case R.id.newsSports:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    final View gambar = findViewById(R.id.img_sports);

                    Intent intent = new Intent(MainActivity.this, SportsActivity.class);
                    ActivityOptionsCompat options = ActivityOptionsCompat
                            .makeSceneTransitionAnimation
                                    (MainActivity.this, gambar, ViewCompat.getTransitionName(gambar));
                    startActivity(intent, options.toBundle());

                } else {

                    Intent intent = new Intent(MainActivity.this, SportsActivity.class);
                    startActivity(intent);

                }
                break;
            case R.id.newsTech:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    final View gambar = findViewById(R.id.img_tech);

                    Intent intent = new Intent(MainActivity.this, TechActivity.class);
                    ActivityOptionsCompat options = ActivityOptionsCompat
                            .makeSceneTransitionAnimation
                                    (MainActivity.this, gambar, ViewCompat.getTransitionName(gambar));
                    startActivity(intent, options.toBundle());

                } else {

                    Intent intent = new Intent(MainActivity.this, TechActivity.class);
                    startActivity(intent);

                }
                break;
            case R.id.newsHeadline:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    final View gambar = findViewById(R.id.img_head);

                    Intent intent = new Intent(MainActivity.this, HeadlineActivity.class);
                    ActivityOptionsCompat options = ActivityOptionsCompat
                            .makeSceneTransitionAnimation
                                    (MainActivity.this, gambar, ViewCompat.getTransitionName(gambar));
                    startActivity(intent, options.toBundle());

                } else {

                    Intent intent = new Intent(MainActivity.this, HeadlineActivity.class);
                    startActivity(intent);

                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        mauKeluar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.about) {
            tampilkanAbout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void mauKeluar() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Konfirmasi");
        builder.setMessage("Anda yakin mau keluar? TvT");
        builder.setCancelable(true);
        builder.setNegativeButton("TIDAK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("YA", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                onDestroy();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void tampilkanAbout() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Jareeda");
        builder.setMessage("Developer: Muhammad Syakir Arif \nPowered by AMOLED Academy, TI UNIDA Gontor.");
        builder.setCancelable(true);
        builder.setIcon(getResources().getDrawable(R.mipmap.ic_launcher));
        builder.setNeutralButton("OKAY", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

}
