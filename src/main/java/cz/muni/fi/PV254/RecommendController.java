package cz.muni.fi.PV254;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * User: PC
 * Date: 26. 11. 2014
 * Time: 11:49
 */

@Controller
@RequestMapping("/")
public class RecommendController {

    @Autowired
    Recommender recommender;

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public String create(@PathVariable int userId, Model model) {
        model.addAttribute("items", recommender.recommend(userId, 10));
        return "main";
    }
}
