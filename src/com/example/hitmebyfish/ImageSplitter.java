package com.example.hitmebyfish;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 晓宇 on 2014/12/29.
 */
public class ImageSplitter
{
    public static List<ImagePiece> split(Bitmap bitmap, int xPiece, int yPiece, int pre)
    {
        List<ImagePiece> pieces = new ArrayList<ImagePiece>(xPiece * yPiece);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        int pieceWidth = width / xPiece;
        int pieceHeight = height / yPiece;

        for (int i = 0; i < yPiece; i++)
        {
            for (int j = 0; j < xPiece; j++)
            {
                if (j % pre == 0)
                {
                    ImagePiece piece = new ImagePiece();
                    piece.index = j + i * xPiece;

                    int xValue = j * pieceWidth;
                    int yValue = i * pieceHeight;

                    piece.bitmap = Bitmap.createBitmap(bitmap, xValue, yValue, pieceWidth, pieceHeight);

                    pieces.add(piece);
                }
            }
        }
        return pieces;
    }
}
