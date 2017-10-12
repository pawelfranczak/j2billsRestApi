package pl.j2dev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.j2dev.dao.jdbc.JournalDaoImpl;
import pl.j2dev.pojo.Journal;

@Service
public class JournalService {

	@Autowired
	JournalDaoImpl dao;
	
	@Autowired
	AccountService accountService;

	public List<Journal> getListObjects(int limit, int offset) {
		return dao.getList(limit, offset);
	}
	
	public Journal getObjectById(long id) {
		return dao.getById(id);
	}
	
	public Journal createJournalEntry(Journal journal) {
		long id = dao.add(journal);
		accountService.actualizeBalance(journal.getAccountId(), journal.getValue());
		return dao.getById(id);
	}
	
}
