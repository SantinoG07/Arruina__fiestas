package world;

public class Isoutils {
	public static int Tilewidth = 64;
	public static int Tileheight = 32;

	public static int ToscreenX(int row, int col) {
		return (col-row)*(Tilewidth/2);  //Aca lo que hacemos es como pasar al plano isometrico.
		//En 2d tenemos grillas normales, pero en isometrico tenemos grillas de cuadrados o rectangulos isometricos,
		/*Por ej:
		 
		 tileWidth = 64
		 tileHeight = 32
		 Si tenés una celda (col=2, fila=1):
		 x = (2 - 1) * (64 / 2) = 32
		 y = (2 + 1) * (32 / 2) = 48
		 */
	}

	public static int ToscreenY(int row, int col) {
		return (col-row)*(Tileheight/2);
	}

}
