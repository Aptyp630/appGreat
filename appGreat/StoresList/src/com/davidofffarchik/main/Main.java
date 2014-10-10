package com.davidofffarchik.main;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import com.davidofffarchik.R;
import com.davidofffarchik.constans.Constans;
import com.davidofffarchik.fragments.MapFragment;
import com.davidofffarchik.fragments.StoreFragment;

public class Main extends ActionBarActivity implements ActionBar.TabListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ActionBar actionbar = getSupportActionBar();
        //устанавливаем режим навигации
        actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        //first TAB item
        ActionBar.Tab tabStore = actionbar.newTab();
        tabStore.setText(R.string.stores);
        tabStore.setTabListener(this);
        tabStore.setTag(Constans.TAG_STORES);
        actionbar.addTab(tabStore);
        tabStore.setTabListener(this);

        //second TAB item
        ActionBar.Tab tabMap = actionbar.newTab();
        tabMap.setText(R.string.map);
        tabMap.setTabListener(this);
        tabMap.setTag(Constans.TAG_MAP);
        actionbar.addTab(tabMap);
        tabMap.setTabListener(this);
    }

    //выбранный таб
    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        if(Constans.TAG_STORES.equals(tab.getTag())){
            StoreFragment storeList = new StoreFragment();
            ft.replace(R.id.fragments_view_two, storeList);
        }else{
            MapFragment storeMap = new MapFragment();
            ft.replace(R.id.fragments_view_two, storeMap);
        }
    }

    //метод, который уведомляет, что выбранный таб уже не выбран, т.к.
    //был выбран следующий таб
    @Override
    public void onTabUnselected(ActionBar.Tab tab,  FragmentTransaction ft) {
        //nothing doing
    }

    //таб, который был выбран снова
    @Override
    public void onTabReselected(ActionBar.Tab tab,  FragmentTransaction ft) {
        //nothing doing
    }
}
