package com.sparta.securityprac1.controller;

import com.sparta.securityprac1.controller.dto.MemberResponseDto;
import com.sparta.securityprac1.controller.dto.PostRequestDto;
import com.sparta.securityprac1.entity.Post;
import com.sparta.securityprac1.repository.PostRepository;
import com.sparta.securityprac1.service.MemberService;
import com.sparta.securityprac1.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {
    private final PostRepository postRepository;
    private final PostService postService;

    //   2. 전체 게시글 목록 조회 API
    @GetMapping("/posts")
    public List<Post> getBoards() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    //  3. 게시글 작성 API
    // PostMapping을 통해서, 같은 주소라도 방식이 다름을 구분합니다.
    @PostMapping("/posts")
    public Post createBoard(@RequestBody PostRequestDto requestDto) {
        // requestDto 는, 생성 요청을 의미합니다.
        // 강의 정보를 만들기 위해서는 강의 제목과 튜터 이름이 필요하잖아요?
        // 그 정보를 가져오는 녀석입니다.

        // 저장하는 것은 Dto가 아니라 Course이니, Dto의 정보를 course에 담아야 합니다.
        // 잠시 뒤 새로운 생성자를 만듭니다.
        Post post = new Post(requestDto);

        // JPA를 이용하여 DB에 저장하고, 그 결과를 반환합니다.
        return postRepository.save(post);
    }

    //   4. 게시글 조회 API
    @GetMapping("/posts/{id}")
    public Post getBoard(@PathVariable Long id) {
        return postRepository.findById(id).get();
    }

    //    5. 게시글 비밀번호 확인 API
    @PostMapping("/posts/{id}")
    public String checkPasswordBoard(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
        if (post.getPassword().equals(requestDto.getPassword())){
            return "비밀번호 일치";
        }else{
            return "비밀번호 불일치"; ////서비스
        }
    }

    //   6. 게시글 수정 API
    @PutMapping("/posts/{id}")
    public Long updateBoard(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        postService.update(id, requestDto);
        return id;
    }

    //  7. 게시글 삭제 API
    @DeleteMapping("/posts/{id}")
    public Long deleteBoard(@PathVariable Long id) {
        postRepository.deleteById(id);
        return id;
    }
}