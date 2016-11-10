package com.game.team309b.ca;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

public class MainActivity extends AndroidApplication implements GameCallback {
	private AdView adview;
	private RelativeLayout layout;
	private RelativeLayout.LayoutParams adParams;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        
        layout = new RelativeLayout(this);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        View gameView = initializeForView(new CarbonizedAnts(this), cfg);
        
        layout.addView(gameView);
        
        adview = new AdView(this, AdSize.BANNER, "a1534264af7aec5");
        adview.loadAd(new AdRequest());
        
        adParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, 
                                RelativeLayout.LayoutParams.WRAP_CONTENT);
        adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        adParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        
        setContentView(layout);
    }

	@Override
	public void run() {
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				layout.removeView(adview);
			}
		});
	}

	@Override
	public void idle() {
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				layout.addView(adview, adParams);
			}
		});
	}
}
