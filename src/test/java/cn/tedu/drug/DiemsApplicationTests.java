package cn.tedu.drug;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DiemsApplicationTests {
	 private Logger logger=LoggerFactory.getLogger(Application.class);
	@Test
	public void contextLoads() {
		logger.info("测试{}，日志级别{}，输出{}", "demo1", "info", "info level log");

		logger.error("测试{}，日志级别{}，输出{}", "error", "error", "error level log");
	}

}
