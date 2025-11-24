package cn.hungslabsec.classloader;

public class BaseTestClassLoad {

    public static void main(String[] args) {
        getClassLoad();
    }

    public static void getClassLoad() {
        /**
         * |--sun.misc.Launcher$AppClassLoader@18b4aac2
         *                 |--sun.misc.Launcher$ExtClassLoader@7cc355be
         *                 |--null
         */


        // 获取类加载器
        ClassLoader classLoader = BaseTestClassLoad.class.getClassLoader();
        // ClassLoader classLoader = String.class.getClassLoader();
        // null

        StringBuilder split = new StringBuilder("|--");
        boolean needContinue = true;
        while (needContinue){
            System.out.println(split.toString() + classLoader);
            if(classLoader == null){
                needContinue = false;
            }else{
                classLoader = classLoader.getParent();
                split.insert(0, "\t");
            }
        }
    }

    public static void classLoadTest() throws ClassNotFoundException {
        ClassLoader loader = BaseTestClassLoad.class.getClassLoader();
        System.out.println(loader);

        //使用ClassLoader.loadclass()来加载类，不会执行初始化块
        loader.loadClass("cn.hungslabsec.classloader.CalcTest");

        //使用Class.forName()来加载类，默认会执行初始化块
        Class.forName("cn.hungslabsec.classloader.CalcTest");

        // 使用Class.forName来加载类并指定ClassLoader，初始化时不执行静态块
        Class.forName("cn.hungslabsec.classloader.CalcTest", false, loader);


    }

//    class Test2 {
//        static {
//            System.out.println("static block has been loaded");
//        }
//    }
}
