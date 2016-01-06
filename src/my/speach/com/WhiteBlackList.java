package my.speach.com;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WhiteBlackList extends Activity {

	
	 @Override
	 public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_white_black_list);
	  final Button btn = (Button) findViewById(R.id.button1);
	  btn.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		    	SaveBlackWhite();
		    }
		});
	 }
	 
	 @Override
	 public void onResume() {
		 super.onResume();
		
		 EditText black = (EditText) findViewById(R.id.editText1);
		 EditText white = (EditText) findViewById(R.id.editText2);
		 SharedPreferences settings = getSharedPreferences("settings", 0);

		 black.setText(settings.getString("black", "[Черный список]"));
		 white.setText(settings.getString("white", "[Белый список]"));
	 }
	
	 
	 public void SaveBlackWhite()
	 {	
		 EditText black = (EditText) findViewById(R.id.editText1);
		 EditText white = (EditText) findViewById(R.id.editText2);

		 SharedPreferences settings = this.getSharedPreferences("settings", 0);
		 SharedPreferences.Editor editor = settings.edit();
		 editor.putString("white", white.getText().toString());
		 editor.putString("black", black.getText().toString());

		 editor.commit();
		 Toast.makeText(WhiteBlackList.this, "Изменения сохранены",
				 Toast.LENGTH_SHORT).show();
	 }
	 
}
