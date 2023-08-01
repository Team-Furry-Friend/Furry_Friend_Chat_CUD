package com.v3.furry_friend_chat_cud.common.dto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.Data;

@Data
public class PageResponseDTO<DTO> {
    private List<DTO> dtoList;
    private int totalPage;
    private int page;
    private int size;
    private int start, end;
    private boolean prev, next;
    private List<Integer> pageList;

    public PageResponseDTO(Page<DTO> result){
        dtoList = result.getContent();
        totalPage = result.getTotalPages();
        makePageList(result.getPageable());
    }

    private void makePageList(Pageable pageable){
        this.page = pageable.getPageNumber() + 1;
        this.size = pageable.getPageSize();

        int tempEnt = (int)(Math.ceil(page / 10.0)) * 10;
        start = tempEnt-9;
        prev = start > 1;
        end = Math.min(totalPage, tempEnt);
        next = totalPage > tempEnt;
        pageList = IntStream.rangeClosed(start, end)
                .boxed().collect(Collectors.toList());
    }
}