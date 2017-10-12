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

import pl.j2dev.pojo.Account;
import pl.j2dev.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountAPI {
	
	@Autowired
	AccountService accountService;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<Account> getAllAccountJSON() {
		return accountService.getListOfAllObjects();
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Account getAccountJSON(@PathVariable("id") long id) {
		System.out.println("Zwracam jsona");
		return accountService.getObjectById(id);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Account saveAccountJSON(@RequestBody Account account) {
		return accountService.createObject(account);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<Account> updateAccountJSON(@RequestBody Account account) {
		// TODO update
		return null;
	}

}
