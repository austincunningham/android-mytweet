package app.mytweet.android.helpers;

import android.app.Activity;
import android.content.Intent;
import android.provider.ContactsContract;

/**
 * Created by ictskills on 03/10/16.
 */
public class IntentHelper {
    public static void selectContact(Activity parent, int id)
    {
        Intent selectContactIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        parent.startActivityForResult(selectContactIntent, id);
    }
}
