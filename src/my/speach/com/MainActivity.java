package my.speach.com;

import my.speach.com.R;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;


public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		 RadioButton checkBoxFlag1;
		 RadioButton checkBoxFlag2;
		 RadioButton radioButton1;
		 OnClickListener radioListener;

		
		checkBoxFlag1 = (RadioButton) findViewById(R.id.checkBox1);
		checkBoxFlag2 = (RadioButton) findViewById(R.id.CheckBox01);
		radioButton1 = (RadioButton) findViewById(R.id.radioButton1);
		
		 
		 radioListener = new OnClickListener() {
				@Override
				public void onClick(View v) {
					 RadioButton rbWhite;
					 RadioButton rbBlack;
					 RadioButton none;
					 rbWhite = (RadioButton) findViewById(R.id.checkBox1);
					 rbBlack = (RadioButton) findViewById(R.id.CheckBox01);

					 RadioButton rb = (RadioButton)v;
				
					 switch (rb.getId()) {
					 case R.id.checkBox1:
					 {
						 SharedPreferences settings = getSharedPreferences("settings", 0);
						 SharedPreferences.Editor editor = settings.edit();
						 editor.putBoolean("white_b", true);
						 editor.putBoolean("black_b", false);
						 editor.putBoolean("none_b", false);
						 editor.commit(); 
						 ToastDisp("Cохранено");
					 }
					 break;
					 case R.id.CheckBox01: 
					 {
						 SharedPreferences settings = getSharedPreferences("settings", 0);
						 SharedPreferences.Editor editor = settings.edit();
						 editor.putBoolean("white_b", false);
						 editor.putBoolean("black_b", true);
						 editor.putBoolean("none_b", false);
						 editor.commit();    
						 ToastDisp("Cохранено");
					 }
					 break;
					 case R.id.radioButton1: 
					 {
						 SharedPreferences settings = getSharedPreferences("settings", 0);
						 SharedPreferences.Editor editor = settings.edit();
						 editor.putBoolean("white_b", false);
						 editor.putBoolean("black_b", false);
						 editor.putBoolean("none_b", true);
						 editor.commit();    
						 ToastDisp("Cохранено");
					 }
					 break;
					 default:
						 break;
					 }
				 }
			 };
		 
		 checkBoxFlag1.setOnClickListener(radioListener);
		 checkBoxFlag2.setOnClickListener(radioListener);		 
		 radioButton1.setOnClickListener(radioListener);		
		
		 addListenerOnChk();
	}
	
	 @Override
	 public void onResume() {
		 super.onResume();
		 CheckBox checkBoxFlag;
		 RadioButton checkBoxFlag1;
		 RadioButton checkBoxFlag2;
		 RadioButton radioButton1;

		 checkBoxFlag = (CheckBox) findViewById(R.id.checkBoxFlag);
		 checkBoxFlag1 = (RadioButton) findViewById(R.id.checkBox1);
		 checkBoxFlag2 = (RadioButton) findViewById(R.id.CheckBox01);
		 radioButton1 = (RadioButton) findViewById(R.id.radioButton1);

		 SharedPreferences settings = getSharedPreferences("settings", 0);
		
		 checkBoxFlag.setChecked(settings.getBoolean("work_mode", false));
		 checkBoxFlag1.setChecked(settings.getBoolean("white_b", false));
		 checkBoxFlag2.setChecked(settings.getBoolean("black_b", false));
		 radioButton1.setChecked(settings.getBoolean("none_b", false));
	 }
	
	 public void addListenerOnChk() {
		 CheckBox checkBoxFlag;
		 checkBoxFlag = (CheckBox) findViewById(R.id.checkBoxFlag);
		 final SharedPreferences settings = this.getSharedPreferences("settings", 0);

		 checkBoxFlag.setOnClickListener(new OnClickListener() {
			 @Override
			 public void onClick(View v) {
				 if (((CheckBox) v).isChecked()) {
					 SharedPreferences.Editor editor = settings.edit();
					 editor.putBoolean("work_mode", true);
					 editor.commit();	
					 ToastDisp("Cохранено");
				 }
				 else
				 {
					 SharedPreferences.Editor editor = settings.edit();
					 editor.putBoolean("work_mode", false);
					 editor.commit();
					 ToastDisp("Cохранено");
				 }
			 }
		 });
	 }
	 
	 public void RadioBtn() 
	 {
		 OnClickListener radioListener;
		 radioListener = new OnClickListener() {
			 @Override
			 public void onClick(View v) {
				 RadioButton rbWhite;
				 RadioButton rbBlack;
				 RadioButton none;
				 rbWhite = (RadioButton) findViewById(R.id.checkBox1);
				 rbBlack = (RadioButton) findViewById(R.id.CheckBox01);
				 RadioButton rb = (RadioButton)v;
			
				 switch (rb.getId()) {
				 case R.id.checkBox1:
				 {
					 SharedPreferences settings = getSharedPreferences("settings", 0);
					 SharedPreferences.Editor editor = settings.edit();
					 editor.putBoolean("white_b", true);
					 editor.putBoolean("black_b", false);
					 editor.putBoolean("none_b", false);
					 editor.commit(); 
					 ToastDisp("Cохранено");
				 }
				 break;
				 case R.id.CheckBox01: 
				 {
					 SharedPreferences settings = getSharedPreferences("settings", 0);
					 SharedPreferences.Editor editor = settings.edit();
					 editor.putBoolean("white_b", false);
					 editor.putBoolean("black_b", true);
					 editor.putBoolean("none_b", false);
					 editor.commit();    
					 ToastDisp("Cохранено");
				 }
				 break;
				 case R.id.radioButton1: 
				 {
					 SharedPreferences settings = getSharedPreferences("settings", 0);
					 SharedPreferences.Editor editor = settings.edit();
					 editor.putBoolean("white_b", false);
					 editor.putBoolean("black_b", false);
					 editor.putBoolean("none_b", true);
					 editor.commit();    
					 ToastDisp("Cохранено");
				 }
				 break;
				 default:
					 break;
				 }
			 }
		 };
	 }

	 public void ToastDisp(String msg)
	 {
		 Toast.makeText(MainActivity.this, msg,
				 Toast.LENGTH_SHORT).show();
	 }
	 
	 @Override
	 public boolean onCreateOptionsMenu(Menu menu) {
		 getMenuInflater().inflate(R.menu.main, menu);
		 return true;
	 }

	 @Override
	 public boolean onOptionsItemSelected(MenuItem item) {
		 int id = item.getItemId();
		 if (id == R.id.action_settings) {
			 Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
			 startActivity(intent);
			 return true;
		 }
		 if (id == R.id.action_settings2) {
			 Intent intent = new Intent(MainActivity.this, WhiteBlackList.class);
			 startActivity(intent);
			 return true;
		 }
   
		 return super.onOptionsItemSelected(item);
	 }	
}
 