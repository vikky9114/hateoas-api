package com.rest.hateoas;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rest.hateoas.controller.ApplicationCtl;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ApplicationCtl.class)
public class HateoasApplicationTests {

	@Test
	public void contextLoads() {
	}

}
