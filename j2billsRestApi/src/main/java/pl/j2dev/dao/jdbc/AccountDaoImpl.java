package pl.j2dev.dao.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import pl.j2dev.dao.IDao;
import pl.j2dev.dao.jdbc.mapper.AccountMapper;
import pl.j2dev.pojo.Account;

/**
 * Created by pfranczak on 18.08.2017.
 */
@Repository
public class AccountDaoImpl implements IDao<Account> {

    @Autowired
    private JdbcTemplate jdbcTemplateObject;

    @Override
    public List<Account> getList() {
        final String SQL = "SELECT * FROM account";
        List<Account> list = jdbcTemplateObject.query(SQL, new AccountMapper());
        return list;
    }

    @Override
    public Account getById(long id) {
        final String SQL = "SELECT * FROM account WHERE id = ?";
        Account account = jdbcTemplateObject.queryForObject(SQL, new AccountMapper(), id);
        return account;
    }

    @Override
    public List<Account> getList(int count, int offset) {
        return null;
    }

    @Override
    public long add(Account object) {
		final String SQL = "INSERT INTO account (accountname, description, balance) values (?,?,?)";
		GeneratedKeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplateObject.update(new PreparedStatementCreator() {
		    @Override
		    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
		        PreparedStatement statement = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
		        statement.setString(1, object.getAccountName());
		        statement.setString(2, object.getDescription());
		        statement.setBigDecimal(3, object.getBalance());
		        return statement;
		    }
		}, holder);
		long primaryKey = holder.getKey().longValue();
		return primaryKey;
    }

    @Override
    public boolean delete(Account object) {
        return false;
    }
    
    public void updateBalance(long accountId, BigDecimal value) {
    	BigDecimal balance = null;
    	Account account = getById(accountId);
    	if (account != null) {
	    	balance = account.getBalance();
	        if (balance != null) {
	        	balance = balance.add(value);
	        	final String SQL = "UPDATE account SET balance = ? WHERE id = ?";
	        	jdbcTemplateObject.update(SQL, balance, accountId);
	        }
    	}
    }

}