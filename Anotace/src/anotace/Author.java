package anotace;

import java.lang.annotation.*;

@Documented
/*
* Retention Policy:
*   Source - doustupný pouze při psaní kodu, při kompilovani se ztratí
*   Class  - dostupný pouze při loadování těch tříd
*   RunTime- dostupný pouze za behu programu, po ukonceni se ztratí
*/
@Retention(RetentionPolicy.RUNTIME)
public @interface Author {
    String name();
    String date();
    String company() default "Bartad A.S";
    int vek();
}
