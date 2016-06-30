package com.example.jack.ioc;


import android.app.Activity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class ViewInjectUtils {
    private static final String METHOD_SET_CONTENTVIEW = "setContentView";
    private static final String METHOD_FIND_VIEW_BY_ID = "findViewById";

    public static void inject(Activity activity) {
        injectContentView(activity);
        injectViews(activity);
    }


    /**
     * ע�����пؼ�
     *
     */
    private static void injectViews(Activity activity) {
        //ʹ���������������
        Class<? extends Activity> clazz = activity.getClass();
        Field[] fields = clazz.getDeclaredFields();
        // �������г�Ա����
        for (Field field : fields) {
            //�õ�ע��ʵ��
            ViewInject viewInjectAnnotation = field.getAnnotation(ViewInject.class);
            if (viewInjectAnnotation != null) {
                int viewId = viewInjectAnnotation.value();
                if (viewId != -1) {
                    // ��ʼ��View
                    try {
                        Method method = clazz.getMethod(METHOD_FIND_VIEW_BY_ID,int.class);
                        Object resView = method.invoke(activity, viewId);
                        field.setAccessible(true);
                        field.set(activity, resView);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }

        }

    }

    /**
     * ע�벼���ļ�
     *
     */
    private static void injectContentView(Activity activity) {
        Class<? extends Activity> clazz = activity.getClass();
        // ��ѯ�����Ƿ����ContentViewע��
        ContentView contentView = clazz.getAnnotation(ContentView.class);
        if (contentView != null)   // �ж��Ƿ����
        {
            int contentViewLayoutId = contentView.value();
            try {
                Method method = clazz.getMethod(METHOD_SET_CONTENTVIEW,int.class);
                method.setAccessible(true);
                method.invoke(activity, contentViewLayoutId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
