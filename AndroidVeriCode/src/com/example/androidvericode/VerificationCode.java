package com.example.androidvericode;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * 描述：验证码生成
 */
public class VerificationCode {
	//因1和l、0和o 不好区分,所以没有1和0
	private static final char[] CHARS = {  
        '2', '3', '4', '5', '6', '7', '8', '9',  
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'l', 'm',   
        'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',  
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',   
        'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'  
    };
	/**
	 * 单例持有器
	 */
	private static final class InstanceHolder {
		private static final VerificationCode INSTANCE = new VerificationCode();
	}

	/**
	 * 获得单例
	 * @return
	 */
	public static VerificationCode getInstance() {
		return InstanceHolder.INSTANCE;
	}
	
	//default settings 
	//验证码显示几个数字
    private static final int DEFAULT_CODE_LENGTH = 4;  
    //字体大小
    private static final int DEFAULT_FONT_SIZE = 50;  
    //线的数量为2条
    //private static final int DEFAULT_LINE_NUMBER = 2;
    
    private static final int BASE_PADDING_LEFT = 30, RANGE_PADDING_LEFT = 20, BASE_PADDING_TOP = 60, RANGE_PADDING_TOP = 30;  
   
    private static final int DEFAULT_WIDTH = 200, DEFAULT_HEIGHT = 100;  
      
    //settings decided by the layout xml  
    //canvas width and height  
    //画布的宽度和高度
    private int width = DEFAULT_WIDTH, height = DEFAULT_HEIGHT;   
      
    //random word space and pading_top  
    private int base_padding_left = BASE_PADDING_LEFT, range_padding_left = RANGE_PADDING_LEFT,   
            base_padding_top = BASE_PADDING_TOP, range_padding_top = RANGE_PADDING_TOP;  
      
    //number of chars, lines; font size  
    private int codeLength = DEFAULT_CODE_LENGTH,font_size = DEFAULT_FONT_SIZE;//line_number = DEFAULT_LINE_NUMBER, ;  
      
    //variables  
    private String code;  
    
    private int padding_left, padding_top;  
    
    private Random random = new Random();  
    //验证码图片  
    public Bitmap createBitmap() {  
        padding_left = 0;  
          
        Bitmap bp = Bitmap.createBitmap(width, height, Config.ARGB_8888);   
        Canvas c = new Canvas(bp);  
  
        code = createCode();  
          
        c.drawColor(Color.WHITE);  
        Paint paint = new Paint();  
        paint.setTextSize(font_size);  
          
        for (int i = 0; i < code.length(); i++) {  
            randomTextStyle(paint);  
            randomPadding();  
            c.drawText(code.charAt(i) + "", padding_left, padding_top, paint);  
        }  
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        c.drawRect(new Rect(0,0,199,99), paint);
        /*for (int i = 0; i < line_number; i++) {  
            drawLine(c, paint);  
        }*/  
          
        c.save(Canvas.ALL_SAVE_FLAG );//保存    
        c.restore();//  
        return bp;  
    }  
      
    public String getCode() {  
        return code;  
    }  
      
    //验证码  
    private String createCode() {  
        StringBuilder buffer = new StringBuilder();  
        for (int i = 0; i < codeLength; i++) {  
            buffer.append(CHARS[random.nextInt(CHARS.length)]);  
        }  
        return buffer.toString();  
    }  
      
    private void drawLine(Canvas canvas, Paint paint) {  
        int color = randomColor();  
        int startX = random.nextInt(width);  
        int startY = random.nextInt(height);  
        int stopX = random.nextInt(width);  
        int stopY = random.nextInt(height);  
        paint.setStrokeWidth(1);  
        paint.setColor(color);  
        canvas.drawLine(startX, startY, stopX, stopY, paint);  
    }  
      
   
    /**
     * 随机颜色
     * @return
     */
    private int randomColor() {  
        return randomColor(1);  
    }  
  
    //随机颜色
    private int randomColor(int rate) {  
        int red = random.nextInt(256) / rate;  
        int green = random.nextInt(256) / rate;  
        int blue = random.nextInt(256) / rate;  
        return Color.rgb(red, green, blue);  
    }  
    /**
     * 设置字体样式  
     * @param paint
     */
    private void randomTextStyle(Paint paint) {  
        int color = randomColor();  
        paint.setColor(color);  
        paint.setFakeBoldText(random.nextBoolean());  //true为粗体，false为非粗体  
        float skewX = random.nextInt(11) / 10;  
        skewX = random.nextBoolean() ? skewX : -skewX;  
        //设置斜体文字
        paint.setTextSkewX(skewX); //float类型参数，负数表示右斜，整数左斜  
//      paint.setUnderlineText(true); //true为下划线，false为非下划线  
//      paint.setStrikeThruText(true); //true为删除线，false为非删除线  
    }  
     
    /**
     * 设置边距
     */
    private void randomPadding() {  
        //padding_left += base_padding_left + random.nextInt(range_padding_left);
        padding_left += base_padding_left;
        System.out.println("padding_left="+padding_left);
        //padding_top = base_padding_top + random.nextInt(range_padding_top); 
        padding_top = base_padding_top;
        System.out.println("padding_top="+padding_top);
    }  
}
