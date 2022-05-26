package sg.edu.np.mad.mad_practical2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button Message = findViewById(R.id.Message);
        Message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MessageGroup.class);
                startActivity(intent);
            }
        });
        TextView displayName=findViewById(R.id.displayname);
        SQLAdapter db = new SQLAdapter(this);
        int id=getIntent().getIntExtra("id",-1);
        ArrayList<User> userList= db.GetUser();
        User user=userList.get(id);
        displayName.setText(user.name);
        TextView displayDescription=findViewById(R.id.displaydescription);
        displayDescription.setText(user.description);
        Button follow=findViewById(R.id.follow);
        if(user.followed){
            follow.setText("UNFOLLOW");
        }
        else{
            follow.setText("FOLLOW");
        }
        follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView follow=findViewById(R.id.follow);
                if (user.followed){
                    user.followed=false;
                    db.UpdateUser(user);
                    follow.setText("FOLLOW");
                    Toast.makeText(MainActivity.this,"unFollowed", Toast.LENGTH_SHORT).show();

                }
                else{
                    user.followed=true;
                    db.UpdateUser(user);
                    follow.setText("UNFOLLOW");
                    Toast.makeText(MainActivity.this,"Followed", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }



    }


