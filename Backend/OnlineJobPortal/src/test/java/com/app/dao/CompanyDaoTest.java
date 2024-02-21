package com.app.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.app.entities.Company;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class CompanyDaoTest {
	
	@Autowired
	private CompanyDao compDao;

	@Test
	void testSaveCompanies() {
		List<Company> list=List.of(new Company("Google", "google@gmial.com", "google123"),
				new Company("Accenture", "accenture@gmial.com", "accenture123"));
		List<Company> list2=compDao.saveAll(list);
		assertEquals(4,list2.size());
	}

}
