package twitter.basics.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.web.servlet.ModelAndView;
import twitter.basics.model.tweet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: gaurav.mi
 * Date: 7/9/11
 * Time: 9:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class tweetstore {

    private SimpleJdbcTemplate db;
    private final ThreadLocal<Long> userid;
    @Autowired
    public tweetstore(@Qualifier ("userid") ThreadLocal<Long> userid, SimpleJdbcTemplate db)
    {
         this.db=db;
         this.userid=userid;
    }

    public List<tweet> list()
    {
            return db.query("Select * from tweets,users,follow where folower=? and follow.following=tweets.userid and tweets.userid=users.uid  ",tweet.rowMapper,userid.get() );

    }

    public tweet insert(tweet addtweet)
    {

           db.update("insert into tweets (content,userid,tweettime) values(?,?,?)",addtweet.getcontent(),userid.get(),addtweet.gettweettime());
           HashMap<String,Object> ht2=(HashMap)db.queryForMap("Select max(tweetid)as mid from tweets where userid=?",userid.get());
           return db.queryForObject("Select *  from tweets where tweetid=?",tweet.rowMapper,(Integer)ht2.get("mid"));
    }





}
