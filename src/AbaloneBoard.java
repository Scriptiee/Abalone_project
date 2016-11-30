import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

public class AbaloneBoard extends GridPane {
	

	
	// private vars for the class
	Cell render = new Cell();
	
	// array of cells 
	private Cell[][] boardCells = new Cell[18][11];
	
	// Board should create master board & add all Cell and Piece to it TODO
	public AbaloneBoard(){
		// position the grid in the center
		setAlignment(Pos.CENTER);
		// set some padding for pretty
		setPadding(new Insets(35,0,0,50));
		// Hgap and Vgap to make it allign with the HEX (might be a better way of doing that)
		setHgap(-15);
		setVgap(15);
		
		// Rendering loop
		for (int i = 0; i < boardCells.length; i++){
			for (int j = 0; j < boardCells[i].length; j++){
				// first and last row
				if(((j==1 || j==9) && i >4 && i <= 13 && i%2!=0) ||
						// second and second last row
						((j==2 || j==8) && i >3 && i < 15 && i%2==0) ||
						// third and seventh row
						((j==3 || j==7) && i >=3 && i <=15 && i%2!=0) ||
						// fourth and sixth row
						((j==4 || j==6) && i >=2 && i<=16 && i%2==0) ||
						// center row
						(j==5 && i>=1 && i<=17 && i%2!=0)) {
					boardCells[i][j] = new Cell();
					add(boardCells[i][j],i,j);		
					}
				}
			}
		}
	}
	
/* -----STILL NEEDS----- 
 * (take a comment and work on it)
 * reset game method
*/