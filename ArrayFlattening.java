package reflectionUsecase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ArrayFlattening {

    public <T> T concat(Class<?> type, Object... arguments) {

        if (arguments.length == 0) {
            return null;
        }

        List<Object> ll= new ArrayList<>();

        for(Object a:arguments){

            if(a.getClass().isArray()){
                int len=Array.getLength(a);

                for(int i=0;i<len;i++){
                    ll.add(Array.get(a,i));
                }
            }else {
                ll.add(a);
            }
        }

       Object flat= Array.newInstance(type,ll.size());

        for(int i=0;i<ll.size();i++){
            Array.set(flat,i,ll.get(i));
        }




        return (T) flat;
    }
}
