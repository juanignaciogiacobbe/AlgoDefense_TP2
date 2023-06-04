package clases;

interface ConvertidorMapa {
    Mapa cargarMapa();
    Parcela definirParcela(String terreno, int fila, int columna, Mapa mapa);
}