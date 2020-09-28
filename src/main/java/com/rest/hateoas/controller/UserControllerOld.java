package com.rest.hateoas.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.hateoas.model.Comment;
import com.rest.hateoas.model.Like;
import com.rest.hateoas.model.Post;
import com.rest.hateoas.model.UserModel;

@RestController
@RequestMapping("/v1/user")
public class UserControllerOld {
	
	List<UserModel> users = new ArrayList<UserModel>();
	UserModel user1 = new UserModel(1, "vivek", 30);
	UserModel user2 = new UserModel(2, "ram", 25);
	UserModel user3 = new UserModel(3, "kamal", 20);
	List<Post> posts = new ArrayList<Post>();

	@GetMapping("/all")
	private List<UserModel> getAllUsers() {
		System.out.println("tert");

		Link link = WebMvcLinkBuilder.linkTo(UserControllerOld.class).slash(user1.getId()).withSelfRel();
		user1.add(link);
		user2.add(WebMvcLinkBuilder.linkTo(UserControllerOld.class).slash(user2.getId()).withSelfRel());
		user3.add(WebMvcLinkBuilder.linkTo(UserControllerOld.class).slash(user3.getId()).withSelfRel());

		users.add(user1);
		users.add(user2);
		users.add(user3);
		return Arrays.asList(user1, user2, user3);
	}

	@GetMapping("/{id}")
	private UserModel getUserById(@PathVariable("id") Integer id) {

		List<UserModel> userList = users.stream().filter(user -> user.getId().equals(id)).collect(Collectors.toList());
		UserModel user = userList.get(0);
		Link link = WebMvcLinkBuilder.linkTo(UserControllerOld.class).slash(user.getId()).slash("posts")
				.withRel("posts");
		user.add(link);
		return user;
	}

	@GetMapping("/{id}/posts")
	private List<Post> getPostsUserById(@PathVariable("id") Integer id) {
		Post post1 = new Post(1, "My first Post");
		post1.add(WebMvcLinkBuilder.linkTo(UserControllerOld.class).slash(id).slash("posts").slash(post1.getId())
				.withSelfRel());
		posts.add(post1);
		Post post2 = new Post(2, "My Second Post");
		post2.add(WebMvcLinkBuilder.linkTo(UserControllerOld.class).slash(id).slash("posts").slash(post2.getId())
				.withSelfRel());
		posts.add(post2);
		Post post3 = new Post(3, "My Third Post");
		post3.add(WebMvcLinkBuilder.linkTo(UserControllerOld.class).slash(id).slash("posts").slash(post3.getId())
				.withSelfRel());
		posts.add(post3);

		return posts;
	}

	@GetMapping("/{id}/posts/{postId}")
	private Post getPostById(@PathVariable("id") Integer id, @PathVariable("postId") Integer postId) {
		List<Post> postList = posts.stream().filter(post -> post.getId().equals(postId)).collect(Collectors.toList());
		Post post = postList.get(0);
		post.add(WebMvcLinkBuilder.linkTo(UserControllerOld.class).slash(id).slash("posts").slash(post.getId())
				.slash("comments").withRel("comments"));
		post.add(WebMvcLinkBuilder.linkTo(UserControllerOld.class).slash(id).slash("posts").slash(post.getId())
				.slash("likes").withRel("likes"));
		return post;
	}

	@GetMapping("/{id}/posts/{postId}/comments")
	private List<Comment> getCommentsByPostId(@PathVariable("id") Integer id, @PathVariable("postId") Integer postId) {
		List<Comment> comments = new ArrayList<Comment>();
		Comment comment1 = new Comment("first comment", 1);
		comments.add(comment1);
		Comment comment2 = new Comment("Second comment", 1);
		comments.add(comment2);
		Comment comment3 = new Comment("Third comment", 1);
		comments.add(comment3);

		return comments;
	}

	@GetMapping("/{id}/posts/{postId}/likes")
	private Like getLikesByPostId(@PathVariable("id") Integer id, @PathVariable("postId") Integer postId) {
		Like like = new Like(100, 20);
		return like;
	}

}
