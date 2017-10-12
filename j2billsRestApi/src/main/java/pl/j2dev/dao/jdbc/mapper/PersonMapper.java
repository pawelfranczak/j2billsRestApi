package pl.j2dev.dao.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pl.j2dev.pojo.Person;

public class PersonMapper implements RowMapper<Person>{

	@Override
	public Person mapRow(ResultSet rs, int row) throws SQLException {
		Person person = new Person();
		person.setId(rs.getInt("id"));
		person.setFirstName(rs.getString("firstname"));
		person.setLastName(rs.getString("lastname"));
		return person;
	}
	
}
