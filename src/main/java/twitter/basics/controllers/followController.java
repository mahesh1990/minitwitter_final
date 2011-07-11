package twitter.basics.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import twitter.basics.model.follow;
import twitter.basics.services.followstore;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gaurav.mi
 * Date: 7/11/11
 * Time: 1:36 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/*")

public class followController {

    private followstore fs;

    @Autowired
    public followController(followstore fs)
    {
        this.fs=fs;
    }
    @RequestMapping("/following")
    @ResponseBody
    List<follow> following(follow f)
    {

        return fs.listfollowing(f);
    }

    @RequestMapping("/followers")
    @ResponseBody
    List<follow> followers(follow f)
    {

        return fs.listfollowers(f);
    }





}
