package ie.domis.config;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ie.domis.domain.Job;
import ie.domis.service.JobService;

@Component
public class Scheduler {

	@Autowired
	JobService jobService;

	@Scheduled(fixedRate = 10000)
	public void checkForExpiredJobs() {
		List<Job> allJobs = jobService.findAllJobs();
		for (Job j : allJobs) {
			if (j.getDate().isBefore(LocalDateTime.now().minusDays(20))) {
				jobService.updateJobActive(j.getJobId(), false);
			}
		}
	}

}
