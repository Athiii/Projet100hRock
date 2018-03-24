package com.example.athi.rock.utilisateur.evenement;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.athi.rock.R;

/**
 * Created by Athi on 13/03/2018.
 */

public class AlbumAdapter extends BaseAdapter{
    private Context mContext;

    public AlbumAdapter(Context c){
        mContext=c;

    }

    @Override
    public int getCount() {return mThumbIds.length;}

    @Override
    public Object getItem(int i) {
        return mThumbIds[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView;
        if(view==null){
            imageView=new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85,85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8,8,8,8);
        }else{
            imageView=(ImageView) view;
        }
        imageView.setImageResource(mThumbIds[i]);
        return imageView;
    }
    private Integer[] mThumbIds={
            R.drawable.ic_etoile,
            R.drawable.ic_etoile,
            R.drawable.ic_etoile,
            R.drawable.ic_etoile,
            R.drawable.ic_etoile,
            R.drawable.ic_etoile,
            R.drawable.ic_etoile,
            R.drawable.ic_etoile,
            R.drawable.ic_etoile,
            R.drawable.ic_etoile,
            R.drawable.ic_etoile,
            R.drawable.ic_etoile,
            R.drawable.ic_etoile,
            R.drawable.ic_etoile,
            R.drawable.ic_etoile,
            R.drawable.ic_etoile,
            R.drawable.ic_etoile,
            R.drawable.ic_etoile,
            R.drawable.ic_etoile,
            R.drawable.ic_etoile};

}

