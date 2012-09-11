package nl.bjornhoogland.connect4;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class About extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		
		Resources res = getResources();
		String text = res.getString(R.string.about_text);
		CharSequence styledText = Html.fromHtml(text);
		
		TextView view = (TextView) findViewById(R.id.about_text);
		view.setText(styledText);
	}
}
