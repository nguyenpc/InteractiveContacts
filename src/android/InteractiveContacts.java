package org.apache.cordova.interactivecontacts;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.provider.ContactsContract;

public class InteractiveContacts extends CordovaPlugin  {

    private static final String addContact = "addContact";
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        
        System.out.println("action: " + action);
        
        boolean success = false;
        
        if (action.equalsIgnoreCase(addContact)) {
            
            JSONObject contactDetails = args.optJSONObject(0);
            
            System.out.println("contactDetails: " + contactDetails);
            
            if(contactDetails != null && !contactDetails.optString("name").isEmpty()) {
                
                this.addContact(contactDetails, callbackContext);
                success =  true;
                
            }
        }
        
        return success;
    }

    private void addContact(JSONObject contactDetails, CallbackContext callbackContext) {
      
        Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION,ContactsContract.Contacts.CONTENT_URI);
        intent.putExtra(ContactsContract.Intents.Insert.NAME, contactDetails.optString("name"));
        intent.putExtra(ContactsContract.Intents.Insert.COMPANY, contactDetails.optString("company"));
        intent.putExtra(ContactsContract.Intents.Insert.EMAIL, contactDetails.optString("email"));
        intent.putExtra(ContactsContract.Intents.Insert.PHONE, contactDetails.optString("phone"));
        intent.putExtra(ContactsContract.Intents.Insert.POSTAL, contactDetails.optString("address"));
        
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        
        this.cordova.getActivity().getApplicationContext().startActivity(intent);
        
    }
    
}
