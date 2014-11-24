package com.enseirb.swissknife33.presenter;

import java.util.List;

import com.enseirb.swissknife33.R;
import com.enseirb.swissknife33.business.ParkingBusiness;
import com.enseirb.swissknife33.dao.model.ParkingDTO;
import com.enseirb.swissknife33.presenter.NavigationDrawerFragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class MainActivity extends Activity implements
NavigationDrawerFragment.NavigationDrawerCallbacks {

	private NavigationDrawerFragment mNavigationDrawerFragment;

	private CheckBox toiletsBox;
	private CheckBox parkingsBox;
	private CheckBox nestsBox;
	private CheckBox defibrillatorsBox;

	private ParkingBusiness parkingBusiness = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);

		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));

		toiletsBox = (CheckBox) findViewById(R.id.toiletsBox);
		parkingsBox = (CheckBox) findViewById(R.id.parkingsBox);
		nestsBox = (CheckBox) findViewById(R.id.nestsBox);
		defibrillatorsBox = (CheckBox) findViewById(R.id.defibrillatorsBox);

		checkBoxJob();

		parkingBusiness = new ParkingBusiness(this);
		parkingBusiness.createAsyncTaskParkingRequest().execute();
	}

	public void updateParkings(List<ParkingDTO> parkings) {
		System.out.println("parkings recuperes ! (" + parkings.size() + ")");
		for (ParkingDTO parkingDTO : parkings) {
			System.out.println(parkingDTO.toString());
		}
	}


	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager
		.beginTransaction().commit();
		// TODO Les deux lignes d'en dessous sont à remettre. Ne compilait sinon.
		// J'ai peut-être fait une erreur de merge !
		//		.replace(R.id.map,
		//				PlaceholderFragment.newInstance(position + 1)).commit();
	}

	public void checkBoxJob(){
		toiletssBoxJob();
		parkingsBoxJob();
		nestsBoxJob();
		defibrillatorsBoxJob();
	}

	private void nestsBoxJob() {
		nestsBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(nestsBox.isChecked()){
					nestsBox.setText("nests checked");
				}
				else{
					nestsBox.setText("nests unchecked");
				}
			}
		});
	}

	private void defibrillatorsBoxJob() {
		defibrillatorsBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(defibrillatorsBox.isChecked()){
					defibrillatorsBox.setText("defibrillators checked");
				}
				else{
					defibrillatorsBox.setText(" defibrillators unchecked");
				}
			}
		});		
	}

	private void parkingsBoxJob() {
		parkingsBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(parkingsBox.isChecked()){
					parkingsBox.setText("parkings checked");
				}
				else{
					parkingsBox.setText("parkings unchecked");
				}				
			}
		});
	}

	private void toiletssBoxJob() {
		toiletsBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(toiletsBox.isChecked()){
					toiletsBox.setText("toilets checked");
				}
				else{
					toiletsBox.setText("toilets unchecked");
				}
			}
		});
	}

}
