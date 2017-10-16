package pl.j2dev.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import pl.j2dev.dao.IDao;
import pl.j2dev.dao.jdbc.mapper.JournalMapper;
import pl.j2dev.pojo.Journal;

@Repository
public class JournalDaoImpl implements IDao<Journal>{
	
    @Autowired
    private JdbcTemplate jdbcTemplateObject;
    
    @Autowired
    AccountDaoImpl accountDao;
    
    List<Journal> journalCache = new LinkedList<Journal>();
    final int queueSize = 512;
    
    private void updateCache (Journal journal) {
    	if (journalCache.size() == queueSize) 
    		journalCache.remove(queueSize-1);
    	if (!existInCache(journal.getId()))
    		journalCache.add(0, journal);
    }
    
    private Journal getFromCache(long id) {
    	for (Journal j : journalCache) {
    		if (j.getId() == id)
    			return j;
    	}
    	return null;
    }
    
    private boolean existInCache(long id) {
    	Journal cached = getFromCache(id);
    	if (cached == null)
    		return false;
    	return true;
    }
	
	@Override
	public List<Journal> getList() {
		return null;
	}

	@Override
	public Journal getById(long id) {
		Journal cachedJournal = getFromCache(id);
		if (cachedJournal != null) 
			return cachedJournal;
		final String SQL = "select * from journal WHERE id = ?";
		Journal journal = jdbcTemplateObject.queryForObject(SQL, new JournalMapper(), id);
		updateCache(journal);
		return journal;
	}

	@Override
	public List<Journal> getList(int count, int offset) {
		final String SQL = "select * from journal  ORDER BY id DESC LIMIT " + count + " OFFSET " + offset;
		List<Journal> list = jdbcTemplateObject.query(SQL, new JournalMapper());
		for (int i = list.size()-1; i >= 0; i--) {
			updateCache(list.get(i));
		}
		return list;
	}

	@Override
	public long add(Journal object) {
		final String SQL = "INSERT INTO journal (person_id, account_id, value, description) values (?,?,?,?)";
		GeneratedKeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplateObject.update(new PreparedStatementCreator() {
		    @Override
		    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
		        PreparedStatement statement = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
		        statement.setInt(1, object.getPersonId());
		        statement.setInt(2, object.getAccountId());
		        statement.setBigDecimal(3, object.getValue());
		        statement.setString(4, object.getDescription());
		        return statement;
		    }
		}, holder);
		long primaryKey = holder.getKey().longValue();
		return primaryKey;
	}

	@Override
	public boolean delete(Journal object) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
