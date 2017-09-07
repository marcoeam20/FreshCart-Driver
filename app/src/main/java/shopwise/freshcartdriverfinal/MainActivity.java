package shopwise.freshcartdriverfinal;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView Title;
    Button Main_btnLogin;
    Button Main_btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Title = (TextView) findViewById(R.id.Main_Title);
        Typeface tx = Typeface.createFromAsset(getApplicationContext().getAssets(), "Fonts/BebasNeue.otf");
        Title.setTypeface(tx);

        Main_btnLogin = (Button) findViewById(R.id.Main_btnLogin);
        Main_btnRegister = (Button) findViewById(R.id.Main_btnRegister);

        Main_btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent Login = new Intent(MainActivity.this, shopwise.freshcartdriverfinal.Login.class);
                startActivity(Login);
            }
        });

        Main_btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent Register = new Intent(MainActivity.this, Registration.class);
                startActivity(Register);
            }
        });


    }
}
