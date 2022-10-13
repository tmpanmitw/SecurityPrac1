package com.sparta.securityprac1;

import com.sparta.securityprac1.controller.dto.PostRequestDto;
import com.sparta.securityprac1.entity.Post;
import com.sparta.securityprac1.repository.PostRepository;
import com.sparta.securityprac1.service.PostService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SecurityPrac1Application {

    public static void main(String[] args) {
        SpringApplication.run(SecurityPrac1Application.class, args);
    }
    @Bean
    public CommandLineRunner demo(PostRepository postRepository, PostService postService) {
        return (args) -> {
            // 데이터 저장하기
            postRepository.save(new Post("타이틀1", "작성자1", "내용1", "비밀번호1"));
            postRepository.save(new Post("타이틀2", "작성자2", "내용2", "비밀번호2"));
            postRepository.save(new Post("타이틀3", "작성자3", "내용3", "비밀번호3"));

            // 데이터 전부 조회하기
            List<Post> postList = postRepository.findAllByOrderByCreatedAtDesc();
            for (int i=0; i<postList.size(); i++) {
                Post post = postList.get(i);
                System.out.println(post.getId());
                System.out.println(post.getTitle());
                System.out.println(post.getCreater());
                System.out.println(post.getContents());
                System.out.println(post.getPassword());
            }

            PostRequestDto requestDto = new PostRequestDto("타이틀수정", "작성자수정","내용수정","비밀번호수정");
            postService.update(1L, requestDto);
            postList = postRepository.findAll();
            for (int i=0; i<postList.size(); i++) {
                Post post = postList.get(i);
                System.out.println(post.getId());
                System.out.println(post.getTitle());
                System.out.println(post.getCreater());
                System.out.println(post.getContents());
                System.out.println(post.getPassword());
            }

//            // 데이터 하나 조회하기
//            Board board = boardRepository.findById(1L).orElseThrow(
//                    () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
//            );

//            boardRepository.deleteAll();

        };
    }

}


