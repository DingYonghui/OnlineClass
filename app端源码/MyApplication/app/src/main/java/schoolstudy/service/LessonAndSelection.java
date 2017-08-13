package schoolstudy.service;

import java.util.List;

import schoolstudy.view.entity.Course;
import schoolstudy.view.entity.Section;

/**
 * Created by blackdog on 2015/10/28.
 */
public interface LessonAndSelection {
    public List<Course> getLesson(String department) throws Exception;
    public List<Section> getSectionAndPart(String lessonId) throws Exception;
}
