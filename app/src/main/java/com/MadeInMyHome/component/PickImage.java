package com.MadeInMyHome.component;

import android.content.Context;
import android.widget.ImageView;
import android.widget.Toast;

import com.MadeInMyHome.R;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickCancel;
import com.vansuita.pickimage.listeners.IPickResult;

import androidx.appcompat.app.AppCompatActivity;


public class PickImage {

    public PickImage(final Context context, final ImageView imageView) {
        PickImageDialog.build(new PickSetup())
                .setOnPickResult(new IPickResult() {
                    @Override
                    public void onPickResult(PickResult r) {
                        //TODO: do what you have to...
                        imageView.setImageBitmap(r.getBitmap());

                    }
                })
                .setOnPickCancel(new IPickCancel() {
                    @Override
                    public void onCancelClick() {
                        //TODO: do what you have to if user clicked cancel
                        Toast.makeText(context, context.getResources().getString(R.string.cancel), Toast.LENGTH_LONG).show();

                    }
                }).show(((AppCompatActivity) context).getSupportFragmentManager());
    }
}