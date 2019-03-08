package com.habibcse009.customfonts;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnOk, btnClear, btnCall, btnMessage, btnEmail, btnShare;
    TextView txtText;
    EditText etxInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnEmail = findViewById(R.id.btn_email);
        btnMessage = findViewById(R.id.btn_sms);
        btnShare = findViewById(R.id.btn_share);
        btnCall = findViewById(R.id.btn_call);
        btnOk = findViewById(R.id.btn1);
        btnClear = findViewById(R.id.btn2);
        txtText = findViewById(R.id.textView);
        etxInput = findViewById(R.id.editText);

        //Initialize font
        Typeface tf = Typeface.createFromAsset(getAssets(), "Milkshake.ttf");
        Typeface tf1 = Typeface.createFromAsset(getAssets(), "Quicksand-Regular.otf");
        Typeface tf2 = Typeface.createFromAsset(getAssets(), "aqua.ttf");
        Typeface tf3 = Typeface.createFromAsset(getAssets(), "SolaimanLipi.ttf");
        btnEmail.setTypeface(tf2);
        btnShare.setTypeface(tf2);
        btnMessage.setTypeface(tf2);
        btnCall.setTypeface(tf2);
        btnClear.setTypeface(tf2);
        btnOk.setTypeface(tf2);
        txtText.setTypeface(tf);
        etxInput.setTypeface(tf1);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = etxInput.getText().toString();
                if (text.isEmpty()) {
                    etxInput.setError("Please enter text!");
                    etxInput.requestFocus();
                } else {
                    txtText.setText(text);
                }
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtText.setText("Text");
                etxInput.setText("");
            }
        });
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = etxInput.getText().toString();
                if (phoneNumber.isEmpty()) {
                    etxInput.setError("Please enter phoneNumber!");
                    etxInput.requestFocus();
                } else {
                    if (phoneNumber.matches("\\d+(?:\\.\\d+)?")) {
                        if (phoneNumber.length()==11 && phoneNumber.charAt(0)=='0' && phoneNumber.charAt(1)=='1'){
                            txtText.setText(phoneNumber);
                            Intent intent = new Intent(Intent.ACTION_DIAL);
                            intent.setData(Uri.parse("tel:" + phoneNumber));
                            startActivity(intent);
                        }
                        else {
                            etxInput.setError("Please Correct Phone Number!");
                            etxInput.requestFocus();
                        }
                    } else {
                        etxInput.setError("Please enter Digit!");
                        etxInput.requestFocus();
                    }
                }

            }
        });
        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = etxInput.getText().toString();

                if (phoneNumber.isEmpty()) {
                    etxInput.setError("Please enter messages!");
                    etxInput.requestFocus();
                } else {
                    Intent sendIntent = new Intent(android.content.Intent.ACTION_VIEW);
                    //sendIntent.setData(Uri.parse("smsto:"+phoneNumber));
                    sendIntent.putExtra("sms_body", "Write message");
                    sendIntent.putExtra("address", phoneNumber);
                    sendIntent.setType("vnd.android-dir/mms-sms");
                    startActivity(sendIntent);
                }

            }
        });
        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailAddress = etxInput.getText().toString();

                if (emailAddress.isEmpty()) {
                    etxInput.setError("Please enter email address!");
                    etxInput.requestFocus();
                } else {
                    if (emailAddress.contains("@") && emailAddress.contains(".")) {
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/html");
                        // intent.putExtra(Intent.EXTRA_EMAIL, emailAddress);
                        intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + emailAddress));
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                        intent.putExtra(Intent.EXTRA_TEXT, "Hello Mr.\n Good Day.\n\n Md. Ahasan Habib");

                        startActivity(Intent.createChooser(intent, "Send Email"));
                    } else {
                        etxInput.setError("Please enter correct email address!");
                        etxInput.requestFocus();
                    }
                }
            }
        });
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = etxInput.getText().toString();
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,
                        text);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });


    }
    /*protected void sendEmail(){
        Log.i("Send email", "");
        String[] TO = {""};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email.", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }*/
}


