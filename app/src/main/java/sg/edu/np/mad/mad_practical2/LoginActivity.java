package sg.edu.np.mad.mad_practical2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Users");
        DatabaseReference nested=myRef.child("mad").push();
        DatabaseReference myRef2 = database.getReference("mad");
        DatabaseReference nested2=myRef2.child("password:").push();
        DatabaseReference nested3=myRef2.child("username:").push();
        nested2.setValue("11877");
        nested3.setValue("mad");
        Button loginButton=findViewById(R.id.LoginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myRef2.child("password:").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String password_data=snapshot.getChildren().toString();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                myRef2.child("username").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String username_data=snapshot.getChildren().toString();
                        loginButton.setText(username_data);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                EditText userName=findViewById(R.id.EditUsername);
                EditText passWord=findViewById(R.id.EditPassword);
                String username=userName.getText().toString();
                Integer password=Integer.valueOf(passWord.getText().toString());
                if(username==username&&password==11877) {
                    Intent intent = new Intent(LoginActivity.this, ListActivity.class);
                    startActivity(intent);
                }



            }
        });

    }
}