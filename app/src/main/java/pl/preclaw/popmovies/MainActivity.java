package pl.preclaw.popmovies;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;

import pl.preclaw.popmovies.Utilities.JsonUtilities;
import pl.preclaw.popmovies.Utilities.Movie;
import pl.preclaw.popmovies.Utilities.MovieAdapter;

public class MainActivity extends AppCompatActivity {

    private String jsonResponse;
    private ArrayList<Movie> movieArrayList;
    private GridView gridView;
    public static String MOVIE = "Movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movieArrayList = new ArrayList<Movie>();
        gridView = (GridView) findViewById(R.id.gridview);


        new FetchMoviesData().execute();
    }

    public class FetchMoviesData extends AsyncTask<String,Void,String>{



        @Override
        protected String doInBackground(String... params) {
//            if (params.length == 0) {
//                return null;
//            }

            URL moviesRequestUrl = JsonUtilities.buildJsonUrl();

            try {
                jsonResponse = JsonUtilities.getResponseFromHttpUrl(moviesRequestUrl);


                return jsonResponse;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            if(s != null){

                try {
                    movieArrayList = JsonUtilities.parseMovieJson(jsonResponse);
                    Log.d("dane",movieArrayList.get(0).getPlot());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                gridView.setAdapter(new MovieAdapter(getApplicationContext(),movieArrayList));

                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View v,
                                            int position, long id) {
                        Movie tempMovie = movieArrayList.get(position);
                        Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                        intent.putExtra(MOVIE, tempMovie);
                        startActivity(intent);
//                        Toast.makeText(MainActivity.this, "" + position,
//                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }

    }
}
