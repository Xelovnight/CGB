package cgb.transfer.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cgb.transfer.entity.Log;
import cgb.transfer.services.LogService;

@RestController
@RequestMapping("/log")
public class LogController {

	@Autowired
	private LogService logService;

	@GetMapping("/{id}")
	public Optional<Log> obtenirLog(@PathVariable Long id) {
		return logService.findById(id);
	}

	@GetMapping("/all")
	public List<Log> obtenirAllLog() {
		return logService.findAllLog();
	}

	@GetMapping("/creerlogfile")
	public ResponseEntity<String> createlogfile() {

		logService.createJsonLog();
		return new ResponseEntity(HttpStatus.OK);
	}

}
