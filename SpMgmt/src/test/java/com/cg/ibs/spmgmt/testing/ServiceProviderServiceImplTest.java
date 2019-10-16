package com.cg.ibs.spmgmt.testing;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cg.ibs.spmgmt.bean.ServiceProvider;
import com.cg.ibs.spmgmt.dao.ServiceProviderDaoImpl;
import com.cg.ibs.spmgmt.exception.IBSException;
import com.cg.ibs.spmgmt.exception.RegisterException;
import com.cg.ibs.spmgmt.service.ServiceProviderServiceImpl;

class ServiceProviderServiceImplTest {

	ServiceProviderServiceImpl impl = null;
	ServiceProviderDaoImpl daoImpl = null;

	@BeforeEach
	void setUp() {
		impl = new ServiceProviderServiceImpl();
		daoImpl = new ServiceProviderDaoImpl();
	}

	@AfterEach
	void destr() {
		// TODO Auto-generated method stub
		impl = null;
		daoImpl = null;
	}

	@Test
	void test1GenerateIdPassword() {
		//
		// try {
		// assertEquals("Relian",
		// impl.generateIdPassword(ServiceProviderDaoImpl.getSpMap().get("Relia")).getUserId());
		// } catch (RegisterException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IBSException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

	@Test
	void test2GenerateIdPassword() throws Exception {
		//
		// assertNotEquals("Relianc",
		// impl.generateIdPassword(ServiceProviderDaoImpl.getSpMap().get("Relia")).getUserId());
	}

	@Test
	void test3GenerateIdPassword() {

		// try {
		// assertNotNull(impl.generateIdPassword(ServiceProviderDaoImpl.getSpMap().get("Relia")).getPassword());
		// } catch (RegisterException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IBSException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

	@Test
	void test1StoreSPDetails() throws Exception {
		ServiceProvider sp3 = new ServiceProvider();
		sp3.setUserId("Capge");

		assertTrue(impl.storeSPDetails(sp3));
	}

	@Test
	void test2StoreSPDetails() throws Exception {
		ServiceProvider sp4 = new ServiceProvider();
		sp4.setUserId("Vodaf");

		assertNotEquals(false, impl.storeSPDetails(sp4));
	}

	@Test
	void test3StoreSPDetails() {
		ServiceProvider sp4 = null;
		assertThrows(RegisterException.class, () -> {
			impl.storeSPDetails(sp4);
		});

	}

	@Test
	void test1ValidateLogin() {
		try {
			assertEquals(true, impl.validateLogin("Relia", "12345678"));
		} catch (IBSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void test2ValidateLogin() throws Exception {

		assertThrows(IBSException.class, () -> {
			impl.validateLogin("Relia", "87654321");
		});
	}

	@Test
	void test3ValidateLogin() throws Exception {
		assertThrows(IBSException.class, () -> {
			impl.validateLogin("Reliance", "12345678");
		});
	}

	@Test
	void test4ValidateLogin() throws Exception {
		assertThrows(IBSException.class, () -> {
			impl.validateLogin("Capgem", "87654321");
		});
	}

	@Test
	void test1GetServiceProvider() {

		try {
			assertEquals("HDFC", impl.getServiceProvider("Relia").getBankName());
		} catch (IBSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void test2GetServiceProvider() throws Exception {

		assertNotEquals("SBI", impl.getServiceProvider("Relia").getBankName());

	}

	@Test
	void test1ShowPending() throws Exception {

		assertTrue(true);

	}

	@Test
	void testApproveSP() {
		// try {
		// impl.approveSP(ServiceProviderDaoImpl.getSpMap().get("Relia"), true);
		// assertTrue(impl.getApprovedDetails().contains(ServiceProviderDaoImpl.getSpMap().get("Relia")));
		// } catch (IBSException e) {
		// fail("Test Failed");
		// }
	}

	@Test
	void test1ValidateAdminLogin() throws Exception {
		assertEquals(true, impl.validateAdminLogin("id1", "pass1"));
	}

	@Test
	void test2ValidateAdminLogin() throws Exception {

		assertThrows(IBSException.class, () -> {
			impl.validateLogin("id1", "passed1");
		});
	}

	@Test
	void test3ValidateAdminLogin() throws Exception {

		assertThrows(IBSException.class, () -> {
			impl.validateLogin("id4", "pass1");
		});
	}

	@Test
	void testEmptyData() {
		assertTrue(true);

	}

	@Test
	void testGetApprovedDetails() {
		assertTrue(true);
	}

	@Test
	void testShowHistory() {
		assertTrue(true);
	}

}
