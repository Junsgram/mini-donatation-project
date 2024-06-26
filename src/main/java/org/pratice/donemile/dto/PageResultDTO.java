package org.pratice.donemile.dto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PageResultDTO<Dto, EN> {
    // field
    private List<Dto> dtoList;          // Dto 리스트
    private int totalPage;              // 총 페이지 번호
    private int page;                   // 현재 페이지 번호
    private int size;                   // 목록 사이즈
    private int start, end;             // 시작, 끝
    private boolean prev, next;         // 이전, 다음
    private List<Integer> pageList;     // 페이지 번호 목록 리스트

    //constructor
    public PageResultDTO(Page<EN> result, Function<EN, Dto> fn) {
        dtoList = result.stream().map(fn).collect(Collectors.toList());
        totalPage = result.getTotalPages();
        makePageList(result.getPageable());
    }

    private void makePageList(Pageable pageable) {
        this.page = pageable.getPageNumber() + 1;
        this.size = pageable.getPageSize();
        int tempEnd = (int) (Math.ceil(page / 10.0)) * 10;
        start = tempEnd - 9;
        prev = start > 1;
        end = totalPage > tempEnd ? tempEnd : totalPage;
        next = totalPage > tempEnd;
        pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
    }

    // method

}
