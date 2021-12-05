package com.MadeInMyHome.component;

import android.graphics.Bitmap;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class convertToString
{
    public String convertToString(Bitmap bitmap) {
        // convert bitmap to byte
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 50, stream);
        byte imageInByte[] = stream.toByteArray();
        String encodedString = Base64.encodeToString(imageInByte, 0);
        return encodedString;
    }
}
