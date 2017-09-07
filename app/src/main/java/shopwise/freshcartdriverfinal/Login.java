package shopwise.freshcartdriverfinal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import shopwise.freshcartdriverfinal.IncomingOrders.NewOrdersActivity;

public class Login extends AppCompatActivity {

    //private Toolbar mToolbar;
    private EditText txtEmail;
    private EditText txtPassword;
    private Button Main_Login;
    TextView Title;
    TextView txtSignUP;

    //Progress Dialog
    private ProgressDialog mLoginProgress;

    //Firebase Connections
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Title = (TextView) findViewById(R.id.txtTitle);
        Typeface tx = Typeface.createFromAsset(getApplicationContext().getAssets(), "Fonts/BebasNeue.otf");
        Title.setTypeface(tx);

        txtSignUP = (TextView) findViewById(R.id.txtSignUP);
        txtSignUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Verify = new Intent(Login.this, VerifyEmail.class);
                startActivity(Verify);
            }
        });


        //Fields
        txtEmail = (EditText) findViewById(R.id.txtLogEmail);
        txtPassword = (EditText) findViewById(R.id.txtLogPassword);
        Main_Login = (Button) findViewById(R.id.Main_btnLogin);

        //Progress Dialog
        mLoginProgress = new ProgressDialog(this);

        //Firebase Authentication
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Drivers");

        Main_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent home = new Intent(Login.this, NewOrdersActivity.class);
                startActivity(home);


                String Email = txtEmail.getEditableText().toString();
                String Password = txtPassword.getEditableText().toString();

                if (!TextUtils.isEmpty(Email) || !TextUtils.isEmpty(Password)) {

                    mLoginProgress.setTitle("Logging In");
                    mLoginProgress.setMessage("Please wait while we check your credentials.");
                    mLoginProgress.setCanceledOnTouchOutside(false);
                    mLoginProgress.show();

                    login_user(Email, Password);
                }
            }
        });

    }


    private void login_user(final String Email, String Password) {


        mAuth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    mLoginProgress.dismiss();

                    String current_driver_id = mAuth.getCurrentUser().getUid();
                    String deviceToken = FirebaseInstanceId.getInstance().getToken();

                    mDatabase.child(current_driver_id).child("device_token").setValue(deviceToken).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {


                            Intent Login = new Intent(shopwise.freshcartdriverfinal.Login.this, Home.class);
                            Login.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(Login);
                            finish();
                        }
                    });
                } else {

                    mLoginProgress.hide();

                    String task_result = task.getException().getMessage().toString();

                    Toast.makeText(Login.this, "Error: " + task_result, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}



