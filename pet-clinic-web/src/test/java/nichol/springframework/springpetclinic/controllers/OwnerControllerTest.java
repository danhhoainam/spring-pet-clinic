package nichol.springframework.springpetclinic.controllers;

import nichol.springframework.springpetclinic.model.Owner;
import nichol.springframework.springpetclinic.services.OwnerService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

	@Mock
	OwnerService ownerService;

	@InjectMocks
	OwnerController ownerController;

	@Mock
	Model model;

	Set<Owner> owners;

	MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		owners = new HashSet<>();
		owners.add(Owner.builder().id(1L).build());
		owners.add(Owner.builder().id(2L).build());

		mockMvc = MockMvcBuilders
				.standaloneSetup(ownerController)
				.build();
	}

	/**
	 * verify model.addAttribute call 1 time
	 * verify service.findAll call 1 time
	 * verify return string model
	 * @throws Exception
	 */
	@Test
	void test_listOwners_shouldReturnViewName_andModelAddAttribute_andStatusIsOk() throws Exception {

		// given
		when(ownerService.findAll()).thenReturn(owners);

		// when - then
		mockMvc.perform(get("/owners"))
				.andExpect(status().isOk())
				.andExpect(view().name("owners/index"))
				.andExpect(model().attribute("owners", Matchers.hasSize(2)));
	}

	/**
	 * verify model.addAttribute call 1 time
	 * verify service.findAll call 1 time
	 * verify return string model
	 * @throws Exception
	 */
	@Test
	void test_listOwners_shouldReturnViewName_andModelAddAttribute_andStatusIsOk_oldWay() {

		// given - data is set up in setUp()
		when(ownerService.findAll()).thenReturn(owners);
		ArgumentCaptor<Set<Owner>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

		// when
		String viewName = ownerController.listOwners(model);

		// then
		assertEquals("owners/index", viewName);
		verify(model, times(1)).addAttribute(eq("owners"), argumentCaptor.capture());
		verify(ownerService, times(1)).findAll();

		Set<Owner> setOwnerInController = argumentCaptor.getValue();
		assertNotNull(setOwnerInController);
		assertEquals(2, setOwnerInController.size());
	}

	/**
	 * verify get path owners/find
	 * verify status is ok
	 * verify view name is correct
	 * verify no interaction to ownerService
	 * @throws Exception
	 */
	@Test
	void test_findOwners_shouldReturnViewName_andOwnerServiceZeroInteration_andStatusIsOk() throws Exception {

		// when - then
		mockMvc.perform(get("/owners/find"))
				.andExpect(status().isOk())
				.andExpect(view().name("notImplemented"));

		verifyZeroInteractions(ownerService);
	}

	/**
	 * verify get path owners/find
	 * verify status is ok
	 * verify view name is correct
	 * verify no interaction to ownerService
	 * @throws Exception
	 */
	@Test
	void test_findOwners_shouldReturnViewName_andOwnerServiceZeroInteration_andStatusIsOk_oldWay() {

		// when
		String viewName = ownerController.findOwners();

		assertEquals("notImplemented", viewName);

		verifyZeroInteractions(ownerService);
	}
}