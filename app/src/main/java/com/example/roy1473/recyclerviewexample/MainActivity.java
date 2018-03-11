package com.example.roy1473.recyclerviewexample;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerButton;
    private ListView drawerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerList = (ListView)findViewById(R.id.drawer_ListView);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_Layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupNavigationDrawer();
        setNavigationDrawerList();

        MainFragment mainFragment = new MainFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.activity_main, mainFragment);
        transaction.commit();
    }

    private void setupNavigationDrawer(){

        drawerButton = new ActionBarDrawerToggle(this, drawerLayout,
                R.string.drawer_open, R.string.drawer_close){

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);

            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }
        };

        drawerLayout.addDrawerListener(drawerButton);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }


    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerButton.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerButton.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (drawerButton.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }


    private void setNavigationDrawerList(){
        String[] osakaAreaList = new String[]{
                "梅田　エリア",
                "福島、野田 エリア",
                "淀屋橋・本町・北浜・天満橋　エリア",
                "京橋・天満・天六・南森町　エリア",
                "心斎橋・なんば・南船場・堀江　エリア",
                "天王寺　エリア",
                "上本町・鶴橋　エリア",
                "針中野･長居･西田辺･西成区･住吉　エリア",
                "関目・千林・緑橋・深江橋　エリア",
                "大阪市その他",
                "堺・高石市・和泉市　エリア",
                "高槻市　エリア",
                "茨木市　エリア",
                "泉大津･岸和田･泉佐野･りんくう　エリア",
                "松原市･藤井寺市･富田林･南河内　エリア",
                "江坂・西中島・新大阪・十三　エリア",
                "東大阪市・八尾市・平野・大東市　エリア",
                "九条･西九条･弁天町･大正･住之江　エリア",
                "枚方・寝屋川・守口・門真　エリア",
                "大阪府その他",
                "箕面・池田　エリア"
        };

        drawerList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                osakaAreaList));

    }






}
