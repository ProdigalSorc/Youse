package br.com.youse.redditfeed.api;

import android.content.Context;
import android.os.AsyncTask;
import br.com.youse.redditfeed.interfaces.AsyncResponse;

public class UpdatePostsTask extends AsyncTask<Void, Void, String> {
    private Context context;
    public AsyncResponse delegate = null;

    public UpdatePostsTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        delegate.processBeforeAsyncTask();
    }

    @Override
    protected String doInBackground(Void... params) {

        WebClient client = new WebClient();
        String resposta = client.get();

        return resposta;
    }

    @Override
    protected void onPostExecute(String result) {
        delegate.processFinish(result);
    }
}