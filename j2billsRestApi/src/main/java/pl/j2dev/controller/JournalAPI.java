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
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Journal addToJournal(@RequestBody Journal journal) {
		return journalService.createJournalEntry(journal);
	}

}
