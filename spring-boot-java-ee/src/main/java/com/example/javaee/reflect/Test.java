package com.example.javaee.reflect;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * 反射是框架设计的灵魂
 * 反射就是把java类中各种成分映射成一个个java对象。
 * 如一个类有：成员变量、方法、构造方法、包等等信息，利用反射技术可以对一个类进行解剖，把个个组成部分映射成一个个对象。
 *
 */
public class Test {
    public static void main(String[] args) throws Exception {

//        Test.getClasses();
//        Test.getConstructors();
//        Test.getFields();
//        Test.getMethods();

//        Test.reflectMainMethod();
//        Test.reflectPropertiesFile();
//        Test.reflectTCheck();
        reflectEnum();
    }

    /**
     * Class获取的三种方式
     * @throws Exception
     */
    public static void getClasses() throws Exception {
        /**
         *
         * 三种方式常用第三种:
         * 第一种对象都有了还要反射干什么。
         * 第二种需要导入类的包，依赖太强，不导包就抛编译错误。
         * 一般都第三种，一个字符串可以传入也可写在配置文件中等多种方法。
         */
        // 1、通过 Object.getClass()
        Car car = new Car();
        Class clazz1 = car.getClass();
        Class cls3 = new int[]{}.getClass();
        Class cls4 = new Car[][]{}.getClass();
        // 2、通过 .class 标识-----任何数据类型（包括基本数据类型)
        Class clazz2 = Car.class;
        Class cls1 = int.class;
        Class cls2 = String.class;
        // 3、通过Class类的静态方法：Class.forName（String  className）
        Class clazz3 = Class.forName("com.example.javaee.reflect.Car");
        // 判断三种方式是否获取的是同一个Class对象
        System.out.println(clazz1 == clazz2); //true
        System.out.println(clazz2 == clazz3); //true

        // getName
        System.out.println(cls1.getName());
        System.out.println(cls2.getName());
        System.out.println(cls3.getName());
        System.out.println(cls4.getName());
        // getSimpleName
        System.out.println(cls1.getSimpleName());
        System.out.println(cls2.getSimpleName());
        System.out.println(cls3.getSimpleName());
        System.out.println(cls4.getSimpleName());
        // getCanonicalName
        System.out.println(cls4.getCanonicalName());

        // 获取修饰符
        int modifier = clazz1.getModifiers();
        System.out.println("modifier values:" + modifier);
        System.out.println("modifier:" + Modifier.toString(modifier));
    }

    /**
     *  通过反射获取构造方法并使用
     */
    public static void getConstructors() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class clazz = Class.forName("com.example.javaee.reflect.Son");
        // 获取所有的公有构造方法
        Constructor[] consArray = clazz.getConstructors();
        for (Constructor constructor : consArray) {
            System.out.println(constructor);
        }
        System.out.println("==========================================");
        // 获取所有的构造方法（私有、受保护、默认、公有）
        consArray = clazz.getDeclaredConstructors();
        for (Constructor constructor : consArray) {
            System.out.println(constructor);
        }
        System.out.println("==========================================");
        // 获取单个公有无参的构造方法，括号中是一个参数类型 null可以不写
        Constructor constructor = clazz.getConstructor(null);
        System.out.println(constructor);
        System.out.println("==========================================");
        // 获取某个构造方法（私有、受保护、默认、公有）
        Constructor constructor2 = clazz.getDeclaredConstructor(Date.class);
        System.out.println(constructor2);
        System.out.println("==========================================");
        // 调用构造方法
        Object object = constructor.newInstance();
        System.out.println(object);
        Son son = (Son)object;
        System.out.println("==========================================");
        // 暴力访问(忽略掉访问修饰符)
        constructor2.setAccessible(true);
        Object obj = constructor2.newInstance(new Date());
        System.out.println(obj);
    }

    /**
     * 获取属性
     * @throws ClassNotFoundException
     * @throws NoSuchFieldException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    public static void getFields() throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class clazz = Class.forName("com.example.javaee.reflect.Son");
        // 获取所有公有的字段,包括从父类继承下来的属性
        Field[] fieldsPublic = clazz.getFields();
        for (Field field : fieldsPublic) {
            System.out.println(field);
        }
        System.out.println("==========================================");
        // 获取所有的字段，不包括父类的。
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println("Field:" + field);
            // Field 类型的获取:两者返回的类型不一样，getGenericType() 方法能够获取到泛型类型
            Class type= field.getType();
            Type genericType = field.getGenericType();
            System.out.println("Field type:" + type);
            System.out.println("Field generic type:" + genericType);
            System.out.println("---------------------------------");

            // 反射中的数组
            // 判断这个类型是不是数组类型
            if (type.isArray()) {
                System.out.println("Type is "+ type.getName());
                System.out.println("Type is "+ type.getComponentType());
            }
        }
        System.out.println("==========================================");
        // 获取某个公有字段并调用
        Field name = clazz.getField("name");
        Object obj = clazz.getConstructor().newInstance();
        name.set(obj, "zhangsan");
        Son son = (Son)obj;
        son.age = 18;
        System.out.println(son.name);
        System.out.println("==========================================");
        // // 获取某个字段并调用
        Field birthday = clazz.getDeclaredField("birthday");
        System.out.println(birthday);
        birthday.setAccessible(true);
        birthday.set(obj, new Date());
        System.out.println(son);
        System.out.println("==========================================");
        Field field = clazz.getDeclaredField("age");
        int age = field.getInt(obj);
        System.out.println("age:" + age);

        // 获取参数符------ int getModifiers()

        System.out.println("==========================================");
        // 动态创建数组
        Field f = clazz.getDeclaredField("array");
        f.setAccessible(true);
        // 创建数组
        Object objectArray = Array.newInstance(int.class, 3);
        Array.set(objectArray, 0, 10);
        Array.set(objectArray, 1, 11);
        Array.set(objectArray, 2, 12);
        f.set(son, objectArray);
        int[] array = son.getArray();
        for ( int i = 0;i < array.length;i++) {
            System.out.println("array index "+ i + " value:"+array[i]);
        }

    }

    /**
     * 获取方法
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    public static void getMethods() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class clazz = Son.class;
        // 获取所有公有方法,包含了父类的方法也包含Object类
        Method[] methodsPublic = clazz.getMethods();
        for (Method method : methodsPublic) {
            System.out.println(method);
        }
        System.out.println("==========================================");
        // 获取所有方法，不包括继承的类
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
        System.out.println("==========================================");
        // 获取单个公有方法并调用
        Method method = clazz.getMethod("method1", String.class);
        Object obj = clazz.getConstructor(String.class).newInstance("wangwu");
        method.invoke(obj, "lisi");
        // 可获取单个父类方法
        Method superMethod1 = clazz.getMethod("superMethod1", null);
        System.out.println(superMethod1);
        System.out.println("==========================================");
        // 获取某个私有方法并调用
        Method methodPrivate = clazz.getDeclaredMethod("method4", int.class);
        methodPrivate.setAccessible(true);
        Object result = methodPrivate.invoke(obj, 8888);
        System.out.println("返回值：" + result);

        // 获取方法参数------- Parameter[] getParameters()
            // 获取参数名字  String getName()
            // 获取参数类型  getType()
            // 获取参数的修饰符 int getModifiers()
            // 获取所有的参数类型 Class<?>[] getParameterTypes()
            // 获取所有的参数类型，包括泛型 Type[] getGenericParameterTypes()
        // 获取返回值类型 Class<?> getReturnType()
        // 获取返回值类型包括泛型 Type getGenericReturnType()
        // 获取异常类型 Class<?>[] getExceptionTypes()
                       //Type[] getGenericExceptionTypes()
    }

    /**
     * 反射中的枚举 Enum
     */
    public static void reflectEnum() {
        try {
            Class clazz = Class.forName("com.example.javaee.reflect.Status");
            // 判定Class对象是不是枚举类型
            if (clazz.isEnum()) {
                System.out.println(clazz.getName()+" is Enum");
                // 获取所有的枚举常量
                Object[] objects = clazz.getEnumConstants();
                System.out.println("所有枚举常量：" + Arrays.asList(objects));
                // 获取枚举中所有的Field
                Field[] fields = clazz.getDeclaredFields();
                for ( Field f : fields ) {
                    // 判断一个Field是不是枚举常量
                    if (f.isEnumConstant()) {
                        System.out.println(f.getName()+" is EnumConstant");
                    } else {
                        System.out.println(f.getName()+" is not EnumConstant");
                    }
                }
            }
            Class cls = Son.class;
            Son son = (Son)cls.getConstructor().newInstance();
            Field field = cls.getDeclaredField("status");
            field.setAccessible(true);
            Status status = (Status)field.get(son);
            System.out.println("Status current is " + status);
            field.set(son, Status.STOPED);
            System.out.println("Status current is " + son.getStatus());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }


    /**
     * 应用
     * 反射main方法
     */
    public static void reflectMainMethod() {
        try {
            Class clazz = Class.forName("com.example.javaee.reflect.Son");
            Method method = clazz.getDeclaredMethod("main", String[].class);
            method.setAccessible(true);
            // 第一个参数，对象类型，因为方法是static静态的，所以为null可以，
            // 第二个参数是String数组，这里要注意在jdk1.4时是数组，jdk1.5之后是可变参数
            // 这里拆的时候将new String[]{"a","b","c"} 拆成3个对象。。。所以需要将它强转。
            method.invoke(null, (Object)new String[]{"a", "b", "c"});
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过反射运行配置文件内容
     */
    public static void reflectPropertiesFile() {
        try {
            Class clazz = Class.forName(getValue("className"));
            Method method = clazz.getDeclaredMethod(getValue("methodName"));
            method.invoke(clazz.getConstructor().newInstance());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    private static String getValue(String key) {
        Properties properties = new Properties();
        try {
            FileReader fileReader = new FileReader("D:\\study_tgl\\ideaproject\\spring-boot-demo\\spring-boot-java-ee\\src\\main\\java\\com\\example\\javaee\\reflect\\resource\\class.properties");
            properties.load(fileReader);
            return properties.getProperty(key);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *  通过反射越过泛型检查
     */
    public static void reflectTCheck() {
        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");

        try {
            Class clazz = list.getClass();
            Method method = clazz.getMethod("add", Object.class);
            method.invoke(list, 100);
            for (Object object : list) {
                System.out.println(object);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
