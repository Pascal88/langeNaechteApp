/*
 * Copyright 2008 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package de.htwg.tetris.gui.activities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import de.htwg.tetris.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.text.SpannableString;
import android.text.util.Linkify;
import android.util.Log;

/**
 * This class handles display of EULAs ("End User License Agreements") to the
 * user.
 */
public class Eula {
	public static final String PREFERENCE_EULA_ACCEPTED = "eula.accepted";
	public static final String PREFERENCES_EULA = "eula";

  public Eula() {/* Nothing to do */}

  /**
   * Displays the EULA if necessary. This method should be called from the
   * onCreate() method of your main Activity.  If the user accepts, the EULA
   * will never be displayed again.  If the user refuses, the activity will
   * finish (exit).
   *
   * @param activity The Activity to finish if the user rejects the EULA
   */
  public static void showEulaRequireAcceptance(final Activity activity) {
    final SharedPreferences preferences =
        activity.getSharedPreferences(PREFERENCES_EULA, Activity.MODE_PRIVATE);
    if (preferences.getBoolean(PREFERENCE_EULA_ACCEPTED, false)) {
      return;
    }

    final AlertDialog.Builder builder = initDialog(activity);
    builder.setPositiveButton(R.string.accept,
        new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int which) {
            accept(activity, preferences);
          }
        });
    builder.setNegativeButton(R.string.decline,
        new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int which) {
            refuse(activity);
          }
        });
    builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
      public void onCancel(DialogInterface dialog) {
        refuse(activity);
      }
    });
    builder.show();
  }

  /**
   * Display the EULA to the user in an informational context.  They won't be
   * given the choice of accepting or declining the EULA -- we're simply
   * displaying it for them to read.
   */
  public static void showEula(Context context) {
    AlertDialog.Builder builder = initDialog(context);
    builder.setPositiveButton(R.string.accept, null);
    builder.show();
  }
  
  private static AlertDialog.Builder initDialog(Context c) {
  CharSequence eula = readEulaFile(c);
  	final SpannableString ss = new SpannableString(eula);
  	Linkify.addLinks(ss, Linkify.ALL);
    AlertDialog.Builder builder = new AlertDialog.Builder(c);
    builder.setCancelable(true);
    builder.setTitle(R.string.eula_title);
    builder.setMessage(ss);
    return builder;
  }

    private static CharSequence readEulaFile(Context c) {
	BufferedReader in = null;
	try {
	    in = new BufferedReader(new InputStreamReader(c.getResources()
		    .openRawResource(R.raw.eula)));
	    String line;
	    StringBuilder buffer = new StringBuilder();
	    while ((line = in.readLine()) != null) {
		buffer.append(line).append('\n');
	    }
	    return buffer;
	} catch (IOException e) {
	    Log.d("Eula", "Exception");
	    return "";
	} finally {
	    if (in != null) {
		try {
		    in.close();
		} catch (IOException e) {
		    Log.d("Eula", "Close Exception");
		}
	    }
	}
    }

private static void accept(Activity activity, SharedPreferences preferences) {
    preferences.edit().putBoolean(PREFERENCE_EULA_ACCEPTED, true).commit();
  }

  private static void refuse(Activity activity) {
    activity.finish();
  }
}
