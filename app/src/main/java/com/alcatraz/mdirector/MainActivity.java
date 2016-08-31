package com.alcatraz.mdirector;

import android.content.*;
import android.graphics.*;
import android.os.*;
import android.support.design.widget.*;
import android.support.v4.view.*;
import android.support.v4.widget.*;
import android.support.v7.app.*;
import android.view.*;
import android.widget.*;
import com.alcatraz.support.v4.appcompat.*;
import java.util.*;
import java.io.*;
import android.support.v7.widget.*;
import android.view.View.*;
import android.content.pm.*;
import java.security.*;
import android.text.method.*;
import android.app.*;
import android.*;

public class MainActivity extends AppCompatActivity implements OnClickListener
{
	/**/
	@Override
	public void onClick(View p1)
	{
		switch(p1.getId()){
			case R.id.gradleCardView1:
				showCreateDlg();
				break;
			case R.id.create1Button1:
				showPermissionAddDlg();
				break;
		}
		// TODO: Implement this method
	}

	ViewPagerMin vpm;
	TabLayout tl;
	View v;
	DrawerLayout dl;
	ViewPager vp;
	TextView txv;
	ViewPagerAdapter vpa;
	List<View> vpd;
	NavigationView ngv;
	TextView m2res_stat;
	CardView cv;
	View bb;
	/*______ARGS*/
	String def_dir;


	/*__________*/
	ImageView m2_stat;
	android.support.v7.widget.Toolbar tb;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		initViews();
		m2resCheck();
		setUpViewPager();
    }
	private void initViews()
	{
		v=findViewById(R.id.mainView1);
		tb=(android.support.v7.widget.Toolbar) findViewById(R.id.mainToolbar1);
		dl=(DrawerLayout) findViewById(R.id.mainDrawerLayout1);
		tl=(TabLayout) findViewById(R.id.mainTabLayout1);
		txv=(TextView) findViewById(R.id.navheaderTextView1);
		txv.setText(getString(R.string.dr_device)+":"+Build.DEVICE);
		ngv=(NavigationView) findViewById(R.id.navigation);
		ngv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){

				@Override
				public boolean onNavigationItemSelected(MenuItem p1)
				{
					String i=p1.getTitle().toString();
					if(i.equals(getString(R.string.dr_1))){
						try{
							Intent in=new Intent();
							in.setClassName("com.aide.ui","com.aide.ui.MainActivity");
							startActivity(in);
						}catch(Exception e){
							Toast.makeText(MainActivity.this,"Failed to start AIDE,Maybe you haven't installed it",Toast.LENGTH_LONG).show();
						}

					}else if(i.equals(getString(R.string.dr_2_1))){
						showDlg();
					}
					// TODO: Implement this method
					return true;
				}
			});
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
		m2res_stat=(TextView) vpa.get(0,R.id.gradleTextView1);
		cv=(CardView) vpa.get(0,R.id.gradleCardView1);
		cv.setOnClickListener(this);
		m2_stat=(ImageView) vpa.get(0,R.id.gradleImageView1);
		if(m2resCheck()){
			m2res_stat.setText(R.string.gra_granted);
			m2res_stat.setTextColor(Color.parseColor("#2CAD00"));
			m2_stat.setImageResource(R.drawable.ic_check);
		}
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
	private void readSettings()
	{
		SharedPreferences spf=getSharedPreferences(getPackageName()+"_preferences",MODE_PRIVATE);
		def_dir=spf.getString("default_dir","/sdcard/AppProjects/");

	}

	@Override
	protected void onResume()
	{
		readSettings();
		// TODO: Implement this method
		super.onResume();
	}

	@Override
	protected void onStart()
	{
		readSettings();
		// TODO: Implement this method
		super.onStart();
	}
	private void showDlg()
	{
		android.support.v7.app.AlertDialog a=new android.support.v7.app.AlertDialog.Builder(this)
			.setTitle(R.string.dr_2_1)
			.setMessage("MaterialDirector Alcatraz"+"\n")
			.setPositiveButton("OK",null)
			.show();
		new AlertDialogUtil().setSupportDialogColor(a,Color.parseColor("#3f51b5"));
	}
	private boolean m2resCheck()
	{
		boolean flag=false;
		try{
			File m=new File("/sdcard/.aide/");
			String[] f_list=m.list();
			for(String i:f_list){
				if(i.indexOf("android_m2repository")>=0){
					flag=true;
				}

			}
		}catch(Exception e){
		}
		return flag;
	}
	private void showCreateDlg()
	{
		bb=getLayoutInflater().inflate(R.layout.create_1,null);
		final EditText c_et_1=(EditText) bb.findViewById(R.id.create1EditText1);
		final EditText c_et_2=(EditText) bb.findViewById(R.id.create1EditText2);
		final EditText c_et_3=(EditText) bb.findViewById(R.id.create1EditText3);
		final EditText c_et_4=(EditText) bb.findViewById(R.id.create1EditText4);
		Spinner spn_1=(Spinner) bb.findViewById(R.id.create1Spinner1);
		Spinner spn_2=(Spinner) bb.findViewById(R.id.create1Spinner2);
		c_et_3.setText(def_dir);
		Button c_btn=(Button) bb.findViewById(R.id.create1Button1);
		c_btn.setOnClickListener(this);
		android.support.v7.app.AlertDialog b=new android.support.v7.app.AlertDialog.Builder(this)
			.setTitle(R.string.cvad)
			.setView(bb)
			.setPositiveButton("Ok",new DialogInterface.OnClickListener(){

				@Override
				public void onClick(DialogInterface p1, int p2)
				{
					// TODO: Implement this method
				}
			})
			.show();
		new AlertDialogUtil().setSupportDialogColor(b,Color.parseColor("#3f51b5"));
		b.setOnDismissListener(new DialogInterface.OnDismissListener(){

				@Override
				public void onDismiss(DialogInterface p1)
				{
					bb=null;
					c_et_1.setText(null);
					c_et_2.setText(null);
					c_et_3.setText(null);
					c_et_4.setText(null);
					// TODO: Implement this method
				}
			});
	}
	private void strToast(String c)
	{
		Toast.makeText(this,c,Toast.LENGTH_SHORT).show();
	}
	private void showPermissionAddDlg()
	{
		String[] per={getString(R.string.perm_1),getString(R.string.perm_2),getString(R.string.perm_3),getString(R.string.perm_4),getString(R.string.perm_5),getString(R.string.perm_6),getString(R.string.perm_7),getString(R.string.perm_8),getString(R.string.perm_9),getString(R.string.perm_10),getString(R.string.perm_p_11),getString(R.string.perm_11),getString(R.string.perm_12),getString(R.string.perm_13),getString(R.string.perm_14),getString(R.string.perm_15),getString(R.string.perm_16),getString(R.string.perm_17),getString(R.string.perm_18),getString(R.string.perm_19),getString(R.string.perm_20),getString(R.string.perm_21),getString(R.string.perm_22),getString(R.string.perm_23),getString(R.string.perm_24),getString(R.string.perm_25),getString(R.string.perm_26),getString(R.string.perm_27),getString(R.string.perm_28),getString(R.string.perm_29),getString(R.string.perm_30),getString(R.string.perm_31),getString(R.string.perm_32),getString(R.string.perm_33),getString(R.string.perm_34),getString(R.string.perm_35),getString(R.string.perm_36),getString(R.string.perm_37),getString(R.string.perm_38),getString(R.string.perm_39),getString(R.string.perm_40),getString(R.string.perm_41),getString(R.string.perm_42),getString(R.string.perm_43),getString(R.string.perm_44),getString(R.string.perm_45),getString(R.string.perm_46),getString(R.string.perm_47),getString(R.string.perm_48),getString(R.string.perm_49),getString(R.string.perm_50),getString(R.string.perm_51),getString(R.string.perm_52),getString(R.string.perm_53),getString(R.string.perm_54),getString(R.string.perm_55),getString(R.string.perm_56),getString(R.string.perm_57),getString(R.string.perm_58),getString(R.string.perm_59),getString(R.string.perm_60),getString(R.string.perm_61),getString(R.string.perm_62),getString(R.string.perm_63),getString(R.string.perm_64),getString(R.string.perm_65),getString(R.string.perm_66),getString(R.string.perm_67),getString(R.string.perm_68),getString(R.string.perm_69),getString(R.string.perm_70),getString(R.string.perm_71),getString(R.string.perm_72),getString(R.string.perm_73),getString(R.string.perm_74),getString(R.string.perm_75),getString(R.string.perm_76),getString(R.string.perm_77),getString(R.string.perm_78),getString(R.string.perm_79),getString(R.string.perm_80),getString(R.string.perm_81),getString(R.string.perm_82),getString(R.string.perm_83),getString(R.string.perm_84),getString(R.string.perm_85),getString(R.string.perm_86),getString(R.string.perm_87),getString(R.string.perm_88),getString(R.string.perm_89),getString(R.string.perm_90),getString(R.string.perm_91),getString(R.string.perm_92),getString(R.string.perm_93),getString(R.string.perm_94),getString(R.string.perm_95),getString(R.string.perm_96),getString(R.string.perm_97),getString(R.string.perm_98),getString(R.string.perm_99),getString(R.string.perm_100),getString(R.string.perm_101),getString(R.string.perm_102),getString(R.string.perm_103),getString(R.string.perm_104),getString(R.string.perm_105),getString(R.string.perm_106),getString(R.string.perm_107),getString(R.string.perm_108),getString(R.string.perm_109),getString(R.string.perm_110),getString(R.string.perm_111),getString(R.string.perm_112),getString(R.string.perm_113),getString(R.string.perm_114),getString(R.string.perm_115),getString(R.string.perm_116),getString(R.string.perm_117),getString(R.string.perm_118),getString(R.string.perm_119),getString(R.string.perm_120),getString(R.string.perm_121),getString(R.string.perm_122),getString(R.string.perm_123),getString(R.string.perm_124),getString(R.string.perm_125),getString(R.string.perm_126),getString(R.string.perm_127),getString(R.string.perm_128),getString(R.string.perm_129),getString(R.string.perm_130),getString(R.string.perm_131),getString(R.string.perm_132)};
		android.support.v7.app.AlertDialog c=new android.support.v7.app.AlertDialog.Builder(this)
			.setTitle(R.string.create_per)
			.setMultiChoiceItems(per,null,new DialogInterface.OnMultiChoiceClickListener(){

				@Override
				public void onClick(DialogInterface p1, int p2, boolean p3)
				{
					// TODO: Implement this method
				}
			})
			.setPositiveButton("Ok",null)
			.show();
			new AlertDialogUtil().setSupportDialogColor(c,Color.parseColor("#3f51b5"));
	}
}
