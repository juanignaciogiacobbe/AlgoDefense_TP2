package clases;

import java.util.List;

public class Rocoso extends Terreno {


    public boolean terrenoConstruible() {
        return false;
    }

    @Override
    public boolean terrenoEsDesfilable() {
        return false;
    }
}
