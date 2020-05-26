package com.bohniman.travelpermit.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bohniman.travelpermit.captcha.CaptchaGenerator;
import com.bohniman.travelpermit.services.MasterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import nl.captcha.Captcha;

// import nl.captcha.Captcha;

/**
 * IndexController
 */
@Controller
public class IndexController {

	@Autowired
	MasterService masterService;

	@Autowired
	CaptchaGenerator captchaGenerator;

	@GetMapping(value = { "/", "/login" })
	public ModelAndView index(ServletRequest request, ModelAndView mv) {
		mv = new ModelAndView("unlogged/index");
		return mv;
	}

	@GetMapping(value = { "/access-denied" })
	public ModelAndView accessDenied(ModelAndView mv) {
		mv = new ModelAndView("error/403");
		return mv;
	}

	@GetMapping(value = { "/no-role" })
	public ModelAndView noRole(ModelAndView mv) {
		mv = new ModelAndView("unlogged/index");
		mv.addObject("msgErr", "Unauthorised ! Please try again.");
		return mv;
	}

	@GetMapping(value = { "/error" })
	public ModelAndView error(ModelAndView mv) {
		mv = new ModelAndView("error");
		return mv;
	}

	@GetMapping(path = "/genCaptcha.png", produces = "image/png") // Map ONLY GET Requests
	@ResponseBody
	public byte[] genCaptcha(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession) {

		Captcha captcha = captchaGenerator.createCaptcha(170, 50);
		httpSession.setAttribute("captcha", captcha);

		response.setContentType("image/png");

		ByteArrayOutputStream bao = new ByteArrayOutputStream();

		try {

			ImageIO.write(captcha.getImage(), "png", bao);
			return bao.toByteArray();

		} catch (IOException e) {

		}

		return null;
	}

}