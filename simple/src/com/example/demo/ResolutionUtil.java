package com.example.demo;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;

/**
 * 屏幕适配
 * @author yuqihui
 *
 */
public class ResolutionUtil {
	
	//单例模式
	private static ResolutionUtil resolutionUtil = null;
	
	/**
	 * 设备屏幕的基准宽度,高度
	 */
	public static final int WINDOWS_STANDARD_SIZE_WIDTH = 720;
	public static final int WINDOWS_STANDARD_SIZE_HIGH = 1280;
	
	/**
	 * 标准密度
	 */
	private static final float DEFAULTDENSITY = 160;
	
	/**
	 * 标准缩放大小
	 */
	private static final float DEFAULTFONTDESITY = 1.0F;

	private static final String TAG = "ResolutionUtil";
	
	/**
	 * 屏幕密度
	 */
	private float density;
	
	/**
	 * 当前屏幕的字体缩放比例
	 */
	private float fontDesity;
	
	/**
	 * 当前屏幕和标准屏幕(720P)的比例
	 */
	private float scale;
	
	/**
	 * 当前设备宽度 
	 */
	private int deviceWidth;

	/**
	 * 当前设备高度
	 */
	private int deviceHeight;
	
	/**
	 * 横向屏幕比例
	 */
	private float scaleWidth ;
	
	/**
	 * 竖向屏幕比例
	 */
	private float scaleHeight;
	
	private  ResolutionUtil(Context ctx){
		scale = ctx.getResources().getDisplayMetrics().density;  
		
		DisplayMetrics dm = ctx.getResources().getDisplayMetrics();
		this.deviceWidth  = dm.widthPixels;
		this.deviceHeight = dm.heightPixels;
		density = dm.densityDpi;
		fontDesity = dm.scaledDensity;
		Log.e(TAG,"deviceWidth : "+deviceWidth+"     "+"scaleHeight : "+deviceHeight);
		if(deviceWidth > deviceHeight){
			scaleWidth = (float)deviceWidth / WINDOWS_STANDARD_SIZE_HIGH;
			scaleHeight = (float)deviceHeight / WINDOWS_STANDARD_SIZE_WIDTH;
			
		}else{
			scaleWidth = (float)deviceWidth / WINDOWS_STANDARD_SIZE_WIDTH;
			scaleHeight = (float)deviceHeight / WINDOWS_STANDARD_SIZE_HIGH;
			Log.e(TAG,scaleWidth+"   asd"+scaleHeight);
		}
		
	}
	
	public static ResolutionUtil getInstance(Context context){
		if(resolutionUtil==null){
			resolutionUtil = new ResolutionUtil(context);
		}
		return resolutionUtil;
	}
	
	
	public int getWidth(){
		return deviceWidth;
	}
	
	public int getHeight(){
		return deviceHeight;
	}
	
	public float getScaleWidth(){
		return scaleWidth;
	}
	
	public float getScaleHeight(){
		return scaleHeight;
	}
	
	/**
	 * 根据屏幕宽高比与密度获取适配当前屏幕px
	 * @param pxVlaue  px
	 * @param isWidth  是否为宽
	 * @return
	 */
	public int px2dp2pxWidth(float pxVlaue){
		float dp = pxVlaue / (density / DEFAULTDENSITY);
		return (int) (dp * (density / DEFAULTDENSITY) * scaleWidth);
	}
	
	
	/**
	 * 根据屏幕宽高比与密度获取适配当前屏幕px
	 * @param pxVlaue  px
	 * @param isWidth  是否为宽
	 * @return
	 */
	public int px2dp2pxHeight(float pxVlaue){
		float dp = pxVlaue / (density / DEFAULTDENSITY);
		return (int) (dp * (density / DEFAULTDENSITY) * scaleHeight);
	}
	
	/**
	 * 根据屏幕宽度与字体缩放比获取适配当前屏幕字体大小
	 * @param spVlaue px
	 * @return
	 */
	public int px2sp2px(float spVlaue){
		float dp = spVlaue / fontDesity;
		int px = (int) (dp * (fontDesity / DEFAULTFONTDESITY) / fontDesity * scaleWidth);
		return px;
	}
	
    /**
     * 将dip转为px值
     * @param context
     * @param dipValue
     * @return
     */
    public int dip2px(float dipValue){ 
		return (int)((int)(dipValue * scale) * density +0.5);
	} 

	public int px2dip(float pxValue) {
		return (int)((int)(pxValue * scale) / density + 0.5 );
	}
	
}
