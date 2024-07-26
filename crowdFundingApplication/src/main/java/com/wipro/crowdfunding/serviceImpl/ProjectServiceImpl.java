package com.wipro.crowdfunding.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.crowdfunding.Exception.ResourceNotFoundException;
import com.wipro.crowdfunding.dto.InvestmentDTO;
import com.wipro.crowdfunding.dto.ProjectDTO;
import com.wipro.crowdfunding.entity.Investment;
import com.wipro.crowdfunding.entity.Project;
import com.wipro.crowdfunding.repo.ProjectRepository;
import com.wipro.crowdfunding.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ProjectDTO createProject(ProjectDTO projectDTO) {
		// write your logic here
		Project project = modelMapper.map(projectDTO, Project.class);

		Project project1 = projectRepository.save(project);
		ProjectDTO projectDto = modelMapper.map(project1, ProjectDTO.class);
		return projectDto;
	}

	@Override
	public ProjectDTO updateProject(Long projectId, ProjectDTO projectDTO) {
		// write your logic here

		Optional<Project> project1 = projectRepository.findById(projectId);
		if (project1.isPresent()) {
			Project project2 = project1.get();

			Project project = modelMapper.map(projectDTO, Project.class);
			project2.setAmountRaised(project.getAmountRaised());
			project2.setDescription(project.getDescription());
			project2.setGoalAmount(project.getGoalAmount());
			project2.setInvestments(project.getInvestments());
			project2.setName(project.getName());

			Project project3 = projectRepository.save(project2);

			return modelMapper.map(project3, ProjectDTO.class);
		} else {
			throw new ResourceNotFoundException("Project By Id not Found");
		}

	}

	@Override
	public boolean deleteProject(Long projectId) {
		// write your logic here
		Optional<Project> project1 = projectRepository.findById(projectId);
		if (project1.isPresent()) {
			Project project2 = project1.get();
			projectRepository.delete(project2);
			return true;
		} else {
			throw new ResourceNotFoundException("Project By Id not Found");

		}
	}

	@Override
	public ProjectDTO getProjectById(Long projectId) {
		Optional<Project> project1 = projectRepository.findById(projectId);
		if (project1.isPresent()) {
			Project project2 = project1.get();

			return modelMapper.map(project2, ProjectDTO.class);

		} else {
			throw new ResourceNotFoundException("Project By Id not Found");

		}
	}

	@Override
	public List<ProjectDTO> getAllProjects() {

		List<ProjectDTO> list = projectRepository.findAll().stream()
				.map(project -> modelMapper.map(project, ProjectDTO.class)).toList();
		return list;

	}
}