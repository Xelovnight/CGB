package cgb.transfer.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import cgb.transfer.entity.Log;
import cgb.transfer.repository.LogRepository;

@Service
public class LogService {

	@Autowired
	private LogRepository logRepository;

	@GetMapping
	public List<Log> findAllLog() {
		return logRepository.findAll();
	}

	@GetMapping
	public Optional<Log> findById(Long id) {
		return logRepository.findById(id);
	}
	
	public void saveLog(Log log) {
		logRepository.save(log);
	}
}
