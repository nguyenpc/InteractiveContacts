package org.apache.cordova.interactivecontacts;

import android.content.Intent;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.util.Log;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class InteractiveContacts extends CordovaPlugin {

    private static final String addContact = "addContact";
    private static final String updateContact = "updateContact";

    private static final String TAG = "InteractiveContacts";

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        JSONObject contactDetails = args.optJSONObject(0);

        Log.i(TAG, "action: " + action);

        if (action.equalsIgnoreCase(addContact)) {

            if (contactDetails != null && !contactDetails.optString("name").isEmpty()) {

                this.addContact(contactDetails, callbackContext);

            }

        } else if (action.equalsIgnoreCase(updateContact)) {

            this.updateContact(contactDetails, callbackContext);

        } else {

            return false;

        }

        return true;
    }

    private void addContact(JSONObject contactDetails, CallbackContext callbackContext) {

        Log.i(TAG, "Entering addContact");

        Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION, ContactsContract.Contacts.CONTENT_URI);
        intent.putExtra(ContactsContract.Intents.Insert.NAME, contactDetails.optString("name"));
        intent.putExtra(ContactsContract.Intents.Insert.COMPANY, contactDetails.optString("company"));
        intent.putExtra(ContactsContract.Intents.Insert.EMAIL, contactDetails.optString("email"));
        intent.putExtra(ContactsContract.Intents.Insert.PHONE, contactDetails.optString("phone"));
        intent.putExtra(ContactsContract.Intents.Insert.POSTAL, contactDetails.optString("address"));

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        this.cordova.getActivity().startActivity(intent);

        Log.i(TAG, "Exiting addContact");

    }

    private void updateContact(JSONObject contactDetails, CallbackContext callbackContext) {

        Log.i(TAG, "Entering updateContact");

        Intent intentInsertEdit = new Intent(Intent.ACTION_INSERT_OR_EDIT);
        intentInsertEdit.setType(Contacts.CONTENT_ITEM_TYPE);

        intentInsertEdit.putExtra(ContactsContract.Intents.Insert.NAME, contactDetails.optString("name"));
        intentInsertEdit.putExtra(ContactsContract.Intents.Insert.COMPANY, contactDetails.optString("company"));
        intentInsertEdit.putExtra(ContactsContract.Intents.Insert.EMAIL, contactDetails.optString("email"));
        intentInsertEdit.putExtra(ContactsContract.Intents.Insert.PHONE, contactDetails.optString("phone"));
        intentInsertEdit.putExtra(ContactsContract.Intents.Insert.POSTAL, contactDetails.optString("address"));

        this.cordova.getActivity().startActivity(intentInsertEdit);

        Log.i(TAG, "Exiting updateContact");

    }

}
