package com.sparta.securityprac1.service;

import com.sparta.securityprac1.controller.dto.PostRequestDto;
import com.sparta.securityprac1.entity.Post;
import com.sparta.securityprac1.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service // 스프링에게 이 클래스는 서비스임을 명시
public class PostService {

    // final: 서비스에게 꼭 필요한 녀석임을 명시
    private final PostRepository postRepository;

    // 생성자를 통해, Service 클래스를 만들 때 꼭 Repository를 넣어주도록
    // 스프링에게 알려줌
    //@RequiredArgsConstructor으로 대체
    //    public BoardService(BoardRepository boardRepository) {
    //        this.boardRepository = boardRepository;
    //    }

    @Transactional // SQL 쿼리가 일어나야 함을 스프링에게 알려줌
    public Long update(Long id, PostRequestDto requestDto) {
        Post post1 = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
        post1.update(requestDto);
        return post1.getId();
    }
}