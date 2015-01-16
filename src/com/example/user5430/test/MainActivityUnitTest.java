package com.example.user5430.test;


import com.example.user5430.MainActivity;

import android.content.Intent;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivityUnitTest extends
	android.test.ActivityUnitTestCase<MainActivity> {

	private MainActivity activity;
	
	public MainActivityUnitTest() {
		super(MainActivity.class);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		Intent intent = new Intent(getInstrumentation().getTargetContext(),
		    MainActivity.class);
		startActivity(intent, null, null);
		activity = getActivity();
	}
	@Override
	protected void tearDown() throws Exception {
	   super.tearDown();
	}
	
	public void testLayoutOnline() {
		activity.onResume();
		boolean isOnline = activity.isOnline(activity.getApplicationContext());
		assertTrue(isOnline);
		if(isOnline && !MainActivity.isFinished) {
			int textViewId = com.example.user5430.R.id.textView1;
		    assertNotNull(activity.findViewById(textViewId));
		    TextView view = (TextView) activity.findViewById(textViewId);
		    
		    assertEquals("Incorrect label of the button", "connecting...", view.getText());
		}
	}
	public void testProcessCompleted() {
		boolean isOnline = activity.isOnline(activity.getApplicationContext());
		assertTrue(isOnline);
		if(isOnline) {
			/*
			* whait until main process has finished or at least 10 seconds
			*/
			int cont = 10;
	    	while(!MainActivity.isFinished && cont > 0) {
	    		try {
	    			Thread.sleep(1000);
	    		} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
	    		}
	    		--cont;
	    	}      
	    	int listViewId = com.example.user5430.R.id.listView1;
	    	assertNotNull(activity.findViewById(listViewId));
	    	ListView lView = (ListView) activity.findViewById(listViewId);

		    assertEquals( "ListView not visible", "0", Integer.toString(lView.getVisibility()) );
		}
	}
}