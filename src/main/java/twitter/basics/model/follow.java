package twitter.basics.model;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: gaurav.mi
 * Date: 7/11/11
 * Time: 1:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class follow {

    private String userid;
    private int uid;

     public final static org.springframework.jdbc.core.RowMapper<follow> rowMapper=new org.springframework.jdbc.core.RowMapper<follow>() {
        @Override
        public  follow mapRow(ResultSet rs,int i) throws SQLException {
            return new follow(rs);
        }
    };

    public follow()
    {}

    public follow(ResultSet rs)
    {
        this.userid=rs.userid;
        this.uid=rs.uid;
    }

    public String getuserid()
    {
        return this.userid;
    }
    public int getuid()
    {
        return this.uid;
    }
    public void setuserid(String userid)
    {
        this.userid=userid;
    }
    public void setuid(int uid)
    {
        this.uid=uid;
    }

}
