package gr.dkateros.util.jee7.data;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.json.JsonObject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Base implementation for EJBs that handle JPA2 persistence.
 * We also handle JSON (de)serialization of entities in order to use the
 * JSON-B reference implementation explicitly.
 * 
 * @param <E> Type of entity
 */
public class BaseDao<E extends Identified> {

	@PersistenceContext EntityManager em;

	private final Class<E> clazz;

	public BaseDao(Class<E> clazz) {
		this.clazz = clazz;
	}
	
	//Entity (JPA)
	
	public void persist(E e) {
		em.persist(e);
	}

	public void remove(E e) {
		em.remove(e);
	}

	public E find(Long id) {
		return em.find(clazz, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<E> query(String name, Map<String, Object> parameters, int startPosition, int maxResult) {
		Query query = em.createNamedQuery(name);
		query.setFirstResult(startPosition);
		query.setMaxResults(maxResult);
		for (Map.Entry<String, Object> entry : parameters.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		return query.getResultList();
	}
	
	public List<E> query(String name, Map<String, Object> parameters) {
		return query(name, parameters, 0, Integer.MAX_VALUE);
	}
	
	public List<E> query(String name) {
		return query(name, Collections.emptyMap());
	}
	
	//JSON (JAX-RS, JSON-B)
	
	public E add(JsonObject jsonObject) {
		E e = entity(jsonObject.toString());
		persist(e);
		return e;
	}
	
	public String get(Long id) {
		E e = find(id);
		if (e == null) {
			return null;
		}
		return json(e);
	}
	
	public String json(E e) {
		Jsonb jsonb = JsonbBuilder.create();
		return jsonb.toJson(e);
	}
	
	public E entity(String json) {
		Jsonb jsonb = JsonbBuilder.create();
		return jsonb.fromJson(json, clazz);
	}
	
}
