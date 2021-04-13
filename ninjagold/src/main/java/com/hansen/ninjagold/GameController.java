package com.hansen.ninjagold;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.TreeMap;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GameController {

	@RequestMapping("/")
	public String index(HttpSession session, Model model) {
		if (session.getAttribute("gold") == null) {
//			Integer gold = (Integer) session.getAttribute("gold");
			session.setAttribute("gold", 0);
		}
		if (session.getAttribute("activities") == null) {
			ArrayList<String> activities = new ArrayList<String>();
			session.setAttribute("activities", activities);
		}
		
		LinkedHashMap<String, String> locations = new LinkedHashMap<String, String>();
		locations.put("Farm", "earns 10-20 gold pieces");
		locations.put("Cave", "earns 5-10 gold pieces");
		locations.put("House", "earns 2-5 gold pieces");
		locations.put("Casino", "earns/takes 0-50 gold pieces");
		
		model.addAttribute("locations", locations);			
		
		return "index.jsp";
	}
	
	@RequestMapping(value = "/processGold", method = RequestMethod.POST)
	public String processGold(HttpSession session,
			@RequestParam(value = "location.key") String location,
			@RequestParam(value = "location.value") String activity) {
		
		session.setAttribute("location", location);
		
		System.out.println(location);
		
		Integer newGold = (Integer) session.getAttribute("gold");
		ArrayList<String> activitList = (ArrayList<String>) session.getAttribute("activities");
		Random random = new Random();
		
		if (session.getAttribute("location").equals("Farm")) {
			Integer goldAmount = random.nextInt((20-10)+1)+10;
			newGold += goldAmount;
			session.setAttribute("gold", newGold);
			activitList.add("%d gold earned from %s".formatted(goldAmount, session.getAttribute("location")));
			session.setAttribute("activities", activitList);
			
		} else if (session.getAttribute("location").equals("Cave")) {
			
			Integer goldAmount = random.nextInt((10-5)+1)+5;
			newGold += goldAmount;
			session.setAttribute("gold", newGold);
			activitList.add("%d gold earned from %s".formatted(goldAmount, session.getAttribute("location")));
			session.setAttribute("activities", activitList);
			
		} else if (session.getAttribute("location").equals("House")) {
			
			Integer goldAmount = random.nextInt((5-2)+1)+2;
			newGold += goldAmount;
			session.setAttribute("gold", newGold);
			activitList.add("%d gold earned from %s".formatted(goldAmount, session.getAttribute("location")));
			session.setAttribute("activities", activitList);
			
		}else if (session.getAttribute("location").equals("Casino")) {
			
			Integer goldAmount = random.nextInt((50-(-50))+1)+(-50);
			newGold += goldAmount;	
			session.setAttribute("gold", newGold);
			if (goldAmount > 0) {
				activitList.add("%d gold won at the %s".formatted(goldAmount, session.getAttribute("location")));
			} else {
				activitList.add("%d gold lost at the %s".formatted(goldAmount, session.getAttribute("location")));
			}
			session.setAttribute("activities", activitList);
			
		}		
		
		return "redirect:/";
	}
	
	@RequestMapping("/reset")
	public String reset(HttpSession session) {
		session.setAttribute("gold", 0);
		session.setAttribute("activities", null);
		return "redirect:/";
	}
	
	
	
}
