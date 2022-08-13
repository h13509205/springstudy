package com.example.demo.mybatis.converter;

import com.example.demo.mybatis.enums.UserNameEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedJdbcTypes(JdbcType.VARCHAR)
public class UserNameTypeHandler extends BaseTypeHandler<UserNameEnum> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, UserNameEnum parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.name());
    }

    @Override
    public UserNameEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String originalUserName = rs.getString(columnName);
        return UserNameEnum.getEnum(originalUserName);
    }

    @Override
    public UserNameEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String originalUserName = rs.getString(columnIndex);
        return UserNameEnum.getEnum(originalUserName);
    }

    @Override
    public UserNameEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String originalUserName = cs.getString(columnIndex);
        return UserNameEnum.getEnum(originalUserName);
    }
}
