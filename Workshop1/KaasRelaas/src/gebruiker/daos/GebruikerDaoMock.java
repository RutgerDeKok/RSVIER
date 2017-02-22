package gebruiker.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import gebruiker.Gebruiker;
import gebruiker.GebruikerType;


public class GebruikerDaoMock extends AbstractGebruikerDao{

	@Override
	public List<Gebruiker> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveNew(Gebruiker t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Gebruiker t) {
		// TODO Auto-generated method stub

	}


	@Override
	public List<Gebruiker> getAllByType(GebruikerType type, boolean include) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gebruiker getAllById(int Id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gebruiker getByLogin(String login) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	protected Gebruiker convertRowToGebruiker(ResultSet myRs) throws SQLException {
		return null;
		
	}

	@Override
	protected void gebruikerToResultSet(ResultSet myRs, Gebruiker gebruiker) throws SQLException {

	}
	
	
	

}
