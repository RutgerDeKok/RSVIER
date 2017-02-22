package main;

import java.util.List;


public interface DaoInterface<T> {

	List<T> getAll();

	void saveOrUpdate(T t);

	void saveNew(T t);

	void update(T t);

	

}
