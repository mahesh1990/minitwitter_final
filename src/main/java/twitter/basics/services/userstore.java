package twitter.basics.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

/**
 * Created by IntelliJ IDEA.
 * User: gaurav.mi
 * Date: 7/11/11
 * Time: 3:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class userstore {

    private SimpleJdbcTemplate db;
    private final ThreadLocal<Long> userid;

    @Autowired
    public userstore(@Qualifier("userid") ThreadLocal<Long> userid, SimpleJdbcTemplate db)
    {
         this.db=db;
         this.userid=userid;
    }


}
