package com.enseirb.swissknife33.presenter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Vibrator;
import com.enseirb.swissknife33.business.model.Defibrillator;
import com.enseirb.swissknife33.business.model.Nest;
import com.enseirb.swissknife33.business.model.Parking;
import com.enseirb.swissknife33.business.model.PersonalItem;
import com.enseirb.swissknife33.business.model.Toilet;
import com.enseirb.swissknife33.presenter.ui.FetchDefibrillatorListener;
import com.enseirb.swissknife33.presenter.ui.FetchNestListener;
import com.enseirb.swissknife33.presenter.ui.FetchParkingListener;
import com.enseirb.swissknife33.presenter.ui.FetchPersonalItemListener;
import com.enseirb.swissknife33.presenter.ui.FetchToiletListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class GoogleMapManager implements OnMapLongClickListener {

	private static final int ZOOM_LEVEL = 12;
	private GoogleMap map;
	private LatLng BORDEAUX_CENTER_COORDINATES = new LatLng(44.842409, -0.574470);
	public Context context;
	private MainActivity parentActivity;
	private Vibrator vibration;
	public static int VIBRATION_DURATION = 25;
	
	public ArrayList<Marker> parkingMarkers = new ArrayList<Marker>();
	public ArrayList<Marker> nestMarkers = new ArrayList<Marker>();
	public ArrayList<Marker> toiletMarkers = new ArrayList<Marker>();
	public ArrayList<Marker> defibrillatorMarkers = new ArrayList<Marker>();
	public ArrayList<Marker> personalMarkers = new ArrayList<Marker>();
	
	public ArrayList<PersonalItem> personalItemsList = new ArrayList<PersonalItem>();

	public GoogleMapManager(MapFragment mapFragment, Context context){
		this.map = mapFragment.getMap();
		this.context = context;
		this.parentActivity = (MainActivity) context;
		initMap();
		
		this.setVibration((Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE));
		map.setOnMapLongClickListener((OnMapLongClickListener) this);
		map.setMyLocationEnabled(true);
	}

	private void initMap() {
		map.setMyLocationEnabled(true);
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(BORDEAUX_CENTER_COORDINATES, ZOOM_LEVEL));
	}

	
	// Parkings Methods
	
	public void renderParkingMarkers(List<Parking> list){

		for(Parking item : list) {
			parkingMarkers.add(
					map.addMarker(new MarkerOptions()
					.icon(BitmapDescriptorFactory.defaultMarker(item.getColor()))
					.anchor(0.0f, 1.0f)
					.title(item.getName())
					.position(new LatLng(item.getLatitude(), item.getLongitude()))
					.visible(((MainActivity) context).isParkingsBoxChecked())));
		}
	}

	public void showParkingMarkers(){
		if(parkingMarkers.isEmpty()){
			parentActivity.businessFactory.getParkingBusiness(context, (FetchParkingListener) context)
			.createFetchParkingsAsyncTask().execute();
		} else {
			for(Marker marker : parkingMarkers){
				marker.setVisible(true);
			}
		}
	}

	public void hideParkingMarkers(){
		for(Marker marker : parkingMarkers){
			marker.setVisible(false);
		}
	}

	// Personal Items Methods
	
	public void renderPersonalItemMarkers(List<PersonalItem> list){

		for(PersonalItem item : list) {
			personalMarkers.add(
					map.addMarker(new MarkerOptions()
					.icon(BitmapDescriptorFactory.defaultMarker(item.getColor()))
					.anchor(0.0f, 1.0f)
					.title(item.getName())
					.position(new LatLng(item.getLatitude(), item.getLongitude()))
					.visible(((MainActivity) context).isPersonalBoxChecked())));;
		}
	}

	public void showPersonalMarkers(){
		if(personalMarkers.isEmpty()){
			parentActivity.businessFactory.getPersonalItemBusiness(context, (FetchPersonalItemListener) context)
			.createFetchPersonalItemsAsyncTask().execute();
		} else {
			for(Marker marker : personalMarkers){
				marker.setVisible(true);
			}
		}
	}

	public void hidePersonalMarkers(){
		for(Marker marker : personalMarkers){
			marker.setVisible(false);
		}
	}
	
	// Defibrillators Methods
	
	public void renderDefibrillatorMarkers(List<Defibrillator> list){

		for(Defibrillator item : list) {
			defibrillatorMarkers.add(
					map.addMarker(new MarkerOptions()
					.icon(BitmapDescriptorFactory.defaultMarker(item.getColor()))
					.anchor(0.0f, 1.0f)
					.title(item.getName())
					.position(new LatLng(item.getLatitude(), item.getLongitude()))
					.visible(((MainActivity) context).isDefibrillatorsBoxChecked())));
		}
	}

	public void showDefibrillatorMarkers(){
		if(defibrillatorMarkers.isEmpty()){
			parentActivity.businessFactory.getDefibrillatorBusiness(context, (FetchDefibrillatorListener) context)
			.createFetchDefibrillatorsAsyncTask().execute();
		} else {
			for(Marker marker : defibrillatorMarkers){
				marker.setVisible(true);
			}
		}
	}

	public void hideDefibrillatorMarkers(){
		for(Marker marker : defibrillatorMarkers){
			marker.setVisible(false);
		}
	}
	
	// Nests Methods 
	
	public void renderNestMarkers(List<Nest> list){

		for(Nest item : list) {
			nestMarkers.add(
					map.addMarker(new MarkerOptions()
					.icon(BitmapDescriptorFactory.defaultMarker(item.getColor()))
					.anchor(0.0f, 1.0f)
					.title(item.getName())
					.position(new LatLng(item.getLatitude(), item.getLongitude()))
					.visible(((MainActivity) context).isNestsBoxChecked())));
		}
	}

	public void showNestMarkers(){
		if(nestMarkers.isEmpty()){
			parentActivity.businessFactory.getNestBusiness(context, (FetchNestListener) context)
			.createFetchNestsAsyncTask().execute();
		} else {
			for(Marker marker : nestMarkers){
				marker.setVisible(true);
			}
		}
	}

	public void hideNestMarkers(){
		for(Marker marker : nestMarkers){
			marker.setVisible(false);
		}
	}
	
	// Toilets Methods 
	
	public void renderToiletMarkers(List<Toilet> list){

		for(Toilet item : list) {
			toiletMarkers.add(
					map.addMarker(new MarkerOptions()
					.icon(BitmapDescriptorFactory.defaultMarker(item.getColor()))
					.anchor(0.0f, 1.0f)
					.title(item.getName())
					.position(new LatLng(item.getLatitude(), item.getLongitude()))
					.visible(((MainActivity) context).isToiletsBoxChecked())));
		}
	}

	public void showToiletMarkers(){
		if(toiletMarkers.isEmpty()){
			parentActivity.businessFactory.getToiletBusiness(context, (FetchToiletListener) context)
			.createFetchToiletsAsyncTask().execute();
		} else {
			for(Marker marker : toiletMarkers){
				marker.setVisible(true);
			}
		}
	}

	public void hideToiletMarkers(){
		for(Marker marker : toiletMarkers){
			marker.setVisible(false);
		}
	}
	
	@Override
	public void onMapLongClick(LatLng pos) {
		float color = BitmapDescriptorFactory.HUE_VIOLET;
		String markerTitle = "My marker";

		MarkerOptions marker = new MarkerOptions()
		.icon(BitmapDescriptorFactory.defaultMarker(color))
		.anchor(0.0f, 1.0f)
		.title(markerTitle)
		.position(pos);
		
		PersonalItem personalItem = new PersonalItem()
		.setKey(0)
		.setName(markerTitle)
		.setLongitude(pos.longitude)
		.setLatitude(pos.latitude);

		showPersonalMarkers();
		personalMarkers.add(map.addMarker(marker));
		personalItemsList.add(personalItem);
		
		vibration.vibrate(VIBRATION_DURATION);
		
		((MainActivity) context).activatePersonalMarkers();
		
		saveMarkerToStorage();

	}

	private void saveMarkerToStorage() {
		parentActivity.businessFactory.getPersonalItemBusiness(context, (FetchPersonalItemListener) context)
		.createSavePersonalItemsAsyncTask(personalItemsList).execute();
	}

	public void removePersonnalItem() {
		for(Marker m : personalMarkers){
			m.setVisible(false);
		}
		personalMarkers.clear();
	}

	public Vibrator getVibration() {
		return vibration;
	}

	public void setVibration(Vibrator vibration) {
		this.vibration = vibration;
	}
}
