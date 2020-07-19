package com.abc.lib;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.boot.test.context.SpringBootTest;

import com.abc.lib.test.BookServiceImplTest;
import com.abc.lib.test.MemberServiceImplTest;

@RunWith(Suite.class)
@SpringBootTest
@SuiteClasses(value= {BookServiceImplTest.class,MemberServiceImplTest.class})
public class LibraryAppApplicationTests {

	@Test
	public void contextLoads() {
	}

}
