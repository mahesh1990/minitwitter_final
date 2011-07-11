package twitter.basics.model;

import javax.swing.tree.RowMapper;
import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import org.springframework.jdbc.*;
/**
 * Created by IntelliJ IDEA.
 * User: gaurav.mi
 * Date: 7/9/11
 * Time: 9:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class tweet {


    private String content;
    private int tweetid;
    private int uid;
    private Timestamp tweettime;

    public final static org.springframework.jdbc.core.RowMapper<tweet> rowMapper=new org.springframework.jdbc.core.RowMapper<tweet>() {
        @Override
        public  tweet mapRow(ResultSet rs,int i) throws SQLException{
            return new tweet(rs);
        }
    };
    public tweet()
    {}

    public tweet(ResultSet rs)
    {
        this.content=rs.content;
        this.tweetid=rs.tweetid;
        this.tweettime=rs.tweettime;
        this.uid=rs.uid;
    }

    public void setuid(int uid)
    {
        this.uid=uid;
    }

    public void settweettime(Timestamp ts)
    {
        this.tweettime=ts;
    }
    public void setcontent(String content)
    {
        this.content=content;
    }

    public  int getuid()
    {
        return this.uid;
    }
    public Timestamp gettweettime()
    {
        return this.tweettime;
    }
    public String getcontent()
    {
        return this.content;
    }
    public int gettweetid()
    {
        return this.tweetid;
    }
}
