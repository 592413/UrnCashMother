package com.skyflight.searchCustomModel;

import com.skyflight.model.Authentication;

public class SearchInputRequest {
	private Authentication Authentication;
	private SearchInput SearchInput;
	
	
	
	
	
	public SearchInputRequest() {
		super();
	}
	
	
	
	public SearchInputRequest(com.skyflight.model.Authentication authentication,
			com.skyflight.searchCustomModel.SearchInput searchInput) {
		super();
		Authentication = authentication;
		SearchInput = searchInput;
	}



	public Authentication getAuthentication() {
		return Authentication;
	}
	public void setAuthentication(Authentication authentication) {
		Authentication = authentication;
	}



	public SearchInput getSearchInput() {
		return SearchInput;
	}



	public void setSearchInput(SearchInput searchInput) {
		SearchInput = searchInput;
	}
	
	

}
