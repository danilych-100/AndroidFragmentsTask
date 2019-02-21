package com.example.fragmentstask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.fragmentstask.fragrments.SimpleFragment;

public class MainActivity extends AppCompatActivity {

    private int fragmentsCounter = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            onMenuAddClicked();
            return true;
        }
        if(item.getItemId() == R.id.action_remove){
            onMenuRemoveClicked();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onMenuAddClicked(){
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, SimpleFragment.newInstance(fragmentsCounter))
                .addToBackStack(null)
                .commit();

        fragmentsCounter++;
    }

    private void onMenuRemoveClicked(){
        getSupportFragmentManager().popBackStack();

        fragmentsCounter--;
    }
}
