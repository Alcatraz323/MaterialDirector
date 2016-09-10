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
import android.support.v4.content.*;
import android.support.v4.app.*;
import android.widget.AdapterView.*;
import java.lang.reflect.*;
import android.util.*;

public class MainActivity extends AppCompatActivity implements OnClickListener
{
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
	View hhh;
	LinearLayout ll;
	DrawerLayout dl;
	ViewPager vp;
	TextView txv;
	TextView txv_c;
	ViewPagerAdapter vpa;
	List<View> vpd;
	NavigationView ngv;
	TextView m2res_stat;
	CardView cv;
	View bb;
	/*______*/
	int[] create_sel=new int[2];
	boolean[] create_selected=new boolean[322];
	String[] create_perm_sel;
	String[] per;
	/*_______*/

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
		setUpViewPager();
		m2resCheck();
		checkPerm(this);
		for(int c=0;c<322;c++){
			create_selected[c]=false;
		}
    }
	private void initViews()
	{
		v=findViewById(R.id.mainView1);
		tb=(android.support.v7.widget.Toolbar) findViewById(R.id.mainToolbar1);
		dl=(DrawerLayout) findViewById(R.id.mainDrawerLayout1);
		tl=(TabLayout) findViewById(R.id.mainTabLayout1);
		ngv=(NavigationView) findViewById(R.id.navigation);
		txv=(TextView) ngv.getHeaderView(0).findViewById(R.id.navheaderTextView1);
		ll=(LinearLayout) ngv.getHeaderView(0).findViewById(R.id.navheaderLinearLayout2);
		txv.setText(getString(R.string.dr_device)+":"+Build.DEVICE);
		
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
	private void m2resCheck()
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
		if(flag){
			m2res_stat.setText(R.string.gra_granted);
			m2res_stat.setTextColor(Color.parseColor("#2CAD00"));
			m2_stat.setImageResource(R.drawable.ic_check);
		}else{
			m2res_stat.setText(R.string.gra_2);
			m2res_stat.setTextColor(Color.parseColor("#C10B00"));
			m2_stat.setImageResource(R.drawable.ic_close);
		}


	}
	private void showCreateDlg()
	{
		String[] spn_1_c={getString(R.string.create_spinner_1_2)};
		String[] spn_2_c={getString(R.string.create_spinner_2_1),getString(R.string.create_spinnet_2_2)};
		bb=getLayoutInflater().inflate(R.layout.create_1,null);
		final EditText c_et_1=(EditText) bb.findViewById(R.id.create1EditText1);
		final EditText c_et_2=(EditText) bb.findViewById(R.id.create1EditText2);
		final EditText c_et_3=(EditText) bb.findViewById(R.id.create1EditText3);
		final EditText c_et_4=(EditText) bb.findViewById(R.id.create1EditText4);
		final Spinner spn_1=(Spinner) bb.findViewById(R.id.create1Spinner1);
		txv_c=(TextView) bb.findViewById(R.id.create1TextView1);
		final Spinner spn_2=(Spinner) bb.findViewById(R.id.create1Spinner2);
		spn_1.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,spn_1_c));
		spn_2.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,spn_2_c));
		spn_1.setOnItemSelectedListener(new OnItemSelectedListener(){

				@Override
				public void onItemSelected(AdapterView<?> p1, View p2, int p3, long p4)
				{
					create_sel[0]=p3;
					// TODO: Implement this method
				}

				@Override
				public void onNothingSelected(AdapterView<?> p1)
				{
					create_sel[0]=0;
					// TODO: Implement this method
				}
			});
		spn_2.setOnItemSelectedListener(new OnItemSelectedListener(){

				@Override
				public void onItemSelected(AdapterView<?> p1, View p2, int p3, long p4)
				{
					create_sel[1]=p3;
					// TODO: Implement this method
				}

				@Override
				public void onNothingSelected(AdapterView<?> p1)
				{
					create_sel[1]=0;
					// TODO: Implement this method
				}
			});

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
					String label=c_et_1.getText().toString();
					String packagename=c_et_2.getText().toString();
					String folder=c_et_3.getText().toString();
					String color=c_et_4.getText().toString();
					int mode=create_sel[0];
					int theme=create_sel[1];
					boolean isNew=true;
					ArrayList<String> permissions=getChoosedPerm(create_selected);
					Bundle data=new Bundle();
					data.putStringArrayList("permissions",permissions);
					data.putString("label",label);
					data.putString("packagename",packagename);
					data.putString("folder",folder);
					data.putString("color",color);
					data.putInt("mode",mode);
					data.putInt("theme",theme);
					data.putBoolean("isNew",isNew);
					Intent intent=new Intent(MainActivity.this,Editor.class);
					intent.putExtras(data);
					startActivity(intent);
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
					for(int c=0;c<322;c++){
						create_selected[c]=false;
					}
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

		ArrayList<Field> fieldList = new ArrayList<Field>();
		Field[] dFields = Manifest.permission.class.getDeclaredFields();
		if(null!=dFields&&dFields.length>0){
			fieldList.addAll(Arrays.asList(dFields));
		}
		per=getPerm(fieldList);
		android.support.v7.app.AlertDialog c=new android.support.v7.app.AlertDialog.Builder(this)
			.setTitle(R.string.create_per)
			.setMultiChoiceItems(per,create_selected,new DialogInterface.OnMultiChoiceClickListener(){

				@Override
				public void onClick(DialogInterface p1, int p2, boolean p3)
				{
					create_selected[p2]=p3;

					// TODO: Implement this method
				}
			})
			.setPositiveButton("Ok",null)
			.show();

		new AlertDialogUtil().setSupportDialogColor(c,Color.parseColor("#3f51b5"));
	}
	public static void checkPerm(Activity mContext)
	{
		if(Build.VERSION.SDK_INT>=23){
            int checkPermission =ContextCompat.checkSelfPermission(mContext,Manifest.permission.WRITE_EXTERNAL_STORAGE);

            if(checkPermission!=PackageManager.PERMISSION_GRANTED){
				ActivityCompat.requestPermissions(mContext,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},0);
                return;
            }
        }
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
	{
		// TODO: Implement this method
		switch(requestCode){
			case 0:
				if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
					super.onRequestPermissionsResult(requestCode,permissions,grantResults);
				}else{
					android.support.v7.app.AlertDialog d=new android.support.v7.app.AlertDialog.Builder(this)
						.setTitle(R.string.perm_req)
						.setMessage(R.string.perm_msg+grantResults[0])
						.setPositiveButton(R.string.perm_pb,new DialogInterface.OnClickListener(){

							@Override
							public void onClick(DialogInterface p1, int p2)
							{
								checkPerm(MainActivity.this);
								// TODO: Implement this method
							}
						})
						.setNegativeButton(R.string.perm_nb,new DialogInterface.OnClickListener(){

							@Override
							public void onClick(DialogInterface p1, int p2)
							{
								finish();
								// TODO: Implement this method
							}
						})
						.show();
					d.setCancelable(false);
					new AlertDialogUtil().setSupportDialogColor(d,Color.parseColor("#3f51b5"));

				}
				m2resCheck();
				break;
		}
	}
	private String[] getPerm(ArrayList<Field> g)
	{
		List<String> b=new ArrayList<String>();
		for(Field h:g){
			try{
				b.add(h.get(new Manifest()).toString());

			}catch(IllegalAccessException e){}catch(IllegalArgumentException e){}}
		return b.toArray(new String[b.size()]);
	}
	private ArrayList<String> getChoosedPerm(boolean[] v)
	{
		ArrayList<String> selected=new ArrayList<String>();
		int l=0;
		for(boolean i:v){
			if(i){
				selected.add(per[l]);
			}
			l++;
		}
		return selected;
	}
}
