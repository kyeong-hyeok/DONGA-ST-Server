package com.teamA.hicardi.domain.notice.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teamA.hicardi.common.dto.PageResponseDto;
import com.teamA.hicardi.domain.notice.dto.response.NoticeGetResponseDto;
import com.teamA.hicardi.domain.notice.service.NoticeService;
import com.teamA.hicardi.error.ErrorCode;
import com.teamA.hicardi.error.exception.custom.BusinessException;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notices")
public class NoticeController {

	private final NoticeService noticeService;

	@GetMapping
	public ResponseEntity<PageResponseDto> getAllNotices(Pageable pageable){
		Page<NoticeGetResponseDto> response = noticeService.getAllNotices(pageable);
		return PageResponseDto.of(response);
	}

	@GetMapping("/category")
	public ResponseEntity<PageResponseDto> getCategoryNotices(@RequestParam String search, Pageable pageable) {
		if (StringUtils.isEmpty(search)) {
			throw new BusinessException(ErrorCode.WRONG_CATEGORY);
		}
		Page<NoticeGetResponseDto> response = noticeService.getCategoryNotices(search, pageable);
		return PageResponseDto.of(response);
	}
}
