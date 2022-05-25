package sg.edu.np.mad.mad_practical2;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ListActivityAlternateViewHolder extends RecyclerView.ViewHolder {
    ImageView image;
    public ListActivityAlternateViewHolder(View viewItem){
        super(viewItem);
        image=viewItem.findViewById(R.id.imageView8);
    }
}
