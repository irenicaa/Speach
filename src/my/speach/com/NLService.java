package my.speach.com;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;

public class NLService extends NotificationListenerService implements OnInitListener{

	    TextToSpeech t1;
	    public Context context;
	    public static TextToSpeech mtts;
	    String pack ="";
	    
	    @Override
	    public void onCreate() {
	        super.onCreate();
	        mtts = new TextToSpeech(getApplicationContext(),this);
	        IntentFilter filter = new IntentFilter();
	        filter.addAction("com.example.test.NOTIFICATION_LISTENER_SERVICE_EXAMPLE");
	    }

	    @Override
	    public void onStart(Intent intent,int startid)
	    {
	       mtts = new TextToSpeech(getApplicationContext(),this);
	       final Locale locale = new Locale("ru");
	       mtts.setLanguage(locale);
	    }
	    
	    
	    @Override
	    public void onDestroy() {
	        super.onDestroy();
	        if(mtts!=null)
	        {
	           mtts.stop();
	        }
	    }
	   
	        @Override
	        public void onInit(int status) {
	        	if(status==TextToSpeech.SUCCESS);
	        }
	  
	    @Override
	    public void onNotificationPosted(StatusBarNotification sbn) {

	    
	    	SharedPreferences settings = this.getSharedPreferences("settings", 0);
			String packages_list = settings.getString("packages_list", "");
			boolean work_mode = settings.getBoolean("work_mode", false);
			
			String white = settings.getString("white", "empty");
			String black = settings.getString("black", "empty");
			
			white= white.replaceAll(";", "|");
			final  Pattern White_PATTERN =  Pattern.compile("(?i)\\b(" +white+")\\b");
			
			black= black.replaceAll(";", "|");
			final  Pattern Black_PATTERN =  Pattern.compile("(?i)\\b(" +black+")\\b");
			
			boolean white_b = settings.getBoolean("white_b", false);
			boolean black_b = settings.getBoolean("black_b", false);

			if(work_mode)
			{
				
				 if (packages_list.toLowerCase().contains(sbn.getPackageName().toString()+";"))
		           {
		            if((!white_b)&&(!black_b))
		            {
		             mtts.speak(pack+ sbn.getNotification().tickerText.toString(), TextToSpeech.QUEUE_FLUSH, null);
		            }
		            else
		            {
		             if(black_b)
		             {
		              Matcher matcher1 = Black_PATTERN.matcher(sbn.getNotification().tickerText.toString());
		              Matcher matcher2 = Black_PATTERN.matcher(pack);

		              if ((!matcher1.find())&&(!matcher2.find()))
		              {
		               mtts.speak(pack+ sbn.getNotification().tickerText.toString(), TextToSpeech.QUEUE_FLUSH, null);
		              }
		             }
		            
		             if(white_b)
		             {
		              Matcher matcher1 = White_PATTERN.matcher(sbn.getNotification().tickerText.toString());
		              Matcher matcher2 = White_PATTERN.matcher(pack);

		              if ((matcher1.find())||(matcher2.find()))
		              {
		               mtts.speak(pack+ sbn.getNotification().tickerText.toString(), TextToSpeech.QUEUE_FLUSH, null);
		              }
		             }
		            }
		           }
		        
		         Intent i = new  Intent("com.example.test.NOTIFICATION_LISTENER_EXAMPLE");
		         sendBroadcast(i);
		      }
	  
	    }
	   
	    class NLServiceReceiver extends BroadcastReceiver{

			@Override
			public void onReceive(Context context, Intent intent) {

		        AudioManager audioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
		        if (intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)) {
		        }
			}
	    }



		@Override
		public void onNotificationRemoved(StatusBarNotification sbn) {
			// TODO Автоматически созданная заглушка метода
			
		}

	     

}
