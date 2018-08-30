package pt.greggz.myapplication;

import android.graphics.Bitmap;
import android.util.LruCache;

public class BitmapCache {

    private LruCache<String, Bitmap> mMemoryCache;
    final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
    final int cacheSize = maxMemory / 8;

    public BitmapCache(){
        createLruCache();
    }

    private void createLruCache() {
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize);
    }

    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    public Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }

}
