package com.example.springlab.controller;


import com.example.springlab.domain.entity.Friend;
import com.example.springlab.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/friends")
public class FriendController {

    @Autowired
    FriendRepository friendRepository;

    // 친구 데이터의 전체 리스트를 JSON 형식으로 리턴하는 메서드를 구현한다. - GET 방식
//    전체 리스트 요청 : 응답 코드 200, JSON 객체의 리스트 형식의 내용
//    내용이 없다면 응답 코드 204, 내용 없음
    @GetMapping
    public ResponseEntity<List<Friend>> getFriends() {
        List<Friend> friends = friendRepository.findAll();
        if (friends.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else  {
            return ResponseEntity.status(HttpStatus.OK).body(friends);
        }

    }

//   ID 로 친구 데이터 1개 요청 : 존재하면 200과 함께 친구 정보 존재하지 않으면
//    존재하지 않는 다는 메시지와 응답 헤더에 다음을 추가 (HttpHeaders 객체의 add() 메서드 사용)
//    BAD_ID : id값 그리고 응답상태코드는 400(Bad Request)
    @GetMapping("{id}")
    public ResponseEntity<Friend> getFriendById(@PathVariable Integer id) {
        Friend friend = friendRepository.findById(id).orElse(null);
        if (friend == null) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("BAD_ID", id.toString());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .headers(headers)
                    .build();
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(friend);

    }
// - 친구 이름을 입력하면 해당 친구 데이터를 JSON 형식으로 리턴하는 메서드를 구현한다.
//  입력 : 성공하면 응답코드 201(Created), 실패하면 500과 실패했다는 메시지
    @GetMapping(params = "username")
    public ResponseEntity<List<Friend>> getFriendByFname(@RequestParam String username) {
        List<Friend> friends = friendRepository.findAllByFname(username);        // 친구를 찾지 못했을 때: 404 Not Found
        if (friends.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // 조회를 성공했을 때: 200 OK
        return ResponseEntity.status(HttpStatus.OK).body(friends);
    }
// 클라이언트에서 JSON 형식으로 전달된 데이터를 Friend 테이블에 저장하는 메서드
//  이 때는 친구의 이름과 나이 데이터만 전달한다. – POST 방식
    @PostMapping
    public ResponseEntity<Friend> create(@RequestBody Friend friend) {
        Friend saved = friendRepository.save(friend);
        return  ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }

// 클라이언트에서 JSON 형식으로 전달된 데이터를 Friend 테이블에 수정하는 메서드
// 이 때는 친구의 이름과 나이 데이터 뿐만 아니라 ID 도 전달해야 한다. – PUT 방식
    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Friend friend) {
        try {
            Friend saved = friendRepository.findById(id).orElse(null);
            if (saved == null) {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .build();
            }

            saved.setFname(friend.getFname());
            saved.setFage(friend.getFage());
            friendRepository.save(saved);

            return ResponseEntity.status(HttpStatus.RESET_CONTENT).build();

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

// - 클라이언트에서 전달된 ID 를 가지고 데이터를 삭제하는 메서드를 구현한다. – DELETE 방식
// - 수정과 삭제 : 성공하면 205(Reset Content), 실패하면 500과 실패했다는 메시지
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            Friend friend = friendRepository.findById(id).orElse(null);
            if (friend == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .build();
            }

            friendRepository.delete(friend);

            return ResponseEntity.status(HttpStatus.OK).body(id + "의 친구를 삭제했어요!");

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

}



