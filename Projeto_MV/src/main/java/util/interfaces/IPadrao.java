package util.interfaces;

import java.util.List;

public abstract interface IPadrao<T, Y> {

	public void insert(T obj);

	public void delet(Y id);

	public void edit(T obj);

	public void searchId(Y id);

	public List<T> list();

}
