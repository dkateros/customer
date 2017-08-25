package gr.dkateros.customer.control;

import javax.ejb.Stateless;

import gr.dkateros.customer.entity.Customer;
import gr.dkateros.util.jee7.data.BaseDao;

@Stateless
public class CustomerDao extends BaseDao<Customer> {
	
	public CustomerDao() {
		super(Customer.class);
	}

}
