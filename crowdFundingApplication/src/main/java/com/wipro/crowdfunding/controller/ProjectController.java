package com.wipro.crowdfunding.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.crowdfunding.dto.ProjectDTO;
import com.wipro.crowdfunding.service.ProjectService;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
	// write your logic here
	@Autowired
	private ProjectService projectService;

	@PostMapping("/")
public ProjectDTO makeInvestment(@RequestBody  ProjectDTO  projectDTO)
{
		System.out.println("projectcontroller   "+projectDTO);
	ProjectDTO investmentDTO2= projectService.createProject(projectDTO);
	return investmentDTO2;
}

@PutMapping("/{projectId}")
public ProjectDTO upadProjectDTO(@PathVariable Long projectId,@RequestBody ProjectDTO projectDTO)
{
   ProjectDTO project=projectService.updateProject(projectId, projectDTO);
   return project;
}

@DeleteMapping("/{projectId}")
public boolean deleteProject(@PathVariable Long projectId)
{
	return projectService.deleteProject(projectId);
	
}

@GetMapping("/{projectId}")
public ProjectDTO gProjectDTO(@PathVariable Long projectId)
{
	ProjectDTO projectDto=projectService.getProjectById(projectId);
	return projectDto;
}
@GetMapping("/")
public List<ProjectDTO> getAllProjects()
{
	List<ProjectDTO> projectDtos=projectService.getAllProjects();
	return projectDtos;
}

}