package com.codewithhamad.headwaybuilders.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.codewithhamad.headwaybuilders.R;
import com.iammert.library.readablebottombar.ReadableBottomBar;


public class MainActivity extends AppCompatActivity {

  ReadableBottomBar readableBottomBar;
  FrameLayout frameLayout; // container for fragments used in this activity

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);


    // initializing views
    readableBottomBar= findViewById(R.id.mainBottomNav);
    frameLayout= findViewById(R.id.mainContainerFrameLayout);

    // by default: home fragment
    FragmentTransaction homeTrans= getSupportFragmentManager().beginTransaction();
    homeTrans.replace(R.id.mainContainerFrameLayout, new ManagerLoginFragment());
    homeTrans.commit();

    // setting onClickListener to bottomNavBar
    readableBottomBar.setOnItemSelectListener(new ReadableBottomBar.ItemSelectListener() {
      @Override
      public void onItemSelected(int i) {
        FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
        switch (i) {
          case 0:
//			transaction.setCustomAnimations(R.anim.fadein,R.anim.sample_anim,R.anim.fadein,R.anim.sample_anim);
            transaction.replace(R.id.mainContainerFrameLayout, new ManagerLoginFragment());
            break;

          case 1:
//		    transaction.setCustomAnimations(R.anim.fadein,R.anim.sample_anim,R.anim.fadein,R.anim.sample_anim);
            transaction.replace(R.id.mainContainerFrameLayout, new AnalystLoginFragment());
            break;
        }
        transaction.commit();
      }
    });



  }

}