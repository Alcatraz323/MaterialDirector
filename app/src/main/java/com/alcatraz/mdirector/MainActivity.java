package com.alcatraz.mdirector;

import android.app.*;
import android.os.*;
import android.support.design.widget.*;
import android.view.*;
import com.alcatraz.support.v4.appcompat.*;
import android.support.v4.widget.*;
import android.support.v7.widget.*;
import android.support.v4.view.*;
import java.util.*;
import android.support.v7.app.*;
import android.widget.*;
import android.content.*;

public class MainActivity extends AppCompatActivity 
{
	ViewPagerMin vpm;
	TabLayout tl;
	View v;
	DrawerLayout dl;
	ViewPager vp;
	TextView txv;
	ViewPagerAdapter vpa;
	List<View> vpd;
	android.support.v7.widget.Toolbar tb;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		initViews();
		setUpViewPager();
    }
	private void initViews()
	{
		v=findViewById(R.id.mainView1);
		tb=(android.support.v7.widget.Toolbar) findViewById(R.id.mainToolbar1);
		dl=(DrawerLayout) findViewById(R.id.mainDrawerLayout1);
		tl=(TabLayout) findViewById(R.id.mainTabLayout1);
		txv=(TextView) findViewById(R.id.navheaderTextView1);
		txv.setText(Build.DEVICE);
		vpm=(ViewPagerMin) findViewById(R.id.mainViewPagerMin1);
		tb.setTitle(R.string.app_name);
		setSupportActionBar(tb);
		new DrawerLayoutUtil().setImmersiveToolbarWithDrawer(tb,dl,MainActivity.this,v,"#3f51b5",Build.VERSION.SDK_INT);
	}
	private void setUpViewPager()
	{
		vpd=new ArrayList<View>();
		vpd.add(getLayoutInflater().inflate(R.layout.gradle,null));
		vpd.add(getLayoutInflater().inflate(R.layout.eclipse,null));
		List<String> t=new ArrayList<String>();
		t.add("Gradle");
		t.add("Eclispse");
		vpa=new ViewPagerAdapter(vpd,t);
		vpm.setAdapter(vpa);
		tl.setupWithViewPager(vpm.getViewPager());
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// TODO: Implement this method
		switch(item.getItemId()){
			case R.id.item1:
				startActivity(new Intent(this,Setting.class));
				break;
			case R.id.item4:
				finish();
				break;
		}
		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// TODO: Implement this method
		MenuInflater mi=new MenuInflater(this);
		mi.inflate(R.menu.main_menu,menu);
		return true;
	}

}
