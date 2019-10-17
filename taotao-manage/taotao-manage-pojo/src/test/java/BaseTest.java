import com.taotao.manage.pojo.TestStudent;
import org.junit.Test;

import java.util.Date;

public class BaseTest {
    @Test
    public void test(){
        TestStudent student=new TestStudent()
                .setName("李明")
                .setAge(78);
        Object data=student.setUpdated(new Date());
        System.out.println(student.getUpdated()+""+student+data);
    }
}
