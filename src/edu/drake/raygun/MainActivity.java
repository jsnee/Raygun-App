package edu.drake.raygun;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity implements ActionBar.TabListener {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a {@link FragmentPagerAdapter}
	 * derivative, which will keep every loaded fragment in memory. If this
	 * becomes too memory intensive, it may be best to switch to a
	 * {@link android.support.v13.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;
	protected static PostListAdapter hotPostAdapter, newPostAdapter, topPostAdapter;

	final Context context = this; 
	protected static Dialog dialog;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		dialog = new Dialog(context, R.style.FullHeightDialog);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		loadPosts();

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
		.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				actionBar.setSelectedNavigationItem(position);
			}
		});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
	}

	private void loadPosts() {

		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);

		List<PostEntry> posts = new ArrayList<PostEntry>();
		Bitmap postImage = PostEntry.decodeSampledBitmapFromResource(getResources(), R.drawable.post_1, size.x, size.y);
		Bitmap profilePic = PostEntry.decodeSampledBitmapFromResource(getResources(), R.drawable.profile_pic2, 50, 50);
		float[] geoLoc = { 3.0f, 4.5f };
		PostEntry post = new PostEntry(postImage, "Drink Like A True Midwesterner", 54, geoLoc, "OZ_THE_GREAT", profilePic);
		posts.add(post);
		postImage = PostEntry.decodeSampledBitmapFromResource(getResources(), R.drawable.post_2, size.x, size.y);
		profilePic = PostEntry.decodeSampledBitmapFromResource(getResources(), R.drawable.profile_pic3, 50, 50);
		post = new PostEntry(postImage, "Don't Run Me Over", 5, geoLoc, "GENERIC_USERNAME", profilePic);
		posts.add(post);

		hotPostAdapter = new PostListAdapter(this, posts);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void showStore() {
		Intent intent = new Intent(this, ShopActivity.class);
		startActivity(intent);
	}
	
	private void startCam() {
		Intent intent = new Intent(this, AddPicture.class);
		startActivity(intent);
	}
	private void startMap(){
		Intent intent = new Intent(this, MapActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		} else if (id == R.id.action_shop) {
			showStore();
		}
		else if (id == R.id.addPic) {
			startCam();
		}
		else if (id == R.id.map){
			startMap();
		}
		
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a PlaceholderFragment (defined as a static inner class
			// below).
			return PlaceholderFragment.newInstance(position + 1);
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			}
			return null;
		}
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container, false);
			Bundle args = this.getArguments();
			int sectionNumber = args.getInt(ARG_SECTION_NUMBER);

			switch (sectionNumber) {
			case 1:
				ListView postList = (ListView) rootView.findViewById(R.id.listView1);
				postList.setAdapter(hotPostAdapter);
				postList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				//String selectedPostTitle = ((TextView) view.findViewById(R.id.postEntryTitle)).getText().toString();

				//Popup up and down votes		
				dialog.setContentView(R.layout.like_dialog);
				dialog.show();

				ImageButton upVote = (ImageButton) dialog.findViewById(R.id.upVote);

				upVote.setOnClickListener( new OnClickListener(){
					public void onClick(View view){
						//code to increment votes
						dialog.hide();
					}
				});

				ImageButton downVote = (ImageButton) dialog.findViewById(R.id.downVote);
				downVote.setOnClickListener( new OnClickListener(){
					public void onClick(View view){
						//code to decrement votes
						dialog.hide();
					}
				});
			}
		});
				break;
			case 2:
				break;
			case 3:
				break;
			default:
				break;
			}
			return rootView;
		}
	}

	protected static OnItemClickListener getPostListener() {
		return new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				//String selectedPostTitle = ((TextView) view.findViewById(R.id.postEntryTitle)).getText().toString();

				//Popup up and down votes
			
				dialog.setContentView(R.layout.like_dialog);
				dialog.show();

				ImageButton upVote = (ImageButton) dialog.findViewById(R.id.upVote);

				upVote.setOnClickListener( new OnClickListener(){
					public void onClick(View view){
						//code to increment votes
					}
				});

				ImageButton downVote = (ImageButton) dialog.findViewById(R.id.downVote);
				downVote.setOnClickListener( new OnClickListener(){
					public void onClick(View view){
						//code to increment votes
					}
				});
			}
		};
	}

}
