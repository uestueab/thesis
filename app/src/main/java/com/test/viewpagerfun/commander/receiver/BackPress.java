package com.test.viewpagerfun.commander.receiver;


import android.content.Intent;
import android.widget.Toast;

import com.test.viewpagerfun.PrefManager;
import com.test.viewpagerfun.StartingScreenActivity;
import com.test.viewpagerfun.commander.state.BackPressState;
import com.test.viewpagerfun.model.entity.FlashCard;

import java.util.Collections;
import java.util.List;

/* ShuffleCards.class
 * ---------------
 * The class who's methods get called by the Command object.
 */
public class BackPress {
	private static String pref_value = null;
	private BackPressState state = null;


	// Since we have a state and the pref key, we can have a more sophisticated method
	public void click() {
		if(state == null)
			return;

		boolean backPress_enabled = PrefManager.get(pref_value,false);
		if(backPress_enabled){
			if (state.getBackPressedTime() + 2000 > System.currentTimeMillis()) {
			    moveToStartingScreen();
			    //Make toast disappear after going to new activity
			    state.getToast().cancel();
			} else {
				state.getToast().show();
			}
			state.setBackPressedTime(System.currentTimeMillis());
		}else{
			moveToStartingScreen();
		}
	}


	public <E> void setState(E pref) {
		if(state == null)
			state = (BackPressState) pref;
	}
	public <E> void setPref(E pref) {
		if(pref_value == null)
			pref_value = (String) pref;
	}

	private void moveToStartingScreen(){
		Intent intent = new Intent(state.getActivity(), StartingScreenActivity.class);

		state.getActivity().startActivity(intent);
		state.getActivity().finish();
	}


}
