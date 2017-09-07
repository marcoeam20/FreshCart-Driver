package shopwise.freshcartdriverfinal;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class VerifyEmail extends AppCompatActivity {

    private static final String TAG = "CreateAccount";
    TextInputEditText mEmailField;
    Button mVerifyEmail;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_email);

        mEmailField = (TextInputEditText) findViewById(R.id.txtVerifyEmail);
        mVerifyEmail = (Button) findViewById(R.id.btn_VerifyEmail);

        mVerifyEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendEmailVerification();
            }
        });
    }




    private void sendEmailVerification() {

//        //Disable Button
//        findViewById(R.id.btn_VerifyEmail).setEnabled(false);

        //Send Verification Email
        final FirebaseUser user = mAuth.getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //Re-enable button
                        findViewById(R.id.btn_VerifyEmail).setEnabled(true);

                        if (task.isSuccessful()) {
                            Toast.makeText(VerifyEmail.this,
                                    "Verification email sent to " + user.getEmail(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e(TAG, "sendEmailVerification", task.getException());
                            Toast.makeText(VerifyEmail.this,
                                    "Failed to send verification email.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

//    private void updateUI(FirebaseUser user) {
//        mProgressDialog.hide();
//        if(user != null){
//            mStatusTextView.setText(getString( user.getEmail(), user.isEmailVerified()));
//            mDetailTextView.setText(getString( user.getUid()));
//
//            findViewById(R.id.email_password_buttons).setVisibility(View.GONE);
//            findViewById(R.id.email_password_fields).setVisibility(View.GONE);
//            findViewById(R.id.email_sign_in_button).setVisibility(View.VISIBLE);
//
//            findViewById(R.id.verify_email_button).setEnabled(!user.isEmailVerified());
//        } else {
//            mStatusTextView.setText(R.string.signOut);
//            mDetailTextView.setText(null);
//
//            findViewById(R.id.email_password_buttons).setVisibility(View.VISIBLE);
//            findViewById(R.id.email_password_fields).setVisibility(View.VISIBLE);
//            findViewById(R.id.signed_in_buttons).setVisibility(View.GONE);
//        }
//    }


}

