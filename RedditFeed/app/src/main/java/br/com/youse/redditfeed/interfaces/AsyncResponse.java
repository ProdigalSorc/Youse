package br.com.youse.redditfeed.interfaces;

public interface AsyncResponse {
    void processBeforeAsyncTask();
    void processFinish(String output);
}
