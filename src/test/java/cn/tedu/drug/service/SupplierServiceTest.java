package cn.tedu.drug.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.drug.entity.Supplier;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SupplierServiceTest {
	
	@Autowired
	private ISupplierService service;
	
	@Test
	public void testAddnew(){
		Supplier supplier = new Supplier();
		supplier.setUsername("齐齐哈尔制药厂");
		supplier.setPhone("18000001111");
		supplier.setEmail("182310@autozi.com");
		supplier.setAddress("内蒙古齐齐哈尔市");
		supplier.setCardBank("河北银行");
		supplier.setCard("622700003000528");
		service.addnew(supplier);
		System.err.println("OK");
	}
	
	@Test
	public void testChangeInfo(){
		Supplier supplier = new Supplier();
		supplier.setUsername("厦门制药厂");
		supplier.setPhone("1855555555");
		supplier.setEmail("66666@autozi.com");
		supplier.setAddress("河北省行唐县");
		supplier.setCardBank("中国银行");
		supplier.setCard("8888888888888");
		service.changeInfo(supplier);
		System.err.println("OK");
	}
}










