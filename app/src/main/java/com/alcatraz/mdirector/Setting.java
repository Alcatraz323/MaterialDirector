package com.alcatraz.mdirector;
import android.support.v7.app.*;
import android.os.*;
import android.preference.*;
import android.content.*;
import android.app.*;
import android.text.*;
import android.support.v7.widget.*;
import com.alcatraz.support.v4.appcompat.*;
import android.graphics.*;
import android.view.View.*;
import android.view.*;

public class Setting extends PreferenceActivity
{
	String def_dir;
	Toolbar tb;
	EditTextPreference etp;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);
		addPreferencesFromResource(R.layout.set_con);
		readSettings();
		etp=(EditTextPreference) findPreference("default_dir");
		tb=(Toolbar) findViewById(R.id.optiToolbar);
		tb.setTitle(R.string.menu_1);
		tb.setNavigationOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					finish();
					// TODO: Implement this method
				}
			});
		StatusBarUtil.setColor(Setting.this,Color.parseColor("#3f51b5"));
		etp.setSummary(def_dir);
	}
	private void readSettings(){
		SharedPreferences spf=getSharedPreferences(getPackageName()+"preferences",Activity.MODE_PRIVATE);
		def_dir=spf.getString("default_dir","/sdcard/AppProjects/");
	}
}
