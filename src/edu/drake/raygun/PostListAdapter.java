package edu.drake.raygun;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PostListAdapter extends BaseAdapter {
	 	private Activity activity;
	    private LayoutInflater inflater;
	    private List<PostEntry> posts;
	    
	    public PostListAdapter(Activity activity, List<PostEntry> posts) {
	    	this.activity = activity;
	    	this.posts = posts;
	    }

		public int getCount() {
			return posts.size();
		}

		public PostEntry getItem(int position) {
			return posts.get(position);
		}

		public long getItemId(int position) {
			return 0;
		}

		@Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	 
	        if (inflater == null)
	            inflater = (LayoutInflater) activity
	                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        if (convertView == null)
	            convertView = inflater.inflate(R.layout.post_listitem, null);
	        
	        ImageView imageView = (ImageView) convertView.findViewById(R.id.postEntryImage);
	        TextView postTitle = (TextView) convertView.findViewById(R.id.postEntryTitle);
	        TextView postUser = (TextView) convertView.findViewById(R.id.postUsername);
	        TextView postVotes = (TextView) convertView.findViewById(R.id.postEntryVotes);
	        
	        PostEntry post = posts.get(position);
	         
	        imageView.setImageBitmap(post.getImage());
	        postTitle.setText(post.getTitle());
	        postUser.setText(" " + post.getUsername() + " ");
	        postVotes.setText("" + post.getVotes());
	 
	        return convertView;
	    }

}