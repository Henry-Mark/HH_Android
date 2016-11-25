package com.henry.hh.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.henry.hh.utils.CheckGetUtil;

import java.util.Random;

/**
 * Date: 2016/11/25. 9:27
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description:自定义验证码实现类
 */
public class CheckView extends View {

    //验证码图片
    private Bitmap bitmap = null;
    //随机生成所有的数组
    final String[] strContent = new String[]{"0", "1", "2", "3", "4", "5", "6",
            "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
            "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C",
            "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    private int width;//控件宽度
    private int height;//控件高度


    public CheckView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = widthMeasureSpec;
        height = heightMeasureSpec;
    }

    public int getViewWidth() {
        return width;
    }

    public int getViewHeght() {
        return height;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, 0, 0, paint);
        } else {
            paint.setColor(Color.GRAY);
            paint.setTextSize(20);
            canvas.drawText("点击换一换", 10, 30, paint);
        }
        super.onDraw(canvas);
    }

    /**
     * 得到验证码；设置图片
     *
     * @return 生成的验证码中的内容
     */
    public String getValidataAndSetImage(int width, int height) {
        //产生随机数
        String[] strRes = generageRadom(strContent);
        //传随机串和随机数
        bitmap = generateValidate(strContent, strRes, width, height);
        //刷新
        invalidate();

        return strRes[0] + strRes[1] + strRes[2] + strRes[3];
    }


    /**
     * 绘制验证码并返回
     *
     * @param strContent
     * @param strRes
     * @return
     */
    private Bitmap generateValidate(String[] strContent, String[] strRes, int width, int height) {

        int isRes = isStrContent(strContent);
        if (isRes == 0) {
            return null;
        }

        //创建图片和画布
        Bitmap sourceBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(sourceBitmap);
        canvas.drawColor(Color.CYAN);
        Paint numPaint = new Paint();
        numPaint.setTextSize(40);
        numPaint.setFakeBoldText(true);
        numPaint.setColor(getRandColor(255,255,255));

        //设置每个字
        canvas.drawText(strRes[0], 10, height * 3 / 5, numPaint);
        Matrix mMatrix = new Matrix();
        mMatrix.setRotate((float) Math.random() * 25);
        canvas.setMatrix(mMatrix);

        canvas.drawText(strRes[1], 40, height * 3 / 5, numPaint);
        mMatrix.setRotate((float) Math.random() * 25);
        canvas.setMatrix(mMatrix);

        canvas.drawText(strRes[2], 70, height * 3 / 5 - 10, numPaint);
        mMatrix.setRotate((float) Math.random() * 25);
        canvas.setMatrix(mMatrix);

        canvas.drawText(strRes[3], 100, height * 3 / 5 - 15, numPaint);
        mMatrix.setRotate((float) Math.random() * 25);
        canvas.setMatrix(mMatrix);

        //设置绘制干扰的画笔
        Paint interferencePaint = new Paint();
        interferencePaint.setAntiAlias(true);
        interferencePaint.setStrokeWidth(4);
        interferencePaint.setColor(Color.BLACK);
        interferencePaint.setStyle(Paint.Style.FILL);    //设置paint的style

        //绘制直线
        int[] line;
        for (int i = 0; i < 2; i++) {
            line = CheckGetUtil.getLine(height, width);
            canvas.drawLine(line[0], line[1], line[2], line[3], interferencePaint);
        }
        // 绘制小圆点
        int[] point;
        for (int i = 0; i < 100; i++) {
            point = CheckGetUtil.getPoint(height, width);
            canvas.drawCircle(point[0], point[1], 1, interferencePaint);
        }

        canvas.save();
        return sourceBitmap;
    }

    /**
     * 判断字符串是否为空
     *
     * @param strContent
     * @return
     */
    private int isStrContent(String[] strContent) {
        if (strContent == null || strContent.length <= 0) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * 从指定数组中随机取出4个字符(数组)
     *
     * @param strContent
     * @return
     */
    private String[] generageRadom(String[] strContent) {
        String[] str = new String[4];
        // 随机串的个数
        int count = strContent.length;
        // 生成4个随机数
        Random random = new Random();
        int randomResFirst = random.nextInt(count);
        int randomResSecond = random.nextInt(count);
        int randomResThird = random.nextInt(count);
        int randomResFourth = random.nextInt(count);

        str[0] = strContent[randomResFirst].toString().trim();
        str[1] = strContent[randomResSecond].toString().trim();
        str[2] = strContent[randomResThird].toString().trim();
        str[3] = strContent[randomResFourth].toString().trim();
        return str;
    }

    /**
     * 从指定数组中随机取出4个字符(字符串)
     *
     * @param strContent
     * @return
     */
    private String generageRadomStr(String[] strContent) {
        StringBuilder str = new StringBuilder();
        // 随机串的个数
        int count = strContent.length;
        // 生成4个随机数
        Random random = new Random();
        int randomResFirst = random.nextInt(count);
        int randomResSecond = random.nextInt(count);
        int randomResThird = random.nextInt(count);
        int randomResFourth = random.nextInt(count);

        str.append(strContent[randomResFirst].toString().trim());
        str.append(strContent[randomResSecond].toString().trim());
        str.append(strContent[randomResThird].toString().trim());
        str.append(strContent[randomResFourth].toString().trim());
        return str.toString();
    }

    /**
     * 给定范围获得随机颜色
     *
     * @param rc
     * @param gc
     * @param bc
     * @return
     */
    public int getRandColor(int rc, int gc, int bc) {
        Random random = new Random();
        if (rc > 255)
            rc = 255;
        if (gc > 255)
            gc = 255;
        if (bc > 255)
            bc = 255;
        int r = rc + random.nextInt(rc);
        int g = gc + random.nextInt(gc);
        int b = bc + random.nextInt(bc);
        return Color.rgb(r, g, b);
    }
}
