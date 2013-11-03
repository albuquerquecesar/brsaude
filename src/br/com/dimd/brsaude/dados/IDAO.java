package br.com.dimd.brsaude.dados;

import java.util.List;


public interface IDAO<T> {

	/**
	 * Armazena um novo objeto no banco de dados
	 * @param model
	 * @return true se o modelo for armazenado com sucesso
	 */
	public boolean save(T model);
	
	/**
	 * Aramazena um novo objeto caso ele ainda não exista, caso contrário atualiza-lo.
	 * @param model
	 * @return true se conseguir inserir ou atualizar o objeto
	 */
	public boolean merge(T model);
	
	/**
	 * Atualiza um objeto caso ele exista
	 * @param model
	 * @return
	 */
	public boolean update(T model);
	
	/**
	 * Retorna todos os objetos
	 * @return
	 */
	public List<T> findAll();
	
	/**
	 * Retorna um objeto em específico. 
	 * Caso não seja encontrado retorna null ou se
	 * tiver mais de um objeto que satisfaça a condição lança uma exceção 
	 * @param model
	 * @return 
	 */
	public T find(T model);
	
	public boolean delete(T model);
	
	/**
	 * 
	 * @param model
	 * @return
	 */
	public T findByHashCode(T model);
	
	public T findByExemple(T model);
	
	public List<T> findAll(T model);
	
	public boolean save(List<T> models);
	
	public boolean merge(List<T> models);
	
	public boolean update(List<T> models);
	
	public boolean deleteAll();
	
}
