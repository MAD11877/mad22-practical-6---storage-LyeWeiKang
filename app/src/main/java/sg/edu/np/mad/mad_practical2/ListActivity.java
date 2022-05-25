package sg.edu.np.mad.mad_practical2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        RecyclerView rv=findViewById(R.id.RecyclerView);
        SQLAdapter db = new SQLAdapter(this);
        ArrayList<User> userList= db.GetUser();
        if(userList.size()==0){
            for(int i=0;i<20;i++){
                Random random = new Random();
                int intRandom = random.nextInt();
                Random random2 = new Random();
                int intRandom2=random2.nextInt();
                User user=new User("Name"+intRandom,"Description"+intRandom2,i,false);
                userList.add(user);
            }
            for(int t=0;t<userList.size();t++){
                db.addUser(userList.get(t));
            }
        }

        ListActivityAdapter adapter=new ListActivityAdapter(userList,ListActivity.this);
        LinearLayoutManager layout=new LinearLayoutManager(this);
        rv.setLayoutManager(layout);
        rv.setAdapter(adapter);
    }
}