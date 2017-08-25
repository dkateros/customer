package gr.dkateros.util.jee7.data;

/**
 * Holds a database key (id) and owns a semantic key (key).
 */
public interface Identified {
	
	void setId(Long id);
	
	Long getId();
	
	String getKey();

}
