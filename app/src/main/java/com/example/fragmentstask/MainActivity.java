package com.example.fragmentstask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.fragmentstask.fragrments.SimpleFragment;

public class MainActivity extends AppCompatActivity {

    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if(getSupportFragmentManager().getBackStackEntryCount() == 0){
                    switchRemoveButtonMode(false);
                } else {
                    switchRemoveButtonMode(true);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        this.menu = menu;

        if(getSupportFragmentManager().getBackStackEntryCount() == 0){
            switchRemoveButtonMode(false);
        }

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

    private void switchRemoveButtonMode(boolean isEnable){
        MenuItem removeItem = this.menu.findItem(R.id.action_remove);
        removeItem.setEnabled(isEnable);
    }

    private void onMenuAddClicked(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainContainer, SimpleFragment.newInstance(
                        getSupportFragmentManager().getBackStackEntryCount() + 1)
                )
                .addToBackStack(null)
                .commit();
    }

    private void onMenuRemoveClicked(){
        getSupportFragmentManager().popBackStack();
    }
}
