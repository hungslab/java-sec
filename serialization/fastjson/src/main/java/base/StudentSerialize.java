package base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;

// 最开始的序列化 demo
public class StudentSerialize {

    public static void main(String[] args) {
//        StudentSerialize();
//        StudentUnserialize02();
//        StudentUnserialize();
        StudentUnserialize03();
    }
    public static void StudentSerialize() {
        Student student = new Student();
        student.setName("Hungs");
//        student.setAge(6);
        String jsonString = JSON.toJSONString(student, SerializerFeature.WriteClassName);
        System.out.println(jsonString);
    }

    public static void StudentUnserialize() {
        String jsonString = "{\"@type\":\"base.Student\",\"age\":6,\"name\":\"Hungs\"}";
        Student student = JSON.parseObject(jsonString, Student.class, Feature.SupportNonPublicField);
        System.out.println(student);
        System.out.println(student.getClass().getName());
    }
    public static void StudentUnserialize02() {
        String jsonString = "{\"@type\":\"base.Student\",\"age\":6,\"name\":\"Hungs\"}";
        Student student = JSON.parseObject(jsonString, Student.class, Feature.SupportNonPublicField);
        System.out.println(student);
        System.out.println(student.getClass().getName());
    }

    public static void StudentUnserialize03() {
        String jsonString ="{\"@type\":\"base.Student\",\"age\":6," +
                "\"name\":\"Hungs\",\"address\":\"china\",\"properties\":{}}";
        Object obj = JSON.parse(jsonString, Feature.SupportNonPublicField);
        // 或以下语句，输出结果一致
        //JSONObject obj = JSON.parseObject(jsonString);
        System.out.println(obj);
        System.out.println(obj.getClass().getName());
    }
}