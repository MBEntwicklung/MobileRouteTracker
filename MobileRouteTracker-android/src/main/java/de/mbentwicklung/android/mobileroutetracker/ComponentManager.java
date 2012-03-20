/**
 * 
 */
package de.mbentwicklung.android.mobileroutetracker;

import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;

/**
 * @author marc
 * 
 */
public class ComponentManager {

	private final Activity activity;

	public ComponentManager(Activity activity) {
		super();
		this.activity = activity;

		getTimeBar().setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(final SeekBar seekBar) {
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
			}
		});
	}

	/**
	 * @param startButton
	 * @param stopButton
	 * @param timeBar
	 */
	public void loadWaitState() {
		getStartButton().setEnabled(true);
		getTimeBar().setEnabled(true);
		getRouteIDEditText().setEnabled(true);
		getRoutePWEditText().setEnabled(true);
		getStopButton().setEnabled(false);
	}

	/**
	 * @param startButton
	 * @param stopButton
	 * @param timeBar
	 */
	public void loadRunState() {
		getStopButton().setEnabled(true);
		getStartButton().setEnabled(false);
		getTimeBar().setEnabled(false);
		getRouteIDEditText().setEnabled(false);
		getRoutePWEditText().setEnabled(false);
	}
	
	public int getTime() {
		return getTimeBar().getProgress();
	}

	/**
	 * @return
	 */
	TextView getTimeValue() {
		return (TextView) activity.findViewById(R.id.timeValue);
	}

	/**
	 * @return
	 */
	SeekBar getTimeBar() {
		return (SeekBar) activity.findViewById(R.id.timeBar);
	}

	/**
	 * @return
	 */
	Button getStopButton() {
		return (Button) activity.findViewById(R.id.button_stop);
	}

	/**
	 * @return
	 */
	Button getStartButton() {
		return (Button) activity.findViewById(R.id.button_start);
	}

	/**
	 * @return
	 */
	EditText getRouteIDEditText() {
		return (EditText) activity.findViewById(R.id.idkey);
	}

	/**
	 * @return
	 */
	EditText getRoutePWEditText() {
		return (EditText) activity.findViewById(R.id.pass);
	}

}
