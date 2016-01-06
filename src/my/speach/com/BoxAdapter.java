package my.speach.com;

import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

public class BoxAdapter extends BaseAdapter {
	  Context ctx;
	  LayoutInflater lInflater;
	  ArrayList<AppItem> objects;

	  BoxAdapter(Context context, ArrayList<AppItem> products) {
		  ctx = context;
		  objects = products;
		  lInflater = (LayoutInflater) ctx
				  .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	  }

	  @Override
	  public int getCount() {
		  return objects.size();
	  }

	  @Override
	  public Object getItem(int position) {
		  return objects.get(position);
	  }

	  @Override
	  public long getItemId(int position) {
		  return position;
	  }

	  @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
		  View view = convertView;
	    if (view == null) {
	    	view = lInflater.inflate(R.layout.item, parent, false);
	    }

	    AppItem p = getProduct(position);

	    ((TextView) view.findViewById(R.id.tvDescr)).setText(p.name);
	    ((ImageView) view.findViewById(R.id.ivImage)).setImageDrawable(p.image);

	    CheckBox cbBuy = (CheckBox) view.findViewById(R.id.cbBox);
	    cbBuy.setOnCheckedChangeListener(myCheckChangList);
	    cbBuy.setTag(position);
	    cbBuy.setChecked(p.box);
	 
	    return view;
	  }

	  AppItem getProduct(int position) {
	    return ((AppItem) getItem(position));
	  }

	  ArrayList<AppItem> getBox() {
		  ArrayList<AppItem> box = new ArrayList<AppItem>();
		  for (AppItem p : objects) {
			  if (p.box)
				  box.add(p);
		  }
		  return box;
	  }

	  OnCheckedChangeListener myCheckChangList = new OnCheckedChangeListener() {
	    public void onCheckedChanged(CompoundButton buttonView,
	        boolean isChecked) {

	    	getProduct((Integer) buttonView.getTag()).box = isChecked;
	    	SelectDeselectPackage(getProduct((Integer) buttonView.getTag()).package_,isChecked);
	    }
	  };
	  
	  
	  public int SelectDeselectPackage(String package_, boolean select_)
	  {
		  SharedPreferences settings = ctx.getSharedPreferences("settings", 0);
		  String packages_list = settings.getString("packages_list", "");
		  if (select_)
		  {
			  packages_list = packages_list + package_.toLowerCase() + ";";
		  }
		  else
		  {
			  packages_list.replace(package_.toLowerCase().concat(";"),"");
		  }
		  SharedPreferences.Editor editor = settings.edit();
		  editor.putString("packages_list", packages_list);
		  editor.commit();
		//  Toast.makeText(ctx, "Изменения сохранены", Toast.LENGTH_SHORT).show();
		  return 0;
	  }
	  
	 

}
