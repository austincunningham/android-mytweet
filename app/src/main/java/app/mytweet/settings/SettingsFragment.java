package app.mytweet.settings;

/**
 * Created by austin on 22/10/2016.
 */


import android.os.Bundle;
import android.preference.PreferenceFragment;
import app.mytweet.R;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import static app.mytweet.android.helpers.IntentHelper.navigateUp;
import static app.mytweet.android.helpers.LogHelpers.info;
import android.content.Intent;

public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener
{
    private SharedPreferences prefs;

    @Override
    public void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
        setHasOptionsMenu(true);
    }

    @Override
    public void onStart()
    {
        super.onStart();
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        prefs.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        info(getActivity(), "Setting change - key : value = " + key + " : " + sharedPreferences.getString(key, ""));
        String refreshIntervalKey = getActivity().getResources().getString(R.string.refresh_interval_preference_key);
        if(key.equals(refreshIntervalKey)) {
            getActivity().sendBroadcast(new Intent("app.mytweet.receivers.SEND_BROADCAST"));
        }
    }

    @Override
    public void onStop()
    {
        super.onStop();
        prefs.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                navigateUp(getActivity());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}