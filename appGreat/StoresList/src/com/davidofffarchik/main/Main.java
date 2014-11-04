package com.davidofffarchik.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.davidofffarchik.R;
import com.davidofffarchik.addnewshop.AddNewShop;
import com.davidofffarchik.constans.Constans;
import com.davidofffarchik.fragments.MapFragment;
import com.davidofffarchik.fragments.StoreFragment;

public class Main extends ActionBarActivity implements ActionBar.TabListener {

    private SharedPreferences sharedPreferences;
    private final static String PRIVATE_TOKEN = "mainToken";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.Tab tabStore = actionbar.newTab();
        tabStore.setText(R.string.stores);
        tabStore.setTabListener(this);
        tabStore.setTag(Constans.TAG_STORES);
        actionbar.addTab(tabStore);
        tabStore.setTabListener(this);

        ActionBar.Tab tabMap = actionbar.newTab();
        tabMap.setText(R.string.map);
        tabMap.setTabListener(this);
        tabMap.setTag(Constans.TAG_MAP);
        actionbar.addTab(tabMap);
        tabMap.setTabListener(this);
    }

    private String getToken() {
        Intent intent = getIntent();
        return intent.getExtras().getString("token");
    }

    private void saveToken(){
        sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(PRIVATE_TOKEN, getToken());
        edit.commit();
        Log.v(PRIVATE_TOKEN, "saveToken " + getToken());
    }

    private String getSavedToken(){
        sharedPreferences = getPreferences(MODE_PRIVATE);
        String getSavedToken = sharedPreferences.getString(PRIVATE_TOKEN, getToken());
        Log.v(PRIVATE_TOKEN, "getSavedToken " + getSavedToken);
        return getSavedToken;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        if(Constans.TAG_STORES.equals(tab.getTag())){
            StoreFragment storeList = new StoreFragment();
            saveToken();
            Bundle bundle = new Bundle();
            bundle.putString("token", getToken());
            storeList.setArguments(bundle);
            ft.replace(R.id.fragments_view_two, storeList, Constans.TAG_STORES);
        }else{
            MapFragment storeMap = new MapFragment();
            saveToken();
            ft.replace(R.id.fragments_view_two, storeMap, Constans.TAG_MAP);
        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab,  FragmentTransaction ft) {}

    @Override
    public void onTabReselected(ActionBar.Tab tab,  FragmentTransaction ft) {}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_for_actiontab, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action :
                Intent intent = new Intent(this, AddNewShop.class);
                intent.putExtra("token", getSavedToken());
                //Intent intent = new Intent(this, SignInRegister.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
