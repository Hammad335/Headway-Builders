package com.codewithhamad.headwaybuilders.analyst;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.FrameLayout;
import com.codewithhamad.headwaybuilders.R;
import com.iammert.library.readablebottombar.ReadableBottomBar;

public class AnalystActivity extends AppCompatActivity {

	ReadableBottomBar readableBottomBar;
	FrameLayout frameLayout; // container for fragments used in this activity

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_analyst);

		// initializing views
		readableBottomBar= findViewById(R.id.analystBottomNav);
		frameLayout= findViewById(R.id.analystContainerFrameLayout);

		// by default: home fragment
		FragmentTransaction homeTrans= getSupportFragmentManager().beginTransaction();
		homeTrans.replace(R.id.analystContainerFrameLayout, new AnalystHomeFragment());
		homeTrans.commit();

		// setting onClickListener to bottomNavBar
		readableBottomBar.setOnItemSelectListener(new ReadableBottomBar.ItemSelectListener() {
			@Override
			public void onItemSelected(int i) {
				FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
				switch (i){
					case 0:
						transaction.replace(R.id.analystContainerFrameLayout, new AnalystHomeFragment());
						break;

					case 1:
						transaction.replace(R.id.analystContainerFrameLayout, new AnalystAddFragment());
						break;

					case 2:
						transaction.replace(R.id.analystContainerFrameLayout, new AnalystEditFragment());
						break;

					case 3:
						transaction.replace(R.id.analystContainerFrameLayout, new AnalystProfileFragment());
						break;
				}
				transaction.commit();
			}
		});
	}
}