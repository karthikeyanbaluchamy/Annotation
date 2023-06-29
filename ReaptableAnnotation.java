package reflectionUsecase;

import java.lang.annotation.*;

public class ReaptableAnnotation {



    @Retention(RetentionPolicy.RUNTIME)
    public @interface  Role{

    }

    @Retention(RetentionPolicy.RUNTIME)
    public  @interface  OperationType{

    }

    @Target(ElementType.TYPE)
    @Repeatable(PermissionsContainer.class)
    public @interface Permissions {
        Role role();
        OperationType[] allowed();

    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public  @interface PermissionsContainer{
        Permissions [] value();
    }
}
