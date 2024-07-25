package com.wipro.crowdfunding.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.crowdfunding.Exception.ResourceNotFoundException;
import com.wipro.crowdfunding.dto.InvestmentDTO;
import com.wipro.crowdfunding.entity.Investment;
import com.wipro.crowdfunding.repo.InvestmentRepository;
import com.wipro.crowdfunding.service.InvestmentService;

@Service
public class InvestmentServiceImpl implements InvestmentService {
	@Autowired
	private InvestmentRepository investmentRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public InvestmentDTO makeInvestment(InvestmentDTO investmentDTO) {
		// write your logic here
		Investment investment=modelMapper.map(investmentDTO,Investment.class);
		Investment investment1=investmentRepo.save(investment);

		return modelMapper.map(investment1,InvestmentDTO.class);
	}

	@Override
	public InvestmentDTO updateInvestment(Long investmentId, InvestmentDTO investmentDTO) {
		Optional<Investment> investment=investmentRepo.findById(investmentId);
		Investment obj=modelMapper.map(investmentDTO, Investment.class);
		if(investment.isPresent()){
			Investment investment1=investment.get();
			investment1.setAmount(obj.getAmount());
			investment1.setId(obj.getId());
			investment1.setInvestorName(obj.getInvestorName());
			investment1.setProject(obj.getProject());
			Investment obj1=investmentRepo.save(investment1);
			return modelMapper.map(obj1, InvestmentDTO.class);
		}
		throw new ResourceNotFoundException("Investment by id not found");
	}
	

	@Override
	public boolean deleteInvestment(Long investmentId) {
		// write your logic here
		Optional<Investment> investment=investmentRepo.findById(investmentId);
		if(investment.isPresent()){
			investmentRepo.delete(investment.get());
			return true;
		}
		throw new ResourceNotFoundException("Investment by id not found");
	}

	@Override
	public InvestmentDTO getInvestmentById(Long investmentId) {
		Optional<Investment> investment=investmentRepo.findById(investmentId);
		if(investment.isPresent()){
		return modelMapper.map(investment.get(), InvestmentDTO.class);
		}
		throw new ResourceNotFoundException("Investment by id not found");
	}
	

	@Override
	public List<InvestmentDTO> getInvestmentsByProjectId(Long projectId) {
		List<Investment> obj=investmentRepo.getInvestmentByProductId(projectId);
		if(obj.isEmpty()){
			throw new ResourceNotFoundException("Investment by Name not found");
		}
		List<InvestmentDTO> list = obj.stream()
				.map(investment1 -> modelMapper.map(investment1, InvestmentDTO.class))
				.toList();
		return list;
	}

	@Override
	public List<InvestmentDTO> getInvestmentsByInvestorName(String investorName) {
		// write your logic here
		List<Investment> investment=investmentRepo.findByName(investorName);
		if(investment.isEmpty()){
			throw new ResourceNotFoundException("Investment by Name not found");
		}
		List<InvestmentDTO> list = investment.stream()
				.map(investment1 -> modelMapper.map(investment1, InvestmentDTO.class))
				.toList();
		return list;
	}
}