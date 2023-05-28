package clases;

import java.util.List;

public class Pasarela extends Terreno {


    @Override
    public boolean terrenoConstruible() {
        return false;
    }

    @Override
    public boolean terrenoEsDesfilable() {
        return true;
    }


}
