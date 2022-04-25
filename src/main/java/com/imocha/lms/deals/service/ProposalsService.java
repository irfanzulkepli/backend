package com.imocha.lms.deals.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.imocha.lms.deals.repositories.ProposalsRepository;
import com.imocha.lms.entities.Proposals;

@Service
public class ProposalsService {

	@Autowired
	private ProposalsRepository proposalsRepository;

	@Lazy
	@Autowired
	private DealsService dealsService;

	public Set<Long> getDealIdsFromProposalWithDeals() {
		List<Proposals> proposalList = proposalsRepository.findAll();

		Set<Long> ids = new HashSet<Long>();
		for (Proposals proposal : proposalList) {
			ids.add(proposal.getDeals().getId());
		}

		return ids;
	}

	public Set<Long> getDealIdsFromProposalWithoutDeals() {
		Set<Long> dealsWithProposals = this.getDealIdsFromProposalWithDeals();
		Set<Long> ids = dealsService.getAllIds();
		for (Long id : dealsWithProposals) {
			ids.remove(id);
		}
		return ids;
	}
}
