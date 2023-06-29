package reflectionUsecase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClassAnalyzer {
    private static final List<String> JDK_PACKAGE_PREFIXES =
            Arrays.asList("com.sun.", "java", "javax", "jdk", "org.w3c", "org.xml");

    public static PopupTypeInfo createPopupTypeInfoFromClass(Class<?> inputClass) {
        PopupTypeInfo popupTypeInfo = new PopupTypeInfo();

        /** Complete the Code **/



            popupTypeInfo.setPrimitive(inputClass.isPrimitive())
                    .setInterface(inputClass.isInterface())
                            .setEnum(inputClass.isEnum())
                            .setName(inputClass.getSimpleName())
                            .setJdk(isJdkClass(inputClass))
                            .addAllInheritedClassNames(getAllInheritedClassNames(inputClass));





        return popupTypeInfo;
    }

    /*********** Helper Methods ***************/

    public static boolean isJdkClass(Class<?> inputClass) {
        /** Complete the code
         Hint: What does inputClass.getPackage() return when the class is a primitive type?
         **/
        System.out.println(inputClass.getPackage());
        String pac=inputClass.getPackage().getName();
        for (String name:JDK_PACKAGE_PREFIXES ){

            if(pac.startsWith(name))
                return true;

        }
        return false;
    }

    public static String[] getAllInheritedClassNames(Class<?> inputClass) {
        /** Complete the code
         Hints: What does inputClass.getSuperclass() return when the inputClass doesn't inherit from any class?
         What does inputClass.getSuperclass() return when the inputClass is a primitve type?

         **/

        String[] inheritedClasses;
        if (inputClass.isInterface()) {
            inheritedClasses = Arrays.stream(inputClass.getInterfaces())
                    .map(e -> e.getSimpleName())
                    .toArray(String[]::new);
        } else {
            Class<?> inheritedClass = inputClass.getSuperclass();
            inheritedClasses = inheritedClass != null ?
                    new String[]{inputClass.getSuperclass().getSimpleName()}
                    : null;
        }
        return inheritedClasses;
    }
}
