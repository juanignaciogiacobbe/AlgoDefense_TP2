package clases;

import java.util.List;

public class Tierra extends Terreno {


    public boolean terrenoConstruible() {
        return true;
    }

    @Override
    public boolean terrenoEsDesfilable() {
        return false;
    }
}
