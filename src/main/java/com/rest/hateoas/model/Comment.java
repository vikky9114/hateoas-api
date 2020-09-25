package com.rest.hateoas.model;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Comment extends RepresentationModel<Comment> {
	private String text;
	private Integer commentedBy;

	public Comment() {
		super();
	}

	public Comment(String text, Integer commentedBy) {
		super();
		this.text = text;
		this.commentedBy = commentedBy;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getCommentedBy() {
		return commentedBy;
	}

	public void setCommentedBy(Integer commentedBy) {
		this.commentedBy = commentedBy;
	}

}
