package msu.ece.xiaozeng.mpf3.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import msu.ece.xiaozeng.mpf3.R;
import msu.ece.xiaozeng.mpf3.ShowPillActivity;
import msu.ece.xiaozeng.mpf3.entity.Pill;

/**
 * Created by xiaozeng on 2/17/17.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PillViewHolder>{


    private ArrayList<Pill> pills;

    //private boolean setClicked = false;
    private Context context;
    public RVAdapter(ArrayList<Pill> pills, Context context){
        this.pills = pills;
        this.context = context;
    }



    @Override
    public PillViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pill_cardview, viewGroup, false);
        PillViewHolder pvh = new PillViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PillViewHolder pillViewHolder, final int i) {
        pillViewHolder.tv_pill_rank.setText(""+(i+1));
        pillViewHolder.tv_pillName.setText(pills.get(i).name);
        pillViewHolder.tv_pillColor.setText(pills.get(i).color);
        pillViewHolder.tv_pillImprint.setText(pills.get(i).imprint);
        pillViewHolder.iv_pillFrontPic.setImageResource(pills.get(i).frontPicID);
        pillViewHolder.iv_pillBackPic.setImageResource(pills.get(i).backPicID);



        pillViewHolder.itemView.setOnClickListener( new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Pill pill = pills.get(i);
                    Intent intent = new Intent(context , ShowPillActivity.class);
                    intent.putExtra("pill",pill);
                    context.startActivity(intent);

                }
            });

    }

    @Override
    public int getItemCount() {
        return pills.size();
    }



    public static class PillViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView tv_pillName;
        TextView tv_pillColor;
        TextView tv_pillImprint;
        ImageView iv_pillFrontPic;
        ImageView iv_pillBackPic;
        TextView tv_pill_rank;
        PillViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            tv_pillName = (TextView)itemView.findViewById(R.id.tv_pill_name);
            tv_pillColor = (TextView)itemView.findViewById(R.id.tv_pill_color);
            iv_pillFrontPic = (ImageView)itemView.findViewById(R.id.iv_pill_front_pic);
            iv_pillBackPic = (ImageView)itemView.findViewById(R.id.iv_pill_back_pic);
            tv_pillImprint = (TextView)itemView.findViewById(R.id.tv_pill_imprint);
            tv_pill_rank = (TextView)itemView.findViewById(R.id.tv_pill_rank);
        }

    }





}
