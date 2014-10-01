package au.moodflip.cardgame.service;

public interface GenericManager<T>{
	public void add(T entity);
	public void update(T entity);
	public T getById(long id);
	public void delete(long id);
}
