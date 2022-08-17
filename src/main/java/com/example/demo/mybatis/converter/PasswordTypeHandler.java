package com.example.demo.mybatis.converter;

import com.example.demo.encrypt.AlgorithmFactory;
import com.example.demo.encrypt.AlgorithmTypes;
import com.example.demo.mybatis.enums.UserNameEnum;
import com.example.demo.mybatis.po.Password;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedJdbcTypes(JdbcType.VARCHAR)
public class PasswordTypeHandler extends BaseTypeHandler<Password> {


    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Password parameter, JdbcType jdbcType) throws SQLException {
        String encryptedPassword = AlgorithmFactory.choose(AlgorithmTypes.Palindrome).encrypt(parameter.getPassword());
        ps.setString(i, encryptedPassword);
    }

    @Override
    public Password getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String encryptedPassword = rs.getString(columnName);
        return new Password(AlgorithmFactory.choose(AlgorithmTypes.Palindrome).decrypt(encryptedPassword));
    }

    @Override
    public Password getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String encryptedPassword = rs.getString(columnIndex);
        return new Password(AlgorithmFactory.choose(AlgorithmTypes.Palindrome).decrypt(encryptedPassword));
    }

    @Override
    public Password getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String encryptedPassword = cs.getString(columnIndex);
        return new Password(AlgorithmFactory.choose(AlgorithmTypes.Palindrome).decrypt(encryptedPassword));
    }
}
