package dev.rodni.ru.bitsandpieces.ui.main;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import dev.rodni.ru.bitsandpieces.BaseActivity;
import dev.rodni.ru.bitsandpieces.R;

//in out base activity we are listening to the session auth state
public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    //because we observing session state we can simply change it
    //to get intent to the auth activity from the base activity state listener
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                sessionManager.logOut();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
