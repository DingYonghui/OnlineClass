package test;

import net.sf.json.JSON;

import org.junit.Test;

import com.mori.Exception.LessonException;
import com.mori.service.LessonService;

public class testLessonService {
	LessonService ls = new LessonService();

	@Test
	public void test() throws LessonException {
		JSON json=ls.returnDetailLesson("C3069");
		System.out.print(json.toString());
	}
}
