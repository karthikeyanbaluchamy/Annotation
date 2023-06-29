package reflectionUsecase;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

public class ObjectSizeCalculator {

    private static final long HEADER_SIZE = 12;
    private static final long REFERENCE_SIZE = 4;

    public long sizeOfObject(Object input) throws IllegalAccessException {
        /**
         * Complete your code here
         */
long size=0;
        for(Field field:input.getClass().getDeclaredFields()){
             field.setAccessible(true);
             if(field.getType().isPrimitive()){
                size+= sizeOfPrimitiveType(field.getType());
             }else {
                 size+=sizeOfString((String) field.get(input));
             }
        }

        return size;
    }


    /*************** Helper methods ********************************/
    private long sizeOfPrimitiveType(Class<?> primitiveType) {
        if (primitiveType.equals(int.class)) {
            return 4;
        } else if (primitiveType.equals(long.class)) {
            return 8;
        } else if (primitiveType.equals(float.class)) {
            return 4;
        } else if (primitiveType.equals(double.class)) {
            return 8;
        } else if (primitiveType.equals(byte.class)) {
            return 1;
        } else if (primitiveType.equals(short.class)) {
            return 2;
        }
        throw new IllegalArgumentException(String.format("Type: %s is not supported", primitiveType));
    }

    private long sizeOfString(String inputString) {
        int stringBytesSize = inputString.getBytes().length;
        return HEADER_SIZE + REFERENCE_SIZE + stringBytesSize;
    }


    public Object getArrayElement(Object array, int index) {
      int len=  (index<0)?Array.getLength(array)+index:index;
        return Array.get(array,len);
    }
}
