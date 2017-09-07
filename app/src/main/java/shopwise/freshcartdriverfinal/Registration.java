package shopwise.freshcartdriverfinal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;
import java.util.jar.Attributes;

public class Registration extends AppCompatActivity {

    private static final String TAG = "Registration" ;
    private TextInputEditText txtName;
    private TextInputEditText txtUsername;
    private TextInputEditText txtEmail;
    private TextInputEditText txtPassword;
    private TextInputEditText txtConfirmPassword;
    private Button Main_Register;

    private Toolbar mToolbar;

    //Progress Dialog
    private ProgressDialog mProgressDialog;

    //Firebase Connections
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    //private FirebaseMethods
    private DatabaseReference mDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        //Toolbar
        mToolbar = (Toolbar) findViewById(R.id.MainAppBar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Create Account");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Progress Dialog
        mProgressDialog = new ProgressDialog(this);

        //Firebase
        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() != null) {
            //profile activity here
            finish();
            startActivity(new Intent(getApplicationContext(), Home.class));
        }


        //Fields
        txtName = (TextInputEditText) findViewById(R.id.txtName);
        txtUsername = (TextInputEditText) findViewById(R.id.txtUsername);
        txtEmail = (TextInputEditText) findViewById(R.id.txtEmail);
        txtPassword = (TextInputEditText) findViewById(R.id.txtPassword);
        txtConfirmPassword = (TextInputEditText) findViewById(R.id.txtConfirmPassword);
        Main_Register = (Button) findViewById(R.id.Main_btnRegister);

        Main_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Name = txtName.getEditableText().toString();
                String Username = txtUsername.getEditableText().toString();
                String Email = txtEmail.getEditableText().toString();
                String Password = txtPassword.getEditableText().toString();


                if(!TextUtils.isEmpty(Name) || !TextUtils.isEmpty(Username) || !TextUtils.isEmpty(Email) || !TextUtils.isEmpty(Password)){

                    mProgressDialog.setTitle("Registering User");
                    mProgressDialog.setMessage("Please wait while we create your account!");
                    mProgressDialog.setCanceledOnTouchOutside(false);
                    mProgressDialog.show();

                    createAccount(Name, Username, Email, Password);

                }
            }
        });








    }





    public void onClick(View v){

        int i = v.getId();
        if(i == R.id.Main_btnRegister){
            createAccount(txtEmail.getEditableText().toString(), txtPassword.getEditableText().toString(), txtName.getEditableText().toString(), txtUsername.getEditableText().toString());
        } else if (i == R.id.btn_VerifyEmail){
            sendEmailVerification();
        }
    }

}
