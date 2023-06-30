module edu.fiuba.algo3 {
	requires javafx.controls;
	requires json.simple;
    requires javafx.media;
    requires java.desktop;
    exports edu.fiuba.algo3;
	exports edu.fiuba.algo3.modelo;
	exports edu.fiuba.algo3.modelo.enemigos;
	exports edu.fiuba.algo3.modelo.parcelas;
	opens edu.fiuba.algo3;
	exports edu.fiuba.algo3.modelo.defensas;
}
