package br.com.dimd.brsaude.dados;

import br.com.dimd.brsaude.exception.ManyObjectFoundException;
import br.com.dimd.brsaude.exception.NoUniqueObjectException;
import br.com.dimd.brsaude.exception.NotFoundObjectException;
import br.com.dimd.brsaude.exception.SaveException;
import br.com.dimd.brsaude.exception.UpdateException;
import br.com.dimd.brsaude.model.Unidade;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Constraint;
import com.db4o.query.Query;

public class UnidadeDAO extends GenericDb4oDAO<Unidade>{

	@Override
	protected boolean update(Unidade modelReceived, ObjectContainer db)
			throws UpdateException, ManyObjectFoundException,
			NotFoundObjectException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean update(Unidade modelRetrieved, Unidade modelReceived,
			ObjectContainer db) throws UpdateException,
			ManyObjectFoundException, NotFoundObjectException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean save(Unidade model, ObjectContainer db)
			throws SaveException, ManyObjectFoundException,
			NotFoundObjectException, NoUniqueObjectException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected Query getQueryToUniqueObject(Unidade model, ObjectContainer db) {
		
		Query query=db.query();
		query.constrain(model.getClass());
		Constraint c1=query.descend("cnes").constrain(model.getCnes());
		Constraint c2=query.descend("rpa").constrain(model.getRpa()).and(c1);
		query.descend("microregicao").constrain(model.getMicroregiao()).and(c2);
		
		return query;
	}

	@Override
	protected Unidade getModel(Unidade model, ObjectContainer db)
			throws ManyObjectFoundException {
		ObjectSet<Unidade> unidades=this.getQueryToUniqueObject(model, db).execute();
		int size=unidades.size();
		
		if (size==1){
			return unidades.next();
		}
		else if (size == 0){
			return null;
		}
		
		throw new ManyObjectFoundException("Mais de uma unidade encontrada para "+model);
		
	}

}
