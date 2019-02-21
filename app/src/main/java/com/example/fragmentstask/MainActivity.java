package com.example.fragmentstask;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.fragmentstask.fragrments.SimpleFragment;

public class MainActivity extends AppCompatActivity {

    private final String FRAGMENTS_COUNTER_EXTRA = "FRAGMENTS_COUNTER_EXTRA";

    private Menu menu;

    private int fragmentsCounter = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null){
            fragmentsCounter = savedInstanceState.getInt(FRAGMENTS_COUNTER_EXTRA);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(FRAGMENTS_COUNTER_EXTRA, fragmentsCounter);
        super.onSaveInstanceState(savedInstanceState);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        this.menu = menu;

        if(fragmentsCounter == 1){
            switchRemoveButtonMode(false);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            onMenuAddClicked();

            switchRemoveButtonMode(true);

            return true;
        }
        if(item.getItemId() == R.id.action_remove){
            onMenuRemoveClicked();

            if(fragmentsCounter == 1){
                item.setEnabled(false);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void switchRemoveButtonMode(boolean isEnable){
        MenuItem removeItem = this.menu.findItem(R.id.action_remove);
        removeItem.setEnabled(isEnable);
    }

    private void onMenuAddClicked(){
        getSupportFragmentManager().beginTransaction()
                .add(R.id.mainContainer, SimpleFragment.newInstance(fragmentsCounter))
                .addToBackStack(null)
                .commit();

        fragmentsCounter++;
    }

    private void onMenuRemoveClicked(){
        getSupportFragmentManager().popBackStack();

        fragmentsCounter--;
    }
}
