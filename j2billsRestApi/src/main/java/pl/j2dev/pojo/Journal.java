package pl.j2dev.pojo;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Journal {

	
	
	long id;
    int personId;
    int accountId;
    BigDecimal value;
    String description;
    Timestamp timestamp;
    
    @JsonIgnore
    Person person;
    @JsonIgnore
    Account account;

    @Override
    public String toString() {
        return "Journal{" +
                "id=" + id +
                ", person=" + person +
                ", account=" + account +
                ", value=" + value +
                ", description='" + description + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
    
}
