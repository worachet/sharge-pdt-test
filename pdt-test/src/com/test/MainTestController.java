package com.test;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class MainTestController {
	public static void main(String[] args) {
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		
		SessionFactory factory=cfg.buildSessionFactory();
		
		//creating session object
		Session session=factory.openSession();
		
		Query query = session.createQuery(" from CustomerTemp where STATUS='0' ");
		List<CustomerTemp> list = query.list();
		System.out.println("----------------------------------------------"+list);

		if(list != null){
			for(int j=0; j < list.size(); j++){
				Transaction t =session.beginTransaction();
				Customer customer = new Customer();
				customer.setId(list.get(j).getId());
				customer.setCid_card_no(list.get(j).getCid_card_no());
				customer.setTax_id(list.get(j).getTax_id());
			
				session.persist(customer);//persisting the object
				
				t.commit();//transaction is commited
				
				System.out.println("Customer--------------------------------------------");
			}
			for(int j=0; j < list.size(); j++){
				Transaction t =session.beginTransaction();
				
				CustomerHistory customerHistory = new CustomerHistory();
				customerHistory.setId(list.get(j).getId());
				customerHistory.setRecord_amount(String.valueOf(list.get(j).getId()));
				customerHistory.setUpload_date(new Date());
				
				session.persist(customerHistory);//persisting the object
				
				t.commit();//transaction is commited
				
				System.out.println("CustomerHistory--------------------------------------------");
			}
			for(int j=0; j < list.size(); j++){
				Transaction t =session.beginTransaction();
				Query update = session.createQuery(" UPDATE CustomerTemp SET STATUS=:statusIn WHERE STATUS=:statusWh ");
				update.setParameter("statusIn", "1");
				update.setParameter("statusWh", "0");
				update.executeUpdate();
				
				t.commit();//transaction is commited
				
				System.out.println("CustomerTemp--------------------------------------------");
			}
		}
		session.close();
	} 

}
