package twitter.basics.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import twitter.basics.model.follow;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: gaurav.mi
 * Date: 7/11/11
 * Time: 2:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class followstore {

    private SimpleJdbcTemplate db;
    private final ThreadLocal<Long> userid;

    @Autowired
    public followstore(@Qualifier("userid") ThreadLocal<Long> userid, SimpleJdbcTemplate db)
    {
         this.db=db;
         this.userid=userid;
    }


    public List<follow> listfollowing(follow f)
    {
            return db.query("Select uid, username from users where uid in (Select following from follow where follower= ? )",follow.rowMapper,f.getuid());
    }
    public List<follow> listfollowers(follow f)
    {
                return db.query("Select uid, username from users where uid in (Select following from follow where following= ? )",follow.rowMapper,f.getuid());
    }
    public boolean isfollowing(follow f)
    {
        boolean status;
        Map<String,Object> mm=null;
        try
        {
            mm=db.queryForMap("Select * from follow where follower=? and following=?", userid.get(),f.getuid());
        }
        catch(Exception e)
        {

        }
        if(mm!=null)
            status=false;
        else
            status=true;
        return status;
    }
    public void follow(follow f)
    {
            db.update("insert into follow(follower,following) values(?,?)",userid.get(),f.getuid());
    }
    public void unfollow(follow f)
    {
            db.update("delete from follow where follower=? and following=?",userid,f.getuid());
    }
}
