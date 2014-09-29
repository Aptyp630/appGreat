package com.example.storeslist;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import com.example.R;
import com.example.fragments.MapFragment;
import com.example.fragments.StoreFragment;

public class Main extends Activity implements ActionBar.TabListener {
    private static final String TAG_STORES = "stores";
    private static final String TAG_MAP = "layout_for_two_fragments";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //возвращает объект actionbar в этом Activity
        ActionBar actionbar = getActionBar();
        //actionbar.newTab();
        //устанавливаем режим навигации
        actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        //first TAB item
        Tab tabStore = actionbar.newTab();
        tabStore.setText(R.string.stores);
        tabStore.setTabListener(this);
        tabStore.setTag(TAG_STORES);
        actionbar.addTab(tabStore);
        tabStore.setTabListener(this);

        //second TAB item
        Tab tabMap = actionbar.newTab();
        tabMap.setText(R.string.map);
        tabMap.setTabListener(this);
        tabMap.setTag(TAG_MAP);
        actionbar.addTab(tabMap);
        tabMap.setTabListener(this);
    }

    //выбранный таб
    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        if(TAG_STORES.equals(tab.getTag())){
            StoreFragment storeList = new StoreFragment();
            ft.replace(R.id.fragments_view_two, storeList, TAG_STORES);
        }else{
            MapFragment storeMap = new MapFragment();
            ft.replace(R.id.fragments_view_two, storeMap, TAG_MAP);
        }
    }

    //метод, который уведомляет, что выбранный таб уже не выбран, т.к.
    //был выбран следующий таб
    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
        //nothing doing
    }

    //таб, который был выбран снова
    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
        //nothing doing
    }
}
