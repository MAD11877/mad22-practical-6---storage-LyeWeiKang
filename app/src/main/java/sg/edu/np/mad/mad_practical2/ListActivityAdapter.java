package sg.edu.np.mad.mad_practical2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class ListActivityAdapter extends RecyclerView.Adapter<ListActivityViewHolder> {
    ArrayList<User> userList;
    ListActivity activity;
    public ListActivityAdapter(ArrayList<User>userList,ListActivity activity){
        this.userList=userList;
        this.activity=activity;
    }
    @NonNull
    @Override
    public ListActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item=null;
        if(viewType==0)
            item= LayoutInflater.from(parent.getContext()).inflate(R.layout.listactivity_alternatercv,parent,false);
        else
            item= LayoutInflater.from(parent.getContext()).inflate(R.layout.listactivity_rcv,null,false);


        return new ListActivityViewHolder(item);
    }
    @Override
    public int getItemViewType(int position) {
        User n = userList.get(position);
        if(Character.toString(n.name.charAt(n.name.length() - 1)).equals("7"))
            return 0;
        else
            return 1;
    }

    @Override
    public void onBindViewHolder(@NonNull ListActivityViewHolder holder, int position) {
        User n=userList.get(position);
        holder.name.setText(n.name);
        holder.description.setText(n.description);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ad = new AlertDialog.Builder(activity);
                ad.setTitle("Profile");
                ad.setMessage(n.name);
                ad.setPositiveButton("View", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent=new Intent(activity,MainActivity.class);
                        intent.putExtra("user",n);
                        activity.startActivity(intent);
                    }
                });
                ad.setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                ad.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}