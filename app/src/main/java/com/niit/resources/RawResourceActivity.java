package com.niit.resources;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class RawResourceActivity extends ListActivity {
	TextView selection;
	ArrayList items=new ArrayList();

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.main);
		selection=(TextView)findViewById(R.id.selection);
		try {
			InputStream in=getResources().openRawResource(R.raw.words);
			DocumentBuilder builder=DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc=builder.parse(in, null);
			NodeList words=doc.getElementsByTagName("word");
			for (int i=0;i<words.getLength();i++) {
				items.add(((Element)words.item(i)).getAttribute("value"));
			}
			in.close();
		}
		catch (Throwable t) {
			//showAlert("Exception!", 0, t.toString(), "Cancel", true);
		}
		setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items));
	}

	public void onListItemClick(ListView parent, View v, int position, long id) {
		selection.setText(items.get(position).toString());
	}
}