package com.wipro.crowdfunding.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.crowdfunding.dto.InvestmentDTO;
import com.wipro.crowdfunding.service.InvestmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/investments")
public class InvestmentController {
	// write your logic here
	@Value("${profile.validate.data}")
	private String name;
	@Autowired
	private InvestmentService investmentService;

	@PostMapping("/")
	public InvestmentDTO makeInvestment(@Valid @RequestBody InvestmentDTO investmentDTO) {
		InvestmentDTO investmentDTO2 = investmentService.makeInvestment(investmentDTO);
		return investmentDTO2;
	}

	@PutMapping("/{investmentId}")
	public InvestmentDTO updateInvestmentDTO(@PathVariable Long investmentId,
			@Valid @RequestBody InvestmentDTO investmentDTO) {
		InvestmentDTO investmentDTO2 = investmentService.updateInvestment(investmentId, investmentDTO);

		return investmentDTO2;
	}

	@DeleteMapping("/{investmentId}")
	public boolean deleteInvestment(@PathVariable Long investmentId) {
		return investmentService.deleteInvestment(investmentId);

	}

	@GetMapping("/{investmentId}")
	public InvestmentDTO getbyId(@PathVariable Long investmentId) {
		InvestmentDTO investmentDTO2 = investmentService.getInvestmentById(investmentId);
		return investmentDTO2;
	}

	@GetMapping("/project/{projectId}")
	public List<InvestmentDTO> getbyprojectid(@PathVariable Long projectId) {
		List<InvestmentDTO> investmentDTOs = investmentService.getInvestmentsByProjectId(projectId);
		return investmentDTOs;
	}

	@GetMapping("/investor/{investmentName}")
	public List<InvestmentDTO> getbyprojectBYName(@PathVariable String investmentName) {
		List<InvestmentDTO> investmentDTOs = investmentService.getInvestmentsByInvestorName(investmentName);
		return investmentDTOs;
	}

	@GetMapping("/profile")
	public String getProfile() {
		return this.name;
	}

}