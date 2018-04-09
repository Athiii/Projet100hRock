package com.example.athi.rock.utilisateur.evenement;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.athi.rock.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Athi on 13/03/2018.
 */

public class AlbumAdapter extends BaseAdapter{
    private Context mContext;
    private List<Photo> mPhotoList;

    public AlbumAdapter(Context c, List<Photo> photoList){
        mContext=c;
        mPhotoList=photoList;

    }

    @Override
    public int getCount() {return mPhotoList.size();}

    @Override
    public Object getItem(int i) {
        return mPhotoList.get(i);
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
        Uri uri = Uri.parse(mPhotoList.get(i).getUrlImage());
        Picasso.with(mContext).load(uri).into(imageView);
//        imageView.setImageResource());
        return imageView;
    }
//    private Integer[] mThumbIds= {

//      };
//            R.drawable.ic_etoile,
//            R.drawable.ic_etoile,
//            R.drawable.ic_etoile,
//            R.drawable.ic_etoile,
//            R.drawable.ic_etoile,
//            R.drawable.ic_etoile,
//            R.drawable.ic_etoile,
//            R.drawable.ic_etoile,
//            R.drawable.ic_etoile,
//            R.drawable.ic_etoile,
//            R.drawable.ic_etoile,
//            R.drawable.ic_etoile,
//            R.drawable.ic_etoile,
//            R.drawable.ic_etoile,
//            R.drawable.ic_etoile,
//            R.drawable.ic_etoile,
//            R.drawable.ic_etoile,
//            R.drawable.ic_etoile,
//            R.drawable.ic_etoile,
//            R.drawable.ic_etoile};

}

