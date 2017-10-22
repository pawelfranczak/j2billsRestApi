package pl.j2dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pl.j2dev.pojo.Journal;
import pl.j2dev.pojo.JournalMovement;
import pl.j2dev.service.JournalService;


@RestController
@RequestMapping("/journal/")
public class JournalAPI {
	
	@Autowired
	JournalService journalService;
	
	@RequestMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Journal getJournal(@PathVariable("id") long id) {
		return journalService.getObjectById(id);
	}
	
	@RequestMapping(path = "/{limit}/{offset}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<Journal> getJournalList(@PathVariable("limit") int limit, @PathVariable("offset") int offset) {
		return journalService.getListObjects(limit, offset);
	}
	
	@RequestMapping(path = "/{limit}/{offset}/{accountId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<Journal> getJournalList(@PathVariable("limit") int limit, @PathVariable("offset") int offset, @PathVariable("accountId") long accountId) {
		return journalService.getListObjects(limit, offset, accountId);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Journal addToJournal(@RequestBody Journal journal) {
		return journalService.createJournalEntry(journal);
	}
	
	@RequestMapping(path = "/createMovement/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody JournalMovement makeJournalMovement(@RequestBody JournalMovement jm) {
		return journalService.createJournalMovement(jm);
	}
	
	@RequestMapping(path = "/count/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody long getCountOfJournalsEntries() {
		return journalService.getCountOfJournalsEntries();
	}
	
	@RequestMapping(path = "/count/{accountId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody long getCountOfJournalsEntries(@PathVariable("accountId") long accountId) {
		return journalService.getCountOfJournalsEntries(accountId);
	}
	
}
