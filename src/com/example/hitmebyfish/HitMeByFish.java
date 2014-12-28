package com.example.hitmebyfish;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.ImageView;
import com.example.theuselessapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 晓宇 on 2014/12/29.
 */
public class HitMeByFish extends Activity
{
    private ImageView hitmeImage;
    List<ImagePiece> pieces1, pieces2, pieces3, pieces4;
    Bitmap bitmap1, bitmap2, bitmap3, bitmap4;
    int WIDTH = 0;
    int X = 0;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.hitmebyfish_lay);

        init();
    }

    private void init()
    {
        hitmeImage = (ImageView)findViewById(R.id.hitme_image);

        bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.eelslap_site_panorama1);
        bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.eelslap_site_panorama2);
        bitmap3 = BitmapFactory.decodeResource(getResources(), R.drawable.eelslap_site_panorama3);
        bitmap4 = BitmapFactory.decodeResource(getResources(), R.drawable.eelslap_site_panorama4);

        pieces1 = ImageSplitter.split(bitmap1, 24, 1, 5);
        pieces2 = ImageSplitter.split(bitmap2, 23, 1, 5);
        pieces3 = ImageSplitter.split(bitmap3, 24, 1, 5);
        pieces4 = ImageSplitter.split(bitmap4, 23, 1, 5);

        hitmeImage.setImageBitmap(pieces1.get(0).bitmap);

        DisplayMetrics dm = getResources().getDisplayMetrics();
        WIDTH = dm.widthPixels;
    }

    private int getSection(int w)
    {
        if (w <= (WIDTH / 4))
            return 4;
        else if (w <= (WIDTH / 2))
            return 3;
        else if (w <= (WIDTH / 4 * 3))
            return 2;
        else
            return 1;
    }

    private int getNum(int x, int s, int n)
    {
        int tempX = 0;
        int tempWidth = 0;
        int N = -1;

        if (s == 1)
        {
            tempWidth = WIDTH - (WIDTH / 4 * 3);
            tempX = x - (WIDTH / 4 * 3);
        }
        else if (s == 2)
        {
            tempWidth = (WIDTH / 4 * 3) - (WIDTH / 2);
            tempX = x - (WIDTH / 2);
        }
        else if (s == 3)
        {
            tempWidth = (WIDTH / 2) - (WIDTH / 4);
            tempX = x - (WIDTH / 4);
        }
        else
        {
            tempWidth = (WIDTH / 4);
            tempX = x;
        }

        int[] sec = new int[n];

        for (int i = 0; i < n; i++)
        {
            sec[i] = tempWidth / n * (i + 1);
        }

        for (int j = 0; j < n - 1; j++)
        {
            if (tempX < sec[j])
            {
                N = j;
                break;
            }
        }

        if (N == -1)
            N = n - 1;

        N = n - N - 1;

        return N;
    }

    public boolean onTouchEvent(MotionEvent e)
    {
        X = (int)e.getX();
        int section = getSection(X);
        if (section == 1)
        {
            hitmeImage.setImageBitmap(pieces1.get(getNum(X, 1, pieces1.size())).bitmap);
        }
        else if (section == 2)
        {
            hitmeImage.setImageBitmap(pieces2.get(getNum(X, 2, pieces2.size())).bitmap);
        }
        else if (section == 3)
        {
            hitmeImage.setImageBitmap(pieces3.get(getNum(X, 3, pieces3.size())).bitmap);
        }
        else
        {
            hitmeImage.setImageBitmap(pieces4.get(getNum(X, 4, pieces4.size())).bitmap);
        }

        return true;
    }
}
