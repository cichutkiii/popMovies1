package pl.preclaw.popmovies.Utilities;


import com.squareup.picasso.Picasso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.ArrayList;

import pl.preclaw.popmovies.R;

public class MovieAdapter extends BaseAdapter {

    private Context context;
    private final ArrayList<Movie> movieArrayList;



    public MovieAdapter(Context context, ArrayList<Movie> movies) {

        this.context = context;
        movieArrayList = movies;
    }

    @Override
    public int getCount() {
        return movieArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if (convertView == null) {

            gridView = new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.movie_item, null);

            // set value into textview
            TextView textView = (TextView) gridView
                    .findViewById(R.id.tv_title);
            textView.setText(movieArrayList.get(position).getOriginalTitle());

            // set image based on selected text
            ImageView imageView = (ImageView) gridView
                    .findViewById(R.id.iv_thumbnail);
            Picasso.with(context).load(movieArrayList.get(position).getThumbnail().toString()).into(imageView);





        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }
}
