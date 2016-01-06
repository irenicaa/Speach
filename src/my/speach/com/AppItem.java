package my.speach.com;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class AppItem {
	  protected Context context;
	  String name;
	  String package_;
	  Drawable image;
	  boolean box;
	  
	  AppItem(String package_name, String name_, Drawable _image, boolean checked_,  Context context_) {
	    name = name_;
	    package_ = package_name;
	    box = checked_;
	    
	    this.context = context_.getApplicationContext();
	    
	    Bitmap b = ((BitmapDrawable)_image).getBitmap();
	    Bitmap bitmapResized = Bitmap.createScaledBitmap(b, 50, 50, false);
	    image = new BitmapDrawable(context.getResources(), bitmapResized);
	  }
}
