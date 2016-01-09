package com.example.gululu.icreatorsdksampler;

import android.content.Context;
import android.graphics.PixelFormat;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by gululu on 2015/10/22.
 */
public class GL2JNIView extends GLSurfaceView {
    private static String TAG = "GL2JNIView";
    private static final boolean DEBUG = false;

    private int mAccountid;
    private int mPasswordid;
    private int mUIIDid;

    private String mAccout;
    private String mPassword;
    private String mUIID;

    /**
     * 高宽的设置
     */

    /**
     * ScaleGestureDetector 手势缩放
     */
    private ScaleGestureDetector mScaleGestureDetector = null;
    private GestureDetector mGestureDetector = null;
    /**
     * fPinchValue是一次缩放的程度
     */
    private float fPinchValue = 1000.0f;
    private float fLastScale = 1.0f;
    private Context mContext;
    /**
     * 得到屏幕的缩放比例
     */
    private int mRatioId;
    private float mRatio;
    /**
     * 可以设置控件的大小
     */
   /* private int mWidthId;
    private int mHeightId;
    private String mHeight;
    private String mWidth;*/

    private iCreatorSDKLib mICreatorSDKLib;



    /**
     * 自定义一个context进行属性的初始化
     */
    public GL2JNIView(Context context,AttributeSet attrs){
        super(context,attrs);
        /**
         * 创建属性类
         */
        mContext=context;

        mAccountid=attrs.getAttributeResourceValue(null, "Account", 0);
        mPasswordid=attrs.getAttributeResourceValue(null, "Password", 0);
        mUIIDid=attrs.getAttributeResourceValue(null, "UIID", 0);
        //mRatioId=attrs.getAttributeResourceValue(null, "Ratio", 0);




        mAccout=context.getResources().getText(mAccountid).toString();
        mPassword=context.getResources().getText(mPasswordid).toString();
        mUIID=context.getResources().getText(mUIIDid).toString();
       // mRatio=Float.parseFloat(context.getResources().getText(mRatioId).toString());

       /*mWidth=attrs.getAttributeValue("http://schemas.android.com/apk/res/android", "layout_width");
        mHeight=attrs.getAttributeValue("http://schemas.android.com/apk/res/android", "layout_height");
        Log.i("Haku",mWidth+" "+mHeight);
        */
        /**
         * 初始化
         */
        //setRenderer(new testReander());
        setPreserveEGLContextOnPause(true);

        mICreatorSDKLib = new iCreatorSDKLib();

        GLinit(true, 24, 0,mICreatorSDKLib);
    }



  /*  public GL2JNIView(Context context, int nCWith, int nCHeight) {
        super(context);
        GLinit(false, 0, 0);

    }*/



    /**
     * 这个自定义函数，可以在Activity中的setContentView中进行调用
     *根据喜好设置各种属性
     * @param context 上下文
     */
    public GL2JNIView(Context context,String accout,String password,String uiid) {
        super(context);
        //EGL context上下文是否在View停止或resume的时候进行保留
        mContext=context;
        mAccout=accout;
        mPassword=password;
        mUIID=uiid;
        mICreatorSDKLib = new iCreatorSDKLib();
        setPreserveEGLContextOnPause(true);
        GLinit(true, 24, 0,mICreatorSDKLib);
    }

    /**
     * GLSurfaceView初始化setRender绘图之前，还可以初始化如下的属性进行
     * GLinit初始化做了4件事情：
     * 1、初始化了Context
     * 2、初始化了config Chooser
     * 3、初始化了renderer
     * 4、初始化了手势监听
     * @param translucent
     * @param depth
     * @param stencil
     */
    public void GLinit(boolean translucent, int depth, int stencil,iCreatorSDKLib iCreatorSDKLib) {

        /* By default, GLSurfaceView() creates a RGB_565 opaque surface.
         * If we want a translucent one, we should change the surface's
         * format here, using PixelFormat.TRANSLUCENT for GL Surfaces
         * is interpreted as any 32-bit surface with alpha by SurfaceFlinger.
         */
        if (translucent) {
            this.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        }

        /* Setup the context factory for 2.0 rendering.
         * See ContextFactory class definition below
         */
        setEGLContextFactory(new ContextFactory());

        /* We need to choose an EGLConfig that matches the format of
         * our surface exactly. This is going to be done in our
         * custom config chooser. See ConfigChooser class definition
         * below.
         */
        /**
         * int red, int green, int blue, int alpha, int depth, int stencil
         * setRender之前进行，进行的属性选择
         * set
         */
        setEGLConfigChooser(translucent ?
                new ConfigChooser(8, 8, 8, 8, depth, stencil) :
                new ConfigChooser(5, 6, 5, 0, depth, stencil));
        //设置一下背景透明的颜色
        getHolder().setFormat(PixelFormat.TRANSLUCENT);
        setZOrderOnTop(true);

        /* Set the renderer responsible for frame rendering */
        Log.i("Haku", mAccout + " " + mPassword + " " + mUIID);
        setRenderer(new Renderer(mAccout, mPassword, mUIID,iCreatorSDKLib));


        /*设置一个手势监听器*/
        mScaleGestureDetector = new ScaleGestureDetector(mContext,new ScaleGestureListener());
        mGestureDetector = new GestureDetector(mContext, new TapGestureListener());



    }


    /**
     *要自定义EGLContext中的createContext 与 destroy Context时使用
     */
    private static class ContextFactory implements EGLContextFactory {
        private static int EGL_CONTEXT_CLIENT_VERSION = 0x3098;

        public EGLContext createContext(EGL10 egl, EGLDisplay display, EGLConfig eglConfig) {
            Log.w(TAG, "creating OpenGL ES 2.0 context");
            checkEglError("Before eglCreateContext", egl);
            int[] attrib_list = {EGL_CONTEXT_CLIENT_VERSION, 2, EGL10.EGL_NONE};
            EGLContext context = egl.eglCreateContext(display, eglConfig, EGL10.EGL_NO_CONTEXT, attrib_list);
            checkEglError("After eglCreateContext", egl);
            return context;
        }

        public void destroyContext(EGL10 egl, EGLDisplay display, EGLContext context) {
            egl.eglDestroyContext(display, context);
        }
    }
    private static void checkEglError(String prompt, EGL10 egl) {
        int error;
        while ((error = egl.eglGetError()) != EGL10.EGL_SUCCESS) {
            Log.e(TAG, String.format("%s: EGL error: 0x%x", prompt, error));
        }
    }


    /**
     *configChooser里面自定义了属性的列表
     */
    private static class ConfigChooser implements EGLConfigChooser {

        public ConfigChooser(int r, int g, int b, int a, int depth, int stencil) {
            mRedSize = r;
            mGreenSize = g;
            mBlueSize = b;
            mAlphaSize = a;
            mDepthSize = depth;
            mStencilSize = stencil;
        }

        /* This EGL config specification is used to specify 2.0 rendering.
         * We use a minimum size of 4 bits for red/green/blue, but will
         * perform actual matching in chooseConfig() below.
         */
        private static int EGL_OPENGL_ES2_BIT = 4;
        private static int[] s_configAttribs2 =
                {
                        EGL10.EGL_RED_SIZE, 4,
                        EGL10.EGL_GREEN_SIZE, 4,
                        EGL10.EGL_BLUE_SIZE, 4,
                        EGL10.EGL_RENDERABLE_TYPE, EGL_OPENGL_ES2_BIT,
                        EGL10.EGL_NONE
                };

        public EGLConfig chooseConfig(EGL10 egl, EGLDisplay display) {

            /* Get the number of minimally matching EGL configurations
             */
            int[] num_config = new int[1];
            egl.eglChooseConfig(display, s_configAttribs2, null, 0, num_config);

            int numConfigs = num_config[0];

            if (numConfigs <= 0) {
                throw new IllegalArgumentException("No configs match configSpec");
            }

            /* Allocate then read the array of minimally matching EGL configs
             */
            EGLConfig[] configs = new EGLConfig[numConfigs];
            egl.eglChooseConfig(display, s_configAttribs2, configs, numConfigs, num_config);

            if (DEBUG) {
                printConfigs(egl, display, configs);
            }
            /* Now return the "best" one
             */
            return chooseConfig(egl, display, configs);
        }

        public EGLConfig chooseConfig(EGL10 egl, EGLDisplay display,
                                      EGLConfig[] configs) {
            for (EGLConfig config : configs) {
                int d = findConfigAttrib(egl, display, config,
                        EGL10.EGL_DEPTH_SIZE, 0);
                int s = findConfigAttrib(egl, display, config,
                        EGL10.EGL_STENCIL_SIZE, 0);

                // We need at least mDepthSize and mStencilSize bits
                if (d < mDepthSize || s < mStencilSize)
                    continue;

                // We want an *exact* match for red/green/blue/alpha
                int r = findConfigAttrib(egl, display, config,
                        EGL10.EGL_RED_SIZE, 0);
                int g = findConfigAttrib(egl, display, config,
                        EGL10.EGL_GREEN_SIZE, 0);
                int b = findConfigAttrib(egl, display, config,
                        EGL10.EGL_BLUE_SIZE, 0);
                int a = findConfigAttrib(egl, display, config,
                        EGL10.EGL_ALPHA_SIZE, 0);

                if (r == mRedSize && g == mGreenSize && b == mBlueSize && a == mAlphaSize)
                    return config;
            }
            return null;
        }

        private int findConfigAttrib(EGL10 egl, EGLDisplay display,
                                     EGLConfig config, int attribute, int defaultValue) {

            if (egl.eglGetConfigAttrib(display, config, attribute, mValue)) {
                return mValue[0];
            }
            return defaultValue;
        }

        private void printConfigs(EGL10 egl, EGLDisplay display,
                                  EGLConfig[] configs) {
            int numConfigs = configs.length;
            Log.w(TAG, String.format("%d configurations", numConfigs));
            for (int i = 0; i < numConfigs; i++) {
                Log.w(TAG, String.format("Configuration %d:\n", i));
                printConfig(egl, display, configs[i]);
            }
        }

        private void printConfig(EGL10 egl, EGLDisplay display,
                                 EGLConfig config) {
            int[] attributes = {
                    EGL10.EGL_BUFFER_SIZE,
                    EGL10.EGL_ALPHA_SIZE,
                    EGL10.EGL_BLUE_SIZE,
                    EGL10.EGL_GREEN_SIZE,
                    EGL10.EGL_RED_SIZE,
                    EGL10.EGL_DEPTH_SIZE,
                    EGL10.EGL_STENCIL_SIZE,
                    EGL10.EGL_CONFIG_CAVEAT,
                    EGL10.EGL_CONFIG_ID,
                    EGL10.EGL_LEVEL,
                    EGL10.EGL_MAX_PBUFFER_HEIGHT,
                    EGL10.EGL_MAX_PBUFFER_PIXELS,
                    EGL10.EGL_MAX_PBUFFER_WIDTH,
                    EGL10.EGL_NATIVE_RENDERABLE,
                    EGL10.EGL_NATIVE_VISUAL_ID,
                    EGL10.EGL_NATIVE_VISUAL_TYPE,
                    0x3030, // EGL10.EGL_PRESERVED_RESOURCES,
                    EGL10.EGL_SAMPLES,
                    EGL10.EGL_SAMPLE_BUFFERS,
                    EGL10.EGL_SURFACE_TYPE,
                    EGL10.EGL_TRANSPARENT_TYPE,
                    EGL10.EGL_TRANSPARENT_RED_VALUE,
                    EGL10.EGL_TRANSPARENT_GREEN_VALUE,
                    EGL10.EGL_TRANSPARENT_BLUE_VALUE,
                    0x3039, // EGL10.EGL_BIND_TO_TEXTURE_RGB,
                    0x303A, // EGL10.EGL_BIND_TO_TEXTURE_RGBA,
                    0x303B, // EGL10.EGL_MIN_SWAP_INTERVAL,
                    0x303C, // EGL10.EGL_MAX_SWAP_INTERVAL,
                    EGL10.EGL_LUMINANCE_SIZE,
                    EGL10.EGL_ALPHA_MASK_SIZE,
                    EGL10.EGL_COLOR_BUFFER_TYPE,
                    EGL10.EGL_RENDERABLE_TYPE,
                    0x3042 // EGL10.EGL_CONFORMANT
            };
            String[] names = {
                    "EGL_BUFFER_SIZE",
                    "EGL_ALPHA_SIZE",
                    "EGL_BLUE_SIZE",
                    "EGL_GREEN_SIZE",
                    "EGL_RED_SIZE",
                    "EGL_DEPTH_SIZE",
                    "EGL_STENCIL_SIZE",
                    "EGL_CONFIG_CAVEAT",
                    "EGL_CONFIG_ID",
                    "EGL_LEVEL",
                    "EGL_MAX_PBUFFER_HEIGHT",
                    "EGL_MAX_PBUFFER_PIXELS",
                    "EGL_MAX_PBUFFER_WIDTH",
                    "EGL_NATIVE_RENDERABLE",
                    "EGL_NATIVE_VISUAL_ID",
                    "EGL_NATIVE_VISUAL_TYPE",
                    "EGL_PRESERVED_RESOURCES",
                    "EGL_SAMPLES",
                    "EGL_SAMPLE_BUFFERS",
                    "EGL_SURFACE_TYPE",
                    "EGL_TRANSPARENT_TYPE",
                    "EGL_TRANSPARENT_RED_VALUE",
                    "EGL_TRANSPARENT_GREEN_VALUE",
                    "EGL_TRANSPARENT_BLUE_VALUE",
                    "EGL_BIND_TO_TEXTURE_RGB",
                    "EGL_BIND_TO_TEXTURE_RGBA",
                    "EGL_MIN_SWAP_INTERVAL",
                    "EGL_MAX_SWAP_INTERVAL",
                    "EGL_LUMINANCE_SIZE",
                    "EGL_ALPHA_MASK_SIZE",
                    "EGL_COLOR_BUFFER_TYPE",
                    "EGL_RENDERABLE_TYPE",
                    "EGL_CONFORMANT"
            };
            int[] value = new int[1];
            for (int i = 0; i < attributes.length; i++) {
                int attribute = attributes[i];
                String name = names[i];
                if (egl.eglGetConfigAttrib(display, config, attribute, value)) {
                    Log.w(TAG, String.format("  %s: %d\n", name, value[0]));
                } else {
                    // Log.w(TAG, String.format("  %s: failed\n", name));
                    while (egl.eglGetError() != EGL10.EGL_SUCCESS) ;
                }
            }
        }

        // Subclasses can adjust these values:
        protected int mRedSize;
        protected int mGreenSize;
        protected int mBlueSize;
        protected int mAlphaSize;
        protected int mDepthSize;
        protected int mStencilSize;
        private int[] mValue = new int[1];
    }

    private static class testReander implements GLSurfaceView.Renderer{

        @Override
        public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
            // 启用阴影平滑
            gl10.glShadeModel(GL10.GL_SMOOTH);

            // 黑色背景
            gl10.glClearColor(0, 0, 0, 0);

            // 设置深度缓存
            gl10.glClearDepthf(1.0f);
            // 启用深度测试
            gl10.glEnable(GL10.GL_DEPTH_TEST);
            // 所作深度测试的类型
            gl10.glDepthFunc(GL10.GL_LEQUAL);

            // 告诉系统对透视进行修正
            gl10.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
        }

        @Override
        public void onSurfaceChanged(GL10 gl10, int width, int height) {
            gl10.glViewport(0, 0, width, height);
        }

        @Override
        public void onDrawFrame(GL10 gl10) {
            // 清除屏幕和深度缓存
            gl10.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
            // 重置当前的模型观察矩阵
            gl10.glLoadIdentity();

        }
    }


    /**
     * 重点的Renderer对象的实现
     */
    private static class Renderer implements GLSurfaceView.Renderer {
        private int nWidth;
        private int nHeight;
        private String nAccount;
        private String nPassword;
        private String nUIID;
        private iCreatorSDKLib niCreatorSDKLib;


        public Renderer(String account,String password,String uiid,iCreatorSDKLib iCreatorSDKLib) {
            nAccount=account;
            nPassword=password;
            nUIID=uiid;
            niCreatorSDKLib=iCreatorSDKLib;


            //nWidth=width;
            //nHeight=height;
        }

        public void onDrawFrame(GL10 gl) {
            niCreatorSDKLib.update();
        }

        /**
         * 为了得到width和height
         * @param gl
         * @param width
         * @param height
         */
        public void onSurfaceChanged(GL10 gl, int width, int height) {

           if( nWidth != width && nHeight != height )
            {
                nWidth = width;
                nHeight = height;

                niCreatorSDKLib.init(0, 0, nWidth, nHeight);

                Log.i("track", "init 执行");
                /**
                 * 一个是冰箱，一个是tv
                 */

                try {
                    niCreatorSDKLib.setServerInfo(nAccount, nPassword, nUIID);
                }catch (Exception e) {
                    Log.i("track", e + "");
                }
            }
        }

        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            //gl.glClearColor(0.8f, 0.8f, 0.8f, 1.0f);
            String path = AndroidUntils.getSDPath() + "iCreatorLog.txt";
            niCreatorSDKLib.setLogPath(path);
            //iCreatorSDKLib.init(0, 0, nWidth, nHeight);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent e)
    {
        //1,2,3对应so类库中不同的动作代码
        //isInProgress()方法表示的是，缩放是否在进行中

        if ( e.getAction() == MotionEvent.ACTION_DOWN && !mScaleGestureDetector.isInProgress() )
        {
            mICreatorSDKLib.sendEvent(0, 1, (int) e.getX(), (int) e.getY());

        }
        else if( e.getAction() == MotionEvent.ACTION_MOVE && !mScaleGestureDetector.isInProgress() )
        {
            mICreatorSDKLib.sendEvent(0, 2, (int) e.getX(), (int) e.getY());

        }
        else if( e.getAction() == MotionEvent.ACTION_UP && !mScaleGestureDetector.isInProgress() )
        {
            mICreatorSDKLib.sendEvent(0, 3, (int) e.getX(), (int) e.getY());

        }
        //剩下的动作返回给ScaleGestureDetector来处理
        mGestureDetector.onTouchEvent(e);
        return  mScaleGestureDetector.onTouchEvent(e);
    }

    public class TapGestureListener extends GestureDetector.SimpleOnGestureListener {
        public TapGestureListener() {
            super();
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            // TODO Auto-generated method stub
            mICreatorSDKLib.resetCameraAndAllMesh();
            return super.onDoubleTap(e);
        }
    }

    public class ScaleGestureListener implements ScaleGestureDetector.OnScaleGestureListener
    {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            /**
             * getScaleFactor()=getCurrentSpan()/getPreviousSpan()
             * eg. 如果是放大的过程，那么>1 如果是缩小的过程 <1
             */
            float detectorScale = detector.getScaleFactor();
            float scale = detectorScale - fLastScale;
            /**
             * 举个栗子，两次值得偏差*1000（fpinchValue），这个也可以理解成缩进的速度。
             * sendEvent发送过去做滚轮处理，X为负数的时候缩小，为整数的时候放大
             */
            float value = fPinchValue * scale;
            mICreatorSDKLib.sendEvent(0, 5, (int) value, 0);
            fLastScale = detectorScale;
            return false;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            //开始的时候先清除一下当前的时间队列
            mICreatorSDKLib.clearEvent();
            return true;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
            fLastScale = 1.0f;
        }
    }


    public float getRatio() {
        return mRatio;
    }

    public void setRatio(float ratio) {
        mRatio = ratio;
        invalidate();
        requestLayout();
    }

    /**
     * 在此可以改变大小
     * 重写一些measure面板，彻底控件化
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
   /* @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        float Ratio=getRatio();
        int Width= (int) (MeasureSpec.getSize(widthMeasureSpec)*Ratio);
        int Height= (int) (MeasureSpec.getSize(heightMeasureSpec)*Ratio);
        setMeasuredDimension(Width,Height);

    }*/

    /**
     * 先写一个接口，在完成点击事件
     */
    public interface GLClickListener{
        void GLClickListener();
    }
}
