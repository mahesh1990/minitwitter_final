package twitter.basics.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: gaurav.mi
 * Date: 7/9/11
 * Time: 9:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserController {
    private SimpleJdbcTemplate db;
    private userstore
    @Autowired
    public UserController(SimpleJdbcTemplate db)
    {
        this.db=db;
    }


    @RequestMapping(value="/")
    public String def(HttpSession session) {
        return "login";

    }

    @RequestMapping(value="/login")
    ModelAndView helloSpringLoginget(HttpSession session) {
        return new ModelAndView();

    }

    @RequestMapping("/logout")
    ModelAndView helloSpringLogoutget(HttpSession session) {
        session.setAttribute("uid",null);
        ModelAndView mv=new ModelAndView();
        mv.setViewName("redirect:/");
        return mv;

    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    ModelAndView helloSpringLoginPost(@RequestParam final String userid, final String password, HttpSession session) {

        try
        {
            Map<String,Object> mp= db.queryForMap("Select password from users where username=?",userid);
            if(!mp.isEmpty() && (mp.get("password").equals(password)))
            {
                Map<String,Object> mp2= db.queryForMap("Select uid from users where username=?",userid);

                session.setAttribute("uid",mp2.get("uid"));
                session.setAttribute("username",userid);
                ModelAndView mv=new ModelAndView();
                mv.addObject("uid",userid);
                mv.setViewName("redirect:/home");
                return mv;
            }
            else
            {
                ModelAndView mv=new ModelAndView();
                mv.addObject("msg","Try again");
                return mv;
            }
        }catch(Exception e)
        {
            ModelAndView mv=new ModelAndView();
            mv.addObject("msg","Try again");
            return mv;
        }

    }

    @RequestMapping(value="/users/*")
    ModelAndView userInfo(HttpSession session,HttpServletRequest request)
    {

        int userid=(Integer)session.getAttribute("uid");
        String url=request.getServletPath();// .getRequestURL().toString();
        String username=url.substring(7);
        System.out.println(username);
        ModelAndView mv=new ModelAndView("user");
        Map<String,Object> m=db.queryForMap("Select * from users where username=?",username);
        System.out.println(m.get("uid"));
        Map<String,Object> mm=null;
        if(userid != (Integer)m.get("uid"))
        {
            try
            {
               mm=db.queryForMap("Select * from followController where follower=? and following=?", userid, m.get("uid"));
            }catch(Exception e)
            {}
            boolean status=
                  if(mm!=null)
                                   status="Unfollow";
                        else
                            status="followController";
                        System.out.println(status);
                        mv.addObject(m);
                        mv.addObject("status",status);
                    }
                    mv.addObject("name",m.get("username"));
                    mv.addObject("fuserid",m.get("uid"));
                    mv.addObject("fname",m.get("fname"));
                    System.out.println(m);
                    return mv;

                    ModelAndView mv=new ModelAndView();
                    mv.setViewName("redirect:/login");
                    return mv;
    }


}
