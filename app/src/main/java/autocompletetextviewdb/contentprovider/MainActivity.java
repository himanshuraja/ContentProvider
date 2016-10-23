package autocompletetextviewdb.contentprovider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Contacts;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts.People;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    Cursor c;
    int count=0;
    Uri u,pu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createContact("mca5a","1111");

        Button b = (Button)findViewById(R.id.button1);
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                createContact("ASDFG","1111");
                //deleteContact("ASDFG");
                //updateContact("pqr");
            }
        });

    }
    private void updateContact(String nm)
    {
        ContentValues cv = new ContentValues();
        //cv.put(People.NAME, "mca5a");
        cv.put(People.Phones.TYPE, People.TYPE_HOME);
        cv.put(People.NUMBER, "89898");
        //  Cursor c = managedQuery(People.CONTENT_URI, new String[]{People.NAME}, People.NAME+"=?", new String[]{nm}, null);
        //Toast.makeText(getApplicationContext(), c.getCount()+"", 50).show();
        getContentResolver().update(pu, cv,
                null,null);
    }
    private void createContact(String nm,String phno)
    {
        ContentValues cv = new ContentValues();
        cv.put(People.NAME, nm);
        u = getContentResolver().insert(People.CONTENT_URI, cv);
        pu = Uri.withAppendedPath(u, People.Phones.CONTENT_DIRECTORY);
        cv.clear();
        cv.put(People.Phones.TYPE, People.TYPE_MOBILE);
        cv.put(People.NUMBER,phno);
        u = getContentResolver().insert(pu, cv);
    }
    private void deleteContact(String nm)
    {
        getContentResolver().delete(People.CONTENT_URI,
                People.NAME+"=?", new String[]{nm});
        ////getContentResolver().delete(u, null, null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

}