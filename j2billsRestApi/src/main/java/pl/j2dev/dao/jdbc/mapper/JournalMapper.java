package pl.j2dev.dao.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import pl.j2dev.dao.IDao;
import pl.j2dev.dao.jdbc.AccountDaoImpl;
import pl.j2dev.dao.jdbc.PersonDaoImpl;
import pl.j2dev.pojo.Account;
import pl.j2dev.pojo.Journal;

@Repository
public class JournalMapper implements RowMapper<Journal> {
	
	@Override
	public Journal mapRow(ResultSet rs, int row) throws SQLException {
        Journal journal = new Journal();
        journal.setId(rs.getInt("id"));
        journal.setValue(rs.getBigDecimal("value"));
        journal.setDescription(rs.getString("description"));
        journal.setTimestamp(rs.getTimestamp("timestamp"));
        journal.setPersonId(rs.getInt("person_id"));
        journal.setAccountId(rs.getInt("account_id"));
        return journal;
	}
	
}
