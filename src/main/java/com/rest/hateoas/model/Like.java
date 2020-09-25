package com.rest.hateoas.model;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Like extends RepresentationModel<Like> {

	private Integer totalLike;
	private Integer totalUnLike;

	public Like() {
		super();
	}

	public Like(Integer totalLike, Integer totalUnLike) {
		super();
		this.totalLike = totalLike;
		this.totalUnLike = totalUnLike;
	}

	public Integer getTotalLike() {
		return totalLike;
	}

	public void setTotalLike(Integer totalLike) {
		this.totalLike = totalLike;
	}

	public Integer getTotalUnLike() {
		return totalUnLike;
	}

	public void setTotalUnLike(Integer totalUnLike) {
		this.totalUnLike = totalUnLike;
	}

}
