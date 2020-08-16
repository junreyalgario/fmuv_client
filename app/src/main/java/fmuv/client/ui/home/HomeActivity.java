package fmuv.client.ui.home;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import fmuv.client.R;
import fmuv.client.ui.about.AboutActivity;
import fmuv.client.ui.account.AccountActivity;
import fmuv.client.ui.auth.AuthActivity;
import fmuv.client.ui.base.BaseActivity;
import fmuv.client.ui.history.HistoryActivity;
import fmuv.client.ui.mybooking.MyBookingActivity;
import fmuv.client.ui.schedule.TripScheduleActivity;
import fmuv.client.ui.traveling.TravelingUvActivity;

import android.view.Menu;
import android.view.MenuItem;

public class HomeActivity extends BaseActivity<HomeViewModel>
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = (Toolbar) findViewById(R.id.app_toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Start here
        initializeBaseActivity();
        attachLiveDataObservers();
    }

    @Override
    protected HomeViewModel createViewModel() {
        return ViewModelProviders.of(this).get(HomeViewModel.class);
    }

    private void attachLiveDataObservers() {
        viewModel.getIsLogoutDone().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                startActivity(new Intent(HomeActivity.this, AuthActivity.class));
                finish();
            }
        });
    }

    // Nav drawer stuff to bottom

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_about_us) {
            slideNextActivity(new Intent(HomeActivity.this, AboutActivity.class));
        } else if (id == R.id.nav_account) {
            slideNextActivity(new Intent(HomeActivity.this, AccountActivity.class));
        } else if (id == R.id.nav_logout) {
            viewModel.logout();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onClickUvSchedule(View view) {
        slideNextActivity(new Intent(HomeActivity.this, TripScheduleActivity.class));
    }

    public void onClickTravelingUv(View view) {
        slideNextActivity(new Intent(HomeActivity.this, TravelingUvActivity.class));
    }


    public void onClickMyBookings(View view) {
        slideNextActivity(new Intent(HomeActivity.this, MyBookingActivity.class));
    }

    public void onClickHistory(View view) {
        slideNextActivity(new Intent(HomeActivity.this, HistoryActivity.class));
    }

    private void slideNextActivity(Intent intent) {
        startActivity(intent);
        overridePendingTransition(R.anim.left_leave, R.anim.left_enter);
    }
}
