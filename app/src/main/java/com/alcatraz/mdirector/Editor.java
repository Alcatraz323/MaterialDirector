package com.alcatraz.mdirector;
import android.support.v7.app.*;
import android.os.*;
import android.support.v7.widget.*;
import java.util.*;
import android.view.*;
import android.support.v4.widget.*;
import com.alcatraz.support.v4.appcompat.*;
import android.widget.*;
import android.support.design.widget.*;
import java.io.*;
import android.util.*;

public class Editor extends AppCompatActivity
{
	android.support.v7.widget.Toolbar tb;
	DrawerLayout dl;
	View v;
	TextView hea;
	TextView hea1;
	NavigationView ngv;
	/*____BUNDLE ARGS___*/
	String label;
	String packagename;
	String folder;
	String color;
	ArrayList<String> permissions;
	int mode;
	int theme;
	boolean isNew;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		readBundle();
		setContentView(R.layout.editor_1);
		initViews();
		if(isNew){
			writeProjectNew();
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// TODO: Implement this method
		
		return super.onOptionsItemSelected(item);
	}
	
	private void readBundle(){
		Bundle data=getIntent().getExtras();
		permissions=data.getStringArrayList("permissions");
		label=data.getString("label");
		packagename=data.getString("packagename");
		folder=data.getString("folder");
		color=data.getString("color");
		mode=data.getInt("mode");
		theme=data.getInt("theme");
		isNew=data.getBoolean("isNew");
	}
	private void initViews(){
		tb=(android.support.v7.widget.Toolbar) findViewById(R.id.editor1Toolbar1);
		tb.setTitle(label);
		v=findViewById(R.id.editor1View1);
		dl=(DrawerLayout) findViewById(R.id.editor1DrawerLayout1);
		ngv=(NavigationView) findViewById(R.id.navigation_1);
		ngv.setCheckedItem(R.id.ed_nav_1);
		hea=(TextView) ngv.getHeaderView(0).findViewById(R.id.navheaderTextView2);
		hea1=(TextView) ngv.getHeaderView(0).findViewById(R.id.navheaderTextView1);
		hea.setText(label);
		hea1.setText(packagename);
		new DrawerLayoutUtil().setImmersiveToolbarWithDrawer(tb,dl,Editor.this,v,color,Build.VERSION.SDK_INT);
	}
	private void writeProjectNew(){
		File root=new File(folder+label+"/");
		root.mkdirs();
		String[] first={root.getAbsolutePath()+"/"+"app/",root.getAbsolutePath()+"/"+"build.gradle",root.getAbsolutePath()+"/"+"settings.gradle"};
		String[] second_f_app={first[0]+"/"+"build.gradle",first[0]+"/"+"libs/",first[0]+"/"+"proguard-rules.pro",first[0]+"/"+"src/"};
		String[] third_f_src={second_f_app[3]+"main/"};
		String[] fourth_f_main={third_f_src[0]+"AndroidManifest.xml",third_f_src[0]+"java/",third_f_src[0]+"res/",third_f_src[0]+"assets/"};
		String[] fifth_f_java=packagename.split("\\.");
		writeDir(first);
		writeDir(second_f_app);
		writeDir(third_f_src);
		writeDir(fourth_f_main);
		String tempDir=null;
		Log.e("Alcatraz","Prepare Package  "+packagename+"/"+fifth_f_java.length);
		for(int h=0;h<fifth_f_java.length;h++){
			if(h==0){
			tempDir=fourth_f_main[1]+fifth_f_java[0]+"/";
			}else{
			tempDir=tempDir+fifth_f_java[h]+"/";	
			}
			new File(tempDir).mkdirs();
			Log.e("Alcatraz",tempDir);
		}
	}
	private void writeDir(String[] ik){
		for(String h:ik){
			if(h.substring(h.length()-1).equals("/")){
				new File(h).mkdirs();
			}else{
				try{
					new File(h).createNewFile();
				}catch(IOException e){}
			}
		}
	}
	
}
