package com.wedontanything.usedmarket.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.wedontanything.usedmarket.R;
import java.util.List;

public class AddImageAdapter extends BaseAdapter {

    LayoutInflater inflater;
    private List<Bitmap> imageLIst;

    public AddImageAdapter(List<Bitmap> imageLIst) {
        this.imageLIst = imageLIst;
    }

    @Override
    public int getCount() {
        return imageLIst.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            final Context context = parent.getContext();
            if (inflater == null)
                inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.add_image_list_item, parent, false);
        }

        ImageView imageview = convertView.findViewById(R.id.addImageListImage);
        imageview.setImageBitmap(imageLIst.get(0));

        return convertView;
    }
}
