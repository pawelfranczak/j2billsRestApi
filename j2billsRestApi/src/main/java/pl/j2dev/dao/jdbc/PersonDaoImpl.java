package pl.j2dev.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Statement;

import pl.j2dev.dao.IDao;
import pl.j2dev.dao.jdbc.mapper.PersonMapper;
import pl.j2dev.pojo.Person;

@Repository
public class PersonDaoImpl implements IDao<Person> {
	
    @Autowired
    private JdbcTemplate jdbcTemplateObject;
	
	@Override
	public List<Person> getList() {
		final String SQL = "SELECT * FROM person";
		List<Person> list = jdbcTemplateObject.query(SQL, new PersonMapper());
		return list;
	}

	@Override
	public Person getById(long id) {
        final String SQL = "SELECT * FROM person WHERE id = ?";
        Person person = jdbcTemplateObject.queryForObject(SQL, new Object[]{id}, new PersonMapper());
        return person;
	}

	@Override
	public List<Person> getList(int count, int offset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long add(Person object) {
		String lastName = object.getLastName();
		final String SQL;
		if (lastName != null && lastName.length() > 0) {
			SQL = "INSERT INTO person (firstname, lastname) VALUES (?,?)";
		} else {
			SQL = "INSERT INTO person (firstname) VALUES (?)";
		}
		
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplateObject.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement statement = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, object.getFirstName());
				if (lastName != null && lastName.length() > 0) {
					statement.setString(2, object.getLastName());
				}
				return statement;
			}
		}, keyHolder);
		long primaryKey = keyHolder.getKey().longValue();
		return primaryKey;
	}

	@Override
	public boolean delete(Person object) {
		// TODO Auto-generated method stub
		return false;
	}


}
