package com.example.muneshkumar.myapplication;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;

public class MainActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    RequestQueue requestQueue;

    View showdialog;
    private static final String TAG = "MainActivity";
    private Button stringRequestButton;
    private Button JsonObjectRequestButton;
    private Button JsonArrayRequestButton;
    private Button ImageRequestButton;
    private View showDialogView;
    private TextView outputTextView;
    private ImageView outputImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate the cache



        vollyStringRequest("https://androidtutorialpoint.com/api/volleyString");
    }


    public void vollyStringRequest(String url) {


        final String Requst_Tag =" this is from the activity ";

         progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)

            {

             //   Log.d(Requst_Tag,response.toString());

                LayoutInflater li = LayoutInflater.from(MainActivity.this);
                showDialogView = li.inflate(R.layout.show_dialog, null);
                outputTextView = (TextView)showDialogView.findViewById(R.id.text_view_dialog);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                alertDialogBuilder.setView(showDialogView);
                alertDialogBuilder
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        })
                        .setCancelable(false)
                        .create();
                outputTextView.setText(response.toString());
                alertDialogBuilder.show();
                progressDialog.hide();


            }
        },
                new Response.ErrorListener()

                {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                progressDialog.hide();
            }
        }
        );
// now we have to adding the request to RequestQueue


       MySingleton.getInstance(this).addToRequestQueue(stringRequest);

      /*requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);*/
    }

}
