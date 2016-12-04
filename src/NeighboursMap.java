/* This is a test file for possibility of using HashMap for storing neighbours as it 
 * would provide us with the ability to store the direction the piece is being moved
 * 
 * second method provides us with the ability to get the key using a value
 * 
 * 
 * No idea would it be overcomplicating it or not tbh, if you know of a better/easier/cleaner way
 * feel free to let me know
 */











import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

class NeighboursMap {
	
	private static HashMap<String, Cell> neighboursList = new HashMap<String, Cell>(6);
	
	public static HashMap<String, Cell> getNeighboursMap(Cell cell) {
			
	int x = AbaloneBoard.getCoords(cell)[0];
	int y = AbaloneBoard.getCoords(cell)[1];
		
	
	for(int i=0; i < 6; i++) {
		if(i==0) neighboursList.put("TopLeft", AbaloneBoard.getCell(x-1, y-1));
		if(i==1) neighboursList.put("TopRight", AbaloneBoard.getCell(x+1, y-1));
		if(i==2) neighboursList.put("Right", AbaloneBoard.getCell(x+2, y));
		if(i==3) neighboursList.put("BottomRight", AbaloneBoard.getCell(x+1, y+1));
		if(i==4) neighboursList.put("BottomLeft", AbaloneBoard.getCell(x-1, y+1));
		if(i==5) neighboursList.put("Left", AbaloneBoard.getCell(x-2, y));
	}
	
	return neighboursList;
}	
	
	public static <Key, Value> Key getKeyByValue(Map<Key, Value> map, Value value) {
	    for (Entry<Key, Value> entry : map.entrySet()) {
	        if (Objects.equals(value, entry.getValue())) {
	            return entry.getKey();
	        }
	    }
	    return null;
	}
	
	
	
}