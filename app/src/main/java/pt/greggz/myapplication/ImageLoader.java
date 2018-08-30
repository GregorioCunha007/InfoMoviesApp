package pt.greggz.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageLoader extends AsyncTask<Void, Void, Bitmap> {

  //  private final int id;
    private final Context context;
    private String url;
    private ImageView imageView;
    private String path = "https://image.tmdb.org/t/p/w185";
    public static BitmapCache cache = new BitmapCache();


    public ImageLoader(String url, ImageView imageView, Context context) {
        this.url = path + url;
      //  this.id = id;
        this.imageView = imageView;
        this.context = context;
    }

    @Override
    protected Bitmap doInBackground(Void... params) {
        Bitmap myBitmap = cache.getBitmapFromMemCache(String.valueOf(url.hashCode()));
        if (myBitmap != null)
            return myBitmap;
        try {
            URL urlConnection = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlConnection
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            myBitmap = BitmapFactory.decodeStream(input);
            cache.addBitmapToMemoryCache(String.valueOf(url.hashCode()), myBitmap);

            return myBitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (myBitmap != null) return myBitmap;
        return cache.getBitmapFromMemCache(Integer.toString(-1));
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        super.onPostExecute(result);

        imageView.setImageBitmap(result);
    }

}
