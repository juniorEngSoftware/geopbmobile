

import java.io.IOException;
import java.net.MalformedURLException;

import com.example.helloandroid.R;
import com.example.helloandroid.R.id;
import com.example.helloandroid.R.layout;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InsercaoEnderecoXML extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

    }
    
    public void requestXML(View view) throws MalformedURLException, IOException {
    	
    	final EditText edittext = (EditText) findViewById(R.id.edittext);
    	String xmlAdress = edittext.getText().toString();
    	
    	GeoPBMobileUtil.getStreamOfConnection(xmlAdress);
    }
}