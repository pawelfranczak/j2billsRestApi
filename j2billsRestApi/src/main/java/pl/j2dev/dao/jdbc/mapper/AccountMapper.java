package pl.j2dev.dao.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pl.j2dev.pojo.Account;

public class AccountMapper implements RowMapper<Account> {
	@Override
	public Account mapRow(ResultSet rs, int row) throws SQLException {
		Account account = new Account();
        account.setId(rs.getLong("id"));
        account.setAccountName(rs.getString("accountname"));
        account.setDescription(rs.getString("description"));
        account.setBalance(rs.getBigDecimal("balance"));
		return account;
	}	
}
