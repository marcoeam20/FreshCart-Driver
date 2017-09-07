package shopwise.freshcartdriverfinal;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class EmailPasswordActivity extends AppCompatActivity {

//    private static final String TAG = "EmailPassword";
//
//    private TextView mStatusTextView;
//    private TextView mDetailTextView;
//    private EditText mEmailField;
//    private EditText mPasswordField;
//
//    private ProgressDialog mProgressDialog;
//
//    //Firebase Auth Connection
//    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_password);

//        //Views
//        mStatusTextView = (TextView) findViewById(R.id.status);
//        mDetailTextView = (TextView) findViewById(R.id.detail);
//        mEmailField = (EditText) findViewById(R.id.field_email);
//        mPasswordField = (EditText) findViewById(R.id.field_password);
//
//        mProgressDialog = new ProgressDialog(this);
//
//        //Initialize Firebase Auth
//        mAuth = FirebaseAuth.getInstance();
//
//    }
//
//    //Check if User already in Firebase
//
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        //Check if user is signed in and Update UI
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
//    }
//
//    private void createAccount(String email, String password){
//        Log.d(TAG, "createAccount:" + email);
//        if(!validateForm()){
//            return;
//        }
//
//        mProgressDialog.show();
//
//        mAuth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//
//                        if(task.isSuccessful()){
//                            //Sign in success, update UI with the signed in driver's information
//                            Log.d(TAG, "createUserWithEmail:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            updateUI(user);
//                        } else {
//                            //If sign in fails, display error message
//                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
//                            Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
//                        }
//
//                        mProgressDialog.hide();
//
//                    }
//                });
//    }
//
//    private void Login(String email, String password){
//        Log.d(TAG, "Login: " + email);
//        if(!validateForm()){
//            return;
//        }
//
//        mProgressDialog.show();
//
//        //Login with Email
//        mAuth.signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//
//                        if (task.isSuccessful()){
//
//                            Log.d(TAG, "signInWithEmail:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            updateUI(user);
//                        } else {
//
//                            Log.w(TAG, "signInWithEmail:failure", task.getException());
//                            Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
//                        }
//
//                        //EXCLUDE
//                        if(!task.isSuccessful()){
//                            mStatusTextView.setText("Failed");
//                        }
//                        mProgressDialog.hide();
//                    }
//                });
//    }
//
//    private void signOut(){
//        mAuth.signOut();
//        updateUI(null);
//    }
//
//    private void sendEmailVerification(){
//        //Disable Button
//        findViewById(R.id.verify_email_button).setEnabled(false);
//
//        //Send Verification Email
//        final FirebaseUser user = mAuth.getCurrentUser();
//        user.sendEmailVerification()
//                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        //Re-enable button
//                        findViewById(R.id.verify_email_button).setEnabled(true);
//
//                        if(task.isSuccessful()){
//
//                            Toast.makeText(EmailPasswordActivity.this,
//                                    "Verification Email sent to " + user.getEmail(),
//                                    Toast.LENGTH_SHORT).show();
//                        } else {
//
//                            Log.e(TAG, "sendEmailVerification", task.getException());
//                            Toast.makeText(EmailPasswordActivity.this,
//                                    "Failed to send Verification Email.",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//    }
//
//    private boolean validateForm(){
//        boolean valid = true;
//
//        String email = mEmailField.getText().toString();
//        if(TextUtils.isEmpty(email)) {
//            mEmailField.setError("Required");
//            valid = false;
//        } else {
//            mEmailField.setError(null);
//        }
//
//        String password = mPasswordField.getText().toString();
//        if(TextUtils.isEmpty(password)){
//            mPasswordField.setError("Required");
//            valid = false;
//        } else {
//            mPasswordField.setError(null);
//        }
//
//        return valid;
//    }
//
//
////    private void updateUI(FirebaseUser user) {
////        mProgressDialog.hide();
////        if(user != null){
////            mStatusTextView.setText(getString( user.getEmail(), user.isEmailVerified()));
////            mDetailTextView.setText(getString( user.getUid()));
////
////            findViewById(R.id.email_password_buttons).setVisibility(View.GONE);
////            findViewById(R.id.email_password_fields).setVisibility(View.GONE);
////            findViewById(R.id.email_sign_in_button).setVisibility(View.VISIBLE);
////
////            findViewById(R.id.verify_email_button).setEnabled(!user.isEmailVerified());
////        } else {
////            mStatusTextView.setText(R.string.signOut);
////            mDetailTextView.setText(null);
////
////            findViewById(R.id.email_password_buttons).setVisibility(View.VISIBLE);
////            findViewById(R.id.email_password_fields).setVisibility(View.VISIBLE);
////            findViewById(R.id.signed_in_buttons).setVisibility(View.GONE);
////        }
////    }
//
//    @Override
//    public void onClick(View v){
//        int i = v.getId();
//        if(i == R.id.email_create_account_button){
//            createAccount(mEmailField.getText().toString(), mPasswordField.getText().toString());
//        } else if (i == R.id.email_sign_in_button){
//            Login(mEmailField.getText().toString(), mPasswordField.getText().toString());
//        } else if (i == R.id.sign_out_button){
//            signOut();
//        } else if (i == R.id.verify_email_button){
//            sendEmailVerification();
//        }
    }
}
