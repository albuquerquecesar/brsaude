package br.com.dimd.brsaude.dados;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.dimd.brsaude.exception.ManyObjectFoundException;
import br.com.dimd.brsaude.exception.NoUniqueObjectException;
import br.com.dimd.brsaude.exception.NotFoundObjectException;
import br.com.dimd.brsaude.exception.SaveException;
import br.com.dimd.brsaude.exception.UpdateException;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.query.Predicate;
import com.db4o.query.Query;

public abstract class GenericDb4oDAO<T extends Serializable> implements IDAO<T> {

	private Class<T> classModel;
	private static String DATA_BASE_FILE = "/data/data/br.com.dimb.brsaude/br_saude.YAP";

	public GenericDb4oDAO() {
		super();
		this.classModel = ((Class) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0]);

	}

	protected ObjectContainer open() {
		return Db4oEmbedded.openFile(this.getConfiguration(),
				GenericDb4oDAO.DATA_BASE_FILE);
	}

	/**
	 * 
	 * @return
	 */
	protected EmbeddedConfiguration getConfiguration() {

		EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
		

		return config;
	}

	@Override
	public boolean save(T model) {
		boolean success = false;
		ObjectContainer db = this.open();
		try {
			if (!this.save(model, db)) {
				throw new SaveException("Impossível salvar o objeto! " + model);
			}
			db.commit();
			success = true;
		} catch (Exception ex) {
			success = false;
			db.rollback();
			ex.printStackTrace();
		} finally {
			db.close();
		}
		return success;
	}

	@Override
	public boolean merge(T model) {
		boolean success = false;
		ObjectContainer db = this.open();
		try {
			ObjectSet<T> set = this.getQueryToUniqueObject(model, db).execute();
			int size = set.size();
			if (size == 0) {
				if (!this.save(model, db)) {
					throw new SaveException("Impossível salvar o objeto! "
							+ model);
				}

			} else if (size == 1) {
				this.update(set.get(0), model, db);
			}
			db.commit();
			success = true;
		} catch (Exception e) {
			// TODO: handle exception
			success = false;
			db.rollback();
			e.printStackTrace();
		} finally {
			db.close();
		}
		return success;
	}

	@Override
	public boolean update(T model) {
		boolean success = false;
		ObjectContainer db = this.open();
		try {
			ObjectSet<T> set = this.getQueryToUniqueObject(model, db).execute();
			// existe o objeto no banco de dados
			if (set.size() == 1) {
				if (!this.update(set.get(0), model, db)) {
					throw new UpdateException("Impossível atualizar o objeto! "
							+ model);
				}
			}
			db.commit();
			success = true;
		} catch (Exception e) {
			success = false;
			db.rollback();
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			db.close();
		}
		return success;
	}

	@Override
	public List<T> findAll() {
		List<T> l = new ArrayList<T>();
		ObjectContainer db = this.open();
		try {
			l.addAll(db.query(this.getClassModel()));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			db.close();
		}
		return l;
	}

	@Override
	public T find(T model) {
		// TODO Auto-generated method stub
		T t = null;
		ObjectContainer db = this.open();

		try {
			ObjectSet<T> set = this.getQueryToUniqueObject(model, db).execute();
			int size=set.size();
			if (size == 1) {
				t = set.get(0);
			}else if( size== 0){
				throw new NotFoundObjectException("Não foi possível encontrar "+model+" no banco de dados.");
			}else{
				throw new ManyObjectFoundException("Mais de um "+model+" encontrado para no banco de dados.");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			db.close();
		}
		return t;
	}

	@Override
	public T findByHashCode(T model) {

		T t = null;
		ObjectContainer db = this.open();

		try {
			ObjectSet<T> set = db.query(new PredicateForHashCode(model));
			if (set.size() == 1) {
				t = set.get(0);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			db.close();
		}
		return t;
	}

	@Override
	public List<T> findAll(T model) {
		// TODO Auto-generated method stub
		List<T> l = new ArrayList<T>();
		ObjectContainer db = this.open();
		try {
			l.addAll((Collection<? extends T>) db.queryByExample(model));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			db.close();
		}
		return l;
	}

	@Override
	public boolean save(List<T> models) {
		// TODO Auto-generated method stub
		ObjectContainer db = this.open();
		boolean success = false;
		try {
			for (T t : models) {
				if (!this.save(t, db)) {
					throw new SaveException("Impossível salvar o objeto! " + t);
				}
			}
			db.commit();
			success = true;
		} catch (Exception ex) {
			success = false;
			ex.printStackTrace();
			db.rollback();
		} finally {
			db.close();
		}
		return success;
	}

	@Override
	public boolean merge(List<T> models) {
		// TODO Auto-generated method stub
		boolean success = false;
		ObjectContainer db = this.open();
		try {
			for (T t : models) {

				ObjectSet<T> set = this.getQueryToUniqueObject(t, db).execute();
				int size = set.size();

				if (size == 0) {
					// não existe um objeto no banco
					if (!this.save(t, db)) {
						throw new SaveException("Impossível salvar o objeto! "
								+ t);
					}
				} else if (size == 1) {
					// existe o objeto no banco, então ele deve atualizá-lo
					if (!this.update(set.get(0), t, db)) {
						throw new UpdateException(
								"Falha ao atualizar o objeto! " + t);
					}
				} else {
					throw new ManyObjectFoundException();
				}
			}
			db.commit();
			success = true;
		} catch (Exception ex) {
			ex.printStackTrace();
			db.rollback();
		} finally {
			db.close();
		}

		return success;
	}

	@Override
	public boolean update(List<T> models) {
		// TODO Auto-generated method stub
		boolean success = false;
		ObjectContainer db = this.open();
		try {
			for (T t : models) {
				if (!this.update(t, db)) {
					throw new UpdateException(
							"Falha ana atualização do objeto!" + t);
				}
			}
			db.commit();
			success = true;
		} catch (Exception e) {
			// TODO: handle exception
			db.rollback();
			e.printStackTrace();
		} finally {
			db.close();
		}
		return success;
	}

	/**
	 * @return the classModel
	 */
	protected Class<T> getClassModel() {
		return classModel;
	}

	@Override
	public boolean delete(T model) {
		// TODO Auto-generated method stub
		boolean success = false;
		ObjectContainer db = this.open();

		try {
			ObjectSet<T> set = this.getQueryToUniqueObject(model, db).execute();
			if (set.size() == 1) {
				db.delete(set.get(0));
			} else if (set.size() > 1) {
				throw new ManyObjectFoundException(
						"Mais de um objeto encontrado para " + model);
			} else {
				throw new NotFoundObjectException(
						"Nenhum objeto encontrado no banco de dados para "
								+ model);
			}
			db.commit();
			success = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			db.rollback();
		} finally {
			db.close();
		}
		return success;
	}

	@Override
	public boolean deleteAll() {
		// TODO Auto-generated method stub
		boolean success = false;
		ObjectContainer db = this.open();
		try {
			ObjectSet<T> all = db.query(this.getClassModel());

			for (T t : all) {
				db.delete(t);
			}
			db.commit();
			success = true;
		} catch (Exception ex) {
			ex.printStackTrace();
			db.rollback();
		} finally {
			db.close();
		}
		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.gov.pe.saudecaruaru.inspetordigital.dao.IDAO#findByExemple(br.gov.
	 * pe.saudecaruaru.inspetordigital.model.IModel)
	 */
	@Override
	public T findByExemple(T model) {
		T t = null;
		ObjectContainer db = this.open();

		try {
			ObjectSet<T> set = db.queryByExample(model);
			if (set.size() == 1) {
				t = set.get(0);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			db.close();
		}
		return t;
	}

	/**
	 * 
	 * @param modelReceived
	 *            Objeto com os dados a serem atualizados
	 * @param db
	 *            Container atual
	 * @return true se tudo ocorrer bem, caso contrário retorna false
	 * @throws UpdateException
	 *             Caso algum erro inesperado aconteça
	 * @throws ManyObjectFoundException
	 *             Se possui atributos que são objetos armazenados no banco,
	 *             então lança essa exceção caso possua mais de objetos iguais
	 *             armazenados no banco. Deve-se levar em consideração a
	 *             implementação de objetos iguais.
	 * @throws NotFoundObjectException
	 *             Mesmo caso acima, porém o objeto não foi encontrado
	 */
	protected abstract boolean update(T modelReceived, ObjectContainer db)
			throws UpdateException, ManyObjectFoundException,
			NotFoundObjectException;

	/**
	 * 
	 * @param modelRetrieved
	 *            objeto recuperado do banco de dados, e que está ativo para o
	 *            container atual
	 * @param modelReceived
	 *            Objeto com os dados a serem atualizados
	 * @param db
	 *            Container atual
	 * @return true se tudo ocorrer bem, caso contrário retorna false
	 * @throws UpdateException
	 *             Caso algum erro inesperado aconteça
	 * @throws ManyObjectFoundException
	 *             Se possui atributos que são objetos armazenados no banco,
	 *             então lança essa exceção caso possua mais de objetos iguais
	 *             armazenados no banco. Deve-se levar em consideração a
	 *             implementação de objetos iguais.
	 * @throws NotFoundObjectException
	 *             Mesmo caso acima, porém o objeto não foi encontrado
	 */
	protected abstract boolean update(T modelRetrieved, T modelReceived,
			ObjectContainer db) throws UpdateException,
			ManyObjectFoundException, NotFoundObjectException;

	/**
	 * A classe filha deve implemntar esse método, pois outras operações podem
	 * ser necessárias para realizar o store do objeto.
	 * 
	 * @param model
	 * @param db
	 * @return true se nenhum erro ocorreu
	 * @throws SaveException
	 *             se ocorrer algum erro não identificado
	 * @throws ManyObjectFoundException
	 *             Atributos que são objetos armazenados no banco, mas possuem
	 *             mais de uma referência para o mesmo, levando-se em
	 *             consideração a impplementação do desenvolver do que vem a ser
	 *             dois objetos iguais.
	 * @throws NotFoundObjectException
	 *             Atributos que são objetos, mas não possuem referência no
	 *             banco de dados
	 * @throws NoUniqueObjectException
	 */
	protected abstract boolean save(T model, ObjectContainer db)
			throws SaveException, ManyObjectFoundException,
			NotFoundObjectException, NoUniqueObjectException;

	/**
	 * Deve retornar uma para consulta onda as retrições aplicadas sejam para
	 * retornar somente um objeto, ou seja, as retrições são baseadas nos mesmos
	 * atributos que os métodos equals() e hash().
	 * 
	 * @param model
	 * @param db
	 * @return
	 */
	protected abstract Query getQueryToUniqueObject(T model, ObjectContainer db);

	/**
	 * Deve retornar o modelo da DAO a partir do Container passado.
	 * 
	 * @param model
	 * @param db
	 * @return
	 */
	protected abstract T getModel(T model, ObjectContainer db)
			throws ManyObjectFoundException;

	protected class PredicateForHashCode<T2> extends Predicate<T2> {

		private T2 object;

		public PredicateForHashCode(T2 object) {
			super();
			this.object = object;
		}

		@Override
		public boolean match(T2 arg0) {
			// TODO Auto-generated method stub
			return this.object.equals(arg0);
		}

	}
}
