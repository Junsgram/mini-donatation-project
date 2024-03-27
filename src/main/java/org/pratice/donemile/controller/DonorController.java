package org.pratice.donemile.controller;

import org.pratice.donemile.dto.DonorRegistrationDTO;
import org.pratice.donemile.service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DonorController {

    private final DonorService donorService;

    @Autowired
    public DonorController(DonorService donorService) {
        this.donorService = donorService;
    }

    @GetMapping("/signup")
    public String showSignUpForm(Model model){
        //폼 바인딩할 DTO 객체를 모델에 추가
        model.addAttribute("donorForm", new DonorRegistrationDTO());
        return "signup"; //signup.html페이지를 반환
    }

    @PostMapping("/signup")
    public String registerDonor(DonorRegistrationDTO donorForm, BindingResult result, RedirectAttributes attributes) {
        donorService.registerNewDonor(donorForm);
        if (result.hasErrors()) {
            return "signup";
        }
        attributes.addFlashAttribute("successMessage", "회원 가입에 성공했습니다.");

        return "redirect:/login"; // 가입 성공 후 로그인 페이지로 리다이렉트

    }


}
