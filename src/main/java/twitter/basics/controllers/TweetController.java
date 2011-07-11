package twitter;

import java.sql.Timestamp;
import java.util.*;
import com.mysql.jdbc.Statement;
import com.sun.corba.se.spi.presentation.rmi.IDLNameTranslator;
import com.sun.org.apache.bcel.internal.generic.Select;
import org.apache.taglibs.standard.tag.rt.core.RedirectTag;
import org.omg.DynamicAny._DynAnyFactoryStub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.tags.form.SelectTag;
import twitter.basics.model.tweet;
import twitter.basics.services.followstore;
import twitter.basics.services.tweetstore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.sql.Timestamp;
import java.util.concurrent.Callable;

/**
 * Created by IntelliJ IDEA.
 * User: gaurav.mi
 * Date: 7/9/11
 * Time: 9:12 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class TweetController {

    private tweetstore ts;

    @Autowired
    public TweetController(tweetstore ts)
    {
        this.ts=ts;
    }


    @RequestMapping(value= {"/","/home"})
    List<tweet> helloSpringGet(HttpSession session) {
        return ts.list();
    }


    @RequestMapping("/home/createtweet")
    @ResponseBody
    tweet createtweet(@RequestParam tweet t, HttpSession session)
    {
            Calendar c=Calendar.getInstance();
            Date now=c.getTime();
            Timestamp tst=new Timestamp(now.getTime());
            t.settweettime(tst);
            return ts.insert(t);
    }




    @RequestMapping(value = "/todo/follow.json")
    @ResponseBody void follow(@RequestParam final int fud,HttpSession session)
    {
        System.out.println("@cfollow ");
        if(session.getAttribute("uid")!=null)
        {
            int userid=(Integer)session.getAttribute("uid");
            db.update("insert into followController(follower,following) values(?,?)",userid,fud);
            System.out.println("@cfollowed ");

        }

    }

    @RequestMapping(value = "/todo/unfollow.json")
    @ResponseBody void unfollow(@RequestParam final String fud,HttpSession session)
    {
        System.out.println("@cunfollow " + fud);
        int fid=Integer.parseInt(fud);

        if(session.getAttribute("uid")!=null)
        {
            int userid=(Integer)session.getAttribute("uid");
            db.update("delete from followController where follower=? and following=?",userid,fid);
            System.out.println("@cunfollowed ");

        }

    }


}
