package lt.codeacademy.invoice;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import lt.codeacademy.invoice.entities.Customer;
import lt.codeacademy.invoice.entities.Invoice;
import lt.codeacademy.invoice.entities.Item;
import lt.codeacademy.invoice.entities.User;
import lt.codeacademy.invoice.services.CustomerService;
import lt.codeacademy.invoice.services.InvoiceService;
import lt.codeacademy.invoice.services.ItemService;
import lt.codeacademy.invoice.services.UserService;

@SpringBootTest
@AutoConfigureMockMvc
public class ApiControllerTests {

	@MockBean
	private CustomerService customerService;

	@MockBean
	private ItemService itemService;

	@MockBean
	private InvoiceService invoiceService;

	@MockBean
	private UserService userService;

	@Autowired
	private MockMvc mockMvc;

	// CUSTOMERS
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	@Test
	@DisplayName("Should show Customers List")
	@WithMockUser(username = "test@test.lt", roles = { "ADMIN" })
	public void should_show_customers_list() throws Exception {
		mockMvc.perform( get( "/api/v1/customers" ) ).andExpect( status().isOk() );
	}

	@Test
	@DisplayName("Create and Save Customer")
	@WithMockUser(username = "test@test.lt", roles = { "ADMIN" })
	public void should_return_created_customer() throws Exception {
		Customer customer = new Customer();

		customer.setId( 50L );
		customer.setVardas( "Testas" );
		customer.setPavarde( "Testaitis" );
		customer.setEmail( "tetas@testas.lt" );
		customer.setTelNumeris( "59112133" );
		customer.setAdresas( "test 25, 5" );
		customer.setTipas( "privatus" );
		customer.setKlientoStatusas( "aktyvus" );

		when( customerService.addCustomer( any( Customer.class ) ) ).thenReturn( customer );

		mockMvc.perform( MockMvcRequestBuilders.get( "/api/v1/customers" ).param( "Testas", "Testaitis",
				"tetas@testas.lt", "59112133", "test 25, 5", "privatus", "aktyvus" ) )
				.andExpect( MockMvcResultMatchers.status().isOk() );
	}

	@Test
	@DisplayName("Should get and update customer by ID")
	@WithMockUser(username = "test@test.lt", roles = { "ADMIN" })
	public void should_get_and_update_customer_by_id() throws Exception {

		Customer customer = new Customer();
		customer.setId( 50L );
		customer.setVardas( "Testas" );
		customer.setPavarde( "Testaitis" );
		customer.setEmail( "tetas@testas.lt" );
		customer.setTelNumeris( "59112133" );
		customer.setAdresas( "test 25, 5" );
		customer.setTipas( "privatus" );
		customer.setKlientoStatusas( "aktyvus" );

		mockMvc.perform( MockMvcRequestBuilders.get( "/api/v1/customers/50" ).param( "Testas", "Testaitis",
				"tetas@testas.lt", "59112133", "test 25, 5", "privatus", "aktyvus" ) ).andExpect( status().isOk() );
	}

	@Test
	@DisplayName("Should delete customer by ID")
	@WithMockUser(username = "test@test.lt", roles = { "ADMIN" })
	public void should_delete_customer_by_id() throws Exception {

		Customer customer = new Customer();
		customer.setId( 1L );

		mockMvc.perform( MockMvcRequestBuilders.delete( "/api/v1/customers/1" ) ).andExpect( status().isAccepted() );
	}

	// ITEMS
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	@Test
	@DisplayName("Should show Items List")
	@WithMockUser(username = "test@test.lt", roles = { "ADMIN" })
	public void should_show_items_list() throws Exception {
		mockMvc.perform( get( "/api/v1/items" ) ).andExpect( status().isOk() );
	}

	@Test
	@DisplayName("Create and Save Item")
	@WithMockUser(username = "test@test.lt", roles = { "ADMIN" })
	public void should_return_created_item() throws Exception {
		Item item = new Item();

		item.setId( 50L );
		item.setPavadinimas( "kastuvas" );
		item.setAprasymas( "test" );
		item.setGrupe( "Kastuvai" );
		item.setStatusas( "aktyvus" );
		item.setKodas( "21454545" );
		item.setBazineKaina( 10D );

		when( itemService.addItem( any( Item.class ) ) ).thenReturn( item );

		mockMvc.perform( MockMvcRequestBuilders.get( "/api/v1/items" ).param( "kastuvas", "test", "Kastuvai", "aktyvus",
				"21454545", "10D" ) ).andExpect( MockMvcResultMatchers.status().isOk() );
	}

	@Test
	@DisplayName("Should get and update item by ID")
	@WithMockUser(username = "test@test.lt", roles = { "ADMIN" })
	public void should_get_and_update_item_by_id() throws Exception {

		Item item = new Item();
		item.setId( 50L );
		item.setPavadinimas( "kastuvas" );
		item.setAprasymas( "test" );
		item.setGrupe( "Kastuvai" );
		item.setStatusas( "aktyvus" );
		item.setKodas( "21454545" );
		item.setBazineKaina( 10D );

		mockMvc.perform( MockMvcRequestBuilders.get( "/api/v1/items/50" ).param( "kastuvas", "test", "Kastuvai",
				"aktyvus", "21454545", "10D" ) ).andExpect( status().isOk() );
	}

	@Test
	@DisplayName("Should delete Item by ID")
	@WithMockUser(username = "test@test.lt", roles = { "ADMIN" })
	public void should_delete_item_by_id() throws Exception {

		Item item = new Item();
		item.setId( 1L );

		mockMvc.perform( MockMvcRequestBuilders.delete( "/api/v1/items/1" ) ).andExpect( status().isAccepted() );
	}

	// USERS
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	@Test
	@DisplayName("Should show Users List")
	@WithMockUser(username = "test@test.lt", roles = { "ADMIN" })
	public void should_show_users_list() throws Exception {
		mockMvc.perform( get( "/api/v1/users" ) ).andExpect( status().isOk() );
	}

	@Test
	@DisplayName("Create and Save User")
	@WithMockUser(username = "test@test.lt", roles = { "ADMIN" })
	public void should_return_created_user() throws Exception {
		User user = new User();

		user.setId( 50L );
		user.setEmail( "testas@testas.lt" );
		user.setPassword( "123456" );
		user.setRoles( null );
		user.setUsername( "admin" );

		when( userService.addUser( any( User.class ) ) ).thenReturn( user );

		mockMvc.perform( MockMvcRequestBuilders.get( "/api/v1/users" ).param( "testas@testas.lt", "123456", "admin" ) )
				.andExpect( MockMvcResultMatchers.status().isOk() );
	}

	@Test
	@DisplayName("Should get and update user by ID")
	@WithMockUser(username = "test@test.lt", roles = { "ADMIN" })
	public void should_get_and_update_user_by_id() throws Exception {

		User user = new User();

		user.setId( 50L );
		user.setEmail( "testas@testas.lt" );
		user.setPassword( "123456" );
		user.setRoles( null );
		user.setUsername( "admin" );

		mockMvc.perform(
				MockMvcRequestBuilders.get( "/api/v1/users/50" ).param( "testas@testas.lt", "123456", "admin" ) )
				.andExpect( status().isOk() );
	}

	@Test
	@DisplayName("Should delete User by ID")
	@WithMockUser(username = "test@test.lt", roles = { "ADMIN" })
	public void should_delete_user_by_id() throws Exception {

		User user = new User();
		user.setId( 1L );

		mockMvc.perform( MockMvcRequestBuilders.delete( "/api/v1/users/1" ) ).andExpect( status().isAccepted() );
	}

	// USERS
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	@Test
	@DisplayName("Should show Invoices List")
	@WithMockUser(username = "test@test.lt", roles = { "ADMIN" })
	public void should_show_invoices_list() throws Exception {
		mockMvc.perform( get( "/api/v1/invoices" ) ).andExpect( status().isOk() );
	}

	@Test
	@DisplayName("Create and Save Invoice")
	@WithMockUser(username = "test@test.lt", roles = { "ADMIN" })
	public void should_return_created_invoice() throws Exception {
		Invoice invoice = new Invoice();
		;

		invoice.setId( 50L );
		invoice.setCustomerId( null );
		invoice.setInvoiceItems( null );
		invoice.setInvoiceNumber( "LT123" );
		invoice.setMyDate( LocalDate.now() );

		when( invoiceService.addInvoice( any( Invoice.class ) ) ).thenReturn( invoice );

		mockMvc.perform(
				MockMvcRequestBuilders.get( "/api/v1/invoice" ).param( "LT123", "50L", "2022-10-12", "test", "test" ) )
				.andExpect( MockMvcResultMatchers.status().isOk() );
	}

	@Test
	@DisplayName("Should get and update invoice by ID")
	@WithMockUser(username = "test@test.lt", roles = { "ADMIN" })
	public void should_get_and_update_invoice_by_id() throws Exception {

		Invoice invoice = new Invoice();

		invoice.setId( 50L );
		invoice.setCustomerId( null );
		invoice.setInvoiceItems( null );
		invoice.setInvoiceNumber( "LT123" );
		invoice.setMyDate( LocalDate.now() );

		mockMvc.perform( MockMvcRequestBuilders.get( "/api/v1/invoices/50" ).param( "LT123", "50L", "2022-10-12",
				"test", "test" ) ).andExpect( status().isOk() );
	}

	@Test
	@DisplayName("Should delete Invoice by ID")
	@WithMockUser(username = "test@test.lt", roles = { "ADMIN" })
	public void should_delete_invoice_by_id() throws Exception {

		Invoice invoice = new Invoice();
		invoice.setId( 1L );

		mockMvc.perform( MockMvcRequestBuilders.delete( "/api/v1/invoices/1" ) ).andExpect( status().isAccepted() );
	}

}
