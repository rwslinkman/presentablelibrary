package nl.rwslinkman.presentablelibrary;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rick Slinkman
 */

class Utils
{
    private static final String TAG = "Utils";

    @NonNull
    static List<String> createRandomNamesList(Resources r, int numberOfNames)
    {
        List<Integer> namesResources = getExampleNamesResources();
        if(numberOfNames >= namesResources.size()) {
            // Cannot use more names than available
            numberOfNames = namesResources.size();
        }

        List<String> randomNames = new ArrayList<>();
        for(int i = 0; i < numberOfNames; i++)
        {
            String name = r.getString(namesResources.get(i));
            randomNames.add(name);
        }
        return randomNames;
    }

    static private List<Integer> getExampleNamesResources()
    {
        List<Integer> result = new ArrayList<>();
        // Find all strings
        Field[] fields = R.string.class.getFields();
        for(final Field field : fields)
        {
            String name = field.getName(); //name of string
            if(name.startsWith("example_data_item_"))
            {
                try
                {
                    // Add string to list of options
                    int id = field.getInt(R.string.class); //id of string
                    result.add(id);
                }
                catch (Exception ex) {
                    // Error grabbing string
                    Log.e(TAG, "Could not get string: " + ex.getMessage());
                }
            }
        }
        return result;
    }

    static void closeKeyboard(Activity a)
    {
        View view = a.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)a.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
