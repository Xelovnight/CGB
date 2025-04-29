package cgb.transfer.services;

import java.io.FileWriter;
import java.util.List;
import java.util.Optional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

	@GetMapping
	public void createJsonLog() {

		JSONObject jsonObject = new JSONObject();
		JSONArray logsArray = new JSONArray();

		for (Log unLog : logRepository.findAll()) {
			JSONObject logJson = new JSONObject();
			logJson.put("etatlog", unLog.getEtatLog().toString());
			logJson.put("description", unLog.getDescription());
			logJson.put("date", unLog.getDate().toString());
			logJson.put("nomClass", unLog.getClass().getSimpleName());
			logsArray.add(logJson);
		}
		jsonObject.put("logs", logsArray);

		try {
			FileWriter file = new FileWriter("C:\\Users\\enzo.jungers\\Documents\\jsonCGB\\cgbLogs.json");
			
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String prettyJsonString = gson.toJson(jsonObject);

			file.write(prettyJsonString);
			file.close();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}
}
