package edu.drake.raygun;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class SplashscreenActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splashscreen);
		Thread logoTimer = new Thread() {
            public void run(){
                try{
                    int logoTimer = 0;
                    while(logoTimer < 3000){ //5000 = 5 sec
                        sleep(100);
                        logoTimer = logoTimer +100;
                    };
                    
            		//setContentView(R.layout.activity_main);
                    System.out.println("here!");
                    //after 5 sec, it will take user to app's home screen
	            	openMainActivity();
                } 
                 
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                 
                finally{
                    finish();
                }
            }
		};
		
		logoTimer.start();
	}
	
	public void openMainActivity() {
		startActivity(new Intent(this, LoginActivity.class));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splashscreen, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
