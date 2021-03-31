package anotace;

import java.lang.annotation.Repeatable;

@Repeatable(Vyrobci.class)
public @interface Vyrobce {
    String nazev();
}

