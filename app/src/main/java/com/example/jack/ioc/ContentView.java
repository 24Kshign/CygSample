package com.example.jack.ioc;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


//��Ա�����������Ƶġ��Ϸ������Ͱ���ԭʼ���ͼ�String��Class��Annotation��Enumeration

@Target(ElementType.TYPE)
//@Target({ElementType.METHOD,ElementType.TYPE})    //ElementType.METHOD��ʾ�������Ƿ�����ElementType.TYPE��ʾ������������߽ӿ�
@Retention(RetentionPolicy.RUNTIME)             //ElementType.TYPE��ʾ�����������ж�����
public @interface ContentView {

    //���ע��ֻ��һ����Ա�����Ա������ȡ��Ϊvalue()����ʹ��ʱ���Ժ��Գ�Ա���͸�ֵ��(=)
    int value();
//    int age() default 18     //ע�������û�г�Ա��û�г�Ա��ע���Ϊ��ʶע��
}
