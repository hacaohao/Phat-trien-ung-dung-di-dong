package background;

import android.os.AsyncTask;

import model.DataStore;
import utils.XMLDownloader;

/**
 * Created by hao on 3/15/2017.
 */

public class BackgroundDownload extends AsyncTask<Void, Void, Void> {
    @Override
    protected Void doInBackground(Void... params) {
        XMLDownloader downloader = new XMLDownloader();
        downloader.downloadFile();
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        DataStore.getInstance();
    }
}
