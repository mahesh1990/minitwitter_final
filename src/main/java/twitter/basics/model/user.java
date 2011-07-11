package twitter.basics.model;

import org.hsqldb.Row;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: gaurav.mi
 * Date: 7/9/11
 * Time: 9:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class user {

    private int uid;
    private String userid;
    private String password;
    private String fname;
    private String lname;
    private String email;

    public final static RowMapper<user> rowmapper=new RowMapper<user>() {
        @Override
        public user mapRow(ResultSet resultSet, int i) throws SQLException {

            return new user(resultSet);  //To change body of implemented methods use File | Settings | File Templates.
        }
    };

    public user()
    {}
    public user(ResultSet rs)
    {
        this.uid=rs.uid;
        this.userid=
    }

}
