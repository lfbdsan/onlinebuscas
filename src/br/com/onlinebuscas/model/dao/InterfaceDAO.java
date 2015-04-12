package br.com.onlinebuscas.model.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;

public abstract interface InterfaceDAO<T>
{
  public abstract void save(T paramT);
  
  public abstract void update(T paramT);
  
  public abstract void remove(T paramT);
  
  public abstract void merge(T paramT);
  
  public abstract T getEntity(Serializable paramSerializable);
  
  public abstract T getEntityByDetachedCriteria(DetachedCriteria paramDetachedCriteria);
  
  public abstract T getEntityByHQLQuery(String paramString);
  
  public abstract List<T> getEntities();
  
  public abstract List<T> getListByDetachedCriteria(DetachedCriteria paramDetachedCriteria);
}