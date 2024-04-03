package org.pratice.donemile.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.pratice.donemile.domain.Donor;
import org.pratice.donemile.domain.Episode;
import org.pratice.donemile.dto.EpisodeDTO;
import org.pratice.donemile.repository.DonorRepository;
import org.pratice.donemile.service.EpisodeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/episode")
@RequiredArgsConstructor
public class EpisodeController {

    private final DonorRepository donorRepository;
    private final EpisodeService episodeService;

    @GetMapping("/register")
    public String registerPage(Model model, Principal principal) {
        if(principal == null ) {
            return "redirect:/login";
        }
        // 아이디 값을 subString 이메일을 제외한 아이디 값만 받아올 예정
        String userId = principal.getName();
        int atIndex = userId.indexOf('@');
        String principalId = userId.substring(0,atIndex);

        model.addAttribute("principal",principalId);
        return "/episode/register";
    }
    @PostMapping("/register")
    public String insertEpisode(EpisodeDTO episodeDTO, Model model,
                                @RequestParam("itemImgFile") List<MultipartFile> itemImgFile,
                                Principal principal) throws Exception {
        Donor donor = donorRepository.findByEmail(principal.getName()).get();
        episodeDTO.setDonor(donor);
        episodeService.saveEpisode(episodeDTO,itemImgFile);
        // TODO: 상세보기 페이지로 이동할 수 있도록 변경하자
        return "/episode/list";
    }

    @GetMapping("/list")
    public String episodeList() {
        // TODO: 리스트를 뿌려주기 위해서는 episode의 데이터 값이 필요하다. page로 구현해보자
        return "/episode/list";
    }
}
