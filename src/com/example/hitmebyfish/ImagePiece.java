package com.example.hitmebyfish;

import android.graphics.Bitmap;

/**
 * Created by 晓宇 on 2014/12/29.
 */
public class ImagePiece
{
    public int index = 0;
    public Bitmap bitmap = null;

    ImagePiece() {}

    ImagePiece(int i, Bitmap b)
    {
        this.index = i;
        this.bitmap = b;
    }
}
