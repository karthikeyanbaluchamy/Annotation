package reflectionUsecase;


import navi.program.car;

public class CheckReflection {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class<?> tClass=  Class.forName("navi.program.car");
        car obj=(car)tClass.newInstance();

     obj.drive();
    }
}
