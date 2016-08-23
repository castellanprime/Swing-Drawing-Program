/**
* 
* @author: Okusanya David
* @date: August 22, 2016
* @version: 0.1
*/

public interface UIContext {

	public abstract void draw(Line line);

	public abstract void draw(Label label);

	public abstract void draw(Item item);

	public abstract void draw(Ellipse ellipse);

	public abstract void draw(Polygon polygon);

	public abstract void draw(Square sq);
}
