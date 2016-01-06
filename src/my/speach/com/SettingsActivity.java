package my.speach.com;

import java.util.ArrayList;
import java.util.List;

import my.speach.com.R;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.widget.ListView;

public class SettingsActivity extends Activity {
	private ListView lView;
	private ArrayList<AppItem> results = new ArrayList<AppItem>();
	BoxAdapter boxAdapter;
	 
	 @Override
	 public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_settings);
	  
	  PackageManager pm = this.getPackageManager();
	  
	  Intent intent = new Intent(Intent.ACTION_MAIN, null);
	  intent.addCategory(Intent.CATEGORY_LAUNCHER);

	  SharedPreferences settings = this.getSharedPreferences("settings", 0);
	  String packages_list = settings.getString("packages_list", "");
	
	  
	  List<ResolveInfo> list = pm.queryIntentActivities(intent, PackageManager.PERMISSION_GRANTED);
	  for (ResolveInfo rInfo : list) {

	   results.add(new AppItem(rInfo.activityInfo.applicationInfo.packageName,
			   				   rInfo.activityInfo.applicationInfo.loadLabel(pm).toString(), 
			   				   rInfo.activityInfo.applicationInfo.loadIcon(pm),
			   				   packages_list.toLowerCase().contains(rInfo.activityInfo.applicationInfo.packageName.toLowerCase()),
			   				   this));
	  } 
	  
	  boxAdapter = new BoxAdapter(this, results);
	  lView = (ListView) findViewById(R.id.list1);
	  lView.setAdapter(boxAdapter);
	  
	 }
}
	

