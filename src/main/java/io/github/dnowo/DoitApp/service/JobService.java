package io.github.dnowo.DoitApp.service;

import io.github.dnowo.DoitApp.model.Job;
import io.github.dnowo.DoitApp.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobService {
    private final JobRepository jobRepository;

    public List<Job> getJobs(){
        return jobRepository.findAll();
    }

    @Transactional
    public Job changeEndedState(Job job){
        Job editedJob = jobRepository.findById(job.getId()).orElseThrow();
        editedJob.setEnded(true);
        return editedJob;
    }

    public Job addJob(Job job) {
        return jobRepository.save(job);
    }

    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }

    @Transactional
    public Job edit(Job job) {
        Job jobToEdit = jobRepository.findById(job.getId()).orElseThrow();
        jobToEdit.setEnded(job.getEnded());
        jobToEdit.setDeadline(job.getDeadline());
        jobToEdit.setDescription(job.getDescription());
        jobToEdit.setNotification(job.getNotification());
        jobToEdit.setPriority(job.getPriority());
        jobToEdit.setTitle(job.getTitle());
        return jobToEdit;
    }

    public Job getJobById(Long id) {
        return jobRepository.getJobById(id);
    }
}