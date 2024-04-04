package org.pratice.donemile.controller;

import org.pratice.donemile.domain.Donor;
import org.pratice.donemile.dto.DonorRegistrationDTO;
import org.pratice.donemile.service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/donor")
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
        return "/donor/signup"; //signup.html페이지를 반환
    }

    @PostMapping("/signup")
    public String registerDonor(DonorRegistrationDTO donorForm, BindingResult result, RedirectAttributes attributes) {
        donorService.registerNewDonor(donorForm);
        if (result.hasErrors()) {
            return "/donor/signup";
        }
        attributes.addFlashAttribute("successMessage", "회원 가입에 성공했습니다.");

        return "redirect:/login"; // 가입 성공 후 로그인 페이지로 리다이렉트

    }

    @GetMapping("/homepage")
    public String showHomePage(){
        return "homepage";
    }

    // 현재 로그인한 사용자의 정보를 수정하는 폼 페이지로 이동
    @GetMapping("/memberInfo")
    public String currentDonorInfo(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        // email을 사용해 현재 로그인한 사용자의 정보 조회
        Donor currentDonor = donorService.findDonorByEmail(email);
        model.addAttribute("donor", currentDonor);
        return "/donor/memberInfo";   // memeberInfo.html로 이동

    }

    @PostMapping("/update")
    public String updateDonor(@ModelAttribute Donor donor, BindingResult result, RedirectAttributes attributes) {
        if(result.hasErrors()){
            return "/donor/memberInfo";
        }
        donorService.updateDonor(donor);
        return "redirect:/donor/memberInfo";
    }

}
