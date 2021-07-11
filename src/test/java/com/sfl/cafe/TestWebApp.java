package com.sfl.cafe;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.sfl.cafe.model.User;
import com.sfl.cafe.model.enums.UserType;
import com.sfl.cafe.repository.UserRepository;
import com.sfl.cafe.service.ManagerService;
import com.sfl.cafe.service.WaiterService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

public class TestWebApp extends CafeApplicationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;


	private UserRepository userRepository;
	private WaiterService waiterService;
	private ManagerService managerService;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		userRepository = webApplicationContext.getBean(UserRepository.class);
		managerService = webApplicationContext.getBean(ManagerService.class);
		waiterService = webApplicationContext.getBean(WaiterService.class);

	}

	@Test
	public void testHomePage() throws Exception {
		mockMvc.perform(get("/home"))
				.andExpect(status().isOk())
				.andExpect(content().contentType("text/html;charset=UTF-8"));
	}

	@Test
	public void findFirstUserByCommandLineRunner(){
		User userByUsername = userRepository.findUserByUsername("manager1");
		assert (userByUsername!=null);
	}

// WaiterService Test
	@Test
	public void getAllWaiter(){
		List<User> allWaiters = waiterService.getAllWaiters();
		assert (allWaiters!=null);
		assert (allWaiters.size()>0);
	}

	//ManagerService test

	@Test
	public void addNewUserTest(){
		User test1 = userRepository.findUserByUsername("test1");
		boolean b = true;
		if(test1==null)
		  b = managerService.addNewUser(User.builder()
				.username("test1")
				.name("test1")
				.password("pass")
				.type(UserType.WAITER)
				.build());
		assert (b==true);
		 b = managerService.addNewUser(User.builder()
				.username("test1")
				.name("test11")
				.password("pass1")
				.type(UserType.WAITER)
				 .build());
		 assert (b==false);
	}

}